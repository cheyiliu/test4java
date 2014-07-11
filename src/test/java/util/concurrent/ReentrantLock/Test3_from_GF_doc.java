
package test.java.util.concurrent.ReentrantLock;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Test3_from_GF_doc {
    public static void main(String[] args) {
        final Test3_from_GF_doc boundedBuffer = new Test3_from_GF_doc();
        final Random random = new Random();

        for (int i = 0; i < 10; i++)
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        // for (int i = 0; i < 10; i++)
                        {
                            Thread.sleep(random.nextInt(10));
                            long x = System.currentTimeMillis();
                            boundedBuffer.put(new Long(x));
                        }
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();

        for (int i = 0; i < 10; i++)
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        // for (int i = 0; i < 10; i++)
                        {
                            Thread.sleep(random.nextInt(10));
                            boundedBuffer.take();
                        }
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();

    }

    final Lock lock = new ReentrantLock();// 锁对象
    final Condition writeCondition = lock.newCondition();// 写线程条件
    final Condition readCondition = lock.newCondition();// 读线程条件

    final Object[] items = new Object[3];// 缓存队列
    int putptr/* 写索引 */, takeptr/* 读索引 */, count/* 队列中存在的数据个数 */;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        System.out.println("to put " + x);
        try {
            while (count == items.length) {
                // 如果队列满了
                System.out.println("queue is full ------------->");
                /*
                 * README: The key property [that waiting for a condition
                 * provides] is that it atomically releases the associated lock
                 * and suspends the current thread, just like Object.wait.
                 */
                writeCondition.await();// 阻塞写线程
            }
            items[putptr] = x;// 赋值
            if (++putptr == items.length)
                putptr = 0;// 如果写索引写到队列的最后一个位置了，那么置为0
            ++count;// 个数++
            readCondition.signal();// 唤醒读线程
        } finally {
            System.out.println("to put " + x + " sucess");
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        System.out.println("to take ");
        try {
            while (count == 0) {
                // 如果队列为空
                System.out.println("queue is empty -------------<<");
                readCondition.await();// 阻塞读线程
            }
            Object x = items[takeptr];// 取值
            if (++takeptr == items.length)
                takeptr = 0;// 如果读索引读到队列的最后一个位置了，那么置为0
            --count;// 个数--
            writeCondition.signal();// 唤醒写线程
            System.out.println("to take " + x + " sucess");
            return x;
        } finally {
            lock.unlock();
        }
    }
}
