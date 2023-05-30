package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

import java.io.IOException;

public class LabyJeu implements Jeu {
    private Labyrinthe labyrinthe;
    public LabyJeu(String nomfichier) throws IOException {
        try {
            this.labyrinthe = new Labyrinthe(nomfichier);
        } catch (IOException e) {
            this.labyrinthe = new Labyrinthe("labySimple/laby1.txt");
        }
    }
    public void update(double secondes, Clavier clavier){
        if (clavier.droite) {
            labyrinthe.deplacerPerso(Labyrinthe.DROITE);
        }
        if (clavier.gauche) {
            labyrinthe.deplacerPerso(Labyrinthe.GAUCHE);
        }
        if (clavier.droite) {
            labyrinthe.deplacerPerso(Labyrinthe.DROITE);
        }
        if (clavier.haut) {
            labyrinthe.deplacerPerso(Labyrinthe.HAUT);
        }
        if (clavier.bas) {
            labyrinthe.deplacerPerso(Labyrinthe.BAS);
        }

    }
    public void init() {

    }
    public boolean etreFini(){
        return labyrinthe.etreFini();
    }

    public Labyrinthe getLabyrinthe() {
        return labyrinthe;
    }
}
