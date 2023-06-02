package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

        Arrays.sort(paths);
        for (File path : paths) {
            this.labyrinthes.add(new Labyrinthe(path.getPath()));
            i++;
        }
        this.labyrinthe=labyrinthes.get(nbcourant);


    }
    public void update(double secondes, Clavier clavier){
        if (clavier.droite) {
            labyrinthe.deplacerCombattant(labyrinthe.pj, Labyrinthe.DROITE);
        }
        if (clavier.gauche) {
            labyrinthe.deplacerCombattant(labyrinthe.pj, Labyrinthe.GAUCHE);
        }
        if (clavier.droite) {
            labyrinthe.deplacerCombattant(labyrinthe.pj, Labyrinthe.DROITE);
        }
        if (clavier.haut) {
            labyrinthe.deplacerCombattant(labyrinthe.pj, Labyrinthe.HAUT);
        }
        if (clavier.bas) {
            labyrinthe.deplacerCombattant(labyrinthe.pj, Labyrinthe.BAS);
        }
        Escalier escalier =labyrinthe.chercherEntitéeInteractive(labyrinthe.pj.getX(),labyrinthe.pj.getY());
        if (clavier.a && escalier!=null) {
            this.changerLabyCourant(escalier.montant);
        }

    }
    public void init() {
        //rien
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
        int x = labyrinthe.pj.getX();
        int y = labyrinthe.pj.getY();

        labyrinthe=labyrinthes.get(nbcourant);

        labyrinthe.pj.setX(x);
        labyrinthe.pj.setY(y);
    }
}
