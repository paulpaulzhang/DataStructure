public class ArrayQueue<E> implements Queue<E> {
    private Array<E> array;

    public ArrayQueue() {
        array = new Array<>();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Queue\t");
        buffer.append("Front [");
        for (int i = 0; i < array.getSize(); i++) {
            buffer.append(array.get(i));
            if (i < getSize() - 1) {
                buffer.append("\t");
            }
        }
        buffer.append("] Tail");
        return buffer.toString();
    }
}
