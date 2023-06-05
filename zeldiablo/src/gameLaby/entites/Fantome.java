package gameLaby.entites;

import gameLaby.laby.*;

public class Fantome extends Combattant {


    /**
     * Constructeur Fantome
     *
     * @param x abscisse
     * @param y ordonnée
     */
    public Fantome(int x, int y) {
        super(Labyrinthe.VIE_FANTOME, x, y, false);
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
        return "/fantome.png";
    }
}
