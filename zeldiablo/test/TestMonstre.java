import gameLaby.laby.Combattant;
import gameLaby.laby.Labyrinthe;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestMonstre {
    /**
     * Test si un monstre possède bien une position après la création d'un labyrinthe
     * @throws IOException
     */
    @Test
    public void testPosMonstre() throws IOException {
        Labyrinthe laby = new Labyrinthe("labySimple/laby0.txt");
        ArrayList<Combattant> listeComb = laby.getComb();
        assertEquals(4, listeComb.get(0).getX());
        assertEquals(2, listeComb.get(0).getY());
    }

    /**
     * Test que la caractère d'un monstre est bien 'M'
     */
    @Test
    public void testCaraMonstre() throws IOException{
        Labyrinthe laby = new Labyrinthe("labySimple/laby0.txt");

        assertEquals('M',laby.MONSTRE);
    }

    /**
     * Vérifie que le déplacement du perso sur un monstre est impossible
     * @throws IOException
     */
    @Test
    public void testMonstreObstacle() throws IOException {
        Labyrinthe laby = new Labyrinthe("labySimple/laby0.txt");
        int xInit = laby.pj.getX();
        laby.deplacerCombattant(laby.pj, "Droite");

        int xFinal = laby.pj.getX();
        assertEquals(xInit, xFinal);
    }

//    @Test
//    public void testAttaqueMonstre() throws IOException {
//        Labyrinthe laby = new Labyrinthe("labySimple/laby0.txt");
//        laby.
//
//
//
//        //assertEquals(, ,);
//    }

}