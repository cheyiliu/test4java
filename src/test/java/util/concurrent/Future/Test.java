
package test.java.util.concurrent.Future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

interface ArchiveSearcher {
    String search(String target);
}

public class Test {
    ExecutorService executor = Executors.newFixedThreadPool(2);
    ArchiveSearcher searcher = new ArchiveSearcher() {

        @Override
        public String search(String target) {
            System.out.println("search, in thread " + Thread.currentThread().getId());
            return "search result";
        }
    };

    void showSearch(final String target)
            throws InterruptedException {
        System.out.println("showSearch, in thread " + Thread.currentThread().getId());
        Future<String> future = executor.submit(new Callable<String>() {
            public String call() {
                System.out.println("call, in thread " + Thread.currentThread().getId());
                try {
                    Thread.sleep(10000);// makes the future.get() to wait
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return searcher.search(target);
            }
        });
        displayOtherThings(); // do other things while searching
        try {
            displayText(future.get()); // use future
        } catch (ExecutionException ex) {
            cleanup();
            return;
        }
    }

    private void cleanup() {
        System.out.println("cleanup, in thread " + Thread.currentThread().getId());
    }

    private void displayText(String string) {
        System.out.println("displayText, in thread " + Thread.currentThread().getId());
        System.out.println("displayText, " + string);
    }

    private void displayOtherThings() {
        System.out.println("displayOtherThings, in thread " + Thread.currentThread().getId());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            new Test().showSearch("search target");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
