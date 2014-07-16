
package test.design.pattern.structure;

import org.junit.Test;

/**
 * 类的层次图-桥
 * 
 * http://www.cnblogs.com/forlina/archive/2011/06/22/2087350.html
 * 当一个类的子类需要扩展时，如果要在两个维度进行扩展，那么就可以使用Bridge模式。 
 * 
 * http://www.importnew.com/6857.html
 * 简单来讲，桥接模式是一个两层的抽象。 桥接模式是用于“把抽象和实现分开，这样它们就能独立变化”。
 * 桥接模式使用了封装、聚合，可以用继承将不同的功能拆分为不同的类。 
 * 
 * http://en.wikipedia.org/wiki/Bridge_pattern
 * The bridge pattern is a design pattern used in software engineering which is
 * meant to
 * "decouple an abstraction from its implementation so that the two can vary independently"
 * .[1] The bridge uses encapsulation, aggregation, and can use inheritance to
 * separate responsibilities into different classes.
 * 
 * http://blog.csdn.net/aaaaaaaa0705/article/details/6284292
 * http://www.tuicool.com/articles/uuiUza
 */
public class testBridge {
    @Test
    public void test() {
        ColorfulPencel bigBluePencel = new BigColorfulPencel(new Blue());
        bigBluePencel.draw();

        ColorfulPencel smallRedPencel = new SmallColorfulPencel(new Red());
        smallRedPencel.draw();

        ColorfulPencel middleGreenPencel = new MiddleColorfulPencel(new Green());
        middleGreenPencel.draw();

        //
        LogitechRemoteControl lrc = new LogitechRemoteControl(new SonyTV());
        lrc.setChannelKeyboard(100);

        //
        Shape[] shapes = new Shape[] {
                new CircleShape(1, 2, 3, new DrawingAPI1()),
                new CircleShape(5, 7, 11, new DrawingAPI2()),
        };

        for (Shape shape : shapes) {
            shape.draw();
            shape.resizeByPercentage(2.5);
            shape.draw();
        }

        //
        Engine engine2000 = new Engine2000();
        Engine engine2200 = new Engine2200();

        Car bus = new Bus(engine2000);
        bus.installEngine();

        Car jeep = new Jeep(engine2200);
        jeep.installEngine();
    }
}

interface IColor {
    public String getColor();
}

class Green implements IColor {

    @Override
    public String getColor() {
        return "Green color";
    }

}

class Red implements IColor {

    @Override
    public String getColor() {
        return "Red color";
    }
}

class Blue implements IColor {

    @Override
    public String getColor() {
        return "Blue color";
    }
}

abstract class ColorfulPencel {
    protected IColor mColor;

    public ColorfulPencel(IColor mColor) {
        this.mColor = mColor;
    }

    abstract public void draw();
}

class BigColorfulPencel extends ColorfulPencel {

    public BigColorfulPencel(IColor mColor) {
        super(mColor);
    }

    @Override
    public void draw() {
        System.out.println("Big pencel with " + mColor.getColor());
    }
}

class MiddleColorfulPencel extends ColorfulPencel {

    public MiddleColorfulPencel(IColor mColor) {
        super(mColor);
    }

    @Override
    public void draw() {
        System.out.println("Middle pencel with " + mColor.getColor());
    }
}

class SmallColorfulPencel extends ColorfulPencel {

    public SmallColorfulPencel(IColor mColor) {
        super(mColor);
    }

    @Override
    public void draw() {
        System.out.println("Small pencel with " + mColor.getColor());
    }
}

interface ITV {
    public void on();

    public void off();

    public void switchChannel(int channel);
}

class SamsungTV implements ITV {
    @Override
    public void on() {
        System.out.println("Samsung is turned on.");
    }

    @Override
    public void off() {
        System.out.println("Samsung is turned off.");
    }

    @Override
    public void switchChannel(int channel) {
        System.out.println("Samsung: channel - " + channel);
    }
}

class SonyTV implements ITV {

    @Override
    public void on() {
        System.out.println("Sony is turned on.");
    }

    @Override
    public void off() {
        System.out.println("Sony is turned off.");
    }

    @Override
    public void switchChannel(int channel) {
        System.out.println("Sony: channel - " + channel);
    }
}

abstract class AbstractRemoteControl {
    /**
     * @uml.property name="tv"
     * @uml.associationEnd
     */
    private ITV tv;

    public AbstractRemoteControl(ITV tv) {
        this.tv = tv;
    }

    public void turnOn() {
        tv.on();
    }

    public void turnOff() {
        tv.off();
    }

    public void setChannel(int channel) {
        tv.switchChannel(channel);
    }
}

class LogitechRemoteControl extends AbstractRemoteControl {

    public LogitechRemoteControl(ITV tv) {
        super(tv);
    }

    public void setChannelKeyboard(int channel) {
        setChannel(channel);
        System.out.println("Logitech use keyword to set channel.");
    }
}

/** "Implementor" */
interface DrawingAPI {
    public void drawCircle(double x, double y, double radius);
}

/** "ConcreteImplementor" 1/2 */
class DrawingAPI1 implements DrawingAPI {
    public void drawCircle(double x, double y, double radius) {
        System.out.printf("API1.circle at %f:%f radius %f\n", x, y, radius);
    }
}

/** "ConcreteImplementor" 2/2 */
class DrawingAPI2 implements DrawingAPI {
    public void drawCircle(double x, double y, double radius) {
        System.out.printf("API2.circle at %f:%f radius %f\n", x, y, radius);
    }
}

/** "Abstraction" */
abstract class Shape {
    protected DrawingAPI drawingAPI;

    protected Shape(DrawingAPI drawingAPI) {
        this.drawingAPI = drawingAPI;
    }

    public abstract void draw(); // low-level

    public abstract void resizeByPercentage(double pct); // high-level
}

/** "Refined Abstraction" */
class CircleShape extends Shape {
    private double x, y, radius;

    public CircleShape(double x, double y, double radius, DrawingAPI drawingAPI) {
        super(drawingAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    // low-level i.e. Implementation specific
    public void draw() {
        drawingAPI.drawCircle(x, y, radius);
    }

    // high-level i.e. Abstraction specific
    public void resizeByPercentage(double pct) {
        radius *= pct;
    }
}

/** "Client" */
class BridgePattern {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[] {
                new CircleShape(1, 2, 3, new DrawingAPI1()),
                new CircleShape(5, 7, 11, new DrawingAPI2()),
        };

        for (Shape shape : shapes) {
            shape.resizeByPercentage(2.5);
            shape.draw();
        }
    }
}

interface Engine {

    /**
     * 安装发动引擎
     */
    public void installEngine();
}

class Engine2000 implements Engine {

    @Override
    public void installEngine() {
        System.out.println("安装2000CC发动引擎");
    }

}

class Engine2200 implements Engine {

    @Override
    public void installEngine() {
        System.out.println("安装2200CC发动引擎");
    }

}

abstract class Car {

    private Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public abstract void installEngine();
}

class Bus extends Car {

    public Bus(Engine engine) {
        super(engine);
    }

    @Override
    public void installEngine() {
        System.out.print("Bus:");
        this.getEngine().installEngine();
    }

}

class Jeep extends Car {

    public Jeep(Engine engine) {
        super(engine);
    }

    @Override
    public void installEngine() {
        System.out.print("Jeep:");
        this.getEngine().installEngine();
    }

}
