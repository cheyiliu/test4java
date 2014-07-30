
package test.design.pattern.behavior;

import java.util.ArrayList;

/**
 * http://www.importnew.com/7924.html
 * http://www.cnblogs.com/java-my-life/archive/2012/05/16/2502279.html
 * 观察者模式是对象的行为模式
 * ，又叫发布-订阅(Publish/Subscribe)模式、模型-视图(Model/View)模式、源-监听器(Source/Listener
 * )模式或从属者(Dependents)模式。
 * 　　观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。这个主题对象在状态上发生变化时
 * ，会通知所有观察者对象，使它们能够自动更新自己。 　观察者模式所涉及的角色有：
 * 　　●　　抽象主题(Subject)角色：抽象主题角色把所有对观察者对象的引用保存在一个聚集
 * （比如ArrayList对象）里，每个主题都可以有任何数量的观察者
 * 。抽象主题提供一个接口，可以增加和删除观察者对象，抽象主题角色又叫做抽象被观察者(Observable)角色。
 * 　　●　　具体主题(ConcreteSubject
 * )角色：将有关状态存入具体观察者对象；在具体主题的内部状态改变时，给所有登记过的观察者发出通知。具体主题角色又叫做具体被观察者(Concrete
 * Observable)角色。
 * 　　●　　抽象观察者(Observer)角色：为所有的具体观察者定义一个接口，在得到主题的通知时更新自己，这个接口叫做更新接口。
 * 　　●　　具体观察者(ConcreteObserver
 * )角色：存储与主题的状态自恰的状态。具体观察者角色实现抽象观察者角色所要求的更新接口，以便使本身的状态与主题的状态
 * 像协调。如果需要，具体观察者角色可以保持一个指向具体主题对象的引用。 在观察者模式中，又分为推模型和拉模型两种方式。 　　●　　推模型 　　　　
 * 主题对象向观察者推送主题的详细信息，不管观察者是否需要，推送的信息通常是主题对象的全部或部分数据。 　　●　　拉模型 　　　　
 * 主题对象在通知观察者的时候，
 * 只传递少量信息。如果观察者需要更具体的信息，由观察者主动到主题对象中获取，相当于是观察者从主题对象中拉数据。一般这种模型的实现中
 * ，会把主题对象自身通过update()方法传递给观察者，这样在观察者需要获取数据的时候，就可以通过这个引用来获取了。
 */
public class testObserver {
    public static void main(String[] args) {
        HeadHunter hh = new HeadHunter();
        hh.registerObserver(new JobSeeker("Mike"));
        hh.registerObserver(new JobSeeker("Chris"));
        hh.registerObserver(new JobSeeker("Jeff"));

        // 每次添加一个个job，所有找工作人都可以得到通知。
        hh.addJob("Google Job");
        hh.addJob("Yahoo Job");
    }
}

interface Subject {
    public void registerObserver(Observer o);

    public void removeObserver(Observer o);

    public void notifyAllObservers();
}

interface Observer {
    public void update(Subject s);
}

class HeadHunter implements Subject {

    // define a list of users, such as Mike, Bill, etc.
    private ArrayList<Observer> userList;
    private ArrayList<String> jobs;

    public HeadHunter() {
        userList = new ArrayList<Observer>();
        jobs = new ArrayList<String>();
    }

    @Override
    public void registerObserver(Observer o) {
        userList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        userList.remove(o);
    }

    @Override
    public void notifyAllObservers() {
        for (Observer o : userList) {
            o.update(this);
        }
    }

    public void addJob(String job) {
        this.jobs.add(job);
        notifyAllObservers();
    }

    public ArrayList<String> getJobs() {
        return jobs;
    }

    public String toString() {
        return jobs.toString();
    }
}

class JobSeeker implements Observer {

    private String name;

    public JobSeeker(String name) {
        this.name = name;
    }

    @Override
    public void update(Subject s) {
        System.out.println(this.name + " got notified!");
        // print job list
        System.out.println(s);
    }
}
