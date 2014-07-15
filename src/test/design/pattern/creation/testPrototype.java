
package test.design.pattern.creation;

import org.junit.Test;

/**
 * 原型模式要求对象实现一个可以“克隆”自身的接口，这样就可以通过复制一个实例对象本身来创建一个新的实例。这样一来，通过原型实例创建新的对象，
 * 就不再需要关心这个实例本身的类型，只要实现了克隆自身的方法，就可以通过这个方法来获取新的对象，而无须再去通过new来创建。
 */
/**
 * http://blog.chinaunix.net/uid-29140694-id-4109772.html 原型模式的优点：
 * （1）根据客户端要求实现动态创建对象，客户端不需要知道对象的创建细节，便于代码的维护和扩展。
 * （2）使用原型模式创建对象比直接new一个对象在性能上要好的多
 * ，因为Object类的clone方法是一个本地方法，它直接操作内存中的二进制流，特别是复制大对象时
 * ，性能的差别非常明显。所以在需要重复地创建相似对象时可以考虑使用原型模式
 * 。比如需要在一个循环体内创建对象，假如对象创建过程比较复杂或者循环次数很多的话，使用原型模式不但可以简化创建过程，而且可以使系统的整体性能提高很多。
 * （3） 原型模式类似于工厂模式，但它没有了工厂模式中的抽象工厂和具体工厂的层级关系，代码结构更清晰和简单。 9、原型模式的注意事项：
 * （1）使用原型模式复制对象不会调用类的构造方法。因为对象的复制是通过调用Object类的clone方法来完成的，它直接在内存中复制数据，因此不
 * 会调用到类的构造方法。不但构造方法中的代码不会执行，甚至连访问权限都对原型模式无效。还记得单例模式吗？单例模式中，只要将构造方法的访问权限设置为
 * private型，就可以实现单例。但是clone方法直接无视构造方法的权限，所以，单例模式与原型模式是冲突的。
 * （2）在使用时要注意深拷贝与浅拷贝的问题。clone方法只会拷贝对象中的基本的数据类型
 * ，对于数组、容器对象、引用对象等都不会拷贝，这就是浅拷贝。如果要实现深拷贝，必须将原型模式中的数组、容器对象、引用对象等另行拷贝。
 */
public class testPrototype {
    @Test
    public void test() {
        Prototype p1 = new ConcretePrototype1();
        Prototype p2 = (Prototype) p1.clone();
        p2.toString();
    }
}

interface Prototype {
    /**
     * 克隆自身的方法
     * 
     * @return 一个从自身克隆出来的对象
     */
    public Object clone();
}

class ConcretePrototype1 implements Prototype {
    public Prototype clone() {
        // 最简单的克隆，新建一个自身对象，由于没有属性就不再复制值了
        Prototype prototype = new ConcretePrototype1();
        return prototype;
    }
}

class ConcretePrototype2 implements Prototype {
    public Prototype clone() {
        // 最简单的克隆，新建一个自身对象，由于没有属性就不再复制值了
        Prototype prototype = new ConcretePrototype2();
        return prototype;
    }
}
