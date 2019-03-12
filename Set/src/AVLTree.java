import java.util.ArrayList;
import java.util.List;

public class AVLTree<K extends Comparable<K>, V> {

    private class Node {
        K key;
        V value;
        int height;
        Node left;
        Node right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            height = 1;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(K key) {
        return getNode(key, root) != null;
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

    /**
     * 得到node的高度
     *
     * @param node
     * @return
     */
    private int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    /**
     * 获得node的平衡因子
     *
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node) {
        if (node == null)
            return 0;

        return getHeight(node.left) - getHeight(node.right);
    }

    //                      右旋转
    //              y                             X
    //             / \                          /   \
    //            x  T4                        z     y
    //           / \        ------->          / \   / \
    //          z  T3                        T1 T2 T3 T4
    //         / \
    //        T1 T2

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        //右旋转操作
        x.right = y;
        y.left = T3;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    //                      左旋转
    //             y                            x
    //            / \                         /   \
    //          T4   X                       y     z
    //              / \     ------>         / \   / \
    //            T3   z                   T4 T3 T1 T2
    //                / \
    //               T1 T2

    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T3 = x.left;

        //左旋转操作
        x.left = y;
        y.right = T3;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            node = new Node(key, value);
            size++;
            return node;
        }

        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else
            node.value = value;

        //更新height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        //平衡维护
        //LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);

        //RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);

        //LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public V remove(K key) {
        Node ret = getNode(key, root);
        if (ret != null) {
            root = remove(key, root);
            return ret.value;
        }
        return null;
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
                node.right = node.left = null;
                size--;
                retNode = successor;
            }
        }

        if (retNode == null)
            return null;

        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;
        int balanceFactor = getBalanceFactor(retNode);

        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }

        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }

        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
    }

    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }


    /**
     * 判断是不是一颗二叉搜索树
     *
     * @return
     */
    public boolean isBST() {
        List<K> list = new ArrayList<>();
        inOrder(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(list.get(i - 1)) < 0)
                return false;
        }
        return true;
    }

    private void inOrder(Node node, List<K> list) {
        if (node == null)
            return;

        inOrder(node.left, list);
        list.add(node.key);
        inOrder(node.right, list);
    }

    /**
     * 判断是不是一颗平衡二叉树
     *
     * @return
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null)
            return true;

        if (Math.abs(getBalanceFactor(node)) > 1)
            return false;

        return isBalanced(node.left) && isBalanced(node.right);
    }
}
