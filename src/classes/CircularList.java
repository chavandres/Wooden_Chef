package classes;

public class CircularList<T> {

    private Node<T> root;
    private Node<T> end;
    int size = 0;

    public void insert(T value) {
        Node<T> temp = new Node<>(value);

        if (root == null) {
            root = temp;
            temp.setNext(root);
        } else {

            temp.setNext(root);
            end.setNext(temp);
        }
        end = temp;
        size += 1;
    }

    public void removeLast() {

        if (root == null) {
            return;
        } else {

            if (root == end) {
                end = null;
                root = null;
            } else {
                Node<T> actual = root;
                while (!actual.getNext().equals(end)) {
                    actual = actual.getNext();
                }
                actual.setNext(root);
                end.setNext(null);
                end = actual;
            }

        }

    }

    public void remove(T value) {
        if (root == null) {
            return;
        } else {

            if (root == end) {
                end = null;
                root = null;
            } else {
                Node<T> actual = root;
                while (!actual.getNext().getData().equals(value)) {
                    if (actual.getNext().equals(root)) {
                        return;
                    }
                    actual = actual.getNext();
                }
                Node<T> temp = actual.getNext();
                actual.setNext(temp.getNext());
                temp.setNext(null);

                if (temp == end) {
                    end = actual;
                }

            }
        }
    }

    public void get() {
        Node<T> actual = root;

        if (root == null) {
            System.out.println("Vacio");
            return;
        } else {
            if (root == end) {
                System.out.println(actual.getData());
            } else {

                for (int x = 0; x < size; x++) {
                    if (actual == root) {
                        System.out.println(actual.getData());
                    }
                    if (actual.getNext().equals(root)) {
                        return;
                    }
                    actual = actual.getNext();
                    System.out.println(actual.getData());
                }
            }
        }
    }
}
