package gameLaby.entites;

import gameLaby.laby.*;

/**
 * Classe Troll qui hérite de la classe Combattant. Représente un troll dans le labyrinthe.
 */
public class Troll extends Combattant {
    /**
     * Constructeur Troll.
     *
     * @param x L'abscisse de la position initiale du troll.
     * @param y L'ordonnée de la position initiale du troll.
     */
    public Troll(int x, int y) {
        super(Labyrinthe.VIE_TROLL, x, y, true);
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
     * Subit une attaque d'un autre combattant.
     *
     * @param force La force de l'attaque subie.
     */
    @Override
    public void etreAttaque(int force) {
        int pvRestant = this.getPv() - force;
        this.setPv(pvRestant);
    }

    /**
     * Déplace le troll vers une nouvelle position.
     *
     * @param suiv Les nouvelles coordonnées du troll.
     */
    @Override
    public void deplacer(int[] suiv) {
        setX(suiv[0]);
        setY(suiv[1]);
    }

    /**
     * Renvoie le chemin d'accès à l'image du troll.
     *
     * @return Le chemin d'accès à l'image du troll.
     */
    @Override
    public String getImage() {
        return "/troll.png";
    }
}
