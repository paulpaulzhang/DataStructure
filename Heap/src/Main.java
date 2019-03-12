import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        MaxHeap<Integer> heap = new MaxHeap<>();
        Integer[] data = new Integer[1000000];
        for (int i = 0; i < 1000000; i++) {
            data[i] = random.nextInt(Integer.MAX_VALUE);
        }

        double time1 = testHeap(data, false);
        double time2 = testHeap(data, true);
        System.out.println("time1: " + time1);
        System.out.println("time2: " + time2);

    }

    public static double testHeap(Integer[] arr, boolean isHeapify) {
        long startTime = System.nanoTime();
        MaxHeap<Integer> maxHeap;
        if (isHeapify)
            maxHeap = new MaxHeap<>(arr);
        else {
            maxHeap = new MaxHeap<>();
            for (int i = 0; i < arr.length; i++) {
                maxHeap.add(arr[i]);
            }
        }

        int[] array = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            array[i] = maxHeap.extractMax();
            if (i >= 1)
                if (array[i - 1] < array[i])
                    throw new IllegalArgumentException("Error");
        }

        System.out.println("MaxHeap complete");

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }
}
