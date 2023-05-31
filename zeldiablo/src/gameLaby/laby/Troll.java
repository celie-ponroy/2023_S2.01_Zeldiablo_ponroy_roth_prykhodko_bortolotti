package gameLaby.laby;

public class Troll extends Combattant{
    /**
     * Constructeur Entite
     *
     * @param vie
     * @param frc
     * @param x   abscisse
     * @param y   ordonnée
     * @param col
     */
    public Troll(int frc, int x, int y) {
        super(3, frc, x, y, true);
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
