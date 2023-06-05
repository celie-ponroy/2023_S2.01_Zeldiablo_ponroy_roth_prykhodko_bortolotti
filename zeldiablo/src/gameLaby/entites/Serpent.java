package gameLaby.entites;
import gameLaby.laby.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;


/**
 * Classe représentant un serpent dans le jeu.
 */
public class Serpent extends Combattant{

    private ArrayList<SerPart> snake;
    private int taille;

    /**
     * Constructeur du Serpent.
     *
     * @param x   Position x du serpent sur la grille.
     * @param y   Position y du serpent sur la grille.
     * @param size Taille du serpent.
     */
    public Serpent(int x, int y, int size) {
        super(Labyrinthe.VIE_SERPENT, x, y, true);
        snake = new ArrayList<>();
        SerPart sp = new SerPart(x, y);
        snake.add(sp);

        taille = size;
    }

    /**
     * Méthode permettant le déplacement du serpent.
     *
     * @param suiv Position suivante du serpent.
     */
    @Override
    public void deplacer(int[] suiv) {
        SerPart sp = new SerPart(suiv[0], suiv[1]);
        snake.add(0, sp);

        if (snake.size()==taille+1){
            snake.remove(snake.size()-1);
        }
        this.setX(suiv[0]);
        this.setY(suiv[1]);
    }


    /**
     * Méthode permettant l'attaque par le serpent.
     *
     * @param c Combattant cible de l'attaque.
     */
    @Override
    public void attaquer(Combattant c) {
        c.etreAttaque(Labyrinthe.ATTAQUE_MONSTRE);
    }

    /**
     * Méthode gérant l'attaque subie par le serpent.
     *
     * @param force Force de l'attaque subie.
     */
    @Override
    public void etreAttaque(int force) {
        int pvRestant = this.getPv() - force;
        this.setPv(pvRestant);
    }

    /**
     * Indique si le serpent est présent à une position spécifique.
     *
     * @param dx La coordonnée x à vérifier.
     * @param dy La coordonnée y à vérifier.
     * @return true si le serpent est à la position (dx, dy), false sinon.
     */
    @Override
    public boolean etrePresent(int dx, int dy) {
        boolean res = false;
        for (SerPart s : snake){
            if (s.etrePresent(dx,dy)){
                res = true;
                break;
            }
        }

        return res;
    }

    /**
     * Méthode permettant de dessiner le serpent.
     *
     * @param gc Contexte graphique dans lequel le serpent doit être dessiné.
     * @param imgCombatant Image du serpent.
     */
    @Override
    public void drawComb(GraphicsContext gc, Image imgCombatant){
        if(snake.size() == 0)
            return;

        imgCombatant = new Image(getImage());
        gc.drawImage(imgCombatant, snake.get(0).getX() * 50, snake.get(0).getY() * 50, 50, 50);
        imgCombatant = new Image(snake.get(0).getImage());
        for (int i = 1; i < snake.size(); i++) {
            gc.drawImage(imgCombatant, snake.get(i).getX() * 50, snake.get(i).getY() * 50, 50, 50);
        }
    }

    /**
     * Renvoie le chemin de l'image représentant la tête du serpent.
     *
     * @return Une chaîne de caractères représentant le chemin de l'image de la tête du serpent.
     */
    @Override
    public String getImage() {
        return "/Tetes_serp.png";
    }
}
