package gameLaby.entites;

import gameLaby.laby.*;

/**
 * Classe Perso qui hérite de la classe Combattant. Cette classe représente un personnage situé en (x, y) dans le labyrinthe.
 */
public class Perso extends Combattant {


    /**
     * Constructeur du personnage.
     *
     * @param dx Position selon l'abscisse.
     * @param dy Position selon l'ordonnée.
     * @param col Booléen indiquant si le personnage peut être en collision.
     */
    public Perso(int dx, int dy, boolean col) {
        super(Labyrinthe.VIE_PERSO, dx, dy, col);
    }


    /**
     * Permet de savoir si le personnage est à une certaine position (dx, dy).
     *
     * @param dx Position selon l'abscisse à tester.
     * @param dy Position selon l'ordonnée à tester.
     * @return true si le personnage est bien à la position (dx, dy).
     */
    public boolean etrePresent(int dx, int dy) {
        return (this.getX() == dx && this.getY() == dy);
    }

    /**
     * Déplace le personnage vers une nouvelle position.
     *
     * @param suiv Le tableau contenant les nouvelles coordonnées du personnage.
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
        c.etreAttaque(Labyrinthe.ATTAQUE_PERSO);
    }

    /**
     * Gère l'attaque reçue par le combattant.
     *
     * @param force La force de l'attaque reçue.
     */
    public void etreAttaque(int force) {
        int pvRestant = this.getPv() - force;
        this.setPv(pvRestant);
    }


    /**
     * Renvoie le chemin d'accès à l'image du personnage.
     *
     * @return Le chemin d'accès à l'image du personnage.
     */
    @Override
    public String getImage() {
        return "link-2.png";
    }
}
