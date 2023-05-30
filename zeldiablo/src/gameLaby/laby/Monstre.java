package gameLaby.laby;
import java.util.Random;

public class Monstre extends Entite{

    public Monstre(int x, int y, boolean col){
        super(x, y, col);
    }
    @Override
    public void deplacer(int[] suiv) {
        setX(suiv[0]);
        setY(suiv[1]);
    }
}
