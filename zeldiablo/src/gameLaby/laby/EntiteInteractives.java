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

    @Override
    public String getImage() {
        return "link-2.png";
    }
}
