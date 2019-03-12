import java.util.Random;

public class AVLSet<E extends Comparable<E>> implements Set<E> {
    private AVLTree<E, Object> avl;

    public AVLSet() {
        avl = new AVLTree<>();
    }

    @Override
    public void add(E e) {
        avl.add(e, null);
    }

    @Override
    public boolean contains(E e) {
        return avl.contains(e);
    }

    @Override
    public void remove(E e) {
        avl.remove(e);
    }

    @Override
    public int getSize() {
        return avl.getSize();
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }

    public static void main(String[] args) {
        AVLSet<Integer> set = new AVLSet<>();
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
