package gameLaby.laby;

public class EntiteInteractives extends Entite{


    /**
     * Constructeur Entite
     *
     * @param x   abscisse
     * @param y   ordonnée
     * @param col
     */
    public EntiteInteractives(int x, int y, boolean col) {
        super(x, y, col);
    }

<<<<<<< HEAD

    public void deplacer(int[] suiv) {
        //a faire
=======
    @Override
    public String getImage() {
        return "link-2.png";
>>>>>>> fb253088a09ca11c9351fa1ad2dfe6a9a4cca227
    }
}
