
package classes;

public class Orden {
    
    private int id;
    private String imagePath;
    private LinkedList<Ingrediente> ingredientes;

    public Orden(int id, String imagePath, LinkedList<Ingrediente> ingredientes) {
        this.id = id;
        this.imagePath = imagePath;
        this.ingredientes = ingredientes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    
    
    
    
}
