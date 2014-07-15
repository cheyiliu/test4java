
package test.design.pattern.structure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

/**
 * http://haolloyin.blog.51cto.com/all/1177454/page/4
 * http://haolloyin.blog.51cto.com/1177454/333257
 * http://haolloyin.blog.51cto.com/1177454/333189
 * 代理模式（Proxy）：为其他对象提供一种代理以控制对这个对象的访问。 使用场景：（选自《设计模式迷你手册》——RedSword软件工作室）
 * 1、远程代理（Remote Proxy ）：为一个对象在不同的地址空间提供局部代理。 2、虚代理（Virtual Proxy
 * ）：根据需要创建开销很大的对象。 3、保护代理（Protection Proxy ）：控制对原始对象的访问。保护代理用于对象应该有不同的访问权限的时候。
 * 4、智能指引（Smart Reference ）：取代了简单的指针，它在访问对象时执行一些附加操作。 动态代理与静态代理的区别：
 * 由程序员创建或由特定工具自动生成源代码
 * ，再对其编译。在程序运行前，代理类的.class文件就已经存在了。动态代理类：在程序运行时，运用反射机制动态创建而成。
 * 无需程序员手工编写它的源代码。动态代理类不仅简化了编程工作，而且提高了软件系统的可扩展性，因为Java 反射机制可以生成任意类型的动态代理类。
 * 哪些地方需要动态代理： 不允许直接访问某些类；对访问要做特殊处理等。或者，要对原方法进行统一的扩展，例如加入日志记录。
 */
public class testProxy {
    @Test
    public void test() {
        // 静态代理
        System.out.println("=静态代理========================================");
        CEO ceo = new CEO();
        Leader leader1 = new Assistant(ceo);
        leader1.sign();

        // 动态代理
        System.out.println("=动态代理========================================");
        // 三种方式
        AssistantHandler ah = new AssistantHandler(ceo);
        Leader leader2 = (Leader) ah.createProxy();
        leader2.sign();

        Leader leader3 = (Leader)
                Proxy.newProxyInstance(CEO.class.getClassLoader(),
                        ceo.getClass().getInterfaces(), ah);
        leader3.sign();

        Leader leader4 = (Leader)
                Proxy.newProxyInstance(Leader.class.getClassLoader(),
                        new Class[] {
                            Leader.class
                        },
                        ah);
        leader4.sign();
        
        
        // Log 代理
        LogHandler logHandler = new LogHandler();  
        UserManager userManager = (UserManager)logHandler.newProxyInstance(new UserManagerImpl());  
        userManager.addUser("00001", "张三");
    }
}

interface Leader {
    public void sign();
}

class CEO implements Leader {

    @Override
    public void sign() {
        System.out.println("CEO签文件");
    }
}

class Assistant implements Leader {
    // 被代理类的实例
    private Leader leader;

    public Assistant(Leader leader) {
        super();
        this.leader = leader;
    }

    @Override
    public void sign() {
        System.out.println("递给领导");
        leader.sign();
        System.out.println("装入袋子，送出");
    }
}

class AssistantHandler implements InvocationHandler {
    private Object targetObject;

    public Object createProxy() {
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(), this);
    };

    public AssistantHandler(CEO ceo) {
        targetObject = ceo;
    }

    /**
     * 覆盖InvocationHandler接口中的invoke()方法 更重要的是，动态代理模式可以使得我们在不改变原来已有的代码结构
     * 的情况下，对原来的“真实方法”进行扩展、增强其功能，并且可以达到 控制被代理对象的行为，下面的before、after就是我们可以进行特殊
     * 代码切入的扩展点了。
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("递给领导");
        result = method.invoke(this.targetObject, args);
        System.out.println("装入袋子，送出");
        return result;
    }

}

interface UserManager {

    public void addUser(String userId, String userName);
}

class UserManagerImpl implements UserManager {

    public void addUser(String userId, String userName) {
        System.out.println("AddUser");
    }
}

class LogHandler implements InvocationHandler {

    private Object targetObject;

    public Object newProxyInstance(Object targetObject) {
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("start-->>" + method.getName());
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        Object ret = null;
        try {
            // 调用目标方法
            ret = method.invoke(targetObject, args);
            System.out.println("success-->>" + method.getName());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error-->>" + method.getName());
            throw e;
        }
        return ret;
    }

}
