
package test.design.pattern;

/*
 * http://www.blogjava.net/kenzhh/archive/2011/09/02/357824.html
 * http://mengqingyu.iteye.com/blog/1838679
 * 总结：五类：懒汉，恶汉，双重校验锁，静态内部类，枚举。
恶汉：因为加载类的时候就创建实例，所以线程安全(多个ClassLoader存在时例外)。缺点是不能延时加载。
懒汉：需要加锁才能实现多线程同步，但是效率会降低。优点是延时加载。
双重校验锁：麻烦，在当前Java内存模型中不一定都管用，某些平台和编译器甚至是错误的，因为instance = new MaYun()这种代码在不同编译器上的行为和实现方式不可预知。
静态内部类：延迟加载，减少内存开销。因为用到的时候才加载，避免了静态field在单例类加载时即进入到堆内存的permanent代而永远得不到回收的缺点(大多数垃圾回收算法是这样)。
枚举：很好，不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象。但是失去了类的一些特性，没有延迟加载。

注：
双重校验所问题：JAVA内存模型一些原因偶尔会失败，内存模型允许所谓的“无序写入”，这是失败的一个主要原因，初始化实例的写入操作和实例字段的写入操作能够被
编译器或者缓冲区重排序，重排序可能会导致返回部分构造的一些东西。在JDK1.5新的内存模型下，实例字段使用volatile可以解决双重锁检查的问题，因为在构造线程来
初始化一些东西和读取线程返回它的值之间有happens-before关系。
防止反序列化：重写readResolve()接口，直接返回当前实例。
防止反射：私有构造器中添加非空逻辑判断和throw new AssertionError(); 
 * */
public class Singleton {
    private static volatile Singleton sInstance;// volatile is necessary 

    private Singleton() {
    };

    public static Singleton getInstance() {
        if (sInstance == null) {
            synchronized (Singleton.class) {
                if (sInstance == null) {// 双重检查锁定
                    sInstance = new Singleton();
                }
            }
        }
        return sInstance;
    }
}

//1 这种写法lazy loading很明显，但是致命的是在多线程不能正常工作。
//public class Singleton {
//    private static Singleton instance;
//
//    private Singleton() {
//    }
//
//    public static Singleton getInstance() {
//        if (instance == null) {
//            instance = new Singleton();
//        }
//        return instance;
//    }
//}
//
//2  这种写法能够在多线程中很好的工作，而且看起来它也具备很好的lazy loading，
//但是，遗憾的是，效率很低，99%情况下不需要同步。
//public class Singleton {
//    private static Singleton instance;
//
//    private Singleton() {
//    }
//
//    public static synchronized Singleton getInstance() {
//        if (instance == null) {
//            instance = new Singleton();
//        }
//        return instance;
//    }
//}
//
//3  这种方式基于classloder机制避免了多线程的同步问题，
//不过，instance在类装载时就实例化，虽然导致类装载的原因有很多种，
//在单例模式中大多数都是调用getInstance方法， 
//但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，
//这时候初始化instance显然没有达到lazy loading的效果。
//public class Singleton {
//    private static Singleton instance = new Singleton();
//
//    private Singleton() {
//    }
//
//    public static Singleton getInstance() {
//        return instance;
//    }
//}
//
//4  表面上看起来差别挺大，其实更第三种方式差不多，都是在类初始化即实例化instance。
//public class Singleton {
//    private Singleton instance = null;
//    static {
//        instance = new Singleton();
//    }
//
//    private Singleton() {
//    }
//
//    public static Singleton getInstance() {
//        return this.instance;
//    }
//}
//
//5 这种方式同样利用了classloder的机制来保证初始化instance时只有一个线程，
//它跟第三种和第四种方式不同的是（很细微的差别）：
//第三种和第四种方式是只要Singleton类被装载了，
//那么instance就会被实例化（没有达到lazy loading效果），
//而这种方式是Singleton类被装载了，instance不一定被初始化。
//因为SingletonHolder类没有被主动使用，只有显示通过调用getInstance方法时，才
//会显示装载SingletonHolder类，从而实例化instance。
//想象一下，如果实例化instance很消耗资源，我想让他延迟加载，
//另外一方面，我不希望在Singleton类加载时就实例化，
//因为我不能确保Singleton类还可能在其他的地方被主动使用从而被加载，
//那么这个时候实例化instance显然是不合适的。
//这个时候，这种方式相比第三和第四种方式就显得很合理。
//public class Singleton {
//    private static class SingletonHolder {
//        private static final Singleton INSTANCE = new Singleton();
//    }
//
//    private Singleton() {
//    }
//
//    public static final Singleton getInstance() {
//        return SingletonHolder.INSTANCE;
//    }
//}
//
//  6这种方式是Effective Java作者Josh Bloch 提倡的方式，
//它不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象，
//可谓是很坚强的壁垒啊，不过，个人认为由于1.5中才加入enum特性，
//用这种方式写不免让人感觉生疏，在实际工作中，我也很少看见有人这么写过。
//public enum Singleton {
//    INSTANCE;
//    public void whateverMethod() {
//    }
//}
//
// 7  这个是第二种方式的升级版，俗称双重检查锁定
//public class Singleton {
//    private volatile static Singleton singleton;
//
//    private Singleton() {
//    }
//
//    public static Singleton getSingleton() {
//        if (singleton == null) {
//            synchronized (Singleton.class) {
//                if (singleton == null) {
//                    singleton = new Singleton();
//                }
//            }
//        }
//        return singleton;
//    }
//}
