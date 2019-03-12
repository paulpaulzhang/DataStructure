import java.util.TreeMap;

public class HashTable<K, V> {

    private final int[] capacity = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
    private static int upperTol = 10;
    private static int lowerTol = 2;

    private TreeMap<K, V>[] hashTable;
    private int capacityIndex = 7;
    private int M;
    private int size;

    public HashTable() {
        this.M = capacity[capacityIndex];
        size = 0;
        hashTable = new TreeMap[M];

        for (int i = 0; i < M; i++) {
            hashTable[i] = new TreeMap<>();
        }
    }


    public void put(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];

        if (map.containsKey(key))
            map.put(key, value);
        else {
            map.put(key, value);
            size++;

            if (size >= upperTol * M && capacityIndex + 1 < capacity.length)
                resize(capacity[++capacityIndex]);
        }
    }

    public V remove(K key) {
        V ret = null;
        TreeMap<K, V> map = hashTable[hash(key)];

        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;

            if (size < lowerTol * M && capacityIndex - 1 >= 0)
                resize(capacity[--capacityIndex]);

        }

        return ret;
    }

    public void set(K key, V newValue) {
        TreeMap<K, V> map = hashTable[hash(key)];

        if (map.containsKey(key))
            map.put(key, newValue);
        else
            throw new IllegalArgumentException();
    }

    public boolean contains(K key) {
        return hashTable[hash(key)].containsKey(key);
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];

        for (int i = 0; i < newM; i++)
            newHashTable[i] = new TreeMap<>();

        int oldM = M;
        this.M = newM;

        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashTable[i];
            for (K key : map.keySet())
                newHashTable[hash(key)].put(key, map.get(key));
        }

        this.hashTable = newHashTable;
    }
}
