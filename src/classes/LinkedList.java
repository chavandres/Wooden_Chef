
package classes;


public class LinkedList<T> {

    private Node<T> root;
    public int length = 0;  
    
    
    public void insert(T value){
        Node<T> temp = new Node<>(value);
        
        if(root == null){
            root = temp; 
        
        } 
        else{
            Node<T> actual = root;
            while(actual.getNext() != null){
                actual = actual.getNext();
            }
            actual.setNext(temp);
        }
        length +=1;
    }
    
    public void insert(T value, int index){ //remove if unused
        if(index < length && index >=0){
            Node<T> temp = new Node(value);

            if(index == 0){
                temp.setNext(root);
                root = temp;
            } 
            else{
                Node<T> actual = root;

                for (int i = 0; i < index-1; i++) {
                    actual = actual.getNext();
                }
                temp.setNext(actual.getNext());
                actual.setNext(temp);
            }
            length +=1;
        } 
        
        else{
            System.out.println("Error");
        } 
    }
    
    
    public T get(int index){

        if(index < length){
            Node<T> actual = root;
            for (int i = 0; i < index; i++) {
                actual = actual.getNext();
            }

            return actual.getData();
        }
        else{
            return null;
        }
    }
    
    public void delete(int index){
            if(index < length && index >=0){

            Node<T> actual = root;
            
            if(index == 0){
                root = actual.getNext();
                actual.setNext(null);
            }
            else{

                for (int i = 0; i < index-1; i++) {
                    actual = actual.getNext();
                }
                Node<T> temp = actual.getNext();
                actual.setNext(actual.getNext().getNext());
                temp.setNext(null);
                   
            }
            length -=1;
        }
    }
    
    public boolean exists(T value){
        boolean exists = false;
        Node<T> actual = root;
            
        while(actual.getNext() != null && exists == false){
            if (actual.getData() != value) {
                actual = actual.getNext();
            } 
            else{
                exists=true;
            }
        }    
        return exists;
    }
    
    public T extract(int index){
        if(index < length && index >=0){
            Node<T> actual = root;
            Node<T> temp = null;
            
            if(index == 0){
                root = actual.getNext();
                actual.setNext(null);
                temp = actual;
            }else{
                for (int i = 0; i < index-1; i++) {
                    actual = actual.getNext();
                }
                temp = actual.getNext();
                actual.setNext(actual.getNext().getNext());
                temp.setNext(null);
            }
            length -=1;
            return temp.getData();
            
        } else{
            return null;
        }
    }
        
}
