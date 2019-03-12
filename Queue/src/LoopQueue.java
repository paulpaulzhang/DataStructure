public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front;
    private int tail; //指向最后一个元素的下一个位置
    private int size;

    /**
     * 数组长度要比要求长度大一，处理队列是否为满的需求
     * @param capacity
     */
    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        size = 0;
        front = 0;
        tail = 0;
    }

    public LoopQueue() {
        this(10);
    }

    @Override
    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front) {
            reSize(data.length * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }


    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Dequeue failed");
        }
        E e = data[front];
        data[front] = null;
        size--;
        front = (front + 1) % data.length;
        if (size % data.length <= data.length / 4 && data.length / 2 != 0) {
            reSize(data.length / 2);
        }
        return e;
    }

    @Override
    public E getFront() {
        return data[front];
    }

    private void reSize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Queue\t")
                .append("size: ")
                .append(size)
                .append("\t capacity ")
                .append(getCapacity())
                .append("\n");
        buffer.append("Front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            buffer.append(data[i]);
            if ((i + 1) % data.length != tail) {
                buffer.append("\t");
            }
        }
        buffer.append("] Tail")
                .append("\n");
        return buffer.toString();
    }
}
