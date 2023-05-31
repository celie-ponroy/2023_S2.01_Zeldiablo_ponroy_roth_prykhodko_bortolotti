package gameLaby.laby;

import javafx.scene.SnapshotParameters;

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
    public Serpent(int vie, int frc, int x, int y,int tCour, int t) {
        super(vie, frc, x, y, true);
        taille = tCour;
        tMax = t;
        isHead = true;
        nextPart = null;
    }
    @Override
    public boolean etrePresent(int dx, int dy) {
        boolean res = false;
        Serpent s = this;
        while (s!=null){
            if ( super.etrePresent(dx, dy) )
            {
                res = true;
                break;
            }
        }
        return res;
    }

    @Override
    public void attaquer(Combattant e) {

    }

    @Override
    public void etreAttaque(int force) {

    }

    @Override
    public void deplacer(int[] suiv){}//deplacer serpent doit retourner un serpent


    public Serpent deplacerSerpent(int[] suiv) {
        Serpent newHead;
        if (taille<tMax){
            newHead = new Serpent(getPv(), getForce(), suiv[0], suiv[1], taille+1 ,tMax);
            isHead = false;
            newHead.nextPart = this;
        }else{
            newHead = new Serpent(getPv(), getForce(), suiv[0], suiv[1], tMax ,tMax);
            Serpent tmpSnake = this;
            while(tmpSnake.nextPart.nextPart!=null){
                tmpSnake = tmpSnake.nextPart;
            }
            tmpSnake.nextPart = null;
            isHead = false;
            newHead.nextPart = this;
        }
        return newHead;
    }
    
    public boolean getIsHead(){
        return isHead;
    }
}
