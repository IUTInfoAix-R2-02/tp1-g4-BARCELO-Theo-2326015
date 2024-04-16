package fr.amu.iut.exercice5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class JeuMain extends Application {

    private Scene scene;
    private BorderPane root;

    private boolean canMove = true;

    private ArrayList<Obstacle> obstacles;

    private long startTimer = System.currentTimeMillis();
    private long endTimer = startTimer + 10 * 1000;

    @Override
    public void start(Stage primaryStage) {

        root = new BorderPane();

        //Acteurs du jeu
        Personnage pacman = new Pacman();
        Personnage fantome = new Fantome();

        // on positionne le fantôme 20 positions vers la droite
        fantome.setLayoutX(20 * 10);

        // obstacles
        obstacles = new ArrayList<Obstacle>(Arrays.asList(
            new Obstacle(4, 4, 10, 5),
            new Obstacle(7, 8, 10, 5)
        ));

        //panneau du jeu
        Pane jeu = new Pane();
        jeu.setPrefSize(640, 480);
        jeu.getChildren().add(pacman);
        jeu.getChildren().add(fantome);
        jeu.getChildren().addAll(obstacles);

        root.setCenter(jeu);

        //on construit une scene 640 * 480 pixels
        scene = new Scene(root);

        //Gestion du déplacement du personnage
        deplacer(pacman, fantome);

        primaryStage.setTitle("... Pac Man ...");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Permet de gérer les événements de type clavier, pression des touches
     * pour le j1(up,down, right, left), pour le j2( z,q,s,d)
     *
     * @param j1
     * @param j2
     */
    private void deplacer(Personnage j1, Personnage j2) {
        scene.setOnKeyPressed((KeyEvent event) -> {
            if (System.currentTimeMillis() > endTimer) return;
            if(!canMove) return;
            switch (event.getCode()) {
                case LEFT:
                    j1.deplacerAGauche();
                    break;
                case RIGHT:
                    j1.deplacerADroite(scene.getWidth());
                    break;
                case UP:
                    j1.deplacerEnHaut();
                    break;
                case DOWN:
                    j1.deplacerEnBas(scene.getHeight());
                    break;
                case Z:
                    j2.deplacerEnHaut();
                    break;
                case Q:
                    j2.deplacerAGauche();
                    break;
                case S:
                    j2.deplacerEnBas(scene.getHeight());
                    break;
                case D:
                    j2.deplacerADroite(scene.getWidth());
                    break;
            }
            if (j1.estEnCollision(j2)) {
                System.out.println("Collision....");
                canMove = false;
                j2.setOpacity(0);
            }
            for (int i = 0; i < obstacles.size(); ++i) {
                Obstacle o = obstacles.get(i);
                if(j1.estEnCollisionObstacle(o)) {
                    j1.setLayoutX(j1.getOldX());
                    j1.setLayoutY(j1.getOldY());
                    System.out.println("j1 Collision Obstacle....");
               }
                if(j2.estEnCollisionObstacle(o)) {
                    System.out.println("j2 Collision Obstacle....");
               }
            }
        });
    }


}
