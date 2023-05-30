import gameLaby.laby.Entite;
import gameLaby.laby.Labyrinthe;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
<<<<<<< HEAD
=======

>>>>>>> 36363c32c7447b8a403946cf649b1e182cf77a6d
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.Assert.assertEquals;

public class TestMonstre {
    /**
     * Test si un monstre possède bien une position après la création d'un labyrinthe
     * @throws IOException
     */
    @Test
    public void testPosMonstre() throws IOException {
        Labyrinthe laby = new Labyrinthe("labySimple/laby0.txt");
        ArrayList<Entite> listeEntite = laby.getEntites();
        assertEquals(4, listeEntite.get(0).getX());
        assertEquals(2, listeEntite.get(0).getY());
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
        laby.deplacerEntite(laby.pj, "Droite");

        int xFinal = laby.pj.getX();
        assertEquals(xInit, xFinal);
    }
}