package gameLaby.entites;

/**
 * Classe abstraite représentant une entité dans le jeu.
 */
public abstract class Entite {
    /**
     * Position de l'entité.
     */
    private int x;
    private int y;
    private boolean collision;


    /**
     * Constructeur de Entite.
     *
     * @param x   Coordonnée x de l'entité sur la grille.
     * @param y   Coordonnée y de l'entité sur la grille.
     * @param col Indique si l'entité est en collision avec des autres objets.
     */
    public Entite(int x, int y, boolean col) {
        this.x = x;
        this.y = y;
        collision = col;
    }

    /**
     * Indique si l'entité se trouve à une position spécifique.
     *
     * @param dx La coordonnée x à vérifier.
     * @param dy La coordonnée y à vérifier.
     * @return true si l'entité se trouve à la position (dx, dy), false sinon.
     */
    public boolean etrePresent(int dx, int dy) {
        return (this.x == dx && this.y == dy);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCollision(boolean col) {
        this.collision = col;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    /**
     * Renvoie la position actuelle de l'entité.
     *
     * @return Un tableau contenant les coordonnées x et y de l'entité.
     */
    public int[] getPosition() {
        int[] positionEntite = new int[2];
        positionEntite[0] = this.getX();
        positionEntite[1] = this.getY();
        return positionEntite;
    }

    /**
     * Renvoie le statut de collision de l'entité.
     *
     * @return true si l'entité est en collision, false sinon.
     */
    public boolean getCollision() {
        return collision;
    }

    /**
     * Renvoie l'image de l'entité.
     *
     * @return Une chaîne de caractères représentant le chemin de l'image de l'entité.
     */
    public abstract String getImage();
}
