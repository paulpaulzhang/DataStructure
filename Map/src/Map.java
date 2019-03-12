public interface Map<K, V> {
    void put(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    int getSize();

    boolean isEmpty();
}
