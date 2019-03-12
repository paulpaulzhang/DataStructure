import java.util.Random;

public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BST<E> bst;

    public BSTSet() {
        bst = new BST<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    public static void main(String[] args) {
        BSTSet<Integer> set = new BSTSet<>();
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
