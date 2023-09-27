import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExpressionTest {
    @BeforeEach
    void setUp() throws Exception {
        System.out.println("启动");
    }

    @Test
    void testExp() throws IOException {
        //从控制台输入数据
        System.out.println("-----generateExpTest-----");
        System.out.println("输入题目数量：");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        System.out.println("你输入的数为：" + num + "\n");

        System.out.println("输入生成的操作数范围：");
        int num1 = scanner.nextInt();
        System.out.println("你输入的操作数范围为：" + num1 + "\n");

        Expression exp = new Expression();//表达式对象
        int j = 0;
        String str1 = "";
        System.out.print("-----生成的orign表达式如下-----" + "\n");
        do {
            str1 = exp.generateExp(num1) + " = ";//获得原始表达式
            System.out.print("N0." + j + "\t" + str1 + "\n");//输出
            j++;
        } while (j < num);
        System.out.println("表达式生成结束" + "\n");


        System.out.println("-----legalExp-----");
        System.out.println("输入题目数量：");
        int num2 = scanner.nextInt();

        System.out.println("你输入的数为：" + num2);

        System.out.println("输入生成的操作数范围：");
        int num3 = scanner.nextInt();
        scanner.close();
        System.out.println("你输入的操作数范围为：" + num3 + "\n");

        System.out.print("-----生成的legal表达式如下-----" + "\n");
        exp.legalExp(num2, num3);
        System.out.println("表达式生成结束" + "\n");
    }

}
