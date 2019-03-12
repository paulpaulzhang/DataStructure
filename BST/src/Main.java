public class Main {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int nums[] = new int[]{5, 3, 1, 2, 6, 8};
        for (int i : nums) {
            bst.add(i);
        }
        bst.inOrder();
        System.out.println();
        bst.remove(3);
        bst.inOrder();
    }
}
