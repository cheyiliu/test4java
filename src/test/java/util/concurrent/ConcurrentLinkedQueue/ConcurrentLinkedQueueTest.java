
package test.java.util.concurrent.ConcurrentLinkedQueue;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentLinkedQueueTest {
    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
    private static int count = 100; // 线程个数
    private static Random random = new Random();
    // CountDownLatch，一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
    private static CountDownLatch latch = new CountDownLatch(count);

    public static void main(String[] args) throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        ExecutorService es = Executors.newFixedThreadPool(5);
        ConcurrentLinkedQueueTest.offer();
        for (int i = 0; i < count; i++) {
            es.submit(new Poll());
        }
        latch.await(); // 使得主线程(main)阻塞直到latch.countDown()为零才继续执行
        System.out.println("cost time " + (System.currentTimeMillis() - timeStart) + "ms");
        es.shutdown();
    }

    /**
     * 生产
     */
    public static void offer() {
        for (int i = 0; i < 100000; i++) {
            queue.offer(i);
        }
    }

    /**
     * 消费
     * 
     * @author 林计钦
     * @version 1.0 2013-7-25 下午05:32:56
     */
    static class Poll implements Runnable {
        public void run() {
            /*
             * ConcurrentLinkedQueue size(). Beware that, unlike in most
             * collections, this method is NOT a constant-time operation.
             * Because of the asynchronous nature of these queues, determining
             * the current number of elements requires an O(n) traversal.
             * Additionally, if elements are added or removed during execution
             * of this method, the returned result may be inaccurate. Thus, this
             * method is typically not very useful in concurrent applications.
             */
            // while (queue.size()>0) {
            while (!queue.isEmpty()) {
                try {
                    Thread.sleep(random.nextInt(2));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getId() + ", " + queue.poll());
            }
            latch.countDown();
        }
    }
}
