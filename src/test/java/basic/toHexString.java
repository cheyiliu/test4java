
package test.java.basic;

import org.junit.Test;

public class toHexString {
    private int i = 2010;

    @Test
    public void testInteger() {
        System.err.println();
        System.err.println("原始数据：" + i);
        // 二进制转换
        System.err.println("==========整型——二进制转换==========");
        System.err.println("二进制：" + Integer.toBinaryString(i));
        System.err.println("十进制："
                + Integer.parseInt(Integer.toBinaryString(i), 2));

        // 八进制转换
        System.err.println("==========整型——八进制转换==========");
        System.err.println("八进制：" + Integer.toOctalString(i));
        System.err.println("十进制："
                + Integer.parseInt(Integer.toOctalString(i), 8));

        // 十六进制转换
        System.err.println("==========整型——十六进进制转换==========");
        System.err.println("十六进制：" + Integer.toHexString(i));
        System.err.println("十进制："
                + Integer.parseInt(Integer.toHexString(i), 16));
    }

    @Test
    public void testLong() {

        System.err.println();
        System.err.println("原始数据：" + i);
        // 二进制转换
        System.err.println("==========长整型——二进制转换==========");
        System.err.println("二进制：" + Long.toBinaryString(i));
        System.err.println("十进制：" + Long.parseLong(Long.toBinaryString(i), 2));

        // 八进制转换
        System.err.println("==========长整型——八进制转换==========");
        System.err.println("八进制：" + Long.toOctalString(i));
        System.err.println("十进制：" + Long.parseLong(Long.toOctalString(i), 8));

        // 十六进制转换
        System.err.println("==========长整型——十六进进制转换==========");
        System.err.println("十六进制：" + Long.toHexString(i));
        System.err.println("十进制：" + Long.parseLong(Long.toHexString(i), 16));

    }

}
