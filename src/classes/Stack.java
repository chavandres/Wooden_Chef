package classes;

public class Stack<T> {

    public Node<T> top = null;
    boolean orden = true;
    int size = 0;

    public void push(T value) {
        Node<T> temp = new Node<>(value);
        temp.setNext(top);
        top = temp;
         size++;
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
        public T printPos(int value) {

        if (value == size || value < 0) {

            return null;
        }
        Node<T> actual = top;
        if (actual == null) {
            System.out.println(".........");
        } else {
            if (value == 0) {
                return actual.getData();
            }
            for (int x = 0; x < value; x++) {
                actual = actual.getNext();
            }

            return actual.getData();
        }
        return null;
    }
}
