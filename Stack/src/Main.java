public class Main {

    public static void main(String[] args) {
        LinkedListStack<String> stack = new LinkedListStack<>();
        stack.push("Test");
        System.out.println(stack);
        for (int i = 0; i < 5; i++) {
            stack.push("S" + i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }
}
