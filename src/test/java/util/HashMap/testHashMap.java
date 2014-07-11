
package test.java.util.HashMap;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

/**
 * http://blog.csdn.net/vking_wang/article/details/141665931. HashMap的数据结构
 * 数据结构中有数组和链表来实现对数据的存储，但这两者基本上是两个极端。 数组
 * 数组存储区间是连续的，占用内存严重，故空间复杂的很大。但数组的二分查找时间复杂度小，为O(1)；数组的特点是：寻址容易，插入和删除困难； 链表
 * 链表存储区间离散，占用内存比较宽松，故空间复杂度很小，但时间复杂度很大，达O（N）。链表的特点是：寻址困难，插入和删除容易。 哈希表
 * 那么我们能不能综合两者的特性，做出一种寻址容易，插入删除也容易的数据结构？答案是肯定的，这就是我们要提起的哈希表。哈希表（(Hash
 * table）既满足了数据的查找方便，同时不占用太多的内容空间，使用也十分方便。 　　哈希表有多种不同的实现方法，我接下来解释的是最常用的一种方法——
 * 拉链法，我们可以理解为“链表的数组” ，如图：
 * 　　从上图我们可以发现哈希表是由数组+链表组成的，一个长度为16的数组中，每个元素存储的是一个链表的头结点。
 * 那么这些元素是按照什么样的规则存储到数组中呢。一般情况是通过hash
 * (key)%len获得，也就是元素的key的哈希值对数组长度取模得到。比如上述哈希表中，
 * 12%16=12,28%16=12,108%16=12,140%16=12。所以12、28、108以及140都存储在数组下标为12的位置。
 * 　　HashMap其实也是一个线性的数组实现的
 * ,所以可以理解为其存储数据的容器就是一个线性数组。这可能让我们很不解，一个线性的数组怎么实现按键值对来存取数据呢？这里HashMap有做一些处理。
 * 　　首先HashMap里面实现一个静态内部类Entry，其重要的属性有 key , value,
 * next，从属性key,value我们就能很明显的看出来Entry就是HashMap键值对实现的一个基础bean
 * ，我们上面说到HashMap的基础就是一个线性数组，这个数组就是Entry[]，Map里面的内容都保存在Entry[]里面。
 */
public class testHashMap {
    @Test
    public void test() {
        HashMap<String, String> map = new HashMap<String, String>(16, 0.75f);
        for (int i = 0; i < 17; i++) {
            String key = String.valueOf(Math.random() * 10);
            String value = key;
            map.put(key, value);
        }
    }

    @Test
    public void testMyHashMap() {
        int size = 100;
        MyHashMap<String, String> myMap = new MyHashMap<String, String>(size);
        HashMap<String, String> map = new HashMap<String, String>(size, 0.75f);
        String keyToGet[] = new String[size];
        for (int i = 0; i < size; i++) {
            String key = String.valueOf(Math.random() * 10);
            keyToGet[i] = key;
            String value = key;
            myMap.put(key, value);
            map.put(key, value);
        }
        for (int i = 0; i < size; i++) {
            System.out.println(map.get(keyToGet[i]));
            System.out.println(myMap.get(keyToGet[i]));
            assertEquals(map.get(keyToGet[i]), myMap.get(keyToGet[i]));
        }
        myMap.getClass();
    }
}
