
package test.java.util.HashMap;

public class MyHashMap<K, V> {
    class Entry<K, V> {

        public Entry(K key, V value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        K key;
        V value;
        Entry next;
    }

    private transient Entry[] table;

    public MyHashMap(int size) {
        table = new Entry[size];
    }

    public void put(K key, V value) {
        int index = indexOf(key);
        if (table[index] == null) {
            table[index] = new Entry<K, V>(key, value, null);
        } else {
            for (Entry e = table[index]; e != null; e = e.next) {
                // 如果key在链表中已存在，则替换为新value
                if ((e.key == key || key.equals(e.key))) {
                    Object oldValue = e.value;

                    e.value = value;
                    return;
                }
            }
            Entry old = table[index];
            table[index] = new Entry<K, V>(key, value, old);
        }

    }

    public V get(K key) {
        // 先定位到数组元素，再遍历该元素处的链表
        for (Entry<K, V> e = table[indexOf(key)]; e != null; e = e.next) {
            Object k;
            if (e.key == key || key.equals(e.key))
                return e.value;
        }
        return null;

    }

    private int indexOf(K key) {
        return key.hashCode() % table.length < 0 ?
                key.hashCode() % table.length + table.length :
                key.hashCode() % table.length;
    }
}
