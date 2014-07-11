
package test.java.util.concurrent.Semaphore;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

import java.util.concurrent.Semaphore;

public class TestSemaphore {
    /*
     * Semaphore实现的功能就类似厕所有5个坑，假如有10个人要上厕所，那么同时只能有多少个人去上厕所呢？同时只能有5个人能够占用，当5个人中
     * 的任何一个人让开后，其中等待的另外5个人中又有一个人可以占用了。另外等待的5个人中可以是随机获得优先机会，也可以是按照先来后到的顺序获得机会，
     * 这取决于构造Semaphore对象时传入的参数选项。
     */
    public static void main(String[] args) {

        // 线程池

//        ExecutorService exec = Executors.newFixedThreadPool(3);
        ExecutorService exec = Executors.newCachedThreadPool();

        // 只能5个线程同时访问

        final Semaphore semp = new Semaphore(5, true);

        // 模拟20个客户端访问

        for (int index = 0; index < 20; index++) {

            final int NO = index;

            Runnable run = new Runnable() {

                public void run() {

                    try {

                        // 获取许可

                        semp.acquire();

                        System.out.println("Accessing: " + NO);

                        Thread.sleep((long) (Math.random() * 1000));

                        // 访问完后，释放

                        semp.release();

                        System.out.println("------semp.availablePermits()-----------" + semp.availablePermits());

                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }

                }

            };

            exec.execute(run);

        }

        // 退出线程池

        exec.shutdown();

    }

}
