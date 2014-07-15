
package test.design.pattern;

import org.junit.Test;

/**
 * http://www.cnblogs.com/java-my-life/archive/2012/04/20/2455726.html 装饰模式的优点
 * 　　（1）装饰模式与继承关系的目的都是要扩展对象的功能，但是装饰模式可以提供比继承更多的灵活性。装饰模式允许系统动态决定“贴上”一个需要的“装饰”，
 * 或者除掉一个不需要的“装饰”。继承关系则不同，继承关系是静态的，它在系统运行前就决定了。
 * 　　（2）通过使用不同的具体装饰类以及这些装饰类的排列组合，设计师可以创造出很多不同行为的组合。 装饰模式的缺点
 * 　　由于使用装饰模式，可以比使用继承关系需要较少数目的类
 * 。使用较少的类，当然使设计比较易于进行。但是，在另一方面，使用装饰模式会产生比使用继承关系更多的对象
 * 。更多的对象会使得查错变得困难，特别是这些对象看上去都很相像。
 */
public class testDecorator {
    @Test
    public void test() {
        /** 原始构件 */
        Component component = new ConcreteComponent();
        /** 边听音乐，边走路 */
        ConcreteDecoratorListen cdl = new ConcreteDecoratorListen(component);
        cdl.go();
        System.out.println();
        /** 边走路，边唱歌 */
        ConcreteDecoratorSing cds = new ConcreteDecoratorSing(component);
        cds.go();

        // 来顿面条
        IEateable noodles = new Noodles();
        noodles.taste();

        // 来顿好吃的鸡蛋面条
        IEateable eggNoodles = new EggDecorator(noodles);
        eggNoodles.taste();

        // 来顿好吃的猪肉面条
        IEateable porkNoodles = new PorkDecorator(noodles);
        porkNoodles.taste();

        // 来顿好吃的猪肉+鸡蛋面条
        IEateable eggPorkNoodles = new PorkDecorator(eggNoodles);
        eggPorkNoodles.taste();
    }
}

interface Component {
    /** 原始接口 */
    public void go();
}

class ConcreteComponent implements Component {

    public void go() {
        System.out.println("行走");
    }

}

class Decorator implements Component {
    /** 持有私有的原始构件 */
    private Component component;

    /** 构造子，委派给原始构件 */
    protected Decorator(Component component) {
        this.component = component;
    }

    /** 调用原始构件功能，通常就可直接把扩展功能加在此方法中 */
    public void go() {
        this.component.go();
    }

}

class ConcreteDecoratorListen extends Decorator {

    /** 构造子，相关初始化 */
    public ConcreteDecoratorListen(Component component) {
        super(component);
        // code is here
    }

    /** 商业逻辑，对原始构件功能的扩展 */
    public void go() {
        listen("听音乐");// 执行扩展功能
        super.go();
    }

    private void listen(Object obj) {
        System.out.println(obj);
    }

}

class ConcreteDecoratorSing extends Decorator {

    /** 构造子，相关初始化 */
    public ConcreteDecoratorSing(Component component) {
        super(component);
        // code is here
    }

    /** 商业逻辑，对原始构件功能的扩展 */
    public void go() {
        super.go();
        System.out.println(sing());// 执行扩展功能
    }

    private String sing() {
        return "唱歌";
    }
}

interface IEateable {
    public void taste();
}

class Noodles implements IEateable {

    @Override
    public void taste() {
        System.out.println("有面条了， 好吃");
    }

}

abstract class EateableDecorator implements IEateable {
    private IEateable mEateable;

    public EateableDecorator(IEateable eateable) {
        mEateable = eateable;
    }

    @Override
    public void taste() {
        mEateable.taste();
    }
}

class EggDecorator extends EateableDecorator {

    public EggDecorator(IEateable eateable) {
        super(eateable);
    }

    @Override
    public void taste() {
        super.taste();
        System.out.println("有鸡蛋了， 好吃+1");
    }

}

class PorkDecorator extends EateableDecorator {

    public PorkDecorator(IEateable eateable) {
        super(eateable);
    }

    @Override
    public void taste() {
        super.taste();
        System.out.println("有猪肉了， 好吃+2");
    }
}
