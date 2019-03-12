import java.util.Random;

public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> linkedList;

    public LinkedListSet() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if (!linkedList.contains(e))
            linkedList.addFirst(e);
    }

    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    @Override
    public void remove(E e) {
        linkedList.removeElement(e);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public static void main(String[] args) {
        LinkedListSet<Integer> set = new LinkedListSet<>();
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
