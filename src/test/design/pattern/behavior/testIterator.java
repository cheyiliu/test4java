
package test.design.pattern.behavior;

import java.util.ArrayList;
import java.util.List;

/**
 * http://blog.csdn.net/zhengzhb/article/details/7610745
 * http://alaric.iteye.com/blog/1978409 迭代器
 * (Iterator)模式：迭代器模式提供一种方法顺序一个聚合对象中各个元素，而又不暴露该对象内部表示。 迭代器模式由以下角色组成：
 * 迭代器角色(Iterator)：迭代器角色负责定义访问和遍历元素的接口。 具体迭代器角色(Concrete
 * Iterator)：具体迭代器角色要实现迭代器接口，并要记录遍历中的当前位置。 容器角色(Container)：容器角色负责提供创建具体迭代器角色的接口。
 * 具体容器角色(Concrete Container)：具体容器角色实现创建具体迭代器角色的接口。这个具体迭代器角色与该容器的结构相关。
 * java中已经完整的实现了所有集合的迭代。迭代器的作用就是把集合的管理和迭代算法分离，体现了单一职责原则，这也是这个模式的主要作用。
 */
public class testIterator {
    public static void main(String[] args) {
        Aggregate<String> ag = new ConcreteAggregate<String>();
        ag.add("red");
        ag.add("green");
        ag.add("blue");
        Iterator<String> it = ag.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            System.out.println(str);
        }
    }
}

interface Iterator<T> {

    public T next();

    public boolean hasNext();
}

interface Aggregate<T> {
    public void add(T obj);

    public void remove(T obj);

    public Iterator<T> iterator();
}

class ConcreteIterator<T> implements Iterator<T> {

    private List<T> list = new ArrayList<T>();
    private int cursor = 0;

    public ConcreteIterator(List<T> list) {
        this.list = list;
    }

    public boolean hasNext() {
        if (cursor == list.size()) {
            return false;
        }
        return true;
    }

    public T next() {
        T obj = null;
        if (this.hasNext()) {
            obj = this.list.get(cursor++);
        }
        return obj;
    }
}

class ConcreteAggregate<T> implements Aggregate<T> {
    private List<T> list = new ArrayList<T>();

    public void add(T obj) {
        list.add(obj);
    }

    public Iterator<T> iterator() {
        return new ConcreteIterator<T>(list);
    }

    public void remove(Object obj) {
        list.remove(obj);
    }
}
