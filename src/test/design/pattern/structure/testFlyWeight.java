
package test.design.pattern.structure;

import static org.junit.Assert.*;

import java.awt.Color;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.junit.Test;

/**
 * http://www.javaworld.com/article/2073632/build-ci-sdlc/make-your-apps-fly.
 * html http://zh.wikipedia.org/zh/%E4%BA%AB%E5%85%83%E6%A8%A1%E5%BC%8F
 * http://langgufu.iteye.com/blog/2090516
 * 解释一下概念：也就是说在一个系统中如果有多个相同的对象，那么只共享一份就可以了
 * ，不必每个都去实例化一个对象。比如说一个文本系统，每个字母定一个对象，那么大小写字母一共就是52个
 * ，那么就要定义52个对象。如果有一个1M的文本，那么字母是何其的多
 * ，如果每个字母都定义一个对象那么内存早就爆了。那么如果要是每个字母都共享一个对象，那么就大大节约了资源。
 * 　　在Flyweight模式中，由于要产生各种各样的对象
 * ，所以在Flyweight(享元)模式中常出现Factory模式。Flyweight的内部状态是用来共享的,Flyweight
 * factory负责维护一个对象存储池（Flyweight
 * Pool）来存放内部状态的对象。Flyweight模式是一个提高程序效率和性能的模式,会大大加快程序的运行速度.应用场合很多
 * 在JAVA语言中，String类型就是使用了享元模式。String对象是final类型，对象一旦创建就不可改变。在JAVA中字符串常量都是存在常量池中的，
 * JAVA会确保一个字符串常量在常量池中只有一个拷贝。String a="abc"，其中"abc"就是一个字符串常量。 享元模式的优缺点
 * 　　享元模式的优点在于它大幅度地降低内存中对象的数量。但是，它做到这一点所付出的代价也是很高的：
 * 　　●　　享元模式使得系统更加复杂。为了使对象可以共享，需要将一些状态外部化，这使得程序的逻辑复杂化。
 * 　　●　　享元模式将享元对象的状态外部化，而读取外部状态使得运行时间稍微变长。
 */
public class testFlyWeight {
    @Test
    public void test() {
        System.out.println("---------------===========----------------");
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight fly = factory.generate(new Character('a'));
        fly.operation("First Call");

        fly = factory.generate(new Character('b'));
        fly.operation("Second Call");

        fly = factory.generate(new Character('a'));
        fly.operation("Third Call");
    }

    @Test
    public void test2() {
        System.out.println("---------------===========----------------");
        List<Character> compositeState = new ArrayList<Character>();
        compositeState.add('a');
        compositeState.add('b');
        compositeState.add('c');
        compositeState.add('a');
        compositeState.add('b');

        CompositeFlyweightFactory flyFactory = new CompositeFlyweightFactory();
        Flyweight compositeFly1 = flyFactory.generate(compositeState);
        Flyweight compositeFly2 = flyFactory.generate(compositeState);
        compositeFly1.operation("Composite Call");

        System.out.println("---------------------------------");
        System.out.println("复合享元模式是否可以共享对象：" + (compositeFly1 == compositeFly2));

        Character state = 'a';
        Flyweight fly1 = flyFactory.factory(state);
        Flyweight fly2 = flyFactory.factory(state);
        System.out.println("单纯享元模式是否可以共享对象：" + (fly1 == fly2));
    }

    @Test
    public void test3() {
        System.out.println("---------------===========----------------");
        CharFactory charFactory = new CharFactory();
        Word word = charFactory.generate("ABCABCABCABC");
        word.printSelf();
    }

    @Test
    public void test4() {
        System.out.println("---------------===========----------------");
        FontData fontData1 = FontData.create(10, "楷体", Color.blue, FontEffect.BOLD);
        FontData fontData2 = FontData.create(10, "楷体", Color.blue, FontEffect.BOLD);
        assertEquals(fontData1, fontData2);
    }

    @Test
    public void testString() {
        System.out.println("---------------===========----------------");
        String fly = "fly", weight = "weight";
        String fly2 = "fly", weight2 = "weight";
        System.out.println(fly == fly2); // fly and fly2 refer to the same
                                         // String instance
        System.out.println(weight == weight2); // weight and weight2 also refer
                                               // to
                                               // the same String instance
        String distinctString = fly + weight;
        System.out.println(distinctString == "flyweight"); // flyweight and
                                                           // "flyweight" do not
                                                           // refer to the same
                                                           // instance
        String flyweight = (fly + weight).intern();
        System.out.println(flyweight == "flyweight"); // The intern() method
                                                      // returns a flyweight
    }
}

interface Flyweight {
    // 一个示意性方法，参数state是外蕴状态
    public void operation(String state);
}

class ConcreteFlyweight implements Flyweight {
    private Character intrinsicState = null;

    /**
     * 构造函数，内蕴状态作为参数传入
     * 
     * @param state
     */
    public ConcreteFlyweight(Character state) {
        this.intrinsicState = state;
    }

    /**
     * 外蕴状态作为参数传入方法中，改变方法的行为， 但是并不改变对象的内蕴状态。
     */
    @Override
    public void operation(String state) {
        System.out.println("Intrinsic State = " + this.intrinsicState);
        System.out.println("Extrinsic State = " + state);
    }

}

class ConcreteCompositeFlyweight implements Flyweight {

    private Map<Character, Flyweight> files = new HashMap<Character, Flyweight>();

    /**
     * 增加一个新的单纯享元对象到聚集中
     */
    public void add(Character key, Flyweight fly) {
        files.put(key, fly);
    }

    /**
     * 外蕴状态作为参数传入到方法中
     */
    @Override
    public void operation(String state) {
        Flyweight fly = null;
        for (Object o : files.keySet()) {
            fly = files.get(o);
            fly.operation(state);
        }

    }

}

class FlyweightFactory {
    private Map<Character, Flyweight> files = new HashMap<Character, Flyweight>();

    public Flyweight generate(Character state) {
        // 先从缓存中查找对象
        Flyweight fly = files.get(state);
        if (fly == null) {
            // 如果对象不存在则创建一个新的Flyweight对象
            fly = new ConcreteFlyweight(state);
            // 把这个新的Flyweight对象添加到缓存中
            files.put(state, fly);
        }
        return fly;
    }
}

class CompositeFlyweightFactory {
    private Map<Character, Flyweight> files = new HashMap<Character, Flyweight>();

    /**
     * 复合享元工厂方法
     */
    public Flyweight generate(List<Character> compositeState) {
        ConcreteCompositeFlyweight compositeFly = new ConcreteCompositeFlyweight();

        for (Character state : compositeState) {
            compositeFly.add(state, this.factory(state));
        }

        return compositeFly;
    }

    /**
     * 单纯享元工厂方法
     */
    public Flyweight factory(Character state) {
        // 先从缓存中查找对象
        Flyweight fly = files.get(state);
        if (fly == null) {
            // 如果对象不存在则创建一个新的Flyweight对象
            fly = new ConcreteFlyweight(state);
            // 把这个新的Flyweight对象添加到缓存中
            files.put(state, fly);
        }
        return fly;
    }
}

interface FlyweightChar {
    public void print();
}

class A implements FlyweightChar {

    @Override
    public void print() {
        System.out.print("A");
    }

}

class B implements FlyweightChar {

    @Override
    public void print() {
        System.out.print("B");
    }

}

class C implements FlyweightChar {

    @Override
    public void print() {
        System.out.print("C");
    }

}

class CharFactory {
    Map<String, FlyweightChar> mCache = new HashMap<String, FlyweightChar>();

    public FlyweightChar generate(char charchar) {
        String key = String.valueOf(charchar).toUpperCase();
        FlyweightChar flyweightChar = mCache.get(key);
        if (flyweightChar == null) {
            if (key.equals("A")) {
                flyweightChar = new A();
            } else if (key.equals("B")) {
                flyweightChar = new B();
            } else {
                flyweightChar = new C();
            }
            mCache.put(key, flyweightChar);
        }
        return flyweightChar;
    }

    public Word generate(String str) {
        List<FlyweightChar> chars = new ArrayList<FlyweightChar>();
        for (int i = 0; i < str.length(); i++) {
            chars.add(generate(str.charAt(i)));
        }
        return new Word(chars);
    }
}

class Word {
    List<FlyweightChar> mChars;

    public Word(List<FlyweightChar> chars) {
        this.mChars = chars;
    }

    public void printSelf() {
        for (FlyweightChar iterable_element : mChars) {
            iterable_element.print();
        }
        System.out.println();
    }
}

enum FontEffect {
    BOLD, ITALIC, SUPERSCRIPT, SUBSCRIPT, STRIKETHROUGH
}

final class FontData {
    /**
     * A weak hash map will drop unused references to FontData. Values have to
     * be wrapped in WeakReferences, because value objects in weak hash map are
     * held by strong references.
     */
    private static final WeakHashMap<FontData, WeakReference<FontData>> FLY_WEIGHT_DATA =
            new WeakHashMap<FontData, WeakReference<FontData>>();
    private final int pointSize;
    private final String fontFace;
    private final Color color;
    private final Set<FontEffect> effects;

    private FontData(int pointSize, String fontFace, Color color, EnumSet<FontEffect> effects) {
        this.pointSize = pointSize;
        this.fontFace = fontFace;
        this.color = color;
        this.effects = Collections.unmodifiableSet(effects);
    }

    public static FontData create(int pointSize, String fontFace, Color color,
            FontEffect... effects) {
        EnumSet<FontEffect> effectsSet = EnumSet.noneOf(FontEffect.class);
        for (FontEffect fontEffect : effects) {
            effectsSet.add(fontEffect);
        }
        // We are unconcerned with object creation cost, we are reducing overall
        // memory consumption
        FontData data = new FontData(pointSize, fontFace, color, effectsSet);

        // Retrieve previously created instance with the given values if it
        // (still) exists
        WeakReference<FontData> ref = FLY_WEIGHT_DATA.get(data);
        FontData result = (ref != null) ? ref.get() : null;

        // Store new font data instance if no matching instance exists
        if (result == null) {
            FLY_WEIGHT_DATA.put(data, new WeakReference<FontData>(data));
            result = data;
        }
        // return the single immutable copy with the given values
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FontData) {
            if (obj == this) {
                return true;
            }
            FontData other = (FontData) obj;
            return other.pointSize == pointSize && other.fontFace.equals(fontFace)
                    && other.color.equals(color) && other.effects.equals(effects);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (pointSize * 37 + effects.hashCode() * 13) * fontFace.hashCode();
    }

    // Getters for the font data, but no setters. FontData is immutable.
}
