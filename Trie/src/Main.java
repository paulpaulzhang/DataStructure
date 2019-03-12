public class Main {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("apple")
                .add("app")
                .add("tank")
                .add("xml")
                .add("e");
        trie.preOrder();
        trie.delete("e");
        System.out.println();
        trie.preOrder();

    }
}
