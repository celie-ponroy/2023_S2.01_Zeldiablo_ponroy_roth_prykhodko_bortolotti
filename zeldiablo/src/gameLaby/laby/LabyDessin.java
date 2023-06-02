package gameLaby.laby;

import gameArkanoid.ArkanoidJeu;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

public class LabyDessin implements DessinJeu {
    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) {
        LabyJeu labyJeu = (LabyJeu) jeu;
        Labyrinthe labyrinthe = labyJeu.getLabyrinthe();


        // recupere un pinceau pour dessiner
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        // dessin fond
        for(int i = 0; i<labyrinthe.getLength(); i++) {
            for(int j = 0; j< labyrinthe.getLengthY(); j++) {
                Image image = new Image("/tiles.png");
                gc.drawImage(image, i * 50, j * 50, 50, 50);
            }
        }

        //dessin escalier

        for(int i = 0; i<labyrinthe.entiteInteractives.size(); i++) {
                Image escalier=new Image(labyrinthe.entiteInteractives.get(i).getImage());
                gc.drawImage(escalier, labyrinthe.entiteInteractives.get(i).getX() * 50, labyrinthe.entiteInteractives.get(i).getY() * 50, 50, 50);

        }

        //dessin perso
        Perso perso = labyrinthe.getPj();
        int x = perso.getX();
        int y = perso.getY();
        Image link = new Image("link-2.png");
        gc.drawImage(link,x*50, y*50, 50, 50);

        //dessin murs
        for(int i = 0; i<labyrinthe.getLength(); i++) {
            for(int j = 0; j< labyrinthe.getLengthY(); j++)
                if(labyrinthe.getMur(i,j)){
                    Image wall = new Image("/tiles_wall.png");//changer avec image murs
                    gc.drawImage(wall, i*50,j*50, 50, 50);
                    }
        }

        //desssin monstre

        for(int i = 0; i<labyrinthe.comb.size(); i++) {
                Image imgCombatant = new Image(labyrinthe.comb.get(i).getImage());
                labyrinthe.comb.get(i).drawComb(gc, imgCombatant, labyrinthe);
//                gc.drawImage(imgCombatant, labyrinthe.comb.get(i).getX() * 50, labyrinthe.comb.get(i).getY() * 50, 50, 50);
        }
    }

}
