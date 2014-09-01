
package test.design.pattern.behavior;

/**
 * http://www.cnblogs.com/chenssy/p/3348520.html 四、 模式优缺点 优点 1、
 * 简化了对象之间的关系，将系统的各个对象之间的相互关系进行封装，将各个同事类解耦，使系统成为松耦合系统。 2、 减少了子类的生成。 3、
 * 可以减少各同事类的设计与实现。 缺点 由于中介者对象封装了系统中对象之间的相互关系，导致其变得非常复杂，使得系统维护比较困难。 五、 模式适用场景 1、
 * 系统中对象之间存在比较复杂的引用关系，导致他们之间的依赖关系结构混乱而且难以复用该对象。 2、
 * 想通过一个中间类来封装多个类中的行为，而又不想生成太多的子类。 六、 模式总结 1、
 * 在中介者模式中通过引用中介者对象，将系统中有关的对象所引用的其他对象数目减少到最少
 * 。它简化了系统的结构，将系统由负责的网状结构转变成简单的星形结构，中介者对象在这里起到中转和协调作用。 2、
 * 中介者类是中介者模式的核心，它对整个系统进行控制和协调，简化了对象之间的交互，还可以对对象间的交互进行进一步的控制。 3、
 * 通过使用中介者模式，具体的同事类可以独立变化，通过引用中介者可以简化同事类的设计和实现。 4、
 * 就是由于中介者对象需要知道所有的具体同事类，封装具体同事类之间相互关系，导致中介者对象变得非常复杂，系统维护起来较为困难。
 */
public class testMediator {
    public static void main(String[] args) {
        // 一个房主、一个租房者、一个中介机构
        MediatorStructure mediator = new MediatorStructure();

        // 房主和租房者只需要知道中介机构即可
        HouseOwner houseOwner = new HouseOwner("张三", mediator);
        Tenant tenant = new Tenant("李四", mediator);

        // 中介结构要知道房主和租房者
        mediator.setHouseOwner(houseOwner);
        mediator.setTenant(tenant);

        tenant.constact("听说你那里有三室的房主出租.....");
        houseOwner.constact("是的!请问你需要租吗?");
    }
}

abstract class Mediator {
    // 申明一个联络方法
    public abstract void constact(String message, Person person);
}

abstract class Person {
    protected String name;
    protected Mediator mediator;

    Person(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

}

class HouseOwner extends Person {

    HouseOwner(String name, Mediator mediator) {
        super(name, mediator);
    }

    /**
     * @desc 与中介者联系
     * @param message
     * @return void
     */
    public void constact(String message) {
        mediator.constact(message, this);
    }

    /**
     * @desc 获取信息
     * @param message
     * @return void
     */
    public void getMessage(String message) {
        System.out.println("房主:" + name + ",获得信息：" + message);
    }
}

class Tenant extends Person {

    Tenant(String name, Mediator mediator) {
        super(name, mediator);
    }

    /**
     * @desc 与中介者联系
     * @param message
     * @return void
     */
    public void constact(String message) {
        mediator.constact(message, this);
    }

    /**
     * @desc 获取信息
     * @param message
     * @return void
     */
    public void getMessage(String message) {
        System.out.println("租房者:" + name + ",获得信息：" + message);
    }
}

class MediatorStructure extends Mediator {
    // 首先中介结构必须知道所有房主和租房者的信息
    private HouseOwner houseOwner;
    private Tenant tenant;

    public HouseOwner getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(HouseOwner houseOwner) {
        this.houseOwner = houseOwner;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public void constact(String message, Person person) {
        if (person == houseOwner) { // 如果是房主，则租房者获得信息
            tenant.getMessage(message);
        }
        else { // 反正则是房主获得信息
            houseOwner.getMessage(message);
        }
    }
}
