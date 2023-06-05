import gameLaby.laby.*;
import moteurJeu.Clavier;
import org.junit.jupiter.api.Test;
import gameLaby.entites.*;
import java.io.IOException;
import java.util.ArrayList;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestEscalier {
    /**
     * Vérifie qu'un escalier est traversable par le joueur
     * @throws IOException
     */
    @Test
    public void testEscalierTraversable() throws IOException {
        Labyrinthe l = new Labyrinthe("labyetageTest/laby2.txt");
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

    /**
     * Véifie que un fois sur la case de l'escalier, l'utilisatteur doit appuyer sur "a" pour l'utiliser
     * @throws IOException
     */
    @Test
    public void testEscalierInteractif() throws IOException {
        LabyJeu lj = new LabyJeu("labyetageTest");
        Labyrinthe l = lj.getLabyrinthe();
        l.deplacerCombattant(l.pj, Labyrinthe.HAUT);
        Clavier c = new Clavier();
        c.interagir();
        lj.update(2, c);
        Labyrinthe l2 = lj.getLabyrinthe();
        assertNotEquals(l,l2);
    }

    /**
     * Vérifie que quand le perso change d'étage, les monstre de l'étage précedent reste inchangé
     * @throws IOException
     */
    @Test
    public void testMonstreInchangé() throws IOException {
        LabyJeu lj = new LabyJeu("labyetageTest");
        Labyrinthe l = lj.getLabyrinthe();
        l.deplacerCombattant(l.pj, Labyrinthe.HAUT);
        Clavier c = new Clavier();
        c.interagir();
        ArrayList<Combattant> l1 = l.getComb();
        lj.update(2, c);
        ArrayList<Combattant> l2 = l.getComb();
        assertEquals(l1,l2);
    }

    /**
     * Vérifie qu'il y a bien d'autres monstre a un étage supérieur
     * @throws IOException
     */
    @Test
    public void testMonstreAletage() throws IOException {
        LabyJeu lj = new LabyJeu("labyetageTest");
        Labyrinthe l = lj.getLabyrinthe();
        l.deplacerCombattant(l.pj, Labyrinthe.HAUT);
        Clavier c = new Clavier();
        c.interagir();
        lj.update(2, c);
        Labyrinthe l2 = lj.getLabyrinthe();
        ArrayList<Combattant> l1 = new ArrayList<Combattant>();
        assertNotEquals(l1,l2.getComb());
    }

    /**
     * Vérifie que quand le héros emprunte un escalier il se trouve bien au
     * même coordonnée où il était dans l'autre labyrinthe
     * @throws IOException
     */
    @Test
    public void testHerosMemeCo() throws IOException {
        LabyJeu lj = new LabyJeu("labyetageTest");
        Labyrinthe l = lj.getLabyrinthe();
        l.deplacerCombattant(l.pj, Labyrinthe.HAUT);
        Clavier c = new Clavier();
        c.interagir();
        int x = l.getPj().getX();
        int y = l.getPj().getY();
        lj.update(2, c);
        l = lj.getLabyrinthe();
        int x1 = l.getPj().getX();
        int y1 = l.getPj().getY();
        assertEquals(x,x1);
        assertEquals(y,y1);
    }

    /**
     * Vérifie qu'il y a bien deux types d'escalier, montant et descendant
     * @throws IOException
     */
    @Test
    public void testEscalierMontantDescendant() throws IOException {
        Labyrinthe l = new Labyrinthe("labyetageTest/laby1.txt");
        Escalier e1 = (Escalier) l.getEntiteInteractives().get(0);
        Escalier e2 = (Escalier) l.getEntiteInteractives().get(1);
        assertNotEquals(e1.montant, e2.montant);
    }

    /**
     * Vérifie qu'un escalier montant a bien
     * un escalier descendant au dessus de lui aux même coordonnées
     * @throws IOException
     */
    @Test
    public void testEscalierDeuxSens() throws IOException {
        Labyrinthe l0 = new Labyrinthe("labyetageTest/laby0.txt");
        Labyrinthe l1 = new Labyrinthe("labyetageTest/laby1.txt");
        Escalier e0 = (Escalier) l0.getEntiteInteractives().get(0);
        Escalier e1 = (Escalier) l1.getEntiteInteractives().get(0);
        assertNotEquals(e0.montant, e1.montant);
        assertEquals(e0.getX(), e1.getX());
        assertEquals(e0.getY(), e1.getY());
    }


}
