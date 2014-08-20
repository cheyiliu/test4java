
package test.design.pattern.behavior;

/**
 * http://www.cnblogs.com/devinzhang/archive/2012/01/06/2315235.html
 */
public class testCommand2 {

    public static void main(String[] args) {
        // 命令接收者
        Tv myTv = new Tv();
        // 开机命令
        CommandOn on = new CommandOn(myTv);
        // 关机命令
        CommandOff off = new CommandOff(myTv);
        // 频道切换命令
        CommandChange channel = new CommandChange(myTv, 2);
        // 命令控制对象
        Control control = new Control(on, off, channel);

        // 开机
        control.turnOn();
        // 切换频道
        control.changeChannel();
        // 关机
        control.turnOff();
    }

    // 命令接收者
    static class Tv {
        public int currentChannel = 0;

        public void turnOn() {
            System.out.println("The televisino is on.");
        }

        public void turnOff() {
            System.out.println("The television is off.");
        }

        public void changeChannel(int channel) {
            this.currentChannel = channel;
            System.out.println("Now TV channel is " + channel);
        }
    }

    // 执行命令的接口
    static interface Command {
        void execute();
    }

    // 开机命令
    static class CommandOn implements Command {
        private Tv myTv;

        public CommandOn(Tv tv) {
            myTv = tv;
        }

        public void execute() {
            myTv.turnOn();
        }
    }

    // 关机命令
    static class CommandOff implements Command {
        private Tv myTv;

        public CommandOff(Tv tv) {
            myTv = tv;
        }

        public void execute() {
            myTv.turnOff();
        }
    }

    // 频道切换命令
    static class CommandChange implements Command {
        private Tv myTv;

        private int channel;

        public CommandChange(Tv tv, int channel) {
            myTv = tv;
            this.channel = channel;
        }

        public void execute() {
            myTv.changeChannel(channel);
        }
    }

    // 可以看作是遥控器吧
    static class Control {
        private Command onCommand, offCommand, changeChannel;

        public Control(Command on, Command off, Command channel) {
            onCommand = on;
            offCommand = off;
            changeChannel = channel;
        }

        public void turnOn() {
            onCommand.execute();
        }

        public void turnOff() {
            offCommand.execute();
        }

        public void changeChannel() {
            changeChannel.execute();
        }
    }
}
