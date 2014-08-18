
package test.design.pattern.behavior;

/**
 * http://alaric.iteye.com/blog/1926447 责任链（Chain of
 * Responsibility）模式：责任链模式是对象的行为模式
 * 。使多个对象都有机会处理请求，从而避免请求的发送者和接受者直接的耦合关系。将这些对象连成一条链
 * ，沿着这条链传递该请求，直到有一个对象处理它为止。责任链模式强调的是每一个对象及其对下家的引用来组成一条链，利用这种方式将发送者和接收者解耦。
 * 责任链模式有两个角色： 抽象处理者（Handler）角色：定义一个请求的接口。如果需要可以定义个一个方法用来设定和返回下家对象的引用。
 * 具体处理者（ConcreteHandler
 * ）角色：如果可以处理就处理请求，如果不能处理，就把请求传给下家，让下家处理。也就是说它处理自己能处理的请求且可以访问它的下家。
 * 责任链模式的优点是调用者不需知道具体谁来处理请求
 * ，也不知道链的具体结构，降低了节点域节点的耦合度；可在运行时动态修改链中的对象职责，增强了给对象指派职责的灵活性
 * ；缺点是没有明确的接收者，可能传到链的最后，也没得到正确的处理。
 */

/**
 * http://haolloyin.blog.51cto.com/1177454/342166
 */
public class testChainOfResponsibility {
    public static void main(String[] args) {
        {
            Handler handler1 = new ConcreteHandler1();
            Handler handler2 = new ConcreteHandler2();
            Handler handlern = new ConcreteHandlerN();

            // 链起来
            handler1.setSuccessor(handler2);
            handler2.setSuccessor(handlern);

            // 假设这个请求是ConcreteHandler2的责任
            handler1.handlerRequest("ConcreteHandler2");
        }

        {
            /**
             * 那么我们再模拟一下OA系统中请假审批流程，假如员工直接上司为小组长，小组长直接上司项目经理，项目经理直接上司部门经理，
             * 部门经理直接上司总经理。公司规定请假审批如下： 请假时间为t,时间单位day，简写d： t< 0.5d，小组长审批；
             * t>=0.5d,t<2,项目经理审批； t>=2,t<5部门经理审批； t>=5总经理审批；
             */
            // 创建节点
            Handler2 gl = new GroupLeader();
            Handler2 pm = new ProjectManager();
            Handler2 dm = new DepartmentManager();
            Handler2 ceo = new CEO();
            // 建立责任链
            gl.setHandler(pm);
            pm.setHandler(dm);
            dm.setHandler(ceo);

            // 向小组长发出申请，请求审批4天的假期
            gl.approve(4D);
        }

        {
            // 创建指责链的所有节点
            AbstractHandler handler01 = new Handler01();
            AbstractHandler handler02 = new Handler02();
            AbstractHandler handler03 = new Handler03();

            // 进行链的组装，即头尾相连，一层套一层
            handler01.setNextHandler(handler02);
            handler02.setNextHandler(handler03);

            // 创建请求并提交到指责链中进行处理
            AbstractRequest request01 = new Request01("请求-01");
            AbstractRequest request02 = new Request02("请求-02");
            AbstractRequest request03 = new Request03("请求-03");

            // 每次提交都是从链头开始遍历
            handler01.handleRequest(request01);
            handler01.handleRequest(request02);
            handler01.handleRequest(request03);
        }
    }
}

abstract class Handler {

    protected Handler successor;

    /**
     * 作者：alaric 时间：2013-8-17上午11:04:22 描述：处理方法
     */
    public abstract void handlerRequest(String condition);

    public Handler getSuccessor() {
        return successor;
    }

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

}

class ConcreteHandler1 extends Handler {

    @Override
    public void handlerRequest(String condition) {
        // 如果是自己的责任，就自己处理，负责传给下家处理
        if (condition.equals("ConcreteHandler1")) {
            System.out.println("ConcreteHandler1 handled ");
            return;
        } else {
            System.out.println("ConcreteHandler1 passed ");
            getSuccessor().handlerRequest(condition);
        }
    }

}

class ConcreteHandler2 extends Handler {

    @Override
    public void handlerRequest(String condition) {
        // 如果是自己的责任，就自己处理，负责传给下家处理
        if (condition.equals("ConcreteHandler2")) {
            System.out.println("ConcreteHandler2 handled ");
            return;
        } else {
            System.out.println("ConcreteHandler2 passed ");
            getSuccessor().handlerRequest(condition);
        }
    }

}

class ConcreteHandlerN extends Handler {

    /**
     * 这里假设n是链的最后一个节点必须处理掉 在实际情况下，可能出现环，或者是树形， 这里并不一定是最后一个节点。
     */
    @Override
    public void handlerRequest(String condition) {

        System.out.println("ConcreteHandlerN handled");

    }

}

abstract class Handler2 {

    protected Handler2 handler;

    /**
     * 作者：alaric 时间：2013-8-17下午1:07:40 描述：审批
     */
    public abstract boolean approve(double day);

    public Handler2 getHandler() {
        return handler;
    }

    public void setHandler(Handler2 handler) {
        this.handler = handler;
    }

}

class GroupLeader extends Handler2 {

    @Override
    public boolean approve(double day) {
        if (day < 0.5) {
            System.out.println("小组长审批通过");
            return true;
        } else {
            System.out.println("小组长传给了他的上司");
            return getHandler().approve(day);
        }
    }

}

class ProjectManager extends Handler2 {

    @Override
    public boolean approve(double day) {
        if (day < 2) {
            System.out.println("项目经理审批通过");
            return true;
        } else {
            System.out.println("项目经理传给了他的上司");
            return getHandler().approve(day);
        }
    }

}

class DepartmentManager extends Handler2 {

    @Override
    public boolean approve(double day) {
        if (day < 5) {
            System.out.println("部门经理审批通过");
            return true;
        } else {
            System.out.println("部门经理传给了他的上司");
            return getHandler().approve(day);
        }
    }

}

class CEO extends Handler2 {

    @Override
    public boolean approve(double day) {
        System.out.println("部门经理审批通过");
        return true;

    }

}

interface Levels {
    public static final int LEVEL_01 = 1;
    public static final int LEVEL_02 = 2;
    public static final int LEVEL_03 = 3;
}

abstract class AbstractRequest {
    private String content = null;

    public AbstractRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    // 获得请求的级别
    public abstract int getRequestLevel();
} // 具体请求类01

class Request01 extends AbstractRequest {
    public Request01(String content) {
        super(content);
    }

    @Override
    public int getRequestLevel() {
        return Levels.LEVEL_01;
    }
}

// 具体请求类02
class Request02 extends AbstractRequest {

    public Request02(String content) {
        super(content);
    }

    @Override
    public int getRequestLevel() {
        return Levels.LEVEL_02;
    }
}

// 具体请求类03
class Request03 extends AbstractRequest {
    public Request03(String content) {
        super(content);
    }

    @Override
    public int getRequestLevel() {
        return Levels.LEVEL_03;
    }
}

abstract class AbstractHandler {
    // 责任链的下一个节点，即处理者
    private AbstractHandler nextHandler = null;

    // 捕获具体请求并进行处理，或是将请求传递到责任链的下一级别
    public final void handleRequest(AbstractRequest request) {

        // 若该请求与当前处理者的级别层次相对应，则由自己进行处理
        if (this.getHandlerLevel() == request.getRequestLevel()) {
            this.handle(request);
        } else {
            // 当前处理者不能胜任，则传递至职责链的下一节点
            if (this.nextHandler != null) {
                System.out.println("当前 处理者-" + this.getHandlerLevel()
                        + " 不足以处理 请求-" + request.getRequestLevel());

                // 这里使用了递归调用
                this.nextHandler.handleRequest(request);
            } else {
                System.out.println("职责链上的所有处理者都不能胜任该请求...");
            }
        }
    }

    // 设置责任链中的下一个处理者
    public void setNextHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    // 获取当前处理者的级别
    protected abstract int getHandlerLevel();

    // 定义链中每个处理者具体的处理方式
    protected abstract void handle(AbstractRequest request);
}

// 具体处理者-01
class Handler01 extends AbstractHandler {
    @Override
    protected int getHandlerLevel() {
        return Levels.LEVEL_01;
    }

    @Override
    protected void handle(AbstractRequest request) {
        System.out.println("处理者-01 处理 " + request.getContent() + "\n");
    }
}

// 具体处理者-02
class Handler02 extends AbstractHandler {
    @Override
    protected int getHandlerLevel() {
        return Levels.LEVEL_02;
    }

    @Override
    protected void handle(AbstractRequest request) {
        System.out.println("处理者-02 处理 " + request.getContent() + "\n");
    }
}

// 具体处理者-03
class Handler03 extends AbstractHandler {
    @Override
    protected int getHandlerLevel() {
        return Levels.LEVEL_03;
    }

    @Override
    protected void handle(AbstractRequest request) {
        System.out.println("处理者-03 处理 " + request.getContent() + "\n");
    }
}
