package moteurJeu;

//https://github.com/zarandok/megabounce/blob/master/MainCanvas.java

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;


// copied from: https://gist.github.com/james-d/8327842
// and modified to use canvas drawing instead of shapes

public class MoteurJeu extends Application {

    /**
     * gestion du temps : nombre de frame par secondes et temps par iteration
     */
    private static double FPS = 100;
    private static double dureeFPS = 1000 / (FPS + 1);

    /**
     * taille par defaut
     */
    private static double WIDTH = 1000;
    private static double HEIGHT = 600;

    /**
     * statistiques sur les frames
     */
    private final FrameStats frameStats = new FrameStats();

    /**
     * jeu en Cours et renderer du jeu
     */
    private static Jeu jeu = null;
    private static DessinJeu dessin = null;

    /**
     * touches appuyee entre deux frame
     */
    Clavier controle = new Clavier();


    /**
     * lancement d'un jeu
     *
     * @param jeu    jeu a lancer
     * @param dessin dessin du jeu
     */
    public static void launch(Jeu jeu, DessinJeu dessin) {
        // le jeu en cours et son afficheur
        MoteurJeu.jeu = jeu;
        MoteurJeu.dessin = dessin;

        // si le jeu existe, on lance le moteur de jeu
        if (jeu != null)
            launch();
    }

    /**
     * frame par secondes
     *
     * @param FPSSouhaitees nombre de frames par secondes souhaitees
     */
    public static void setFPS(int FPSSouhaitees) {
        FPS = FPSSouhaitees;
        dureeFPS = 1000 / (FPS + 1);
    }

    public static void setTaille(double width, double height) {
        WIDTH = width;
        HEIGHT = height;
    }


    //#################################
    // SURCHARGE Application
    //#################################
    protected MediaPlayer media_player;
    public void start(Stage primaryStage) {
        VBox pane = new VBox();
        Scene s = new Scene(pane, WIDTH, HEIGHT);

        //ajout de l'image de fond
        Image img = new Image("/final.png");
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(WIDTH, HEIGHT, false, false, false, false));
        Background bGround = new Background(bImg);
        pane.setBackground(bGround);

        //ajout du boutton play
        Image img1 = new Image("/play.png");
        ImageView start = new ImageView(img1);
        start.setFitWidth(200);
        start.setFitHeight(110);
        start.setTranslateX(300);
        start.setTranslateY(400);
        pane.getChildren().add(start);

        //ajout du boutton quit
        Image img2 = new Image("/quit.png");
        ImageView quit = new ImageView(img2);
        quit.setFitWidth(160);
        quit.setFitHeight(90);
        quit.setTranslateX(320);
        quit.setTranslateY(400);
        pane.getChildren().add(quit);

        //ajout d'une musique de fond
<<<<<<< HEAD
        String path = "musique/menu.mp3";
        Media media = new Media(new File(path).toURI().toString());
        media_player = new MediaPlayer(media);
       media_player.play();
=======
        String chemin = "musique/menu.mp3";
        Media menu = new Media(new File(chemin).toURI().toString());
        media_player = new MediaPlayer(menu);
>>>>>>> f5fffe597b634a09d2d39bf41d6417314511f6bb

        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent MouseEvent) {
                startJeu(primaryStage);
            }
        });

        /*start.setOnMouseDragOver(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent mouseDragEvent) {
                start.setFitWidth(230);
                start.setFitHeight(150);
            }
        });
        
         */

        quit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.close();
            }
        });


        primaryStage.setScene(s);
        primaryStage.show();
    }


    /**
     * creation de l'application avec juste un canvas et des statistiques
     */
    public void startJeu(Stage primaryStage) {
        // initialisation du canvas de dessin et du container
        final Canvas canvas = new Canvas();
        final Pane canvasContainer = new Pane(canvas);
        canvas.widthProperty().bind(canvasContainer.widthProperty());
        canvas.heightProperty().bind(canvasContainer.heightProperty());


        // affichage des stats
        final Label stats = new Label();
        stats.textProperty().bind(frameStats.textProperty());

        // ajout des statistiques en bas de la fenetre
        final BorderPane root = new BorderPane();
        root.setCenter(canvasContainer);
        root.setBottom(stats);

        // creation de la scene
        final Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();


        // listener clavier
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controle.appuyerTouche(event);
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controle.relacherTouche(event);
            }
        });


        // creation du listener souris
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() == 2) {
                            jeu.init();
                        }
                    }
                });

        // lance la boucle de jeu
        startAnimation(canvas);
    }

    /**
     * gestion de l'animation (boucle de jeu)
     *
     * @param canvas le canvas sur lequel on est synchronise
     */
    private void startAnimation(final Canvas canvas) {
        // stocke la derniere mise e jour
        final LongProperty lastUpdateTime = new SimpleLongProperty(0);

        // timer pour boucle de jeu
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                //fin du jeu
                if (jeu.etreFini()) {
                    this.stop();
                    return;
                }

                // si jamais passe dans la boucle, initialise le temps
                if (lastUpdateTime.get() == 0) {
                    lastUpdateTime.set(timestamp);
                }

                // mesure le temps ecoule depuis la derniere mise a jour
                long elapsedTime = timestamp - lastUpdateTime.get();
                double dureeEnMilliSecondes = elapsedTime / 1_000_000.0;


                // si le temps ecoule depasse le necessaire pour FPS souhaite
                if (dureeEnMilliSecondes > dureeFPS) {
                    // met a jour le jeu en passant les touches appuyees
                    jeu.update(dureeEnMilliSecondes / 1_000., controle);

                    // dessine le jeu
                    dessin.dessinerJeu(jeu, canvas);

                    // ajoute la duree dans les statistiques
                    frameStats.addFrame(elapsedTime);

                    // met a jour la date de derniere mise a jour
                    lastUpdateTime.set(timestamp);
                }

            }
        };

        // lance l'animation
        timer.start();
    }
}