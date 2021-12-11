
package classes;

public class Queue<T> {
    
    private Node<T> front;
    private Node<T> rear;
    int size = 0;
    
    public void add(T value){
        Node<T> newInput = new Node<>(value);
        if(front==null){
            front=newInput;
            rear=newInput;    
        } else{
            rear.setNext(newInput);
            rear=newInput;
        }
        size+=1;
    }
    
    public Node<T> poll(){
        Node<T> output = front;
        if(output!=null){
            front = front.getNext();
            output.setNext(null);
        }
        size-=1;
        return output;
    }

    public int getSize() {
        return size;
    }
    
    
     
}
