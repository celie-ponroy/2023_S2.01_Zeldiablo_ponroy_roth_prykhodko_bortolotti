package gameLaby.laby;

import gameArkanoid.ArkanoidJeu;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

public class LabyDessin implements DessinJeu {
    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) {
        LabyJeu labyJeu = (LabyJeu) jeu;

        // recupere un pinceau pour dessiner
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        // dessin fond
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //dessin perso
        gc.setFill(Color.RED);
        Labyrinthe labyrinthe = labyJeu.getLabyrinthe();
        Perso perso = labyrinthe.getPj();
        int x = perso.getX();
        int y = perso.getY();
        gc.fillOval(x*50, y*50, 50, 50);

        //dessin murs
        gc.setFill(Color.BLACK);
        for(int i = 0; i<labyrinthe.getLength(); i++) {
            for(int j = 0; j< labyrinthe.getLengthY(); j++)
                if(labyrinthe.getMur(i,j))
                    gc.fillRect(i*50,j*50,50,50);
        }

        //desssin monstre

    }
}
