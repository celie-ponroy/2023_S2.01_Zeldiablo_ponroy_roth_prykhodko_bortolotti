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
//        Monstre m = new Monstre(4, 2,true);
//        laby.entites.add(m);

        int posPersoX = 3;
        int posPersoY = 2;

        //methode testee
        laby.deplacerEntite(laby.pj,Labyrinthe.DROITE);

        // verification
        assertEquals(posPersoX,laby.pj.getX(),"le perso n'a normalement pas du bougé X");
        assertEquals(posPersoY,laby.pj.getY(),"le perso n'a normalement pas du bougé Y");

    }


    @Test
    void testDeplacementPersoMonstre_Movable() throws IOException {

        // preparation des donnees
        Labyrinthe laby = new Labyrinthe("labySimple/laby0.txt");

        int posPersoX = 3;
        int posPersoY = 2;

        //methode testee
        laby.deplacerEntite(laby.pj,Labyrinthe.BAS);

        // verification
        assertEquals(posPersoX,laby.pj.getX(),"le perso ne doit pas bouger горизонтально");
        assertEquals(posPersoY + 1,laby.pj.getY(),"le perso doit bouger вниз");
    }


    @Test
    void testDeplacementFantome() throws IOException {

        // preparation des donnees
        Labyrinthe laby = new Labyrinthe("labySimple/laby0.txt");
        Monstre m = new Monstre(1, 1,false);
        laby.entites.add(m);

        int posMonstreX = 0;
        int posMonstreY = 1;

        //methode testee
        laby.deplacerEntite(m,Labyrinthe.GAUCHE);

        // verification
        assertEquals(posMonstreX ,m.getX(),"le monstre-fantôme doit bouger влево");
        assertEquals(posMonstreY,m.getY(),"le monstre-fantôme ne doit pas bouger вертикально");
    }
}
