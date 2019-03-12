import java.util.Random;

public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node {
        K key;
        V value;
        Node next;

        Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, Node next) {
            this(key, null, next);
        }

        public Node(Node next) {
            this(null, null, next);
        }

        public Node() {
            this(null, null, null);
        }
    }

    private int size;
    private Node dummyHead;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
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

    @Override
    public void put(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else {
            node.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.key.equals(key))
                break;

            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
        return null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }


    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    public static void main(String[] args) {
        Random random = new Random();
        LinkedListMap<Integer, Integer> map = new LinkedListMap<>();
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
