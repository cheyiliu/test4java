
package test.design.pattern.structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

/**
 * http://alaric.iteye.com/blog/1910919 通过以上结构图可以看出，组合模式有以下角色：
 * 1、抽象构建角色（component）：作为抽象角色，给组合对象的统一接口。 2、树叶构建角色（leaf）：代表组合对象中的树叶对象。
 * 3、树枝构建角色（composite）：参加组合的所有子对象的对象，并给出树枝构构建对象的行为。
 * 组合模式在现实中使用很常见，比如文件系统中目录和文件的组成
 * ，算式运算，android里面的view和viewgroup等控件，淘宝产品分类信息的展示等都是组合模式的例子
 * 。还有个故事：从前山里有座庙，庙里有个老和尚，老和尚对象小和尚讲故事说，从前山里有个庙......退出循环的条件是听厌烦了，或者讲的人讲累了。
 * 这个模式的举例最多的是公司员工的例子。 　　 适用性 　　以下情况下适用Composite模式： 　　1．你想表示对象的部分-整体层次结构
 * 　　2．你希望用户忽略组合对象与单个对象的不同，用户将统一地使用组合结构中的所有对象。 　　总结
 * 组合模式解耦了客户程序与复杂元素内部结构，从而使客户程序可以向处理简单元素一样来处理复杂元素。
 * 如果你想要创建层次结构，并可以在其中以相同的方式对待所有元素，那么组合模式就是最理想的选择。
 * http://www.cnblogs.com/itTeacher/archive/2012/12/13/2816039.html
 * 组合模式又叫做部分-整体模式
 * ,它使我们树型结构的问题中,模糊了简单元素和复杂元素的概念,客户程序可以向处理简单元素一样来处理复杂元素,从而使得客户程序与复杂元素的内部结构解藕.
 */
public class testComposite {
    @Test
    public void test() {
        TeamLeader leader1 = new TeamLeader("张三");
        TeamLeader leader2 = new TeamLeader("李四");
        Employe employe1 = new Employe("王五");
        Employe employe2 = new Employe("赵六");
        Employe employe3 = new Employe("陈七");
        Employe employe4 = new Employe("徐八");
        leader1.add(leader2);
        leader1.add(employe1);
        leader1.add(employe2);
        leader2.add(employe3);
        leader2.add(employe4);
        leader1.doSomething();

        // 2
        /**
         * 通过实现组合模式，用户对文件夹的操作与对普通文件的操作并无差异。用户完全不用关心这是文件夹还是文件，也不用关心文件夹内部的具体结构，
         * 就可以完成相关操作。 同样的道理，我们可以表达如下：
         * 通过实现组合模式，调用者对组合对象的操作与对单一对象的操作具有一致性。调用者不用关心这是组合对象还是文件
         * ，也不用关心组合对象内部的具体结构，就可以调用相关方法，实现功能。
         * 仔细分析copy()方法的代码，我们会发现，如果从面向过程的角度思考，组合模式通过递归原理实现了树结构（组合对象）的深度优先遍历。
         */
        Folder document = new Folder("我的资料"); // 我的资料文件夹
        File book = new File("Java编程思想.pdf"); // 文档文件
        Folder music = new Folder("我的音乐"); // 我的音乐文件夹
        File music1 = new File("你是我的眼.mp3"); // 音乐文件1
        File music2 = new File("Without You.mp3"); // 音乐文件2
        // 确定树形结构关系
        document.add(book);
        document.add(music);
        music.add(music1);
        music.add(music2);

        document.copy(); // 复制“我的资料”文件夹，递归地复制了其下所有文件夹和文件。

        //
        final FolderComponent leaf = new FileLeaf("runnable file");
        leaf.display();

        final FolderComponent folder = new FolderComposite("new folder");
        folder.add(new FileLeaf("content1 in new folder"));
        folder.add(new FileLeaf("content2 in new folder"));
        folder.display();

    }

}

/**
 * 作者：alaric 时间：2013-7-20下午5:11:41 描述：员工和领导的统一接口
 */
interface Worker {

    public void doSomething();

}

/**
 * 作者：alaric 时间：2013-7-20下午5:59:11 描述：普通员工类
 */
class Employe implements Worker {

    private String name;

    public Employe(String name) {
        super();
        this.name = name;
    }

    @Override
    public void doSomething() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "我叫" + getName() + ",是一普通员工!";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

/**
 * 作者：alaric 时间：2013-7-20下午5:14:50 描述：领导类
 */
class TeamLeader implements Worker {
    private List<Worker> workers = new CopyOnWriteArrayList<Worker>();
    private String name;

    public TeamLeader(String name) {
        super();
        this.name = name;
    }

    public void add(Worker worker) {
        workers.add(worker);
    }

    public void remove(Worker worker) {
        workers.remove(worker);
    }

    public Worker getChild(int i) {
        return workers.get(i);
    }

    @Override
    public void doSomething() {
        System.out.println(toString());
        Iterator<Worker> it = workers.iterator();
        while (it.hasNext()) {
            it.next().doSomething();
        }

    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "我叫" + getName() + ", 我是一个领导,有 " + workers.size() + "下属。";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

interface Node {
    public void copy(); // 定义统一的接口：复制
}

class Folder implements Node {
    private String folderName;
    private ArrayList<Node> nodeList = new ArrayList<Node>(); // 用于存储文件夹下的文件夹或文件的信息

    public Folder(String folderName) {
        this.folderName = folderName;
    }

    public void add(Node node) { // 增加文件或文件夹
        nodeList.add(node);
    }

    public void copy() { // 文件夹复制操作实现递归
        System.out.println("复制文件夹：" + folderName);
        for (int i = 0; i < nodeList.size(); i++) {
            Node node = (Node) nodeList.get(i);
            node.copy();
        }
    }
}

class File implements Node {
    private String fileName;

    public File(String fileName) {
        this.fileName = fileName;
    }

    public void copy() {
        System.out.println("复制文件：" + fileName);
    }
}

abstract class FolderComponent
{
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public FolderComponent()
    {
    }

    public FolderComponent(final String name)
    {
        this.name = name;
    }

    public abstract void add(FolderComponent component);

    public abstract void remove(FolderComponent component);

    public abstract void display();
}

class FileLeaf extends FolderComponent
{
    public FileLeaf(final String name)
    {
        super(name);
    }

    @Override
    public void add(final FolderComponent component)
    {
        // ...
    }

    @Override
    public void remove(final FolderComponent component)
    {
        // ...
    }

    @Override
    public void display()
    {
        System.out.println("FileLeaf:" + this.getName());
    }
}

class FolderComposite extends FolderComponent
{
    private final List<FolderComponent> components;

    public FolderComposite(final String name)
    {
        super(name);
        this.components = new ArrayList<FolderComponent>();
    }

    public FolderComposite()
    {
        this.components = new ArrayList<FolderComponent>();
    }

    @Override
    public void add(final FolderComponent component)
    {
        this.components.add(component);
    }

    @Override
    public void remove(final FolderComponent component)
    {
        this.components.remove(component);
    }

    @Override
    public void display()
    {
        System.out.println("FolderComposite---name:" + this.getName());
        for (final FolderComponent component : components)
        {
            System.out.println("FolderComposite---component-name:" + component.getName());
        }
    }
}
