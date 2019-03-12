import java.util.TreeMap;

public class Trie {

    private class Node {

        boolean isWord;
        TreeMap<Character, Node> children;

        public Node(boolean isWord) {
            this.isWord = isWord;
            children = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private int size;
    private Node root;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public Trie add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.children.get(c) == null)
                cur.children.put(c, new Node());
            cur = cur.children.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
        return this;
    }

    private void add(Node node, String word, int index) {
        if (index == word.length()) {
            if (!node.isWord) {
                node.isWord = true;
                size++;
            }
            return;
        }

        char c = word.charAt(index);
        if (node.children.get(c) == null)
            node.children.put(c, new Node());
        add(node.children.get(c), word, index + 1);
    }

    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.children.get(c) == null)
                return false;
            cur = cur.children.get(c);
        }
        return cur.isWord;
    }

    private boolean contains(Node node, String word, int index) {
        if (index == word.length())
            return node.isWord;

        char c = word.charAt(index);
        if (node.children.get(c) == null)
            return false;
        return contains(node.children.get(c), word, index + 1);
    }

    public void delete(String word) {
        delete(word, root, 0);
    }

    private Node delete(String word, Node node, int index) {
        if (index == word.length()) {
            node.isWord = false;
            if (node.children == null || node.children.size() == 0)
                return null;
            return node;
        }

        if (node.children.get(word.charAt(index)) == null)
            return null;

        Node ret = delete(word, node.children.get(word.charAt(index)), index + 1);
        if (ret == null)
            node.children.remove(word.charAt(index));
        else
            node.children.put(word.charAt(index), ret);

        if (node.children.size() != 0 || node.isWord)
            return node;
        return null;
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node root) {
        if (root == null || root.children == null || root.children.size() == 0) {
            System.out.print("\t");
            return;
        }

        for (char c : root.children.keySet()) {
            System.out.print(c);
            preOrder(root.children.get(c));
        }
    }

    /**
     * 查找单词前缀是否在其中，一个单词也是其本身的前缀
     *
     * @param prefix
     * @return
     */
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.children.get(c) == null)
                return false;
            cur = cur.children.get(c);
        }
        return true;
    }
}
