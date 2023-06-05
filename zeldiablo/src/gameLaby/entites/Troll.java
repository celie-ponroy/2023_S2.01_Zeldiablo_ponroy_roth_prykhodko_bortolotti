package gameLaby.entites;

import gameLaby.laby.*;

/**
 * Class troll
 */
public class Troll extends Combattant {
    /**
     * Constructeur Entite
     *
     * @param vie
     * @param frc
     * @param x   abscisse
     * @param y   ordonnée
     * @param col
     */
    public Troll(int x, int y) {
        super(Labyrinthe.VIE_TROLL,  x, y, true);
    }



    @Override
    public void attaquer(Combattant c) {
        c.etreAttaque(Labyrinthe.ATTAQUE_MONSTRE);
    }

    @Override
    public void etreAttaque(int force) {
        int pvRestant = this.getPv() - force;
        this.setPv(pvRestant);
    }

    @Override
    public void deplacer(int[] suiv) {
        setX(suiv[0]);
        setY(suiv[1]);
    }

    @Override
    public String getImage() {
        return "/troll.png";
    }
}
