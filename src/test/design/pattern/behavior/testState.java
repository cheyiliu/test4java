
package test.design.pattern.behavior;

public class testState {
    public static void main(String[] args) {

        // 假设路灯开始是绿灯
        State state = new GreenState();
        TrafficLight light = new TrafficLight(state);
        light.work();

    }
}

interface State {

    public void change(TrafficLight light);
}

class GreenState implements State {
    private static final Long SLEEP_TIME = 2000L;

    @Override
    public void change(TrafficLight light) {

        System.out.println("现在是绿灯，可以通行");
        // 绿灯亮1秒
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        light.setState(new YellowState());
    }

}

class YellowState implements State {
    private static final Long SLEEP_TIME = 500L;

    @Override
    public void change(TrafficLight light) {

        System.out.println("现在是黄灯，警示");
        // 红灯亮0.5秒
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        light.setState(new RedState());
    }

}

class RedState implements State {
    private static final Long SLEEP_TIME = 1000L;

    @Override
    public void change(TrafficLight light) {

        System.out.println("现在是红灯，禁止通行");
        // 红灯亮1秒
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        light.setState(new GreenState());
    }

}

class TrafficLight {

    private State state;

    private void change() {
        state.change(this);
    }

    public void work() {
        while (true) {
            change();
        }
    }

    public TrafficLight(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
