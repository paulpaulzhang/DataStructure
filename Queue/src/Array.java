public class Array<E> {
    private int size;
    private E[] arr;

    public Array() {
        this(10);
    }

    public Array(int capacity) {
        arr = (E[]) new Object[capacity];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return arr.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void add(int index, E e) {
        if (size == arr.length) {
            reSize(2 * arr.length);
        }
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Require index >= 0 & index <= size");
        }

        for (int i = size - 1; i >= index; i--) {
            arr[i + 1] = arr[i];
        }
        arr[index] = e;
        size++;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Require index >= 0 & index <= size");
        }
        return arr[index];
    }

    public E getLast() {
        return get(size - 1);
    }

    public E getFirst() {
        return get(0);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Require index >= 0 & index <= size");
        }
        arr[index] = e;
    }

    public boolean contains(E e) {
        for (E e1 : arr) {
            if (e1.equals(e)) {
                return true;
            }
        }
        return false;
    }

    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Require index >= 0 & index <= size");
        }
        if (size <= arr.length / 4 && arr.length / 2 != 0) {
            reSize(arr.length / 2);
        }
        E e = arr[index];
        for (int i = index; i < size; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
        return e;
    }

    public boolean remove(E e) {
        if (find(e) != -1) {
            remove(find(e));
            return true;
        }

        return false;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    private void reSize(int newCapacity) {
        if (size == arr.length) {
            E[] newArr = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("size: ")
                .append(size)
                .append("  capacity: ")
                .append(arr.length)
                .append("\n");
        buffer.append('[');
        for (int i = 0; i < size; i++) {
            buffer.append(arr[i]);
            if (i < size - 1) {
                buffer.append("\t");
            }
        }
        buffer.append(']')
                .append("\n");
        return buffer.toString();
    }
}
