
package test.breaklabel;

public class Main {

    // from https://github.com/cheyiliu/All-in-One/wiki/java-break-label
    /**
     * @param args
     */
    public static void main(String[] args) {
        A: {// a
            System.out.println("a begin");
            B: if (isOK()) {// b
                System.out.println("-b begin");
                C: if (isOK()) {// c
                    System.out.println("--c begin");

                    System.out.println("--c end");
                    break A;// test this
                    // break B;// test this
                    // break C;// test this
                }// end c
                System.out.println("-b end");
            }// end b
            System.out.println("a end");
        }// end a

        A: while (true) {
            System.out.println("while a begin");
            B: while (true) {
                System.out.println("while b begin");
                break A;
            }
        }

        System.out.println("=== end of test ===");
    }

    private static boolean isOK() {
        return true;
    }

}
