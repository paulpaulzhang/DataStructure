import java.util.Random;

public class AVLMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        Node left;
        Node right;
        K key;
        V value;
        int height;

        Node(K key, V value) {
            left = right = null;
            this.key = key;
            this.value = value;
            height = 1;
        }
    }

    private int size;
    private Node root;

    public AVLMap() {
        root = null;
        size = 0;
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

        if (key.compareTo(node.key) < 0) {
            node.left = put(key, value, node.left);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(key, value, node.right);
        } else {
            node.value = value;
        }

        int newHeight = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        if (node.height == newHeight)
            return node;

        node.height = newHeight;

        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balanceFactor < -1 && getBalanceFactor(node.right) >= 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        if (balanceFactor < -1 && getBalanceFactor(node.right) < 0) {
            return leftRotate(node);
        }

        return node;
    }

    @Override
    public V remove(K key) {
        Node ret = getNode(key, root);
        if (ret == null)
            return null;
        root = remove(key, root);
        return ret.value;
    }

    private Node remove(K key, Node node) {
        if (node == null)
            return null;

        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(key, node.left);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(key, node.right);
            retNode = node;
        } else {
            if (node.left == null) {
                Node right = node.right;
                node.right = null;
                size--;
                retNode = right;
            } else if (node.right == null) {
                Node left = node.left;
                node.left = null;
                size--;
                retNode = left;
            } else {
                Node successor = minimum(node.right);
                successor.right = remove(successor.key, node.right);
                successor.left = node.left;
                node.left = node.right = null;
                size--;
                retNode = successor;
            }
        }

        if (retNode == null)
            return null;

        int newHeight = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;
        if (retNode.height == newHeight)
            return retNode;
        retNode.height = newHeight;

        int balanceFactor = getBalanceFactor(retNode);

        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }

        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            node.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        if (balanceFactor < -1 && getBalanceFactor(retNode.right) >= 0) {
            node.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        if (balanceFactor < -1 && getBalanceFactor(retNode.right) < 0) {
            return leftRotate(retNode);
        }

        return retNode;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key, root) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key, root);
        if (node == null)
            return null;
        return node.value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    private int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int getBalanceFactor(Node node) {
        return getHeight(node.left) - getHeight(node.right);
    }

    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T3 = x.left;

        x.left = y;
        y.right = T3;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        x.right = y;
        y.left = T3;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    private Node getNode(K key, Node root) {
        if (root == null)
            return null;

        if (key.compareTo(root.key) < 0)
            return getNode(key, root.left);
        else if (key.compareTo(root.key) > 0)
            return getNode(key, root.right);
        else
            return root;
    }

    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    public static void main(String[] args) {
        Random random = new Random();
        AVLMap<Integer, Integer> map = new AVLMap<>();
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
