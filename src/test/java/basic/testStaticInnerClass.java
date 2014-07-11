
package test.java.basic;

/*http://www.cnblogs.com/Alex--Yang/p/3386863.html*/
public class testStaticInnerClass {
    private String name;
    private int age;

    public static class Builder {
        private String name;
        private int age;

        public Builder(int age) {
            this.age = age;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAge(int age) {
            this.age = age;
            return this;
        }

        public testStaticInnerClass build() {
            return new testStaticInnerClass(this);
        }
    }

    private testStaticInnerClass(Builder b) {
        this.age = b.age;
        this.name = b.name;
    }

    public static void main(String[] args) {
        testStaticInnerClass outer = new Builder(2)
                .withName("Yang Liu")
                .build();
        System.out.println(outer.name);
        System.out.println(outer.age);
    }
}
