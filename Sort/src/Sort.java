
public class Sort<E extends Comparable<E>> {

    /**
     * 插入排序
     *
     * @param data
     */
    public static <E extends Comparable<E>> void insertionSort(E[] data) {
        insertionSort(data, 0, data.length - 1);
    }

    private static <E extends Comparable<E>> void insertionSort(E[] data, int left, int right) {
        int j;
        for (int i = left + 1; i <= right; i++) {
            E temp = data[i];
            for (j = i; j > 0 && temp.compareTo(data[j - 1]) < 0; j--) {
                data[j] = data[j - 1];
            }
            data[j] = temp;
        }
    }

    /**
     * 希尔排序  增量序列：N / 2
     *
     * @param data
     * @param <E>
     */
    public static <E extends Comparable<E>> void shellSort(E[] data) {
        int j;
        for (int gap = data.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < data.length; i++) {
                E temp = data[i];
                for (j = i; j >= gap && temp.compareTo(data[j - gap]) < 0; j -= gap) {
                    data[j] = data[j - gap];
                }
                data[j] = temp;
            }
        }
    }

    /**
     * 堆排序 先进行生成堆操作 然后进行堆排序
     *
     * @param data
     * @param <E>
     */
    public static <E extends Comparable<E>> void heapSort(E[] data) {
        for (int i = data.length / 2 - 1; i >= 0; i--) {
            siftDown(data, i, data.length);
        }

        for (int i = data.length - 1; i > 0; i--) {
            swap(data, 0, i);
            siftDown(data, 0, i);
        }
    }

    private static <E extends Comparable<E>> void siftDown
            (E[] data, int i, int size) {
        int index = i;
        E temp = data[index];
        while (leftChild(index) < size) {
            int child = leftChild(index);
            if ((child + 1) < size &&
                    data[child + 1].compareTo(data[child]) > 0)
                child++;

            if (temp.compareTo(data[child]) < 0)
                data[index] = data[child];
            else
                break;

            index = child;

        }
        data[index] = temp;
    }

    private static int leftChild(int index) {
        return index * 2 + 1;
    }

    private static <E extends Comparable<E>> void swap
            (E[] data, int index1, int index2) {
        E temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }

    /**
     * 归并排序
     * 可以理解为自下而上的排序
     *
     * @param data
     * @param <E>
     */
    public static <E extends Comparable<E>> void mergeSort(E[] data) {
        E[] temps = (E[]) new Comparable[data.length];
        mergeSort(data, temps, 0, data.length - 1);
    }

    private static <E extends Comparable<E>> void mergeSort
            (E[] data, E[] temps, int left, int right) {
        if (left == right)
            return;

        int mid = left + (right - left) / 2;
        mergeSort(data, temps, left, mid);
        mergeSort(data, temps, mid + 1, right);
        merge(data, temps, left, mid + 1, right);
    }

    private static <E extends Comparable<E>> void merge
            (E[] data, E[] temps, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int num = rightEnd - leftPos + 1; //需要排序的元素数量
        int tempPos = leftPos;

        while (leftPos <= leftEnd && rightPos <= rightEnd)
            if (data[leftPos].compareTo(data[rightPos]) < 0)
                temps[tempPos++] = data[leftPos++];
            else
                temps[tempPos++] = data[rightPos++];


        while (leftPos <= leftEnd)
            temps[tempPos++] = data[leftPos++];

        while (rightPos <= rightEnd)
            temps[tempPos++] = data[rightPos++];

        for (int i = 0; i < num; i++, rightEnd--)
            data[rightEnd] = temps[rightEnd];
    }


    /**
     * 快速排序
     * 可以理解为自上而下的排序
     *
     * @param data
     * @param <E>
     */
    public static <E extends Comparable<E>> void quickSort(E[] data) {

        quickSort(data, 0, data.length - 1);
    }

    private static int CUT_OFF = 10;

    private static <E extends Comparable<E>> void quickSort
            (E[] data, int left, int right) {

        if (left + CUT_OFF <= right) {
            E pivot = median3(data, left, right);
            int i = left;
            int j = right - 1;
            while (true) {
                while (data[++i].compareTo(pivot) < 0) ;
                while (data[--j].compareTo(pivot) > 0) ;
                if (i < j)
                    swap(data, i, j);
                else
                    break;
            }

            swap(data, i, right - 1);
            quickSort(data, left, i - 1);
            quickSort(data, i + 1, right);

        } else {
            //递归终止条件  当排序数组为小数组时启用插入排序
            insertionSort(data, left, right);
        }
    }

    /**
     * 三数中值分割法
     *
     * @param data
     * @param left
     * @param right
     * @param <E>
     * @return
     */
    private static <E extends Comparable<E>> E median3(E[] data, int left, int right) {

        int mid = left + (right - left) / 2;

        if (data[left].compareTo(data[mid]) > 0)
            swap(data, left, mid);

        if (data[right].compareTo(data[mid]) < 0)
            swap(data, right, mid);

        swap(data, mid, right - 1);
        return data[right - 1];
    }

    public static void main(String[] args) {
        Integer[] nums = {5, 1, 7, 3, 9, 1, 10, 4, 45678, 6, 1, 9, 0, 3, 6, 3,
                90, 78, 56, 24, 56, 34, 56, 234, 56, 3245, 679, 345, 234};
        quickSort(nums);
        for (Integer i : nums)
            System.out.print(i + "\t");
    }
}
