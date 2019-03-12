public class Main {

    public static void main(String[] args) {
        MyArray<Integer> array = new MyArray<>();
        for (int i = 0; i < 8; i++) {
            array.addLast(i);
        }
        System.out.println(array);

        array.add(6, 10);
        System.out.println(array);

        array.addLast(10010);
        array.addLast(10012);
        System.out.println(array);

        System.out.println(array.get(2) + "\n");
        System.out.println(array.isEmpty() + "\n");
        System.out.println(array.find(10010) + "\n");
        array.set(array.getSize() - 1, 10013);
        System.out.println(array);
        System.out.println(array.removeFirst());
        System.out.println(array.removeLast());
        array.addFirst(0);
        System.out.println(array);
        for (int i = 0; i < 10; i++) {
            array.remove(0);
        }
        System.out.println(array);
    }
}
