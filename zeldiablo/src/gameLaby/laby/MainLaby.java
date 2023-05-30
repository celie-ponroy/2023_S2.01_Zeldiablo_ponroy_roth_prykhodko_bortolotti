package gameLaby.laby;

import gameArkanoid.ArkanoidDessin;
import gameArkanoid.ArkanoidJeu;
import moteurJeu.DessinJeu;
import moteurJeu.MoteurJeu;

import java.io.IOException;

public class MainLaby {
    public static void main(String[] args) throws IOException {
        int width = 800;
        int height = 600;
        int pFPS = 60;

        // creation des objets
        LabyJeu labyJeu = new LabyJeu("labySimple/laby1.txt");
        LabyDessin dessinJeu = new LabyDessin();

        // parametrage du moteur de jeu
        MoteurJeu.setTaille(width,height);
        MoteurJeu.setFPS(pFPS);

        // lancement du jeu
        MoteurJeu.launch(labyJeu, dessinJeu);
    }
}
