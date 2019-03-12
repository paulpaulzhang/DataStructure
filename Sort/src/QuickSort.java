import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickSort {

    public static void quickSort(List<Integer> list) {
        List<Integer> smaller = new ArrayList<>();
        List<Integer> same = new ArrayList<>();
        List<Integer> larger = new ArrayList<>();
        if (list.size() <= 1)
            return;

        for (Integer num : list) {
            if (num < list.get(list.size() / 2))
                smaller.add(num);
            else if (num > list.get(list.size() / 2))
                larger.add(num);
            else
                same.add(num);
        }

        quickSort(smaller);
        quickSort(larger);

        list.clear();
        list.addAll(smaller);
        list.addAll(same);
        list.addAll(larger);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Random().nextInt(Integer.MAX_VALUE));
        }
        quickSort(list);
        for (Integer num : list)
            System.out.print(num + "\t");

    }
}
