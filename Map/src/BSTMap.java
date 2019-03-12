import java.util.Random;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
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
    public void put(K key, V value) {
        root = put(key, value, root);
    }

    private Node put(K key, V value, Node node) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0)
            node.left = put(key, value, node.left);
        else if (key.compareTo(node.key) > 0)
            node.right = put(key, value, node.right);
        else
            node.value = value;

        return node;
    }

    private Node getNode(K key, Node node) {
        if (node == null)
            return null;

        if (key.compareTo(node.key) == 0)
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(key, node.left);
        else
            return getNode(key, node.right);
    }

    @Override
    public boolean contains(K key) {
        return getNode(key, root) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key, root);
        return node == null ? null : node.value;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(key, root);
        if (node != null) {
            root = remove(key, root);
            return node.value;
        }
        return null;
    }

    private Node minimum(Node node) {
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 利用后继节点删除元素
     *
     * @param key
     * @param node
     * @return
     */
    private Node remove(K key, Node node) {
        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(key, node.left);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(key, node.right);
            return node;
        } else {
            if (node.left == null) {
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            }
            if (node.right == null) {
                Node left = node.left;
                node.left = null;
                size--;
                return left;
            }

            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            size--;
            return successor;
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        BSTMap<Integer, Integer> map = new BSTMap<>();
        for (int i = 0; i < 100000; i++) {
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
