/**
 * 采用非递归路径压缩进行优化
 */
public class UnionFind5 implements UF {
    private int[] parent;
    private int[] rank;

    public UnionFind5(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    /**
     * 通过路径压缩进行优化的find操作
     *
     * @param p
     * @return
     */
    private int find(int p) {
        if (p < 0 || p >= parent.length)
            throw new ArrayIndexOutOfBoundsException();

        while (p != parent[p]) {
            //路径压缩
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        if (rank[pRoot] > rank[qRoot])
            parent[qRoot] = pRoot;
        else if (rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }

    }
}
