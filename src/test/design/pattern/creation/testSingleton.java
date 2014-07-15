
package test.design.pattern.creation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 http://www.blogjava.net/kenzhh/archive/2011/09/02/357824.html
 */
public class testSingleton {
    public enum SingletonEnum {
        INSTANCE;
        private String mName;

        public String getName() {
            return mName;
        }

        public void setName(String mName) {
            this.mName = mName;
        }

        public void print() {
            System.out.println(mName);
        }

        public void whateverMethod() {
        }
    }

    public static class SingletonStaticHolder {
        private static class SingletonHolder {
            private static final SingletonStaticHolder INSTANCE = new SingletonStaticHolder();
        }

        private SingletonStaticHolder() {
        }

        public static final SingletonStaticHolder getInstance() {
            return SingletonHolder.INSTANCE;
        }
    }

    @Test
    public void test() {
        assertEquals(Singleton.getInstance(), Singleton.getInstance());

        assertEquals(SingletonStaticHolder.getInstance(), SingletonStaticHolder.getInstance());

        SingletonEnum s = SingletonEnum.INSTANCE;
        s.setName("123");
        s.print();

        SingletonEnum s2 = SingletonEnum.INSTANCE;
        assertEquals(s, s2);
    }
}
