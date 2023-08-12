import java.util.*;

/**
 * 小美拿到了一棵树，每个节点有一个权值。初始每个节点都是白色
 * 小美有若干次操作，每次操作可以选择两个相邻的节点
 * 如果它们都是白色且权值的乘积是完全平方数，小美就可以把这两个节点同时染红。
 * 最多可以染红多少个节点?
 * 输入描述：
 * 第一行输入一个正整数n，代表节点的数量。
 * 第二行输入n个正整数ai，代表每个节点的权值。
 * 接下来的n-1行，每行输入两个正整数u、v，代表节点u和节点u有一条
 */
class Node {
    int id;
    int value;
    boolean isRed;
    ArrayList<Node> neighbors;

    public Node(int id, int value) {
        this.id = id;
        this.value = value;
        this.isRed = false;
        this.neighbors = new ArrayList<>();
    }
}

class Solution {

    int n;
    Node[] nodes;

    int maxRedNodes() {
        int redNodes = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                redNodes += dfs(i, visited);
            }
        }

        return redNodes;
    }

    int dfs(int u, boolean[] visited) {
        visited[u] = true;
        int reds = 0;

        for (Node v : nodes[u].neighbors) {
            if (!visited[v.id] && !v.isRed) {
                long prod = (long)nodes[u].value * v.value;
                if (Math.sqrt(prod) * Math.sqrt(prod) == prod) {
                    nodes[u].isRed = true;
                    v.isRed = true;
                    reds += 1;
                }
                reds += dfs(v.id, visited);
            }
        }
        return reds;
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 读入树结构
        Solution sol = new Solution();
        sol.n = n;
        sol.nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            int value = sc.nextInt();
            sol.nodes[i] = new Node(i, value);
        }

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            sol.nodes[u].neighbors.add(sol.nodes[v]);
            sol.nodes[v].neighbors.add(sol.nodes[u]);
        }

        int redNodes = sol.maxRedNodes();
        System.out.println(redNodes);
    }
}