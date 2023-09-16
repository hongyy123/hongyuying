package test;

import java.text.DecimalFormat;

//数字格式化测试（控制输出的数值为一位整数和两位小数）
public class DecimalFormatTest {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00");
        double d1 = 0.342453;
        System.out.println(df.format(d1));
    }
}
