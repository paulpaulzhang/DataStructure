public class MaxHeap<E extends Comparable<E>> {

    private MyArray<E> array;

    public MaxHeap() {
        array = new MyArray<>();
    }

    public MaxHeap(int capacity) {
        array = new MyArray<>(capacity);
    }

    public MaxHeap(E[] arr) {
        array = new MyArray<>(arr);

        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * 返回二叉堆的元素个数
     *
     * @return
     */
    public int size() {
        return array.getSize();
    }

    /**
     * 判断二叉堆是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 得到父亲节点的索引
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("Don't have parent index");
        return (index - 1) / 2;
    }

    /**
     * 得到左孩子节点的索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 得到右孩子节点的索引
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 向堆中添加元素
     *
     * @param e
     */
    public void add(E e) {
        array.addLast(e);
        percolateUp(array.getSize() - 1, e);
    }

    /**
     * 通过比较子节点和父节点元素大小将新元素添加再合适位置
     *
     * @param index
     */
    private void siftUp(int index) {
        while (index > 0 && array.get(parent(index)).compareTo(array.get(index)) < 0) {
            array.swap(index, parent(index));
            index = parent(index);
        }
    }

    /**
     * 与siftUp相比没有swap操作，经过d层赋值次数为d + 1, 而siftUp操作需要3d次
     *
     * @param k
     * @param e
     */
    private void percolateUp(int k, E e) {
        int index = k;
        while (index > 0 && array.get(parent(index)).compareTo(e) < 0) {
            array.set(index, array.get(parent(index)));
            index = parent(index);
        }
        array.set(index, e);
    }

    /**
     * 从二叉堆中找到最大元素
     *
     * @return
     */
    public E findMax() {
        if (array.getSize() == 0)
            throw new IllegalArgumentException("The heap is empty!");
        return array.get(0);
    }

    /**
     * 从二叉堆中取出最大元素
     *
     * @return
     */
    public E extractMax() {
        E ret = findMax();
        percolateDown(0, array.removeLast());
        return ret;
    }

    /**
     * 通过比较父亲节点元素和左右孩子节点元素大小，下沉元素，
     * 若父亲节点元素比左右孩子节点元素中最大的小时，与其交换位置
     * 直到元素下沉到合适位置
     */
    private void siftDown(int index) {
        while (leftChild(index) < size()) {
            int j = leftChild(index);
            if ((j + 1) < size() && array.get(j + 1).compareTo(array.get(j)) > 0)
                j++;

            if (array.get(index).compareTo(array.get(j)) >= 0)
                break;

            array.swap(index, j);
            index = j;
        }
    }

    /**
     * 与siftDown相比没有swap操作，经过d层赋值次数为d + 1, 而siftDown操作需要3d次
     *
     * @param k
     * @param e
     */
    private void percolateDown(int k, E e) {
        int index = k;
        while (leftChild(index) < size()) {
            int j = leftChild(index);
            if ((j + 1) < size() && array.get(j + 1).compareTo(array.get(j)) > 0)
                j++;

            if (e.compareTo(array.get(j)) >= 0)
                break;

            array.set(index, array.get(j));
            index = j;
        }
        array.set(index, e);
    }

    /**
     * 取出堆中最大元素并将其替换为元素e
     *
     * @param e
     * @return
     */
    public E replace(E e) {
        E ret = findMax();
        percolateDown(0, e);
        return ret;
    }
}
