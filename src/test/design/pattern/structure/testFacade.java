
package test.design.pattern.structure;

import org.junit.Test;

/**
 * http://www.cnblogs.com/xinhuaxuan/p/3487755.html 外观模式的本质是：封装交互，简化调用。
 */
public class testFacade {
    @Test
    public void test1() {
        /**
         * 客户端为了使用这个功能，需要与子系统内的多个模块交互。 这对于客户端而言，相当麻烦，使得客户端不能简单的使用系统功能，而且，如果
         * 其中的某个模块方式了变化，还可能会引起客户端也要随着变化。
         */
        new GovernmentA().dealWith();
        new GovernmentB().dealWith();
        new GovernmentC().dealWith();
    }

    @Test
    public void test2() {
        /**
         * 外观模式的目的： 外观模式的目的不是给予子系统添加新的功能接口，而是为了让外部减少与子系统内多个模块的交互，松散耦合，
         * 从而让外部能够更简单地使用子系统。 使用外观模式和不使用外观模式相比有何变化？
         * 看到Facade的实现，可能有人说，这不就是把原来的客户端的代码搬到Facade里面吗？没有什么变化？
         * 但是实质上是发生了变化，因为Facade是位于部门A,B,C模块组成的系统这边，那么它就相当于屏蔽了外部客户端和系统内部模块的将会，
         * 从而把部门A,B,C模块组合成为一个整体对外，不但方便了客户端的调用，而且封装了系统内部的细节功能。
         * 外观模式的本质是：封装交互，简化调用。
         */
        new Facade().deailWith();
    }
}

class GovernmentA
{
    public void dealWith()
    {
        System.out.println("部门A的章盖好了");
    }
}

class GovernmentB
{
    public void dealWith()
    {
        System.out.println("部门B的章盖好了");
    }
}

class GovernmentC
{
    public void dealWith()
    {
        System.out.println("部门C的章盖好了");
    }
}

interface GovernmentAService
{
    public void dealWith();
}

class GovernmentAServiceImpl implements GovernmentAService
{
    @Override
    public void dealWith()
    {
        System.out.println("部门A的章盖好了...");
    }
}

interface GovernmentBService
{
    public void dealWith();
}

class GovernmentBServiceImpl implements GovernmentBService
{
    @Override
    public void dealWith()
    {
        System.out.println("部门B的章盖好了...");
    }
}

interface GovernmentCService
{
    public void dealWith();
}

class GovernmentCServiceImpl implements GovernmentCService
{
    @Override
    public void dealWith()
    {
        System.out.println("部门C章已经盖好了...");
    }
}

class Facade
{
    public void deailWith()
    {
        GovernmentAService a = new GovernmentAServiceImpl();
        a.dealWith();

        GovernmentBService b = new GovernmentBServiceImpl();
        b.dealWith();

        GovernmentCService c = new GovernmentCServiceImpl();
        c.dealWith();
    }
}
