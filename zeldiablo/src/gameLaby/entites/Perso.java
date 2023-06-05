package gameLaby.entites;
import gameLaby.laby.*;

/**
 * gere un personnage situe en x,y
 */
public class Perso extends Combattant{


    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Perso(int dx, int dy, boolean col) {
        super(Labyrinthe.VIE_PERSO, dx, dy, col);
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
    @Override
    public void deplacer(int[] suiv){
        setX(suiv[0]);
        setY(suiv[1]);
    }

    public void attaquer(Combattant c){
        c.etreAttaque(Labyrinthe.ATTAQUE_PERSO);
    }
    public void etreAttaque(int force){
        int pvRestant = this.getPv() - force;
        this.setPv(pvRestant);
    }

    @Override
    public String getImage() {
        return "link-2.png";
    }
}
