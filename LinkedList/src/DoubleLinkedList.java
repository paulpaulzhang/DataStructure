public class DoubleLinkedList<E> implements LinkedList<E>{
    private class Node {
        public E e;
        public Node next;
        public Node prev;

        public Node(Node prev, E e, Node next) {
            this.e = e;
            this.next = next;
            this.prev = prev;
        }

        public Node(E e) {
            this(null, e, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head; //虚拟头节点
    private Node tail; //虚拟尾节点
    private int size;

    public DoubleLinkedList() {
        head = new Node();
        tail = new Node(head, null, null);
        head.next = tail;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(E e, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed");
        }
        Node node = getNode(index);
        Node newNode = new Node(node.prev, e, node);
        newNode.prev.next = newNode;
        node.prev = newNode;
        size++;
    }

    public void addFirst(E e) {
        add(e, 0);
    }

    public void addLast(E e) {
        add(e, size);
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed");
        }

        Node delNode = getNode(index);
        delNode.next.prev = delNode.prev;
        delNode.prev.next = delNode.next;
        delNode.next = null;
        delNode.prev = null;
        size--;
        return delNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    private Node getNode(int index) {
        Node cur;
        if ((double) index / size > 0.5) {
            cur = tail.prev;
            for (int i = size - 1; i > index; i--) {
                cur = cur.prev;
            }
        } else {
            cur = head.next;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
        }

        return cur;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node cur = head.next;
        while (cur.next != null) {
            builder.append(cur.e);
            if (cur.next.next != null) {
                builder.append(" -> ");
            }

            cur = cur.next;
        }
        return builder.toString();
    }
}
