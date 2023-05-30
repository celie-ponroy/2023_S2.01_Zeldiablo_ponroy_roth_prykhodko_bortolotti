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
        Monstre m = new Monstre(4, 3,true);
        laby.entites.add(m);

        int posPersoX = 3;
        int posPersoY = 2;

        //methode testee

        laby.deplacerEntite(laby.pj, "Droite");


        // verification
        assertEquals(posPersoX,laby.pj.getX(),"le perso n'a normalement pas du bouger");
        assertEquals(posPersoY,laby.pj.getY(),"le perso n'a normalement pas du bouger");

    }
}
