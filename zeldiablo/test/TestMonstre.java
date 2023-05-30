import gameLaby.laby.Entite;
import gameLaby.laby.Labyrinthe;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TestMonstre {
    /**
     * Test si un monstre possède bien une position après la création d'un labyrinthe
     * @throws IOException
     */
    @Test
    public void testPosMonstre() throws IOException {
        Labyrinthe laby = new Labyrinthe("laby0.txt");
        ArrayList<Entite> listeEntite = laby.getEntites();
        assertEquals(5, listeEntite.get(0).getX());
        assertEquals(2, listeEntite.get(0).getY());
    }

    /**
     * Test que la caractère d'un monstre est bien 'M'
     */
    @Test
    public void testCaraMonstre(){
        Labyrinthe laby = new Labyrinthe("laby0.txt");
        assertEquals('M',laby.MONSTRE);
    }

    /**
     * Vérifie que le déplacement du perso sur un monstre est impossible
     * @throws IOException
     */
    @Test
    public void testMonstreObstacle() throws IOException {
        Labyrinthe laby = new Labyrinthe("laby0.txt");
        int xInit = laby.pj.getX();
        laby.deplacerPerso("DROITE");
        int xFinal = laby.pj.getX();
        assertEquals(xInit, xFinal);
    }

}