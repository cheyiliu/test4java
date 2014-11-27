package test.Volatile;

import java.util.concurrent.CountDownLatch;

/**
 * from https://www.ibm.com/developerworks/java/library/j-jtp06197/
 * 
 * 
 * Volatile variables
 * 
 * Volatile variables share the visibility features of synchronized, but none of
 * the atomicity features. This means that threads will automatically see the
 * most up-to-date value for volatile variables. They can be used to provide
 * thread safety, but only in a very restricted set of cases: those that do not
 * impose constraints between multiple variables or between a variable's current
 * value and its future values. So volatile alone is not strong enough to
 * implement a counter, a mutex, or any class that has invariants that relate
 * multiple variables (such as "start <=end").
 * 
 * You might prefer to use volatile variables instead of locks for one of two
 * principal reasons: simplicity or scalability. Some idioms are easier to code
 * and read when they use volatile variables instead of locks. In addition,
 * volatile variables (unlike locks) cannot cause a thread to block, so they are
 * less likely to cause scalability problems. In situations where reads greatly
 * outnumber writes, volatile variables may also provide a performance advantage
 * over locking. Conditions for correct use of volatile
 * 
 * You can use volatile variables instead of locks only under a restricted set
 * of circumstances. Both of the following criteria must be met for volatile
 * variables to provide the desired thread-safety:
 * 
 * Writes to the variable do not depend on its current value. The variable does
 * not participate in invariants with other variables.
 * 
 * Basically, these conditions state that the set of valid values that can be
 * written to a volatile variable is independent of any other program state,
 * including the variable's current state.
 * 
 *  
 */

/**
 * Listing 6. Combining volatile and synchronized to form a
 * "cheap read-write lock"
 * 
 * @ThreadSafe public class CheesyCounter { // Employs the cheap read-write lock
 *             trick // All mutative operations MUST be done with the 'this'
 *             lock held
 * @GuardedBy("this") private volatile int value;
 * 
 *                    public int getValue() { return value; }
 * 
 *                    public synchronized int increment() { return value++; } }
 * 
 */

/**
 * https://github.com/cheyiliu/All-in-One/wiki/volatile
 * 
 * @author housy
 * 
 */
public class Test {

	public static volatile int count = 0;

	public static synchronized void inc() {

		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
		}

		count++;
	}

	public static void main(String[] args) {

		int total = 1000;
		final CountDownLatch cdl = new CountDownLatch(total);
		for (int i = 0; i < total; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Test.inc();

					cdl.countDown();
				}
			}).start();
		}

		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("运行结果:Counter.count=" + Test.count);
	}
}
