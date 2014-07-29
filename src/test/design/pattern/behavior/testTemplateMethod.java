
package test.design.pattern.behavior;

/**
 * http://computerdragon.blog.51cto.com/6235984/1167937 *
 * 模板方法模式定义为：在一个方法中定义了一个算法的骨架或者步骤
 * ，而将一些步骤延迟到子类中去实现。模板方法使得子类可以在不改变算法结构的情况下，重新定义算法中的某一些步骤。
 * 模板方法在基类中定义了一个操作的流程顺序，能够保证该步骤按序进行
 * ，有一些步骤的具体实现在基类中已经声明，而将一些变化的步骤的具体实现交给了子类去实现，从而就达到了延迟一些步骤到子类中
 * ，模板方法一个最大的好处就是能够设定一个业务流程能够按照一定严格的顺序执行，控制了整个算法的执行步骤。
 * 这个方法将算法定义成一组步骤，其中凡是想让子类进行自定义实现的步骤
 * ，均定义为抽象方法。抽象基类的特点是，一般将模板方法设置为final，这样防止子类覆盖该算法的步骤
 * ，将一些相同的操作或步骤直接在基类中实现，将一些变化的步骤设置为抽象由子类去完成。 模板方法中挂钩：
 * 当在模板方法中某一些步骤是可选的时候，也就是该步骤不一定要执行
 * ，可以由子类来决定是否要执行，则此时就需要用上钩子。钩子是一种被声明在抽象类中的方法，但一般来说它只是空的或者具有默认值
 * ，子类可以实现覆盖该钩子，来设置算法步骤的某一步骤是否要执行
 * 。钩子可以让子类实现算法中可选的部分，让子类能够有机会对模板方法中某些一即将发生的步骤做出反应。 设计原则： 别调用我们，我来调用你们。
 * 这种原则主要就是，我们允许底层组件将自己挂钩到系统上，但是高层组件会决定什么时候和怎么样来使用这些底层组件。 要点：
 * 1）钩子是一种方法，它在抽象类中不做事，或者是默认的事情，子类可以选择覆盖它 2）为了防止子类改变模板方法中的算法骨架，一般将模板方法声明为final
 * 3）策略模式和模板方法都是用于封装算法，前者是利用组合和委托模型，而后者则是继承
 */

/**
 * http://haolloyin.blog.51cto.com/1177454/333063/ 模板方法模式（Template
 * Method）：定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。该模式使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。 使用场景：
 * 1、一次性实现一个算法的不变的部分，并将可变的行为留给子类来实现。
 * 2、各子类中公共的行为应被提取出来并集中到一个公共父类中以避免代码重复。即“重分解以一般化
 * ”，首先识别现有代码中的不同之处，并且将不同之处分离为新的操作。最后，用一个调用这些新的操作的模板方法来替换这些不同的代码。
 * 3、控制子类扩展。模板方法只在特定点调用“Hook Method（钩子方法）”操作，这样就只允许在这些点进行扩展。
 */
public class testTemplateMethod {
    public static void main(String[] args) {
        // 1
        new Tea().prepareRecipe();
        new Coffee().prepareRecipe();

        // 2
        AbstractMethod methodA = new ConcreteMethodA();
        methodA.templateMethod();
        AbstractMethod methodB = new ConcreteMethodB();
        methodB.templateMethod();
    }
}

// 模板方法
abstract class BeverageMake {

    // final可以防止子类更改覆盖该算法，这样可以保证算法步骤不被破坏
    public final void prepareRecipe()
    {
        boilWater();
        brew();
        pourInCup();
        if (hookCondiments())
            addCondiments();
    }

    abstract void brew();

    abstract void addCondiments();

    // 烧水
    public void boilWater()
    {
        System.out.println("Now start to boiling water");
    }

    // 饮料导入杯子汇总
    public void pourInCup()
    {
        System.out.println("pour the beverage into the cup");
    }

    // 加入了钩子，来让子类决定是否执行该步骤
    public boolean hookCondiments()
    {
        return true;
    }
}

class Tea extends BeverageMake {

    @Override
    void brew() {
        // TODO Auto-generated method stub
        System.out.println("boil the tea in the water");
    }

    @Override
    void addCondiments() {
        // TODO Auto-generated method stub
        System.out.println("put some condiments into the tea");
    }

}

class Coffee extends BeverageMake {

    @Override
    void brew() {
        // TODO Auto-generated method stub
        System.out.println("boil the coffee in the water");
    }

    @Override
    void addCondiments() {
        // TODO Auto-generated method stub
        System.out.println("put some condiments into the coffee");
    }

    // 设置不需要加饮料，这样就可以控制算法的某一个步骤不执行
    @Override
    public boolean hookCondiments()
    {
        return false;
    }
}

abstract class AbstractMethod {

    protected abstract void method1();

    protected abstract void method2();

    // 模板方法，统一调用上面两个在子类中会被实现的方法，即不同的实现
    public final void templateMethod() {
        /*
         * 此方法体内也可以通过“钩子方法”来实现根据一定情况调用 不同的方法组合
         */
        if (hookMethod()) {
            method1();
        } else {
            method1();
            method2();
        }
    }

    // 钩子方法,protected权限可被子类覆盖，默认为返回true
    protected boolean hookMethod() {
        return true;
    }
}

class ConcreteMethodA extends AbstractMethod {
    protected void method1() {
        System.out.println("子类 A ：method1() ...");
    }

    protected void method2() {
        System.out.println("子类 A ：method2() ...");
    }
}

class ConcreteMethodB extends AbstractMethod {
    protected void method1() {
        System.out.println("子类 B ：method1() ...");
    }

    protected void method2() {
        System.out.println("子类 B ：method2() ...");
    }

    // 覆盖钩子方法
    protected boolean hookMethod() {
        return false;
    }
}
