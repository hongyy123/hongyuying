import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class FractionTest {

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("启动测试");
	}

	@Test
	void testAdd() {
		Fraction f1= new Fraction(1,3);//分数形式1/3
		Fraction f2= new Fraction(8,4);//分数形式8/4
		Fraction outf=f1.add(f2);//两者相加，分数形式

		String exceptedOut="28/12";//预计结果28/12，即7/3
		String Out=outf.getNumerator()+"/"+outf.getDenominator();
		assertEquals(exceptedOut,Out);
	}

	@Test
	void testSub() {
		Fraction f1= new Fraction(8,7);//分数形式8/7
		Fraction f2= new Fraction(4,6);//分数形式4/6
		Fraction outf=f1.sub(f2);//两者相减，分数形式

		String exceptedOut="20/42";//预计结果20/42
		String Out=outf.getNumerator()+"/"+outf.getDenominator();
		assertEquals(exceptedOut,Out);
	}

	@Test
	void testMuti() {
		Fraction f1= new Fraction(7,8);//分数形式7/8
		Fraction f2= new Fraction(4,2);//分数形式4/2
		Fraction outf=f1.muti(f2);//两者相乘，分数形式

		String exceptedOut="28/16";//预计结果28/16
		String Out=outf.getNumerator()+"/"+outf.getDenominator();
		assertEquals(exceptedOut,Out);
	}

	@Test
	void testDiv() {
		Fraction f1= new Fraction(7,8);//分数形式7,8
		Fraction f2= new Fraction(4,2);//分数形式4/2
		Fraction outf=f1.div(f2);//两者相除，分数形式

		String exceptedOut="14/32";//预计结果14/32
		String Out=outf.getNumerator()+"/"+outf.getDenominator();
		assertEquals(exceptedOut,Out);
	}

	@Test
	void testAppointment() {
		Fraction f= new Fraction(6,4);//分数形式6/4
		f.Appointment();//简单约分
		String exceptedAppointed="3/2";//预计结果3/2
		String Out=f.getNumerator()+"/"+f.getDenominator();
		assertEquals(exceptedAppointed,Out);
	}

	@Test
	void testTransferFraction() {
		Fraction f= new Fraction(6,4);//分数形式6/4
		String exceptedTransfer="1'1/2";//预计结果1'1/2
		String Out=f.transferFraction(f);
		assertEquals(exceptedTransfer,Out);
	}

}
