
package test.runtimeCallStack;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Test {
    public static void main(String[] args) {
        new Test().f();
    }

    public static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        return sw.toString();
    }

    private void a() {
        // eg. how to use, add the below code the the method you want to trace
        Throwable t = new Throwable();
        t.fillInStackTrace();
        System.out.println(getStackTraceString(t));
    }

    private void b() {
        a();
    }

    private void c() {
        b();
    }

    private void d() {
        c();
    }

    private void e() {
        d();
    }

    private void f() {
        e();
    }

}
