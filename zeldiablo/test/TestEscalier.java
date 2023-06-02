import gameLaby.laby.Escalier;
import gameLaby.laby.Labyrinthe;
import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEscalier {
    /**
     * Déplace le personnage sur un esclalier et vérifie que les coordonnées de chacun sont les même
     * @throws IOException
     */
    @Test
    public void testEscalierTraversable() throws IOException {
        Labyrinthe l = new Labyrinthe("labyetage/laby2.txt");
        l.deplacerCombattant(l.pj, Labyrinthe.HAUT);
        int x = l.pj.getX();
        int y = l.pj.getY();
        Escalier e=null;
        for (int i = 0; i<l.entiteInteractives.size(); i++){
            e = (Escalier) l.entiteInteractives.get(i);
        }
        assertEquals(x, e.getX());
        assertEquals(y, e.getY());
    }
}
