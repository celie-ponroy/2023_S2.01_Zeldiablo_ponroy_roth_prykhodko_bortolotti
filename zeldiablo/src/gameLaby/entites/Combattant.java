package gameLaby.entites;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Combattant extends Entite {

    private int pv;

    /**
     * Constructeur Entite
     *
     * @param x   abscisse
     * @param y   ordonnée
     * @param col
     */
    public Combattant(int vie, int x, int y, boolean col) {
        super(x, y, col);
        pv = vie;
    }

    public int getPv(){
        return pv;
    }


    public void setPv(int pv){
        this.pv = pv;
    }

    /**
     * permet de savoir si le personnage est en x,y
     *
     * @param dx position testee
     * @param dy position testee
     * @return true si le personnage est bien en (dx,dy)
     */
    public boolean etrePresent(int dx, int dy) {
        return (this.getX() == dx && this.getY() == dy);
    }

    public boolean etreMort(){
        return this.pv <=0 ;
    }

    public void drawComb(GraphicsContext gc, Image imgCombatant){
        gc.drawImage(imgCombatant, getX() * 50, getY() * 50, 50, 50);
    }


    public abstract void deplacer(int[] suiv);

    public abstract void attaquer(Combattant c);
    public abstract void etreAttaque(int force);

}
