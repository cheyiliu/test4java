
package test.design.pattern.behavior;

import java.util.ArrayList;
import java.util.List;

/**
 * http://blog.csdn.net/qbg19881206/article/details/18772201
 */
/**
 * 中介者模式将一个网状的系统结构变成一个以中介者对象为中心的星形结构，
 * 在这个星型结构中，使用中介者对象与其他对象的一对多关系来取代原有对象之间的多对多关系。
 * 中介者模式在事件驱动类软件中应用较为广泛，特别是基于GUI（Graphical User Interface，
 * 图形用户界面）的应用软件，此外，在类与类之间存在错综复杂的关联关系的系统中， 中介者模式都能得到较好的应用。
 * 
 * @author qbg
 */
public class testMediator2 {
    public static void main(String[] args) {
        List<Person2> ps = new ArrayList<Person2>();
        Person2 b0 = new Boy();
        ps.add(b0);
        Person2 b1 = new Boy();
        ps.add(b1);
        Person2 g0 = new Girl();
        ps.add(g0);
        Person2 g1 = new Girl();
        ps.add(g1);
        Mediator2 mediator = new ConcreteMediator(ps);
        b0.setInterest("创业");
        b0.setMediator(mediator);
        b1.setInterest("挣钱");
        b1.setMediator(mediator);
        g0.setInterest("逛街");
        g0.setMediator(mediator);
        g1.setInterest("持家");
        g1.setMediator(mediator);

        b0.interest("持家");
        System.out.println("==========男女有别===============");
        g0.interest("挣钱");
    }
}

/**
 * 中介者抽象接口
 */
interface Mediator2 {
    public void interestChanged(Person2 p);
}

/**
 * 具体中介者，充当媒婆的角色。
 */
class ConcreteMediator implements Mediator2 {
    private List<Person2> miai; // 相亲队伍

    public ConcreteMediator(List<Person2> ps) {
        this.miai = ps;
    }

    /**
     * 相亲者兴趣改变时进行响应,重新匹配候选人. 封装同事对象之间的交互.
     */
    @Override
    public void interestChanged(Person2 p) {
        boolean succeed = false;
        Person2 candidate = null;
        for (Person2 person : miai) {
            // 不是相亲者本身且兴趣相同则匹配成功!
            if (person != p && p.interest.equals(person.interest)) {
                succeed = true;
                candidate = person;
                break;
            }
        }
        p.update(succeed);
        if (candidate != null) {
            candidate.update(succeed);
        }
    }

}

/**
 * 人，抽象同事类
 */
abstract class Person2 {
    protected Mediator2 mediator;// 中介者引用
    protected String interest;// 兴趣

    public void setMediator(Mediator2 m) {
        this.mediator = m;
    }

    public void setInterest(String in) {
        this.interest = in;
    }

    public void interest(String interest) {
        this.interest = interest;
        mediator.interestChanged(this);
    }

    public abstract void update(boolean succeed);
}

/**
 * 男生，具体同事类
 */
class Boy extends Person2 {

    @Override
    public void update(boolean succeed) {
        if (succeed) {
            System.out.println("相亲成功，大喝一顿！！！");
        } else {
            System.out.println("相亲失败，明天再战！！！");
        }
    }
}

/**
 * 女生，具体同事类
 */
class Girl extends Person2 {

    @Override
    public void update(boolean succeed) {
        if (succeed) {
            System.out.println("找到白马王子了，去KTV狂欢!");
        } else {
            System.out.println("黑驴都没找到，全是骡子，呜呜....");
        }
    }
}
