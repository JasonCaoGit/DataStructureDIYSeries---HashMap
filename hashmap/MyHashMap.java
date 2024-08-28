package hashmap;

import java.util.*;

import java.util.Random;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Zhuoyuan Cao
 */
public class MyHashMap<K, V> implements Map61B<K, V> {



       public static void main(String[] args) {
       MyHashMap<String, String> myHashMap = new MyHashMap<String, String>();
        myHashMap.put("hi", "hello");


        myHashMap.put("this one", "hello");
        myHashMap.put("summary", "hello");
        myHashMap.put("summary", "hi");

         for (String key : myHashMap) {
            System.out.println(key);
        }

//           MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>();
//           Random rand = new Random();
//           for (int i = 0; i < 10000; i++) {
//               Integer key = rand.nextInt(1000000);
//               Integer value = rand.nextInt(1000000);
//               myHashMap.put(key, value);
//           }
//           System.out.println(myHashMap.size());

//           HashMap<Integer, Integer> myHashMap2 = new HashMap<>();
//            for (int i = 0; i < 10000; i++) {
//               Integer key = rand.nextInt(10000);
//               Integer value = rand.nextInt(10000);
//               myHashMap2.put(key, value);
//           }
//           System.out.println(myHashMap2.size());

    }

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size;
    private double loadFactor;
    private int nodeCounts;
    private double currentLoadFactor;
    // You should probably define some more!
    /*
    * Increase the size of your hashmap when the load factor exceeds the loadFactor
    * loadFactor = N/M, or elements/buckets.
    * Default initial size =16, default load factor =0.75
    *
    *
    *
    * */

    private final static int DEFAULT_SIZE = 16;
    private final static double DEFAULT_LOAD_FACTOR = 0.75;


      //Always call create bucket when you are referencing a bucket box to a bucket of nodes
    public static void throwException() throws UnsupportedOperationExceptions {
        throw new UnsupportedOperationExceptions("Unsupported!");
    }
    @Override
    public HashMapIterator iterator() {
        return new HashMapIterator();

    }

    private class HashMapIterator implements Iterator {

        private int currentBucketIndex;
        private Iterator<Node> currentBucketIterator;

        /*
         * I have an iterator for the linkedlist
         * I wanna find the correct iterator for the index
         * has
         * */
//private Iterator<Node> findNextNonEmptyBucket(int startIndex) {
//    for (int i = startIndex; i < buckets.length; i++) {
//        if (buckets[i] != null ) {
//            currentBucketIndex = i;
//            return buckets[i].iterator();
//        }
//    }
//    return null;
//}
        //You have to update the currentbucketindex too
        private Iterator<Node> findNextNonEmptyBucket(int startIndex) {
            for (int i = startIndex; i < buckets.length; i++) {
                if(buckets[i] != null) {
                    currentBucketIndex = i;
                    return buckets[i].iterator();
                }
            }
            return null;
        }


        //When constructing the outside iterator, we need to find the correct inside iterator
        public HashMapIterator() {
            currentBucketIndex = 0;
            currentBucketIterator = findNextNonEmptyBucket(currentBucketIndex);

        }
        //Iterate through all the keys in the set
        /*
        HasNext use the iterator of the current linkedlist to see if there is the next item
        CurrentIterator has to be an attribute of the class




        * */
        @Override
        public boolean hasNext() {


            //if we can get a inside iterator initially
            //or if we can get an inside iterator after updating the index
            //check if the linked list has a next item
            if (currentBucketIterator == null) {
                return false;
            } else {
                return currentBucketIterator.hasNext();
            }





        }
        /*
        * We use next to first check if there is still is next item
        * then get the next key
        * move the two indices to the correct position
        *
        *
        * */
        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }


            Node node = (Node) currentBucketIterator.next();
            K key = node.key;


            if (!currentBucketIterator.hasNext() && currentBucketIndex < buckets.length - 1) {
                currentBucketIndex++;
                currentBucketIterator = findNextNonEmptyBucket(currentBucketIndex);
            }





            return key;

        }

    }


      /** Removes all of the mappings from this map. */
    public void clear()  {
        try {
            throwException();
        } catch (UnsupportedOperationExceptions e) {
            throw new RuntimeException(e);
        }
    }
    public int findIndexOfKey(K key) {
         int Hashcode = key.hashCode();
        int index = Math.floorMod(Hashcode, this.buckets.length);
        return index;

    }
    /**
     * Returns true if this map contains a mapping for the specified key.
     */
    public boolean containsKey(K key)  {
       int index= this.findIndexOfKey(key);
       Collection<Node> bucket = this.buckets[index];
       if(bucket == null) {
           return false;
       }
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * Find the bucket that the key should be stored
     * iterate through the bucket
     */
    public V get(K key) {
        int Hashcode = key.hashCode();
        int index = Math.floorMod(Hashcode, buckets.length);
        Collection<Node> bucket = buckets[index];
        if (bucket==null) {
            return null;
        }
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    public int size(){
        return nodeCounts;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     *
     * Hashmap first gets the hashcode of the key
     * modulus the hashcode with the size of the map(how many buckets there are)
     * The result is the index of the node or the key value pair
     * Iterate through the List in that bucket of the index, if the key already exists, update the value
     * if not, add the NODE to the tail of the list
     *
     *
     *
     *
     */
    public void put(K key, V value)  {
        Node nodeToPut = createNode(key, value);
        int Hashcode = key.hashCode();
        int index = Math.floorMod(Hashcode, size);
        Collection<Node> bucket = buckets[index];
        if(bucket == null) {
            buckets[index] = createBucket();
            buckets[index].add(nodeToPut);
            nodeCounts++;
            currentLoadFactor = nodeCounts / buckets.length;

            //If current load factor is larger than set load factor
            //resize the map
            if (currentLoadFactor > loadFactor) {
                resize();
            }

            return;

        }
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }

        }
        //If after the iteration, nothing gets returned, means no duplicates
        bucket.add(nodeToPut);
        nodeCounts++;
          currentLoadFactor = nodeCounts / buckets.length;

            //If current load factor is larger than set load factor
            //resize the map
            if (currentLoadFactor > loadFactor) {
                resize();
            }


    }

    public double getCurrentLoadFactor() {
        return this.nodeCounts/buckets.length;
    }

    public void resize() {
        MyHashMap<K, V> newHashMap = new MyHashMap<K, V>(size * 2);
        for (K key : this) {

            newHashMap.put(key, this.get(key));

        }
        this.size = size * 2;
        this.currentLoadFactor = newHashMap.getCurrentLoadFactor();
        this.nodeCounts = newHashMap.size();
        this.buckets = newHashMap.buckets;
    }

    /**
     * Returns a Set view of the keys contained in this map.
     *
     *
     * Todo
     */
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (K key : this) {
            keySet.add(key);
        }
        return keySet;
    }


    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key){
       try {
            throwException();
        } catch (UnsupportedOperationExceptions e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value){
         try {
            throwException();
        } catch (UnsupportedOperationExceptions e) {
            throw new RuntimeException(e);
        }
        return null;
    }

















    /** Constructors */
    public MyHashMap() {
        this.size = DEFAULT_SIZE;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.buckets = createTable(DEFAULT_SIZE);
        nodeCounts = 0;

    }

    public MyHashMap(int initialSize) {
        this.size = initialSize;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.buckets = createTable(initialSize);
        nodeCounts = 0;


    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */



    public MyHashMap(int initialSize, double maxLoad) {
        this.size = initialSize;
        this.loadFactor = maxLoad;
        this.buckets = createTable(initialSize);
        nodeCounts = 0;
    }





    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);

    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     *
     *
     *
     * Need to take a look at other's implementation
     * Default structure for myHashMap is linked list
     */
    protected Collection<Node> createBucket() {

        return new LinkedList<Node>(); // needs to be overid in the sub class
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    public Collection<Node>[] createTable(int tableSize) {


        Collection<Node>[] table = new LinkedList[tableSize];


        return table;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!










}
