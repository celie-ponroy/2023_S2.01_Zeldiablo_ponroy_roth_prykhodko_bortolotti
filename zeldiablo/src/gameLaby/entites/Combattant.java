package gameLaby.entites;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Classe abstraite représentant un combattant dans le jeu.
 */
public abstract class Combattant extends Entite {

    private int pv;

    /**
     * Constructeur de Combattant.
     *
     * @param vie Points de vie initiaux du combattant.
     * @param x   Coordonnée x du combattant sur la grille.
     * @param y   Coordonnée y du combattant sur la grille.
     * @param col Indique si le combattant est en collision avec des autres objets.
     */
    public Combattant(int vie, int x, int y, boolean col) {
        super(x, y, col);
        pv = vie;
    }

    /**
     * Renvoie les points de vie actuels du combattant.
     *
     * @return Les points de vie actuels du combattant.
     */
    public int getPv(){
        return pv;
    }


    /**
     * Modifie les points de vie du combattant.
     *
     * @param pv Les nouveaux points de vie du combattant.
     */
    public void setPv(int pv){
        this.pv = pv;
    }

    /**
     * Indique si le combattant se trouve à une position spécifique.
     *
     * @param dx La coordonnée x à vérifier.
     * @param dy La coordonnée y à vérifier.
     * @return true si le combattant se trouve à la position (dx, dy), false sinon.
     */
    public boolean etrePresent(int dx, int dy) {
        return (this.getX() == dx && this.getY() == dy);
    }


    /**
     * Indique si le combattant est mort (c'est-à-dire si ses points de vie sont égaux ou inférieurs à zéro).
     *
     * @return true si le combattant est mort, false sinon.
     */
    public boolean etreMort(){
        return this.pv <=0 ;
    }

    /**
     * Dessine le combattant sur le contexte graphique donné.
     *
     * @param gc Le contexte graphique sur lequel dessiner le combattant.
     * @param imgCombatant L'image du combattant à dessiner.
     */
    public void drawComb(GraphicsContext gc, Image imgCombatant){
        gc.drawImage(imgCombatant, getX() * 50, getY() * 50, 50, 50);
    }

    /**
     * Déplace le combattant selon une stratégie spécifique.
     *
     * @param suiv Les coordonnées de la prochaine position du combattant.
     */
    public abstract void deplacer(int[] suiv);

    /**
     * Attaque un autre combattant.
     *
     * @param c Le combattant à attaquer.
     */
    public abstract void attaquer(Combattant c);

    /**
     * Subit une attaque d'un autre combattant.
     *
     * @param force La force de l'attaque subie.
     */
    public abstract void etreAttaque(int force);

}
