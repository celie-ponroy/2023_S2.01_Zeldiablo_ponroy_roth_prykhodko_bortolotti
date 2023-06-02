package gameLaby.laby;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Serpent extends Combattant{

    private ArrayList<SerPart> snake;
    private int taille;

    /**
     * Constructeur Entite
     *
     * @param vie
     * @param f
     * @param x   abscisse
     * @param y   ordonnée
     * @param col
     */
    public Serpent(int x, int y, int size) {
        super(Labyrinthe.VIE_SERPENT, x, y, true);
        snake = new ArrayList<>();
        SerPart sp = new SerPart(x, y);

        taille = size;
    }


    @Override
    public void deplacer(int[] suiv) {
        SerPart sp = new SerPart(suiv[0], suiv[1]);
        snake.add(sp);

        if (snake.size()==taille){
            snake.remove(snake.size()-1);
        }
        this.setX(suiv[0]);
        this.setY(suiv[1]);
    }

    @Override
    public void attaquer(Combattant c) {
        c.etreAttaque(Labyrinthe.ATTAQUE_MONSTRE);
    }

    @Override
    public void etreAttaque(int force) {
        int pvRestant = this.getPv() - force;
        this.setPv(pvRestant);
    }

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

    public void drawComb(GraphicsContext gc, Image imgCombatant, Labyrinthe labyrinthe){
        for (SerPart s : snake ){
            gc.drawImage(imgCombatant, s.getX() * 50, s.getY() * 50, 50, 50);
        }
    }

    @Override
    public String getImage() {
        return "/Tetes_serp.png";
    }
}
