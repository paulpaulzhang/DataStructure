import java.util.Random;
import java.util.TreeMap;

public class HashMap<K, V> implements Map<K, V> {

    private int[] capacity = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
    private static int lowTol = 2;
    private static int upperTol = 10;
    private int capacityIndex = 0;

    private TreeMap<K, V>[] hashTable;
    private int size;
    private int M;

    public HashMap() {
        this.M = capacity[capacityIndex];
        hashTable = new TreeMap[M];
        size = 0;

        for (int i = 0; i < M; i++)
            hashTable[i] = new TreeMap<>();
    }

    @Override
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

    @Override
    public V remove(K key) {
        V ret = null;
        TreeMap<K, V> map = hashTable[hash(key)];
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;

            if (size < lowTol * M && capacityIndex - 1 >= 0)
                resize(capacity[--capacityIndex]);
        }

        return ret;
    }

    @Override
    public boolean contains(K key) {
        return hashTable[hash(key)].containsKey(key);
    }

    @Override
    public V get(K key) {
        TreeMap<K, V> map = hashTable[hash(key)];
        if (map.containsKey(key))
            return map.get(key);
        return null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
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

    public static void main(String[] args) {
        Random random = new Random();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            int r = random.nextInt(100);
            if (!map.contains(r))
                map.put(r, 1);
            else
                map.put(r, map.get(r) + 1);
        }

        System.out.println("size : " + map.getSize());
        System.out.println("99 : " + map.get(99));
        System.out.println("1 : " + map.get(1));

        for (int i = 0; i < 50; i++) {
            int r = random.nextInt(100);
            map.remove(r);
        }

        System.out.println("\nsize : " + map.getSize());
        System.out.println("99 : " + map.get(99));
        System.out.println("1 : " + map.get(1));

    }
}
