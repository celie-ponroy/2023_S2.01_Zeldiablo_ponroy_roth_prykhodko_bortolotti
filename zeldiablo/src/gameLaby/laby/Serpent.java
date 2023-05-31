package gameLaby.laby;

public class Serpent extends Combattant{
     private boolean isHead;
     private int taille, tMax;
    private Serpent nextPart;
    /**
     * Constructeur Entite
     *
     * @param vie
     * @param frc
     * @param x   abscisse
     * @param y   ordonnée
     * @param col
     */
    public Serpent(int vie, int frc, int x, int y, int t) {
        super(vie, frc, x, y, true);
        taille = 1;
        tMax = t;
        isHead = true;
    }
    @Override
    public boolean etrePresent(int dx, int dy) {
        return (this.getX() == dx && this.getY() == dy);//111111
    }

    @Override
    public void attaquer(Entite e) {

    }

    @Override
    public void etreAttaquer(int force) {

    }

    @Override
    public void deplacer(int[] suiv) {
//        int size =
//        Serpent newHead = new Serpent(getPv(), getForce(), suiv[0], suiv[1], tMax);
//
//        if (taille<tMax){
//
//        }else{
//
//        }

    }


    public boolean getIsHead(){
        return isHead;
    }
}
