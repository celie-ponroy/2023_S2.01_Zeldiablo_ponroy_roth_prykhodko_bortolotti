import gameLaby.laby.Labyrinthe;
import gameLaby.laby.Monstre;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLabyrinthe {

    @Test
    void testDeplacementPersoMonstre_OK() throws IOException {

        // preparation des donnees

        Labyrinthe laby = new Labyrinthe("labySimple/laby0.txt");
        Monstre m = new Monstre(4, 4,true);
        laby.entites.add(m);

        int posPersoX = 4;
        int posPersoY = 2;



        System.out.println("Perso\nx: " + laby.pj.getX() + "  y: " + laby.pj.getY());
        //methode testee
        laby.deplacerEntite(laby.pj,Labyrinthe.DROITE);

        System.out.println("x: " + laby.pj.getX() + "  y: " + laby.pj.getY());
        // verification
        assertEquals(posPersoX,laby.pj.getX(),"le perso n'a normalement pas du bougé X");
        assertEquals(posPersoY,laby.pj.getY(),"le perso n'a normalement pas du bougé Y");

    }
}
