package main;

import java.text.DecimalFormat; //数字格式化
import java.util.Scanner; //扫描控制台输入

public class ReadMain {
    public static void main(String[] args) {
        //扫描控制台输入
        Scanner scan1 = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        //获取论文原文的文件的绝对地址
        System.out.print("请输入论文原文的文件的绝对路径：");
        String str1 = scan1.nextLine(); //获取输入的内容
        //获取抄袭版论文的文件的绝对路径
        System.out.print("请输入抄袭版论文的文件的绝对路径：");
        String str2 =scan2.nextLine(); //获取输入的内容
        if(str1.length()==0 || str2.length()==0) {
            System.out.println("输入文件为空！");
        }

        //控制输出的数值为一位整数和两位小数
        DecimalFormat df = new DecimalFormat("0.00");
        SimilarityMain similar = new SimilarityMain(str1, str2);
        System.out.println("文章相似度为："+df.format(similar.sim()));
    }
}
