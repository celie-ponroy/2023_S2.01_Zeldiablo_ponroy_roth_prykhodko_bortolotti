package gameLaby.laby;
import java.util.Random;

public class Monstre extends Entite{

    public Monstre(int vie, int frc, int x, int y, boolean col){
        super(vie, frc, x, y, col);
    }
    @Override
    public void deplacer(int[] suiv) {
        setX(suiv[0]);
        setY(suiv[1]);
    }

    public void attaquer(Entite e){

    }
    public void etreAttaquer(int force){

    }
}
