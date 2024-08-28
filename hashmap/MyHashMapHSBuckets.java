package hashmap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collection;

/**
 * Hash Table with buckets that are Hash Sets (trippy!)
 * @author Neil Kulkarni
 */
public class MyHashMapHSBuckets<K, V> extends MyHashMap<K, V> {

    /**
     * Constructor that creates a backing array with default
     * initial size and load factor
     */
    public static void main(String[] args) {
              MyHashMapALBuckets<String, Integer> bucket = new MyHashMapALBuckets<String, Integer>();
        bucket.put("a", 1);
        bucket.put("b", 2);
        for (String s : bucket) {
            System.out.println(s);

        }
    }

    public MyHashMapHSBuckets() {
        super();
    }

    /**
     * Constructor that creates a backing array of initialSize
     * and default load factor
     *
     * @param initialSize initial size of backing array
     */
    public MyHashMapHSBuckets(int initialSize) {
        super(initialSize);
    }

    /**
     * Constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMapHSBuckets(int initialSize, double maxLoad) {
        super(initialSize, maxLoad);
    }

    @Override
    protected Collection<Node> createBucket() {
        return new HashSet<>();
    }
}

