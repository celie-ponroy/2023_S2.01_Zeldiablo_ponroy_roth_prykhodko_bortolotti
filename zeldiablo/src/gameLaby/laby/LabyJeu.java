package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LabyJeu implements Jeu {
    private Labyrinthe labyrinthe;
    private ArrayList<Labyrinthe> labyrinthes;
    private int nbcourant;
    public LabyJeu(String nomdossier) throws IOException {
        nbcourant=0;
        labyrinthes= new ArrayList<Labyrinthe>();
        //initialiser labyrinthes
        File f  = new File("./"+nomdossier);
        File[] paths;

        int i=0;
        paths = f.listFiles();
        for (File path : paths) {
            this.labyrinthes.add(new Labyrinthe(path.getPath()));

           i++;
        }
        this.labyrinthe=labyrinthes.get(nbcourant);


    }
    public void update(double secondes, Clavier clavier){
        if (clavier.droite) {
            labyrinthe.deplacerEntite(labyrinthe.pj, Labyrinthe.DROITE);
        }
        if (clavier.gauche) {
            labyrinthe.deplacerEntite(labyrinthe.pj, Labyrinthe.GAUCHE);
        }
        if (clavier.droite) {
            labyrinthe.deplacerEntite(labyrinthe.pj, Labyrinthe.DROITE);
        }
        if (clavier.haut) {
            labyrinthe.deplacerEntite(labyrinthe.pj, Labyrinthe.HAUT);
        }
        if (clavier.bas) {
            labyrinthe.deplacerEntite(labyrinthe.pj, Labyrinthe.BAS);
        }
        Escalier escalier =labyrinthe.chercherEscalier(labyrinthe.pj.getX(),labyrinthe.pj.getY());
        if (clavier.a && escalier!=null) {
            this.changerLabyCourant(escalier.montant);
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
    public void changerLabyCourant(boolean suivant){
        if(suivant){
            nbcourant+=1;
        }else{
            nbcourant-=1;
        }
        labyrinthe=labyrinthes[nbcourant];
    }
}
