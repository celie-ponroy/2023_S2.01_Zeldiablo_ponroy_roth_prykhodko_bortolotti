package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import gameLaby.entites.*;

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
                        // ajoute mur
                        this.murs[colonne][numeroLigne] = true;
                        break;
                    case VIDE:
                        // ajoute case vide
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
            // on verifie si un combattant se trouve aux coordonnees x y
            res = comb.get(i).etrePresent(x, y);
            if (res)
                break;
        }
        return res;
    }

    public boolean deplacementValide(Combattant c, int[] suivante){
        // si il y a un mur
        if (suivante[0]<0 || suivante[0] > murs.length-1 || suivante[1] < 0 || suivante[1] > murs[0].length-1)
            return false;

        return /*un fantome qui se deplace*/!c.getCollision() ||
                /*case vide(ou avec entite) */(!this.murs[suivante[0]][suivante[1]] &&
                        /*entite de type fantome */((etreCombattant(suivante[0],suivante[1]) &&
                                                    !getCombattant(suivante[0],suivante[1]).getCollision()) ||
                        /*case vide*/!etreCombattant(suivante[0],suivante[1])));
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
                // on recupere le combattant se trouvant aux coordonnees x y
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

    public ArrayList<Combattant> combattantAutourPerso(Combattant c) {
        // liste des Combattants autour du perso
        ArrayList<Combattant> m = new ArrayList<>();

        // recupere les coordonnees du combattant en parametre
        int coordX = c.getX();
        int coordY = c.getY();

        // tableau stockant les directions vers lesquelles on peut attaquer
        String[] str = { Labyrinthe.GAUCHE, Labyrinthe.DROITE, Labyrinthe.HAUT, Labyrinthe.BAS };

        for (String s : str ) {
            // coordonnees de la case suivante selon l'action
            int[] suiv = this.getSuivant(coordX, coordY, s);
            // renvoie l'eventuel combattant aux coordonnees suiv
            Combattant c1 = this.getCombattant(suiv[0], suiv[1]);
            if (c1!=null)
                // si il y a effectivement un combattant, on l'ajoute a la liste m
                m.add(c1);
        }

        return m;
    }

    public boolean persoAutour(Combattant c) {
        boolean res = false;

        // recupere les coordonnees du combattant en parametre
        int coordX = c.getX();
        int coordY = c.getY();

        // recupere les coordonnees du perso
        int coordPersoX = this.pj.getX();
        int coordPersoY = this.pj.getY();

        // on teste si le perso est autour du combattant en parametre
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
        // donne un chiffre aleatoire 1, 2, 3 ou 4
        int valeur = (int) Math.floor ((Math.random() * 4) + 1) ;
        // donne une direction selon le chiffre obtenu
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

        // on parcourt la liste des combattants du labyrinthe
        for(Combattant c : comb){
            // si le monstre est mort, on enleve sa collision
            if (c.etreMort()){
                c.setCollision(false);
                continue;
            }

            // fait attaquer le monstre si le perso est autour
            if(this.persoAutour(c)){
                c.attaquer(pj);
            }
            // si le perso n'est pas autour, le monstre se deplace
            else{
                String action = deplacementAleatoire();
                this.deplacerCombattant(c, action);
            }
        }
    }
}
