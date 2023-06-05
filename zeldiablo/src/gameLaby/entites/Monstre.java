package gameLaby.entites;

import gameLaby.laby.*;

/**
 * Classe Monstre qui hérite de la classe Combattant.
 * Cette classe représente un monstre dans le labyrinthe.
 */
public class Monstre extends Combattant {

    /**
     * Constructeur du monstre.
     *
     * @param vie la quantité de vie du monstre.
     * @param x position du monstre selon l'axe des abscisses.
     * @param y position du monstre selon l'axe des ordonnées.
     * @param col paramètre indiquant si le monstre est solide (true) ou non (false).
     */
    public Monstre(int vie, int x, int y, boolean col) {
        super(vie, x, y, col);
    }

    /**
     * Déplace le monstre vers une nouvelle position.
     *
     * @param suiv Le tableau contenant les nouvelles coordonnées du monstre.
     */
    @Override
    public void deplacer(int[] suiv) {
        setX(suiv[0]);
        setY(suiv[1]);
    }

    /**
     * Attaque un autre combattant.
     *
     * @param c Le combattant à attaquer.
     */
    public void attaquer(Combattant c) {
        c.etreAttaque(Labyrinthe.ATTAQUE_MONSTRE);
    }

    /**
     * Gère l'attaque reçue par le monstre.
     *
     * @param force La force de l'attaque reçue.
     */

    public void etreAttaque(int force) {
        int pvRestant = this.getPv() - force;
        this.setPv(pvRestant);
    }

    /**
     * Méthode à surcharger dans les classes dérivées pour renvoyer le chemin de l'image du monstre.
     *
     * @return null par défaut. À surcharger dans les classes dérivées.
     */
    @Override
    public String getImage() {
        return null;
    }
}
