package gameLaby.laby;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;
import gameLaby.entites.*;

/**
 * class LabyDessin
 */
public class LabyDessin implements DessinJeu {
    static int tailleCase = 50;

    /**
     * Méthode qui dessine un jeu
     *
     * @param jeu    jeu a afficher
     * @param canvas canvas dans lequel dessiner l'etat du jeu
     */
    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) {


        LabyJeu labyJeu = (LabyJeu) jeu;
        Labyrinthe labyrinthe = labyJeu.getLabyrinthe();


        // recupere un pinceau pour dessiner
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        //netoyer le canvas
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        dessinSol(labyrinthe, gc);
        dessinEntiteInteractives(labyrinthe, gc);
        dessinPerso(labyrinthe, canvas);
        dessinMurs(labyrinthe, gc);
        dessinCombatants(labyrinthe, gc);

        if (jeu.etreFini()) {
            if (labyrinthe.getPj().getPv() == 0) {
                gameover(canvas, labyrinthe);
            } else {
                win(canvas, labyrinthe);
            }
        }

    }

    /**
     * affichage de l'écran quand le jeu est win
     *
     * @param canvas     canvas utilisé pour l'affichage
     * @param labyrinthe
     */
    private void win(Canvas canvas, Labyrinthe labyrinthe) {
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //Texte
        Image youWin = new Image("/youWin.png");
        double centerXwin = (canvas.getWidth() - youWin.getWidth()) / 2;
        double centerYwin = (canvas.getHeight() - youWin.getHeight()) / 2;
        gc.drawImage(youWin, centerXwin, centerYwin + tailleCase * 2);
        //dessiner le perso par dessus le texte
        Perso perso = labyrinthe.getPj();
        int x = perso.getX();
        int y = perso.getY();

        Image link = new Image("/link-win.png");
        double centerX = (canvas.getWidth() - link.getWidth() * 2) / 2;//a changer
        double centerY = (canvas.getHeight() - link.getHeight() * 2) / 2;
        gc.drawImage(link, centerX, centerY, (link.getWidth() * 2), (link.getHeight() * 2));

    }

    /**
     * affichage de l'écran gameOver
     *
     * @param canvas     canvas utilisé pour l'affichage
     * @param labyrinthe
     */

    private void gameover(Canvas canvas, Labyrinthe labyrinthe) {
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
        gc.drawImage(link, x * tailleCase, y * tailleCase, tailleCase, tailleCase);
    }

    /**
     * Méthode qui dessine le sol du niveau
     *
     * @param labyrinthe
     * @param gc         GraphicContent utilisé our afficher
     */
    private void dessinSol(Labyrinthe labyrinthe, GraphicsContext gc) {

        for (int i = 0; i < labyrinthe.getLength(); i++) {
            for (int j = 0; j < labyrinthe.getLengthY(); j++) {
                Image image = new Image("/tiles.png");
                gc.drawImage(image, i * tailleCase, j * tailleCase, tailleCase, tailleCase);
            }
        }
    }

    /**
     * Méthode qui dessine les Entités interracives du niveau
     *
     * @param labyrinthe
     * @param gc         GraphicContent utilisé our afficher
     */

    private void dessinEntiteInteractives(Labyrinthe labyrinthe, GraphicsContext gc) {
        for (int i = 0; i < labyrinthe.entiteInteractives.size(); i++) {
            Image escalier = new Image(labyrinthe.entiteInteractives.get(i).getImage());
            gc.drawImage(escalier, labyrinthe.entiteInteractives.get(i).getX() * 50, labyrinthe.entiteInteractives.get(i).getY() * tailleCase, tailleCase, tailleCase);

        }
    }

    /**
     * Dessine le personnage du labyrinthe
     *
     * @param labyrinthe
     * @param canvas     canvas utilisé pour l'affichage
     */
    private void dessinPerso(Labyrinthe labyrinthe, Canvas canvas) {
        //dessin perso
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        Perso perso = labyrinthe.getPj();
        int x = perso.getX();
        int y = perso.getY();

        if (perso.getPv() == 0) {        //faire si le perso meurt
            Image link = new Image("link-mort.png");
            gc.drawImage(link, x * tailleCase, y * tailleCase, tailleCase, tailleCase);
        } else {
            Image link = new Image("link-2.png");
            gc.drawImage(link, x * tailleCase, y * tailleCase, tailleCase, tailleCase);
            for (int i = 0; i < perso.getPv(); i++) {
                Image coeur = new Image("/coeur.png");
                gc.drawImage(coeur, 0 + (i * tailleCase), canvas.getHeight() - tailleCase, tailleCase, tailleCase);
            }
        }

    }

    /**
     * dessine les murs du labyrinthe
     *
     * @param labyrinthe
     * @param gc         GraphicContent utilisé our afficher
     */
    private void dessinMurs(Labyrinthe labyrinthe, GraphicsContext gc) {
        for (int i = 0; i < labyrinthe.getLength(); i++) {
            for (int j = 0; j < labyrinthe.getLengthY(); j++)
                if (labyrinthe.getMur(i, j)) {
                    Image wall = new Image("/tiles_wall.png");
                    gc.drawImage(wall, i * tailleCase, j * tailleCase, tailleCase, tailleCase);
                }
        }
    }

    /**
     * dessin des combatants
     *
     * @param labyrinthe
     * @param gc         GraphicContent utilisé our afficher
     */
    private void dessinCombatants(Labyrinthe labyrinthe, GraphicsContext gc) {
        for (int i = 0; i < labyrinthe.comb.size(); i++) {
            if (labyrinthe.comb.get(i).etreMort())
                continue;
            Image imgCombatant = new Image(labyrinthe.comb.get(i).getImage());
            labyrinthe.comb.get(i).drawComb(gc, imgCombatant);
        }
    }

}
