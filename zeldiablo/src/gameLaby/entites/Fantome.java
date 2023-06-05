package gameLaby.entites;

import gameLaby.laby.*;

/**
 * Classe Fantome qui hérite de la classe Combattant. Cette classe représente un fantôme dans le labyrinthe.
 */
public class Fantome extends Combattant {


    /**
     * Constructeur du Fantôme.
     *
     * @param x Position selon l'abscisse.
     * @param y Position selon l'ordonnée.
     */
    public Fantome(int x, int y) {
        super(Labyrinthe.VIE_FANTOME, x, y, false);
    }

    /**
     * Attaque un autre combattant.
     *
     * @param c Le combattant à attaquer.
     */
    @Override
    public void attaquer(Combattant c) {
        c.etreAttaque(Labyrinthe.ATTAQUE_MONSTRE);
    }

    /**
     * Gère l'attaque reçue par le combattant.
     *
     * @param force La force de l'attaque reçue.
     */
    @Override
    public void etreAttaque(int force) {
        int pvRestant = this.getPv() - force;
        this.setPv(pvRestant);
    }

    /**
     * Déplace le fantôme vers une nouvelle position.
     *
     * @param suiv Le tableau contenant les nouvelles coordonnées du fantôme.
     */
    @Override
    public void deplacer(int[] suiv) {
        setX(suiv[0]);
        setY(suiv[1]);
    }

    /**
     * Renvoie le chemin d'accès à l'image du fantôme.
     *
     * @return Le chemin d'accès à l'image du fantôme.
     */
    @Override
    public String getImage() {
        return "/fantome.png";
    }
}
