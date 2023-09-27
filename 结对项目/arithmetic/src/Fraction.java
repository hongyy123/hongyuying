import java.util.Random;

/*
  构建一个分数类，用来表示分数，封装相关的方法
 */
public class Fraction {
    /*
      成员变量，可以直接使用；后期代码整理时，用成员变量代替一部分getDenominator或者getNumerator的方法
     */
    private int denominator;// 分母
    private int numerator;// 分子

    // 构建一个分数
    public Fraction(int numerator, int denominator) {
        super();
        this.denominator = denominator;
        this.numerator = numerator;
    }

    // 构建一个可化简为整数的分数
    public Fraction(int numerator) {
        this.denominator = 1;
        this.numerator = numerator;
    }

    public Fraction() {
        super();
    }

    // 用辗转相除法求最大公约数
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public int getDenominator() {//获取分母
        return denominator;
    }

    public int getNumerator() {//获取分子
        return numerator;
    }

    // 加法运算
    public Fraction add(Fraction r) {
        int a = r.getNumerator();// 获得分子
        int b = r.getDenominator();// 获得分母
        int newNumerator = numerator * b + denominator * a;
        int newDenominator = denominator * b;
        Fraction result = new Fraction(newNumerator, newDenominator);
        return result;
    }

    // 减法运算
    public Fraction sub(Fraction r) {
        int a = r.getNumerator();// 获得分子
        int b = r.getDenominator();// 获得分母
        int newNumerator = numerator * b - denominator * a;
        int newDenominator = denominator * b;
        Fraction result = new Fraction(newNumerator, newDenominator);
        return result;
    }

    // 分数的乘法运算
    public Fraction muti(Fraction r) { // 乘法运算
        int a = r.getNumerator();// 获得分子
        int b = r.getDenominator();// 获得分母
        int newNumerator = numerator * a;
        int newDenominator = denominator * b;
        Fraction result = new Fraction(newNumerator, newDenominator);
        return result;
    }

    // 分数除法运算
    public Fraction div(Fraction r) {
        int a = r.getNumerator();// 获得分子
        int b = r.getDenominator();// 获得分母
        int newNumerator = numerator * b;
        int newDenominator = denominator * a;
        Fraction result = new Fraction(newNumerator, newDenominator);
        return result;
    }

    // 对分数进行约分
    public void Appointment() {
        if (numerator == 0 || denominator == 1) return;
        // 如果分子是0或分母是1就不用约分了
        long gcd = gcd(numerator, denominator);
        this.numerator /= gcd;
        this.denominator /= gcd;
    }

    public String transferFraction(Fraction fraction) {//对分数进行约分化简
        int a = fraction.numerator;
        int b = fraction.denominator;
        int c = a / gcd(a, b);
        int d = b / gcd(a, b);
        int e = c / d;
        int f = c % d;
        String str = "";
        if (f == 0) {
            str += e;
        } else if (e != 0) {
            str = e + "'" + f + "/" + d;
        } else {
            str += f + "/" + d;
        }
        return str;
    }
}
