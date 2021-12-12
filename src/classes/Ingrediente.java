
package classes;

public class Ingrediente {
    
    private String nombre;
    private String imagePath;

    public Ingrediente(String nombre, String imagePath) {
        this.nombre = nombre;
        this.imagePath = imagePath;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    
    
    
    
    
}
