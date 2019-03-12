public class Main {

    public static void main(String[] args) {
        Integer[] nums = new Integer[]{-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segmentTree.query(1, 2));
    }
}
