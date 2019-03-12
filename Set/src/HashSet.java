import java.util.Hashtable;
import java.util.Random;

public class HashSet<E> implements Set<E> {
    private HashTable<E, Object> hashTable;

    public HashSet() {
        hashTable = new HashTable<>();
    }

    @Override
    public void add(E e) {
        hashTable.put(e, null);
    }

    @Override
    public boolean contains(E e) {
        return hashTable.contains(e);
    }

    @Override
    public void remove(E e) {
        hashTable.remove(e);
    }

    @Override
    public int getSize() {
        return hashTable.size();
    }

    @Override
    public boolean isEmpty() {
        return hashTable.isEmpty();
    }

    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            int r = random.nextInt(1000);
            set.add(r);
        }
        System.out.println("size: " + set.getSize());
        System.out.println("99: " + set.contains(99));
        int size = random.nextInt(1000);
        for (int i = 0; i < size; i++) {
            int r = random.nextInt(1000);
            set.remove(r);
        }

        System.out.println("\nsize: " + set.getSize());
        System.out.println("99: " + set.contains(99));
    }
}
