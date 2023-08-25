package DataStructure.Tree;

import java.sql.SQLOutput;

public class MorrisTest {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        buildSimpleTree(root,1);
        buildSimpleTree(root.left,3);
        buildSimpleTree(root.right,5);
        morrisInOrder(root);



    }
    public static void buildSimpleTree(TreeNode node,int val){
        node.left = new TreeNode(val);
        node.right = new TreeNode(++val);
    }

    /**
     * 莫里斯遍历
     * 前序中序遍历的顺序都是一样的不过输出时机不一样
     */
    public static void morrisInOrder(TreeNode root) {   //
        TreeNode cur = root;
        while (cur != null) {
            // 判断是否有左子树
            if (cur.left == null) {
                System.out.println(cur.val);
                cur = cur.right;
            } else {
                TreeNode mostRight = getRightNode(cur);
                // 拐点
                if (mostRight.right == cur) {
                    mostRight.right = null;
                    System.out.println(cur.val + " ");
                    cur = cur.right;
                }else {
                    mostRight.right = cur;
                    cur = cur.left;
                }
            }
        }
    }

    /**
     * 获取左子树最右节点
     *
     * @param origin 父节点
     * @return 最右节点
     */
    public static TreeNode getRightNode(TreeNode origin)
    {
        // 左子树根节点
        TreeNode root = origin.left;
        while (root.right != null && root.right != origin)
        {
            root = root.right;
        }

        return root;
    }

}
