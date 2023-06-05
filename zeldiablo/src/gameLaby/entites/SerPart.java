package gameLaby.entites;

/**
 * Classe représentant une partie du corps d'un serpent dans le jeu.
 */
public class SerPart extends Entite {

    /**
     * Constructeur de SerPart.
     *
     * @param xPos Coordonnée x de la partie du corps du serpent sur la grille.
     * @param yPos Coordonnée y de la partie du corps du serpent sur la grille.
     */
    public SerPart(int xPos, int yPos) {
        super(xPos, yPos, true);
    }

    /**
     * Indique si la partie du corps du serpent se trouve à une position spécifique.
     *
     * @param dx La coordonnée x à vérifier.
     * @param dy La coordonnée y à vérifier.
     * @return true si la partie du corps du serpent se trouve à la position (dx, dy), false sinon.
     */
    public boolean etrePresent(int dx, int dy) {
        boolean res = false;

        if (dx == getX() && dy == getY()) {
            res = true;
        }

        return res;
    }

    /**
     * Renvoie le chemin de l'image représentant la partie du corps du serpent.
     *
     * @return Une chaîne de caractères représentant le chemin de l'image de la partie du corps du serpent.
     */
    public String getImage() {
        return "/corps_serp.png";
    }
}
