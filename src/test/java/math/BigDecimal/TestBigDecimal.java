
package test.java.math.BigDecimal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestBigDecimal {

    /*
     * http://lavasoft.blog.51cto.com/62575/228705/ BigInteger不可变的任意精度的整数;
     * BigDecimal不可变的、任意精度的有符号十进制数
     */
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        // fail("Not yet implemented");

        StringBuilder para = new StringBuilder();
        StringBuilder rightReusult = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            if (i == 5) {
                para = para.append(".3");
                rightReusult = rightReusult.append(".6");
            } else {
                para = para.append("3");
                rightReusult = rightReusult.append("6");
            }
        }

        BigDecimal bigDec = new BigDecimal(para.toString());
        BigDecimal two = new BigDecimal("2");

        System.out.println("para:                 " + para);
        System.out.println("bigInt.toString():    " + bigDec.toString());
        System.out.println("bigInt.multiply(two): " + bigDec.multiply(two));
        System.out.println("bigInt.add(10):       " + bigDec.add(new BigDecimal(10)));
        Assert.assertEquals("Wrong: ", rightReusult.toString(), bigDec
                .multiply(two).toString());
        //
        System.out.println("------------构造BigDecimal-------------");
        // 从char[]数组来创建BigDecimal
        BigDecimal bd1 = new BigDecimal("123456789.123456888".toCharArray(), 4, 12);
        System.out.println("bd1=" + bd1);
        // 从char[]数组来创建BigDecimal
        BigDecimal bd2 = new BigDecimal("123456789.123456111133333213".toCharArray(), 4, 18,
                MathContext.DECIMAL128);
        System.out.println("bd2=" + bd2);
        // 从字符串创建BigDecimal
        BigDecimal bd3 = new BigDecimal("123456789.123456111133333213");
        System.out.println("bd3=" + bd3);
        // 从字符串创建BigDecimal，3是有效数字个数
        BigDecimal bd4 = new BigDecimal("88.456", new MathContext(3, RoundingMode.UP));
        System.out.println("bd4=" + bd4);
        System.out.println("------------使用BigDecimal-------------");
        System.out.println("bd1+bd2=" + bd1.add(bd2));
        System.out.println("bd1+bd2=" + bd1.add(bd2, new MathContext(24, RoundingMode.UP)));
        System.out.println("bd1-bd2=" + bd1.subtract(bd2).toPlainString());
        System.out.println("bd1-bd2="
                + bd1.subtract(bd2, new MathContext(24, RoundingMode.UP)).toPlainString());
        System.out.println("bd1*bd2=" + bd1.multiply(bd2));
        System.out.println("bd1*bd2=" + bd1.multiply(bd2, new MathContext(24, RoundingMode.UP)));
        System.out.println("bd1/bd4=" + bd1.divideToIntegralValue(bd4));
        System.out.println("bd1/bd4="
                + bd1.divideToIntegralValue(bd4, new MathContext(24, RoundingMode.UP)));
        System.out.println("bd1末位数据精度=" + bd1.ulp());
        System.out.println("bd2末位数据精度=" + bd2.ulp());
        System.out.println("bd2末位数据精度=" + bd2.ulp().toPlainString());
        System.out.println("bd1符号：" + bd1.signum());
        System.out.println("bd4的标度：" + bd4.scale());
        //
        System.out.println("-------------------构造BigInteger---------------------");
        // 通过byte数组来创建BigInteger
        BigInteger bi1 = new BigInteger(new byte[] {
                1, 1
        });
        System.out.println("bi1=" + bi1.toString());
        // 创建带符号的BigInteger
        BigInteger bi2 = new BigInteger(-1, new byte[] {
                1, 1
        });
        System.out.println("bi2=" + bi2.toString());
        // 创建带符号的BigInteger随机数
        BigInteger bi3 = new BigInteger(128, 20, new Random());
        System.out.println("bi3=" + bi3.toString());
        // 通过10进制字符串创建带符号的BigInteger
        BigInteger bi4 = new BigInteger("12342342342342123423423412341");
        System.out.println("bi4=" + bi4.toString());
        // 通过10进制字符串创建带符号的BigInteger
        BigInteger bi5 = new BigInteger("88888888888888888888888888888", Character.digit('a', 33));
        System.out.println("bi5=" + bi5.toString());
        System.out.println("BigInteger的常量：");
        System.out.println("BigInteger.ZERO=" + BigInteger.ZERO);
        System.out.println("BigInteger.ONE=" + BigInteger.ONE);
        System.out.println("BigInteger.TEN=" + BigInteger.TEN);

        System.out.println("-------------------使用BigInteger---------------------");
        System.out.println("bi1的相反数=" + bi1.negate());
        System.out.println("bi1的相反数=" + bi1.negate());
        System.out.println("bi1+bi2=" + bi1.add(bi2));
        System.out.println("bi1-bi2=" + bi1.subtract(bi2));
        System.out.println("bi1*bi2=" + bi1.multiply(bi2));
        System.out.println("bi1/bi2=" + bi1.divide(bi2));
        System.out.println("bi1的10次方=" + bi1.pow(10));
        System.out.println("bi1的10次方=" + bi1.pow(1));
        BigInteger[] bx = bi4.divideAndRemainder(bi1);
        System.out.println(">>>:bx[0]=" + bx[0] + ",bx[1]=" + bx[1]);
        System.out.println("bi2的绝对值=" + bi2.abs());
    }

}
