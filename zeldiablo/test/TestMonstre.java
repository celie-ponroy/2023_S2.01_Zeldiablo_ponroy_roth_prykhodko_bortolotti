import gameLaby.laby.Combattant;
import gameLaby.laby.Labyrinthe;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


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

    @Test
    public void testComportementMonstreAttaque() throws IOException {
        // preparation des donnees
        Labyrinthe laby = new Labyrinthe("labySimple/laby0.txt");
        int vie = laby.pj.getPv() - 1;

        //methode testee
        laby.comportementMonstre();

        // verification
        assertEquals(vie,laby.pj.getPv() ,"le perso a du perdre un pv");
    }

    public void testComportementMonstreDeplacement() throws IOException {
        // preparation des donnees
        Labyrinthe laby = new Labyrinthe("labySimple/laby1.txt");
        int[] positionMonstre = laby.comb.get(1).getPosition();
        int[] posInit = new int[2];
        posInit[0] = 4;
        posInit[1] = 3;

        //methode testee
        laby.comportementMonstre();

        // verification
        assertNotEquals(posInit, positionMonstre,"le monstre a du bouger");

    }

}