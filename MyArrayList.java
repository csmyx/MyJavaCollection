/**
 * 自定义实现ArrayList
 */
public class    MyArrayList<E> {
    private Object[] elementData;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new RuntimeException("Capacity is invalid: "+capacity);
        } else if (capacity == 0) {
            elementData = new Object[DEFAULT_CAPACITY];
        } else {
            elementData = new Object[capacity];
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void grow() {
        Object[] newArray = new Object[elementData.length + (elementData.length >> 1)];
        System.arraycopy(elementData, 0, newArray, 0, elementData.length);
        elementData = newArray;
    }

    public void add(E element) {
        if (size == elementData.length) {
            // 扩容操作
            grow();
        }
        elementData[size++] = element;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size - 1; i++) {
            sb.append(elementData[i] + ", ");
        }
        if (size != 0) {
            sb.append(elementData[size-1]);
        }
        sb.append(']');
        return sb.toString();
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            //索引非法
            throw new RuntimeException("Index: "+index+", Size: "+size);
        }
    }

    public E get(int index) {
        checkIndex(index);
        return (E)elementData[index];
    }

    public void set(int index, E value) {
        checkIndex(index);
        elementData[index] = value;
    }

    public void remove(int index) {
        checkIndex(index);
        System.arraycopy(elementData, index+1, elementData, index, size - 1 - index);
        --size;
    }

    public void remove(E element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elementData[i])) {
                remove(i);
            }
        }
    }

//    public static void main(String[] args) {
//        MyArrayList<Integer> list = new MyArrayList(0);
//        for (Integer i = 0; i < 11; i++) {
//            list.add(i+10);
//        }
//        System.out.println(list);
//        list.remove(4);
//        System.out.println(list);
//        list.remove((Integer)11);
//        System.out.println(list);
//
//    }
}
