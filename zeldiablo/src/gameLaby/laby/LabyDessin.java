package gameLaby.laby;

import gameArkanoid.ArkanoidJeu;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        //netoyer le canvas
        gc.setFill(Color.BLACK);

        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

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

        if(perso.getPv()==0){        //faire si le perso meurt
            Image link = new Image("link-mort.png");
            gc.drawImage(link,x*50, y*50, 50, 50);
        }else{
            Image link = new Image("link-2.png");
            gc.drawImage(link,x*50, y*50, 50, 50);
            for( int i=0; i<perso.getPv();i++){
                Image coeur = new Image("/coeur.png");
                gc.drawImage(coeur,0+(i*50),canvas.getHeight()-50,50,50);
            }
        }
        


        //dessin murs
        for(int i = 0; i<labyrinthe.getLength(); i++) {
            for(int j = 0; j< labyrinthe.getLengthY(); j++)
                if(labyrinthe.getMur(i,j)){
                    Image wall = new Image("/tiles_wall.png");
                    gc.drawImage(wall, i*50,j*50, 50, 50);
                    }
        }

        //desssin monstres

        for(int i = 0; i<labyrinthe.comb.size(); i++) {
                if (labyrinthe.comb.get(i).etreMort())
                    continue;

                Image imgCombatant = new Image(labyrinthe.comb.get(i).getImage());
                labyrinthe.comb.get(i).drawComb(gc, imgCombatant, labyrinthe);

        }
    }

}
