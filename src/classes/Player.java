

package classes;

public class Player {
    
    private int score;
    private LinkedList<Ingrediente> platillo;

    public Player() {
    }
    
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LinkedList<Ingrediente> getPlatillo() {
        return platillo;
    }

    public void setPlatillo(LinkedList<Ingrediente> platillo) {
        this.platillo = platillo;
    }
    
    
    
}
