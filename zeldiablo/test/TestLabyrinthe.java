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

        int posPersoX = 3;
        int posPersoY = 2;

        //methode testee
        laby.deplacerEntite(laby.pj,Labyrinthe.DROITE);

        // verification
        assertEquals(posPersoX,laby.pj.getX(),"le perso n'a normalement pas du bougé");
        assertEquals(posPersoY,laby.pj.getY(),"le perso n'a normalement pas du bougé");

    }
}
