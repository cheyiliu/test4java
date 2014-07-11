
package test.System.nanoTime;

public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            System.out.println("ns:" + System.nanoTime());// 纳秒
            System.out.println("ms:" + System.currentTimeMillis());
        }

        long start = System.nanoTime();
        for (int i = 0; i < 1000 * 1000; ++i) {
            long v = System.nanoTime();
        }
        System.out.println("time used: " + (System.nanoTime() - start));

        long start2 = System.nanoTime();
        for (int i = 0; i < 1000 * 1000; ++i) {
            long v = System.nanoTime();
        }
        System.out.println("time used: " + (System.nanoTime() - start2));
    }
}
