/**
 * 自定义实现LinkedList
 */
class Node {
    Node prev;      // 上一个节点
    Node next;      // 下一个节点
    Object item;    // 元素数据

    public Node() { }

    public Node(Object element) {
        this.item = element;
    }


    public Node(Node prev, Node next, Object element) {
        super();
        this.prev = prev;
        this.next = next;
        this.item = element;
    }
}

public class MyLinkedList<E> {
    private Node first;
    private Node last;
    private int size;

    public MyLinkedList() {}

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Node tmp = first;
        if (tmp != null) {
            sb.append(tmp.item);
            tmp = tmp.next;
        }
        while (tmp != null) {
            sb.append(", " + tmp.item);
            tmp = tmp.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            //索引非法
            throw new RuntimeException("Index: "+index+", Size: "+size);
        }
    }

    // 返回对应下标的节点
    private Node getNode(int index) {
        checkIndex(index);

        Node tmp;
        if (index < (size >> 1)) { //从头部向后查找
            tmp = first;
            for (int i = 0; i < index; i++) {
                tmp = tmp.next;
            }
        } else { // 从尾部向前查找
            tmp = last;
            for (int i = 0; i < size - index - 1; i++) {
                tmp = tmp.prev;
            }
        }
        return tmp;
    }

    public E get(int index) {
        Node tmp = getNode(index);
        return (E)tmp.item;
    }

    // 从尾部插入节点
    public void add(E e) {
        Node node = new Node(e);

        if (first == null) { // 第一次插入元素
            first = node;
            last = node;
        } else {
            node.prev = last;
            last.next = node;
            last = node;
        }
        size++;
    }

    public void add(int index, E e) {
        Node node = new Node(e);
        if (index == 0) { // 在首部插入节点,size为0时也可以插入
            if (size != 0) {
                first.prev = node;
            } else { // size为0时，需要更改last
                last = node;
            }
            node.next = first;
            first = node;
        } else if (index == size) { // 在尾部插入节点
            node.prev = last;
            last.next = node;
            last = node;
            return;
        } else {
            Node tmp = getNode(index);
            node.prev = tmp.prev;
            tmp.prev.next = node;
            node.next = tmp;
            tmp.prev = node;
        }
        size++;
    }

    public void remove(int index) {
        Node tmp = getNode(index);
        if (tmp.prev != null) {
            tmp.prev.next = tmp.next;
        } else { // 移除节点为首节点，需更改first
            first = tmp.next;
        }
        if (tmp.next != null) {
            tmp.next.prev = tmp.prev;
        } else { // 移除节点为尾节点，需更改last
            last = tmp.prev;
        }
        size--;
    }

    public void clear() {
        for (Node tmp = first; tmp != null; ) {
            Node nextTmp = tmp.next;
            tmp.prev = tmp.next = null;
            tmp.item = null;
            tmp = nextTmp;
        }
        first = last = null;
        size = 0;
    }

    public static void test1(MyLinkedList<String> m) {
        m.add("🐎");
        m.add("🚊");
        m.add("❤");
        m.add("🍇");
        System.out.println(m);
        m.remove(2);
        System.out.println(m);
        m.remove(0);
        System.out.println(m);
        m.remove(1);
        System.out.println(m);
        System.out.println(m.getNode(0).item);
    }


    public static void main(String[] args) {
        MyLinkedList<String> m = new MyLinkedList<>();
        test1(m);
    }
}
