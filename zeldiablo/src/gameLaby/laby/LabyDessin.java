package gameLaby.laby;

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
            //faire un game over
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
        if (jeu.etreFini()){
            if(perso.getPv()==0){
                gameover(canvas,labyrinthe);
            }else {
                win(canvas,labyrinthe);
            }
        }

    }
    public void win ( Canvas canvas,Labyrinthe labyrinthe){
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //Texte
        Image youWin = new Image("/youWin.png");
        double centerXwin = (canvas.getWidth() - youWin.getWidth()) / 2;
        double centerYwin = (canvas.getHeight() - youWin.getHeight()) / 2;
        gc.drawImage(youWin, centerXwin, centerYwin+100);
        //dessiner le perso par dessus le texte
        Perso perso = labyrinthe.getPj();
        int x = perso.getX();
        int y = perso.getY();

        Image link = new Image("/link-win.png");
        double centerX = (canvas.getWidth() - link.getWidth()*2) / 2;//a changer
        double centerY = (canvas.getHeight() - link.getHeight()*2) / 2;
        gc.drawImage(link, centerX, centerY,(link.getWidth() *2),(link.getHeight()*2));

    }
    public void gameover( Canvas canvas,Labyrinthe labyrinthe) {
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //ajouter texte
        Image gameOver = new Image("/gameOver.png");
        double centerX = (canvas.getWidth() - gameOver.getWidth()) / 2;
        double centerY = (canvas.getHeight() - gameOver.getHeight()) / 2;
        gc.drawImage(gameOver, centerX, centerY);
        //dessiner le perso par dessus le texte
        Perso perso = labyrinthe.getPj();
        int x = perso.getX();
        int y = perso.getY();

        Image link = new Image("link-mort.png");
        gc.drawImage(link, x * 50, y * 50, 50, 50);
    }


}
