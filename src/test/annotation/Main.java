
package test.annotation;

@ReportsCrashes(
        formKey = "fromKey value",
        formUri = "fromUrl value")
public class Main {

    public static void main(String[] args) {
        Main main = new Main();

        // DEMO 1
        // 运行时获取这个对象的额外属性, from ARAC
        ReportsCrashes anotation = main.getClass().getAnnotation(ReportsCrashes.class);
        if (anotation != null) {
            System.out.println(anotation.formKey());
            System.out.println(anotation.formUri());
        }

        // DEMO 2
        // from Roboguice
        DemoActivity demoActivity = new DemoActivity();
        System.out.println("demoActivity.mView=" + demoActivity.mView);
        System.out.println("demoActivity.mView=" + demoActivity.mView2);
    }

}
