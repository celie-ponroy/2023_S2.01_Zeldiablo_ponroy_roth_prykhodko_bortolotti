package gameLaby.laby;

public abstract class Entite {
    /**
     * position du personnage
     */
    private int x;
    private int y;
    private boolean collision;


    /**
     * Constructeur Entite
     * @param x abscisse
     * @param y ordonnée
     */
    public Entite(int x, int y, boolean col){
        this.x = x;
        this.y = y;
        collision = col;
    }

    public boolean etrePresent(int dx, int dy) {
        return (this.x == dx && this.y == dy);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCollision(boolean col){
        this.collision = col;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] getPosition(){
        int[] positionEntite = new int[2];
        positionEntite[0] = this.getX();
        positionEntite[1] = this.getY();
        return positionEntite;
    }
    public  boolean getCollision(){
        return collision;
    }

    public abstract String getImage();


}
