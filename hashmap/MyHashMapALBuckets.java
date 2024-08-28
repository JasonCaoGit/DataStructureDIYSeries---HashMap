package hashmap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Hash Table with Array List buckets
 * @author Neil Kulkarni
 */
public class MyHashMapALBuckets<K, V> extends MyHashMap<K, V> {

    public static void main(String[] args) {
        MyHashMapALBuckets<String, Integer> bucket = new MyHashMapALBuckets<String, Integer>();
        bucket.put("a", 1);
        bucket.put("b", 2);
        for (String s : bucket) {
            System.out.println(s);

        }
    }

    /**
     * Constructor that creates a backing array with default
     * initial size and load factor
     */
    public MyHashMapALBuckets() {
        super();
    }

    /**
     * Constructor that creates a backing array of initialSize
     * and default load factor
     *
     * @param initialSize initial size of backing array
     */
    public MyHashMapALBuckets(int initialSize) {
        super(initialSize);
    }

    /**
     * Constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMapALBuckets(int initialSize, double maxLoad) {
        super(initialSize, maxLoad);
    }

    @Override
    protected Collection<Node> createBucket() {
        return new ArrayList<Node>();
    }

    @Override
   public Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = new ArrayList[tableSize];


        return table;
    }
}
