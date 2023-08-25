import java.util.Scanner;

/**
 * Scanner类测试
 * next()、nextLine()
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter words separated by spaces: ");
        if (scanner.hasNext()){
            String words = scanner.next(); // 读取一个单词（以空格分隔），不读取换行符
            System.out.println("Word entered: " + words);
        }

        // 读取上一个next()操作余留的换行符
        scanner.nextLine();

        System.out.print("Enter a sentence: ");
        if (scanner.hasNextLine()){
            String sentence = scanner.nextLine(); // 读取整行（包括换行符）
            System.out.println("Sentence entered: " + sentence);
        }



        scanner.close();
    }
}