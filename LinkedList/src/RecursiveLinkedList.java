public class RecursiveLinkedList<E> implements LinkedList<E> {

    private Node dummyHead;
    private int size;

    @Override
    public void add(E o, int index) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    public E get(int index) {
        return null;
    }

    public boolean set(E e, int index) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }
}
