
package test.design.pattern;

/**
 * from http://www.cnblogs.com/java-my-life/archive/2012/04/13/2442795.html
 */
public class testAdapter {
    public interface Target {
        /**
         * 这是源类Adaptee也有的方法
         */
        public void sampleOperation1();

        /**
         * 这是源类Adapteee没有的方法
         */
        public void sampleOperation2();
    }

    public class Adaptee {

        public void sampleOperation1() {
        }

    }

    public class AdapterClass extends Adaptee implements Target {
        /**
         * 由于源类Adaptee没有方法sampleOperation2() 因此适配器补充上这个方法
         */
        @Override
        public void sampleOperation2() {
            // 写相关的代码
        }

    }

    public class AdapterObject {
        private Adaptee adaptee;

        public AdapterObject(Adaptee adaptee) {
            this.adaptee = adaptee;
        }

        /**
         * 源类Adaptee有方法sampleOperation1 因此适配器类直接委派即可
         */
        public void sampleOperation1() {
            this.adaptee.sampleOperation1();
        }

        /**
         * 源类Adaptee没有方法sampleOperation2 因此由适配器类需要补充此方法
         */
        public void sampleOperation2() {
            // 写相关的代码
        }
    }

}
