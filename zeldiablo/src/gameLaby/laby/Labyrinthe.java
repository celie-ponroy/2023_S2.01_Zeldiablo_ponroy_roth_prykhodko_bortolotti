package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * classe labyrinthe. represente un labyrinthe avec
 * <ul> des murs </ul>
 * <ul> un personnage (x,y) </ul>
 */
public class Labyrinthe {

    /**
     * Constantes char
     */
    public static final char MUR = 'X';
    public static final char PJ = 'P';
    public static final char VIDE = '.';
    public static final char MONSTRE = 'M';
    public static final char ESCALIER_DESC = 'L';
    public static final char ESCALIER_MONT = 'J';
    public static final int VIE_PERSO = 10;
    public static final int VIE_MONSTRE = 9;
    public static final int ATTAQUE_PERSO = 3;
    public static final int ATTAQUE_MONSTRE = 1;

    /**
     * constantes actions possibles
     */
    public static final String HAUT = "Haut";
    public static final String BAS = "Bas";
    public static final String GAUCHE = "Gauche";
    public static final String DROITE = "Droite";



    /**
     * attribut du personnage
     */
    public Perso pj;
    public ArrayList<Entite> entites;

    /**
     * les murs du labyrinthe
     */
    public boolean[][] murs;


    /**
     * retourne la case suivante selon une actions
     *
     * @param x      case depart
     * @param y      case depart
     * @param action action effectuee
     * @return case suivante
     */
    static int[] getSuivant(int x, int y, String action) {
        switch (action) {
            case HAUT:
                // on monte une ligne
                y--;
                break;
            case BAS:
                // on descend une ligne
                y++;
                break;
            case DROITE:
                // on augmente colonne
                x++;
                break;
            case GAUCHE:
                // on augmente colonne
                x--;
                break;
            default:
                throw new Error("action inconnue");
        }
        int[] res = {x, y};
        return res;
    }

    /**
     * charge le labyrinthe
     *
     * @param nom nom du fichier de labyrinthe
     * @return labyrinthe cree
     * @throws IOException probleme a la lecture / ouverture
     */
    public Labyrinthe(String nom) throws IOException {
        // ouvrir fichier
        FileReader fichier = new FileReader(nom);
        BufferedReader bfRead = new BufferedReader(fichier);

        int nbLignes, nbColonnes;
        // lecture nblignes
        nbLignes = Integer.parseInt(bfRead.readLine());
        // lecture nbcolonnes
        nbColonnes = Integer.parseInt(bfRead.readLine());

        // creation labyrinthe vide
        this.murs = new boolean[nbColonnes][nbLignes];
        this.pj = null;
        this.entites = new ArrayList<Entite>();

        // lecture des cases
        String ligne = bfRead.readLine();

        // stocke les indices courants
        int numeroLigne = 0;

        // parcours le fichier
        while (ligne != null) {

            // parcours de la ligne
            for (int colonne = 0; colonne < ligne.length(); colonne++) {
                char c = ligne.charAt(colonne);
                switch (c) {
                    case MUR:
                        this.murs[colonne][numeroLigne] = true;
                        break;
                    case VIDE:
                        this.murs[colonne][numeroLigne] = false;
                        break;
                    case PJ:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.pj = new Perso(VIE_PERSO,ATTAQUE_PERSO, colonne, numeroLigne, true);
                        break;
                    case MONSTRE:
                        //pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        //ajoute MONSTRE
                        this.entites.add(new Monstre(VIE_MONSTRE, ATTAQUE_MONSTRE,colonne, numeroLigne, true));
                        break;
                    case ESCALIER_DESC:
                        //pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        //ajoute MONSTRE
                        this.entites.add(new Escalier(colonne, numeroLigne, false));
                        break;
                    case ESCALIER_MONT:
                        //pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        //ajoute MONSTRE
                        this.entites.add(new Escalier(colonne, numeroLigne, true));
                        break;

                    default:
                        throw new Error("caractere inconnu " + c);
                }
            }

            // lecture
            ligne = bfRead.readLine();
            numeroLigne++;
        }

        // ferme fichier
        bfRead.close();
    }


    /**
     * deplace l'entite en fonction de l'action.
     * gere la collision avec les murs
     * et les monstres
     * @param act une des actions possibles
     */

    public void deplacerEntite(Entite e, String act){
        // case courante
        int[] courante = {e.getX(), e.getY()};

        // calcule case suivante
        int[] suivante = getSuivant(courante[0], courante[1], act);

        // si c'est pas un mur, on effectue le deplacement
        if (deplacementValide(e, suivante))  {
            // on met a jour personnage
            e.deplacer(suivante);
        }
    }

    public boolean etreEntite(int x, int y){
        boolean res = false;
        for(int i = 0; i < entites.size(); i++) {
            res = entites.get(i).etrePresent(x, y);
            if (res)
                break;
        }
        return res;
    }

    public boolean deplacementValide(Entite e, int[] suivante){
        return /*un fantome qui se deplace*/!e.getCollision() ||
                /*case vide(ou avec entite) */(!this.murs[suivante[0]][suivante[1]] &&
                                            /*entite de type phantom */((etreEntite(suivante[0],suivante[1]) && !getEntite(suivante[0],suivante[1]).getCollision())
                                            || /*case vide*/!etreEntite(suivante[0],suivante[1])));
    }


    /**
     * jamais fini
     *
     * @return fin du jeu
     */
    public boolean etreFini() {
        return false;
    }

    // ##################################
    // GETTER
    // ##################################

    /**
     * return taille selon Y
     *
     * @return
     */
    public int getLengthY() {
        return murs[0].length;
    }

    /**
     * return taille selon X
     *
     * @return
     */
    public int getLength() {
        return murs.length;
    }

    /**
     * return mur en (i,j)
     * @param x
     * @param y
     * @return
     */
    public boolean getMur(int x, int y) {
        // utilise le tableau de boolean
        return this.murs[x][y];
    }

    public Entite getEntite(int x, int y){
        Entite res = null;
        for(int i = 0; i < entites.size(); i++) {
            if(entites.get(i).etrePresent(x, y)){
                res = entites.get(i);
                break;
            }
        }
        return res;
    }



    public Perso getPj() {
        return pj;
    }

    public ArrayList<Entite> getEntites() {
        return entites;
    }

    public Entite[] monstreAutour(){
        Entite[] m = new Entite[4];

        int coordX = pj.getX();
        int coordY = pj.getY();

        int[] suivantGauche = this.getSuivant(coordX, coordY, Labyrinthe.GAUCHE);
        int[] suivantDroite = this.getSuivant(coordX, coordY, Labyrinthe.DROITE);
        int[] suivantHaut = this.getSuivant(coordX, coordY, Labyrinthe.HAUT);
        int[] suivantBas = this.getSuivant(coordX, coordY, Labyrinthe.BAS);

        if(etreEntite(suivantGauche[0], suivantGauche[1])){
            Entite e = this.getEntite(suivantGauche[0], suivantGauche[1]);
            m[0] = e;
        }
        if(etreEntite(suivantDroite[0], suivantDroite[1])){
            Entite e = this.getEntite(suivantDroite[0], suivantDroite[1]);
            m[1] = e;
        }
        if(etreEntite(suivantHaut[0], suivantHaut[1])){
            Entite e = this.getEntite(suivantHaut[0], suivantHaut[1]);
            m[2] = e;
        }
        if(etreEntite(suivantBas[0], suivantBas[1])){
            Entite e = this.getEntite(suivantBas[0], suivantBas[1]);
            m[3] = e;
        }

        return m;
    }
}
