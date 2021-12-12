package classes;

public class Stack<T> {

    public Node<T> top = null;
    boolean orden = true;

    public void push(T value) {
        Node<T> temp = new Node<>(value);
        temp.setNext(top);
        top = temp;

    }

    public Node<T> pop() {
        Node<T> actual = top;
        top = actual.getNext();
        actual.setNext(null);
        return actual;
    }

    public void print() {
        Node<T> actual = top;
        while (actual.getNext() != null) {
            if (actual == top) {
                System.out.println(actual.getData());
            }
            actual = actual.getNext();
            System.out.println(actual.getData());
        }

    }
}
