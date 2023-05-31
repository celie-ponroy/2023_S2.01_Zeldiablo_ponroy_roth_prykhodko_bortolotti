package gameLaby.laby;

public class Fantome extends Combattant{


    /**
     * Constructeur Entite
     *
     * @param vie
     * @param frc
     * @param x   abscisse
     * @param y   ordonnée
     * @param col
     */
    public Fantome(int vie, int frc, int x, int y) {
        super(vie, frc, x, y, false);
    }





    @Override
    public void attaquer(Entite e) {

    }

    @Override
    public void etreAttaquer(int force) {

    }

    @Override
    public void deplacer(int[] suiv) {
        setX(suiv[0]);
        setY(suiv[1]);
    }
}
