/**
 * 通过数组进行简单的并查集实现
 */
public class UnionFind1 implements UF {
    private int[] id;

    public UnionFind1(int size) {
        id = new int[size];

        for (int i = 0; i < id.length; i++)
            id[i] = i;
    }

    @Override
    public int getSize() {
        return id.length;
    }

    /**
     * 返回id代表元素的集合
     *
     * @param p
     * @return
     */
    private int find(int p) {
        if (p < 0 || p >= id.length)
            throw new ArrayIndexOutOfBoundsException();
        return id[p];
    }

    /**
     * 判断p,q所代表元素的集合是否相同
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
     * 合并两个元素的所属集合
     *
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pID = find(p); //p所在集合
        int qID = find(q); //q所在集合
        if (pID == qID)
            return;
        for (int i = 0; i < id.length; i++)
            if (id[i] == pID)
                id[i] = qID;
    }

}
