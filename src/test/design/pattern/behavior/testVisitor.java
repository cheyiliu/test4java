
package test.design.pattern.behavior;

/**
 * http://developer.51cto.com/art/200807/82651_all.htm 一、引子
 * 对于系统中一个已经完成的类层次结构，我们已经给它提供了满足需求的接口
 * 。但是面对新增加的需求，我们应该怎么做呢？如果这是为数不多的几次变动，而且你不用为了一个需求的调整而将整个类层次结构统统地修改一遍
 * ，那么直接在原有类层次结构上修改也许是个不错的主意。
 * 但是往往我们遇到的却是：这样的需求变动也许会不停的发生；更重要的是需求的任何变动可能都要让你将整个类层次结构修改个底朝天
 * ……。这种类似的操作分布在不同的类里面，不是一个好现象，我们要对这个结构重构一下了。 那么，访问者模式也许是你很好的选择。 二、定义与结构
 * 访问者模式，顾名思义使用了这个模式后就可以在不修改已有程序结构的前提下，通过添加额外的“访问者”来完成对已有代码功能的提升。
 * 《设计模式》一书对于访问者模式给出的定义为：表示一个作用于某对象结构中的各元素的操作。它使你可以在不改变各元素的类的前提下定义作用于这些元素的新操作。
 * 从定义可以看出结构对象是使用访问者模式必须条件，而且这个结构对象必须存在遍历自身各个对象的方法。这便类似于java中的collection概念了。
 * 以下是访问者模式的组成结构： 1)
 * 访问者角色（Visitor）：为该对象结构中具体元素角色声明一个访问操作接口。该操作接口的名字和参数标识了发送访问请求给具体访问者的具体元素角色
 * 。这样访问者就可以通过该元素角色的特定接口直接访问它。 2) 具体访问者角色（Concrete
 * Visitor）：实现每个由访问者角色（Visitor）声明的操作。 3) 元素角色（Element）：定义一个Accept操作，它以一个访问者为参数。
 * 4) 具体元素角色（Concrete Element）：实现由元素角色提供的Accept操作。 5) 对象结构角色（Object
 * Structure）：这是使用访问者模式必备的角色
 * 。它要具备以下特征：能枚举它的元素；可以提供一个高层的接口以允许该访问者访问它的元素；可以是一个复合（组合模式）或是一个集合，如一个列表或一个无序集合。
 * 三、举例 四、双重分派 对了，你在上面的例子中体会到双重分派的实现了没有？
 * 首先在客户程序中将具体访问者模式作为参数传递给具体元素角色（加亮的地方所示）。这便完成了一次分派。 进入具体元素角色后
 * ，具体元素角色调用作为参数的具体访问者模式中的visitor方法，同时将自己（this）作为参数传递进去。 具体访问者模式再根据参数的不同来选择方法来执行
 * （加亮的地方所示）。这便完成了第二次分派。 五、优缺点及适用情况
 * 先来看下访问者模式的使用能否避免引言中的痛苦。使用了访问者模式以后，对于原来的类层次增加新的操作
 * ，仅仅需要实现一个具体访问者角色就可以了，而不必修改整个类层次
 * 。而且这样符合“开闭原则”的要求。而且每个具体的访问者角色都对应于一个相关操作，因此如果一个操作的需求有变
 * ，那么仅仅修改一个具体访问者角色，而不用改动整个类层次。 看来访问者模式确实能够解决我们面临的一些问题。
 * 而且由于访问者模式为我们的系统多提供了一层“访问者”，因此我们可以在访问者中添加一些对元素角色的额外操作。
 * 但是“开闭原则”的遵循总是片面的。如果系统中的类层次发生了变化，会对访问者模式产生什么样的影响呢？你必须修改访问者角色和每一个具体访问者角色……
 * 看来访问者角色不适合具体元素角色经常发生变化的情况
 * 。而且访问者角色要执行与元素角色相关的操作，就必须让元素角色将自己内部属性暴露出来，而在java中就意味着其它的对象也可以访问
 * 。这就破坏了元素角色的封装性。而且在访问者模式中，元素与访问者之间能够传递的信息有限，这往往也会限制访问者模式的使用。
 * 《设计模式》一书中给出了访问者模式适用的情况： 1) 一个对象结构包含很多类对象，它们有不同的接口，而你想对这些对象实施一些依赖于其具体类的操作。 2)
 * 需要对一个对象结构中的对象进行很多不同的并且不相关的操作
 * ，而你想避免让这些操作“污染”这些对象的类。Visitor使得你可以将相关的操作集中起来定义在一个类中。 3)
 * 当该对象结构被很多应用共享时，用Visitor模式让每个应用仅包含需要用到的操作。 4)
 * 定义对象结构的类很少改变，但经常需要在此结构上定义新的操作。改变对象结构类需要重定义对所有访问者的接口
 * ，这可能需要很大的代价。如果对象结构类经常改变，那么可能还是在这些类中定义这些操作较好。 你是否能很好的理解呢？ 六、总结
 * 这是一个巧妙而且复杂的模式，它的使用条件比较苛刻。当系统中存在着固定的数据结构（比如上面的类层次），而有着不同的行为，那么访问者模式也许是个不错的选择。
 * http://zh.wikipedia.org/zh/%E8%AE%BF%E9%97%AE%E8%80%85%E6%A8%A1%E5%BC%8F
 * 访问者模式是一种将算法与对象结构分离的软件设计模式。
 * 这个模式的基本想法如下：首先我们拥有一个由许多对象构成的对象结构，这些对象的类都拥有一个accept方法用来接受访问者对象
 * ；访问者是一个接口，它拥有一个visit方法
 * ，这个方法对访问到的对象结构中不同类型的元素作出不同的反应；在对象结构的一次访问过程中，我们遍历整个对象结构，对每一个元素都实施accept方法
 * ，在每一个元素的accept方法中回调访问者的visit方法
 * ，从而使访问者得以处理对象结构的每一个元素。我们可以针对对象结构设计不同的实在的访问者类来完成不同的操作。
 * 访问者模式使得我们可以在传统的单分派语言（如Smalltalk
 * 、Java和C++）中模拟双分派技术。对于支持多分派的语言（如CLOS），访问者模式已经内置于语言特性之中了，从而不再重要。
 */
interface Visitor {
    void visit(Wheel wheel);

    void visit(Engine engine);

    void visit(Body body);

    void visit(Car car);
}

class Wheel {
    private String name;

    Wheel(String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }

    void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Engine {
    void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Body {
    void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Car {
    private Engine engine = new Engine();
    private Body body = new Body();
    private Wheel[] wheels = {
            new Wheel("front left"), new Wheel("front right"),
            new Wheel("back left"), new Wheel("back right")
    };

    void accept(Visitor visitor) {
        visitor.visit(this);
        engine.accept(visitor);
        body.accept(visitor);
        for (int i = 0; i < wheels.length; ++i)
            wheels[i].accept(visitor);
    }
}

class PrintVisitor implements Visitor {
    public void visit(Wheel wheel) {
        System.out.println("Visiting " + wheel.getName()
                + " wheel");
    }

    public void visit(Engine engine) {
        System.out.println("Visiting engine");
    }

    public void visit(Body body) {
        System.out.println("Visiting body");
    }

    public void visit(Car car) {
        System.out.println("Visiting car");
    }
}

public class testVisitor {
    static public void main(String[] args) {
        Car car = new Car();
        Visitor visitor = new PrintVisitor();
        car.accept(visitor);
    }
}
