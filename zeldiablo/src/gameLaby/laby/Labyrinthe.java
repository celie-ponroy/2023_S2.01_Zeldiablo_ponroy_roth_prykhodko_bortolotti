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
    public static final char FANTOME = 'F';
    public static final char SERPENT = 'S';
    public static final char TROLL = 'T';
    public static final char ESCALIER_DESC = 'L';
    public static final char ESCALIER_MONT = 'J';
    public static final int VIE_PERSO = 10;
    public static final int VIE_MONSTRE = 9;
    public static final int VIE_FANTOME = 9;
    public static final int VIE_TROLL = 6;
    public static final int VIE_SERPENT = 12;

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
    public ArrayList<Combattant> comb;
    public ArrayList<EntiteInteractives> entiteInteractives;

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
        this.entiteInteractives = new ArrayList<EntiteInteractives>();
        this.comb = new ArrayList<Combattant>();


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
                        this.pj = new Perso(colonne, numeroLigne, true);
                        break;
                    case MONSTRE:
                        //pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        //ajoute MONSTRE
                        this.comb.add(new Monstre(VIE_MONSTRE, colonne, numeroLigne, true));
                        break;
                    case TROLL:
                        //pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        //ajoute TROLL
                        this.comb.add(new Troll(colonne, numeroLigne));
                        break;
                    case FANTOME:
                        //pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        //ajoute MONSTRE
                        this.comb.add(new Fantome(colonne, numeroLigne));
                        break;
                    case SERPENT:
                        //pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        //ajoute MONSTRE
                        this.comb.add(new Serpent(colonne, numeroLigne, 4));
                        break;
                    case ESCALIER_DESC:
                        //pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        //ajoute MONSTRE
                        this.entiteInteractives.add(new Escalier(colonne, numeroLigne, false));
                        break;
                    case ESCALIER_MONT:
                        //pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        //ajoute MONSTRE
                        this.entiteInteractives.add(new Escalier(colonne, numeroLigne, true));
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

    public void deplacerCombattant(Combattant c, String act){
        // case courante
        int[] courante = {c.getX(), c.getY()};

        // calcule case suivante
        int[] suivante = getSuivant(courante[0], courante[1], act);

        // si c'est pas un mur, on effectue le deplacement
        if (deplacementValide(c, suivante))  {
            // on met a jour personnage
            c.deplacer(suivante);
        }
    }

    public boolean etreCombattant(int x, int y){
        boolean res = false;
        for(int i = 0; i < comb.size(); i++) {
            res = comb.get(i).etrePresent(x, y);
            if (res)
                break;
        }
        return res;
    }

    public boolean deplacementValide(Combattant c, int[] suivante){
        return /*un fantome qui se deplace*/(!c.getCollision() && suivante[0]>0 && suivante[0] < murs.length-2 && suivante[1] > 0 && suivante[1] < murs[0].length-2 )||
                /*case vide(ou avec entite) */(!this.murs[suivante[0]][suivante[1]]
                &&/*entite de type phantom */((etreCombattant(suivante[0],suivante[1])
                && !getCombattant(suivante[0],suivante[1]).getCollision())
                || /*case vide*/!etreCombattant(suivante[0],suivante[1])));
    }


    /**
     * jamais fini
     *
     * @return fin du jeu
     */
    public boolean etreFini() {
        return true;
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

    public Combattant getCombattant(int x, int y){
        Combattant res = null;
        for(int i = 0; i < comb.size(); i++) {
            if(comb.get(i).etrePresent(x, y)){
                res = comb.get(i);
                break;
            }
        }
        return res;
    }

    public Perso getPj() {
        return pj;
    }

    public ArrayList<Combattant> getComb() {
        return comb;
    }

    public ArrayList<EntiteInteractives> getEntiteInteractives() {
        return entiteInteractives;
    }

    public Combattant[] combattantAutourPerso(Combattant c) {
        Combattant[] m = new Combattant[4];

        int coordX = c.getX();
        int coordY = c.getY();

        int[] suivantGauche = this.getSuivant(coordX, coordY, Labyrinthe.GAUCHE);
        int[] suivantDroite = this.getSuivant(coordX, coordY, Labyrinthe.DROITE);
        int[] suivantHaut = this.getSuivant(coordX, coordY, Labyrinthe.HAUT);
        int[] suivantBas = this.getSuivant(coordX, coordY, Labyrinthe.BAS);

        if (etreCombattant(suivantGauche[0], suivantGauche[1])) {
            Combattant c1 = this.getCombattant(suivantGauche[0], suivantGauche[1]);
            m[0] = c1;
        }
        if (etreCombattant(suivantDroite[0], suivantDroite[1])) {
            Combattant c2 = this.getCombattant(suivantDroite[0], suivantDroite[1]);
            m[1] = c2;
        }
        if (etreCombattant(suivantHaut[0], suivantHaut[1])) {
            Combattant c3 = this.getCombattant(suivantHaut[0], suivantHaut[1]);
            m[2] = c3;
        }
        if (etreCombattant(suivantBas[0], suivantBas[1])) {
            Combattant c4 = this.getCombattant(suivantBas[0], suivantBas[1]);
            m[3] = c4;
        }

        return m;
    }

    public boolean persoAutour(Combattant c) {
        boolean res = false;

        int coordX = c.getX();
        int coordY = c.getY();

        int coordPersoX = this.pj.getX();
        int coordPersoY = this.pj.getY();

        if ((coordX == coordPersoX) && (coordY + 1 == coordPersoY)) {
            res = true;
        }
        else if ((coordX == coordPersoX) && (coordY - 1 == coordPersoY)) {
            res = true;
        }
        else if ((coordX + 1 == coordPersoX) && (coordY == coordPersoY)) {
            res = true;
        }
        else if ((coordX - 1 == coordPersoX) && (coordY == coordPersoY)) {
            res = true;
        }

        return res;
    }


    public Escalier chercherEntitéeInteractive(int x, int y){
        Escalier res =null;
        for(int i = 0; i<this.entiteInteractives.size();i++){
            if(entiteInteractives.get(i) instanceof Escalier){
                if(entiteInteractives.get(i).getX()==x&& entiteInteractives.get(i).getY()==y){
                    res=(Escalier) entiteInteractives.get(i);
                }
            }

        }
        return res;
    }

    public String deplacementAleatoire(){
        String res = "";
        int valeur = (int) Math.floor ((Math.random() * 4) + 1) ;
        switch (valeur){
            case 1:
                res = Labyrinthe.DROITE;
                break;
            case 2:
                res = Labyrinthe.GAUCHE;
                break;
            case 3:
                res = Labyrinthe.HAUT;
                break;
            case 4:
                res = Labyrinthe.BAS;
                break;
        }
        return res;
    }

    public void comportementMonstre(){
        for(Combattant c : comb){
            if(this.persoAutour(c)){
                c.attaquer(pj);
            }
            else{
                String action = deplacementAleatoire();
                this.deplacerCombattant(c, action);
            }
        }
    }
}
