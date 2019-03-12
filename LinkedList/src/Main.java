import java.util.Random;

public class Main {

    public static void main(String[] args) {
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();
        DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>();
        System.out.println(testSpeed(100000, singleLinkedList));
        System.out.println(testSpeed(100000, doubleLinkedList));
    }

    public static double testSpeed(int num, LinkedList linkedList) {
        long start = System.nanoTime();
        for (int i = 0; i < num; i++) {
            linkedList.add(new Random().nextInt(Integer.MAX_VALUE), 0);
        }
        for (int i = 0; i < linkedList.getSize(); i++) {
            linkedList.remove(linkedList.getSize() - 1);
        }
        long end = System.nanoTime();
        return (end - start) / 1000000000.0;
    }
}
