import jdk.dynalink.NamedOperation;

import java.util.*;

public class BST<E extends Comparable<E>> {

    private class Node {
        public Node left, right;
        public E e;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 向以node为根的二分搜索树中插入元素e，递归算法
     *
     * @param node
     * @param e
     * @return 插入新节点后二分搜索树的根
     */
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    /**
     * 前序遍历 递归实现
     *
     * @param node
     */
    private void preOrder(Node node) {
        if (node == null)
            return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 前序遍历 非递归实现
     */
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    public void inOrder() {
        inOrder(root);
    }

    /**
     * 中序遍历 递归实现
     *
     * @param node
     */
    private void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 中序遍历 非递归实现
     */
    public void inOrderNR() {
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null)
                cur = cur.right;
            else
                cur = null;
        }
    }

    public void postOrder() {
        postOrder(root);
    }

    /**
     * 后序遍历 递归实现
     *
     * @param node
     */
    private void postOrder(Node node) {
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 后序遍历 非递归实现
     * 先访问左孩子，再访问右孩子，最后访问根节点
     */
    public void postOrderNR() {
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        Node last = root;


        //左孩子依次入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }

        while (!stack.isEmpty()) {
            cur = stack.pop();

            //如果有右孩子,再将右孩子的左孩子依次入栈
            if (cur.right != null && cur.right != last) {
                stack.push(cur);
                cur = cur.right;
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
            } else {
                System.out.println(cur.e);
                last = cur;
            }
        }
    }

    /**
     * 层序遍历
     */
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.e);
            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);
        }
    }

    public E minimum() {
        if (size == 0)
            throw new IllegalArgumentException("Find failed");

        return minimum(root).e;
    }

    /**
     * 查找最小值 递归实现
     *
     * @param node
     * @return
     */
    private Node minimum(Node node) {
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    /**
     * 查找最小值 非递归实现
     *
     * @return
     */
    private Node minimumNR() {
        Node cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }

    public E maximum() {
        if (size == 0)
            throw new IllegalArgumentException("Find failed");

        return maximum(root).e;
    }

    /**
     * 查找最大值 递归实现
     *
     * @param node
     * @return
     */
    private Node maximum(Node node) {
        if (node.right == null)
            return node;

        return maximum(node.right);
    }

    /**
     * 查找最大值 非递归实现
     *
     * @return
     */
    private Node maximumNR() {
        Node cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur;
    }

    /**
     * 删除最小值
     *
     * @return
     */
    public E removeMin() {
        Node ret = minimum(root);
        root = removeMin(root);
        return ret.e;
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
     * 删除最大值
     *
     * @return
     */
    public E removeMax() {
        Node ret = maximum(root);
        root = removeMax(root);
        return ret.e;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node left = node.left;
            node.left = null;
            size--;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 删除二叉树中任一元素 递归实现
     * 利用前驱节点进行操作  还可利用后继节点进行操作
     *
     * @param node
     * @param e
     * @return 根节点
     */
    private Node remove(Node node, E e) {
        if (node == null)
            return null;

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
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

            Node predecessor = maximum(node.left);
            predecessor.left = removeMax(node.left);
            predecessor.right = node.right;
            node.left = node.right = null;
            return predecessor;
        }
    }
}
