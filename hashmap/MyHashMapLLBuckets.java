package hashmap;

import java.util.LinkedList;
import java.util.Collection;

/**
 * Hash Table with Linked List buckets
 * @author Neil Kulkarni
 */
public class MyHashMapLLBuckets<K, V> extends MyHashMap<K, V> {

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
    public MyHashMapLLBuckets() {
        super();
    }

    /**
     * Constructor that creates a backing array of initialSize
     * and default load factor
     *
     * @param initialSize initial size of backing array
     */
    public MyHashMapLLBuckets(int initialSize) {
        super(initialSize);
    }

    /**
     * Constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMapLLBuckets(int initialSize, double maxLoad) {
        super(initialSize, maxLoad);
    }

    @Override
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }
}
