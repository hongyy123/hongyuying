import java.io.IOException;
import java.util.Scanner;

public class Main {//后期可以考虑拆分合法性检验和正确率判断

    public static void main(String[] args) throws IOException {
       /*
       从命令行接受参数，题目的数量，和题目中数值（自然数、真分数和真分数分母）的范围，生成题目
       */
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要生成的题目个数：");
        int number = sc.nextInt();//题目数量
        System.out.println("请输入题目中数值（自然数、真分数和真分数分母）的范围：");
        int limit = sc.nextInt();//
        Expression exp = new Expression();
        exp.legalExp(number, limit);

        //答题
        System.out.println("是否进行答题(输入'1'表示答题，'0'表示不答题)");
        if (sc.nextInt() == 1) {
            new Grade().grade();
        } else {
            System.exit(1);
        }
    }
}
