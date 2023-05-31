package gameLaby.laby;

public abstract class Combattant extends Entite {

    private int pv;
    private int force;

    /**
     * Constructeur Entite
     *
     * @param x   abscisse
     * @param y   ordonnée
     * @param col
     */
    public Combattant(int vie, int f, int x, int y, boolean col) {
        super(x, y, col);
        pv = vie;
        force = f;
    }

    public int getPv(){
        return pv;
    }

    public int getForce(){
        return force;
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

    public abstract void attaquer(Entite e);
    public abstract void etreAttaquer(int force);

}
