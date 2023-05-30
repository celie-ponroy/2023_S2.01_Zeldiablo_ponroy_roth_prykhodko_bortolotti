package gameLaby.laby;

public abstract class Entite {
    private int x;
    private int y;

    public Entite(int x, int y){
        this.x = x;
        this.y = y;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public abstract void deplacer();
}
