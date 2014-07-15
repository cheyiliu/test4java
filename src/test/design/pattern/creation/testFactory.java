
package test.design.pattern.creation;

import org.junit.Test;

/*
 * http://www.cnblogs.com/java-my-life/archive/2012/03/28/2418836.html
 * http://haolloyin.blog.51cto.com/1177454/332802
 * */

/*
 * 总体来说，工厂模式适合：凡是出现了大量的产品需要创建，并且具有共同的接口时，
 * 可以通过工厂方法模式进行创建。在以上的三种模式中，
 * 第一种如果传入的字符串有误，不能正确创建对象，
 * 第三种相对于第二种，不需要实例化工厂类，
 * 所以，大多数情况下，我们会选用第三种——静态工厂方法模式。
 * */

/*
 * 工厂方法模式有一个问题就是，类的创建依赖工厂类，
 * 也就是说，如果想要拓展程序，必须对工厂类进行修改，
 * 这违背了闭包原则，所以，从设计角度考虑，有一定的问题，如何解决？
 * 就用到抽象工厂模式，创建多个工厂类，这样一旦需要增加新的功能，
 * 直接增加新的工厂类就可以了，不需要修改之前的代码。
 * 因为抽象工厂不太好理解，我们先看看图，然后就和代码，就比较容易理解。
 * */
public class testFactory {

    public interface Sender {
        public void Send();
    }

    public static class MailSender implements Sender {
        @Override
        public void Send() {
            System.out.println("this is mailsender!");
        }
    }

    public static class SmsSender implements Sender {

        @Override
        public void Send() {
            System.out.println("this is sms sender!");
        }
    }

    public class SendFactory {

        public Sender produce(String type) {
            if ("mail".equals(type)) {
                return new MailSender();
            } else if ("sms".equals(type)) {
                return new SmsSender();
            } else {
                System.err.println("请输入正确的类型!");
                return null;
            }
        }
    }

    public class SendFactory2 {
        public Sender produceMail() {
            return new MailSender();
        }

        public Sender produceSms() {
            return new SmsSender();
        }
    }

    public static class SendFactory3 {

        public static Sender produceMail() {
            return new MailSender();
        }

        public static Sender produceSms() {
            return new SmsSender();
        }
    }

    public interface Provider {
        public Sender produce();
    }

    public class SendSmsFactory implements Provider {

        @Override
        public Sender produce() {
            return new SmsSender();
        }
    }

    public class SendMailFactory implements Provider {

        @Override
        public Sender produce() {
            return new MailSender();
        }
    }

    @Test
    public void test() {
        // 一般工厂
        SendFactory2 factory = new SendFactory2();
        Sender sender = factory.produceMail();
        sender.Send();

        // 静态方法工厂
        SendFactory3.produceMail().Send();
        SendFactory3.produceSms().Send();

        // 抽象工厂
        Provider provider = new SendMailFactory();
        Sender sender123 = provider.produce();
        sender123.Send();
    }

    public interface Cpu {
        public void calculate();
    }

    public static class IntelCpu implements Cpu {
        /**
         * CPU的针脚数
         */
        private int pins = 0;

        public IntelCpu(int pins) {
            this.pins = pins;
        }

        @Override
        public void calculate() {
            // TODO Auto-generated method stub
            System.out.println("Intel CPU的针脚数：" + pins);
        }

    }

    public static class AmdCpu implements Cpu {
        /**
         * CPU的针脚数
         */
        private int pins = 0;

        public AmdCpu(int pins) {
            this.pins = pins;
        }

        @Override
        public void calculate() {
            // TODO Auto-generated method stub
            System.out.println("AMD CPU的针脚数：" + pins);
        }
    }

    public interface Mainboard {
        public void installCPU();
    }

    public static class IntelMainboard implements Mainboard {
        /**
         * CPU插槽的孔数
         */
        private int cpuHoles = 0;

        /**
         * 构造方法，传入CPU插槽的孔数
         * 
         * @param cpuHoles
         */
        public IntelMainboard(int cpuHoles) {
            this.cpuHoles = cpuHoles;
        }

        @Override
        public void installCPU() {
            // TODO Auto-generated method stub
            System.out.println("Intel主板的CPU插槽孔数是：" + cpuHoles);
        }

    }

    public static class AmdMainboard implements Mainboard {
        /**
         * CPU插槽的孔数
         */
        private int cpuHoles = 0;

        /**
         * 构造方法，传入CPU插槽的孔数
         * 
         * @param cpuHoles
         */
        public AmdMainboard(int cpuHoles) {
            this.cpuHoles = cpuHoles;
        }

        @Override
        public void installCPU() {
            // TODO Auto-generated method stub
            System.out.println("AMD主板的CPU插槽孔数是：" + cpuHoles);
        }
    }

    public static class CpuFactory {
        public static Cpu createCpu(int type) {
            Cpu cpu = null;
            if (type == 1) {
                cpu = new IntelCpu(755);
            } else if (type == 2) {
                cpu = new AmdCpu(938);
            }
            return cpu;
        }
    }

    public static class MainboardFactory {
        public static Mainboard createMainboard(int type) {
            Mainboard mainboard = null;
            if (type == 1) {
                mainboard = new IntelMainboard(755);
            } else if (type == 2) {
                mainboard = new AmdMainboard(938);
            }
            return mainboard;
        }
    }

    public class ComputerEngineer {
        /**
         * 定义组装机需要的CPU
         */
        private Cpu cpu = null;
        /**
         * 定义组装机需要的主板
         */
        private Mainboard mainboard = null;

        public void makeComputer(int cpuType, int mainboard) {
            /**
             * 组装机器的基本步骤
             */
            // 1:首先准备好装机所需要的配件
            prepareHardwares(cpuType, mainboard);
            // 2:组装机器
            // 3:测试机器
            // 4：交付客户
        }

        private void prepareHardwares(int cpuType, int mainboard) {
            // 这里要去准备CPU和主板的具体实现，为了示例简单，这里只准备这两个
            // 可是，装机工程师并不知道如何去创建，怎么办呢？

            // 直接找相应的工厂获取
            this.cpu = CpuFactory.createCpu(cpuType);
            this.mainboard = MainboardFactory.createMainboard(mainboard);

            // 测试配件是否好用
            this.cpu.calculate();
            this.mainboard.installCPU();
        }
    }

    @Test
    public void test2() {
        ComputerEngineer cf = new ComputerEngineer();
        cf.makeComputer(1, 2);
    }

    public interface AbstractFactory {
        /**
         * 创建CPU对象
         * 
         * @return CPU对象
         */
        public Cpu createCpu();

        /**
         * 创建主板对象
         * 
         * @return 主板对象
         */
        public Mainboard createMainboard();
    }

    public class IntelFactory implements AbstractFactory {

        @Override
        public Cpu createCpu() {
            // TODO Auto-generated method stub
            return new IntelCpu(755);
        }

        @Override
        public Mainboard createMainboard() {
            // TODO Auto-generated method stub
            return new IntelMainboard(755);
        }

    }

    public class AmdFactory implements AbstractFactory {

        @Override
        public Cpu createCpu() {
            // TODO Auto-generated method stub
            return new IntelCpu(938);
        }

        @Override
        public Mainboard createMainboard() {
            // TODO Auto-generated method stub
            return new IntelMainboard(938);
        }

    }

    public class ComputerEngineer2 {
        /**
         * 定义组装机需要的CPU
         */
        private Cpu cpu = null;
        /**
         * 定义组装机需要的主板
         */
        private Mainboard mainboard = null;

        public void makeComputer(AbstractFactory af) {
            /**
             * 组装机器的基本步骤
             */
            // 1:首先准备好装机所需要的配件
            prepareHardwares(af);
            // 2:组装机器
            // 3:测试机器
            // 4：交付客户
        }

        private void prepareHardwares(AbstractFactory af) {
            // 这里要去准备CPU和主板的具体实现，为了示例简单，这里只准备这两个
            // 可是，装机工程师并不知道如何去创建，怎么办呢？

            // 直接找相应的工厂获取
            this.cpu = af.createCpu();
            this.mainboard = af.createMainboard();

            // 测试配件是否好用
            this.cpu.calculate();
            this.mainboard.installCPU();
        }
    }

    @Test
    public void test3() {
        // 创建装机工程师对象
        ComputerEngineer2 cf = new ComputerEngineer2();
        // 客户选择并创建需要使用的产品对象
        AbstractFactory af = new IntelFactory();
        // 告诉装机工程师自己选择的产品，让装机工程师组装电脑
        cf.makeComputer(af);
    }
}
