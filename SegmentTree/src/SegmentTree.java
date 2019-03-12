public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 在index的位置创建表示区间[l...r]的线段树
     *
     * @param index
     * @param l
     * @param r
     */
    private void buildSegmentTree(int index, int l, int r) {
        if (l == r) {
            tree[index] = data[l];
            return;
        }

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftChild(index), l, mid);
        buildSegmentTree(rightChild(index), mid + 1, r);
        tree[index] = merger.merge(tree[leftChild(index)], tree[rightChild(index)]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index illegal");
        }
        return data[index];
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    /**
     * 返回[queryL, queryR]的值
     *
     * @param queryL
     * @param queryR
     * @return
     */
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index illegal");

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在以index为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
     *
     * @param index  根节点
     * @param l      根节点所代表区间的左边界
     * @param r      根节点所代表区间的右边界
     * @param queryL 查询区间左边界
     * @param queryR 查询区间右边界
     * @return 查搜结果
     */
    private E query(int index, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR)
            return tree[index];

        int mid = l + (r - l) / 2;
        if (queryL >= mid + 1)
            return query(rightChild(index), mid + 1, r, queryL, queryR);
        else if (queryR <= mid)
            return query(leftChild(index), l, mid, queryL, queryR);

        E leftResult = query(leftChild(index), l, mid, queryL, mid);
        E rightResult = query(rightChild(index), mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    public void set(int index, E val) {
        data[index] = val;
        set(0, 0, data.length - 1, index, val);
    }

    private E set(int treeIndex, int l, int r, int index, E val) {
        if (l == r) {
            tree[treeIndex] = val;
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        if (index <= mid) {
            set(leftChild(treeIndex), l, mid, index, val);
        } else {
            set(rightChild(treeIndex), mid + 1, r, index, val);
        }

        tree[treeIndex] = merger.merge(tree[leftChild(treeIndex)], tree[rightChild(treeIndex)]);
        return tree[treeIndex];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                builder.append(tree[i]);
            } else {
                builder.append("null");
            }
            if (i < tree.length - 1) {
                builder.append(',');
            }
        }
        builder.append(']');
        return builder.toString();
    }
}
