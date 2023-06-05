package mains;

import moteurJeu.MoteurJeu;
import java.io.IOException;
import gameLaby.laby.*;


public class MainLaby {
    public static void main(String[] args) throws IOException {
        int width = 800;
        int height = 600;
        int pFPS = 10;

        // creation des objets
        LabyJeu labyJeu = new LabyJeu("Dongeon1");
        LabyDessin dessinJeu = new LabyDessin();

        // parametrage du moteur de jeu
        MoteurJeu.setTaille(width,height);
        MoteurJeu.setFPS(pFPS);

        // lancement du jeu
        MoteurJeu.launch(labyJeu, dessinJeu);
    }
}
