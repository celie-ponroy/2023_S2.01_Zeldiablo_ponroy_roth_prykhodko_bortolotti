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
            labyrinthe.comportementMonstre();
        }
        if (clavier.gauche) {
            labyrinthe.deplacerCombattant(labyrinthe.pj, Labyrinthe.GAUCHE);
            labyrinthe.comportementMonstre();
        }
        if (clavier.haut) {
            labyrinthe.deplacerCombattant(labyrinthe.pj, Labyrinthe.HAUT);
            labyrinthe.comportementMonstre();
        }
        if (clavier.bas) {
            labyrinthe.deplacerCombattant(labyrinthe.pj, Labyrinthe.BAS);
            labyrinthe.comportementMonstre();
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
//        return labyrinthe.etreFini();
        boolean res = false;
        //perso mort
        if (labyrinthe.pj.etreMort()) {
            res = true;
            return res;
        }
//        Labyrinthe l = labyrinthes.get(labyrinthes.size()-1);
//        boss mort
//        if (){
//
//
//        }
        return res;
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

        Perso perso = labyrinthe.pj;

        labyrinthe=labyrinthes.get(nbcourant);

        labyrinthe.pj=perso;
    }


}
