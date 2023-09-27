import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;

public class Expression {
    public String generateExp(Integer limit) throws StringIndexOutOfBoundsException{
        //生成表达式，未过合法性检验，限定运算数的大小
        Random rd = new Random();
        rd.nextInt(limit);
        int e1=  rd.nextInt(limit);
        int e2=  rd.nextInt(limit);
        int e3=  rd.nextInt(limit);
        int e4=  rd.nextInt(limit);
        String str = new String();

        int []num = {e1,e2,e3,e4};//数字
        char []opt ={'+','-','*','/'};//运算符
        int []no = {1,2,3};//控制选择的运算符(0-3)//no[rd.nextInt(3)}
        for(int j = 0; j <  no[rd.nextInt(3)]; j++){//调整j的范围可以控制运算符的数目
            str += num[rd.nextInt(4)]+" ";
            str += opt[rd.nextInt(4)]+" ";//操作数与运算符需用空格隔开，以便后续区分÷和/
        }
        str += num[rd.nextInt(4)]+" ";

        int index = 0;//记录数组的下标
        int len = 0;
        String str1 = "";
        String str2 = "";
        String str3= "";
        String str4 ="";
        /*
         * 进行加入一个括号的操作,暂时未实现加入多个括号
         * */
        int []indexs = {0,4,8};//从indexs开始加左括号
        int []lens ={5,9,13};//括号的宽度，即从从indexs+len开始加右括号
        index = indexs[rd.nextInt(3)];
        len = lens[rd.nextInt(3)];
        // System.out.println("str0的长度"+str.length());
        if( index+len< str.length()-1 ||(index+len==str.length()+1 && index!= 0) ){
            //去除首尾同时加括号的情况
            str1 = str.substring(0,index)+"( ";//substring 不包括index所对应的的字符
            //  System.out.println("str前面部分"+str1);
            str2 = str.substring(index,index+len)+" )";//i>0
            // System.out.println("str中间部分"+str2);
            str3 =  str.substring(index+len);
            //   System.out.println("str后面部分"+str3);
        }
        str4 = str1+str2+str3;//随机加了一个括号的表达式
        return str4;
    }
    public void legalExp (Integer number,Integer limit) throws IOException {
        /* 生成合法未重复的表达式，number表示题目数量,limit表示运算数范围*/
        int j = 1;//控制题目生成的数量,从1开始

        String str1 = "";
        String str2 = "";//存放中间结果
        String str3 = "";//算术表达式，写入Expression.txt
        String str4 = "";//答案，写入Answer.txt

        HashMap<String, Integer> answers = new HashMap<String, Integer>();

        FileIO writer = new FileIO();//输入流对象
        Expression exp = new Expression();//表达式对象

        do {
            str1 = exp.generateExp(limit) + "= ";//获得原始表达式
            Calculate cal = new Calculate();
            Fraction f = cal.outcome(str1);//计算结果，未化简
            if (f.getNumerator() == 100000) {
                // //剔除表达不合法的算术表达式
                //   System.out.println("表达式出错");
                continue;
            }
            str2 = f.transferFraction(f);//最终结果，已经化简
            /*
            查重开始：
             存放值的时候，如果先检测下碰撞，如果有碰撞，则break 跳出do while 循环
             如果没有碰撞，则存进去
             通过比对中间结果是否相同,运算数在10以内，生成题目的上限是55778道题目，
             随着运算数范围的缩小，上限更小
             */
            if(answers.containsKey(Save.string)){
                //System.out.println("key碰撞");
                continue;
            }else{
                answers.put(Save.string, null);
                // System.out.print("NO."+j+" "+str1 + "\n");//输出合法且没有重复的式子
                System.out.printf("NO.%4d      %s%n",j,str1);//格式化输出

                str3 += j+"."+"    "+str1+"\n";
                str4 += j+"."+"    "+str1+str2+"\n";
                j++;
            }
        } while (j <= number);
        System.out.println("题目生成完毕！");
        writer.fileWrite(str3, Paths.get("arithmetic/textFile/Expression.txt"));//整个字符串
        writer.fileWrite(str4, Paths.get("arithmetic/textFile/Answer.txt"));//整个字符串
    }

    public static void main(String[] args) throws IOException {
        Expression exp = new Expression();
        exp.legalExp(10000,10);
        //String str = exp.generateExp(10);
    }

}
