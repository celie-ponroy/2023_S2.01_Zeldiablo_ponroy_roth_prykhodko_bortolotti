package gameLaby.entites;

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

    public void deplacer(int[] suiv) {}

    @Override
    public String getImage() {
        return null;
    }
}
