/**
 * 红黑树的性质
 * 每个节点或者为红色或者为黑色
 * 根节点是黑色的
 * 每个叶子节点(最后的空节点)是黑色的
 * 如果一个节点是红色的，那么他的孩子节点都是黑色的
 * 从任意一个节点到叶子节点，经过的黑色节点是一样的
 */

public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        Node left, right;
        K key;
        V value;
        boolean color;

        Node(K key, V value) {
            left = right = null;
            this.key = key;
            this.value = value;
            color = RED;
        }

    }

    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    public void put(K key, V value) {
        root = put(key, value, root);
        root.color = BLACK;
    }

    private Node put(K key, V value, Node node) {
        if (node == null) {
            size++;
            return new Node(key, value); //默认插入红色节点
        }

        if (key.compareTo(node.key) < 0)
            node.left = put(key, value, node.left);
        else if (key.compareTo(node.key) > 0)
            node.right = put(key, value, node.right);
        else
            node.value = value;

        if (!isRed(node.left) && isRed(node.right))
            node = leftRotate(node);

        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);

        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isRed(Node node) {
        if (node == null)
            return BLACK;
        return node.color;
    }

    /**
     * 左旋转
     *
     * @param node
     * @return
     */
    private Node leftRotate
    (Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     * 右旋转
     *
     * @param node
     * @return
     */
    private Node rightRotate(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     * 颜色翻转
     *
     * @param node
     */
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }
}
