/**
 * 通过树的形式实现并查集
 */
public class UnionFind2 implements UF {
    private int[] parent;

    public UnionFind2(int size) {
        parent = new int[size];

        for (int i = 0; i < parent.length; i++)
            parent[i] = i;
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    /**
     * 查找过程，查找元素p所对应的集合编号
     * 时间复杂度为O(h), h为树的高度
     *
     * @param p
     * @return
     */
    private int find(int p) {
        if (p < 0 || p >= parent.length)
            throw new ArrayIndexOutOfBoundsException();

        while (p != parent[p])
            p = parent[p];

        return p;
    }

    /**
     * 查找元素p和q是否属于同一个集合
     * 判读q和p的根节点是否相同，相同即属于同一个集合
     * 时间复杂度为O(h), h为树的高度
     *
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并元素p和q的所属集合
     * 取到p和q的根节点，使p的根节点指向q的根节点
     * 时间复杂度为O(h), h为树的高度
     *
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (qRoot == pRoot)
            return;

        parent[pRoot] = qRoot;
    }
}
