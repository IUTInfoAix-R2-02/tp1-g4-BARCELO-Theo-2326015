package fr.amu.iut.exercice6;

import fr.amu.iut.exercice5.Obstacle;
import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class IHMPendu extends Application {
    private int nbVies = 7;
    private VBox root;
    private Dico dictionnaire;
    private String motActuel;
    private Label mot;
    private Label vie;
    private Button boutonRejouer;
    private ArrayList<Integer> listePosDecouverte = new ArrayList<Integer>();
    boolean aGagne = false;
    boolean aPerdu = false;

    private ImageView iv;

    // Chargement de l'image
    private static Image pendu0 = new Image( IHMPendu.class.getResource("/exercice6/pendu0.png").toString() );
    private static Image pendu1 = new Image( IHMPendu.class.getResource("/exercice6/pendu1.png").toString() );
    private static Image pendu2 = new Image( IHMPendu.class.getResource("/exercice6/pendu2.png").toString() );
    private static Image pendu3 = new Image( IHMPendu.class.getResource("/exercice6/pendu3.png").toString() );
    private static Image pendu4 = new Image( IHMPendu.class.getResource("/exercice6/pendu4.png").toString() );
    private static Image pendu5 = new Image( IHMPendu.class.getResource("/exercice6/pendu5.png").toString() );
    private static Image pendu6 = new Image( IHMPendu.class.getResource("/exercice6/pendu6.png").toString() );
    private static Image pendu7 = new Image( IHMPendu.class.getResource("/exercice6/pendu7.png").toString() );
    private static Image penduWin = new Image( IHMPendu.class.getResource("/exercice6/penduWin.png").toString() );

    private ArrayList<Button> boutons = new ArrayList<Button>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        for(char i = 97; i < 123; ++i) {
            Button bouton = new Button(String.valueOf(i));
            bouton.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-border-radius: 10px;" +
                    "-fx-border-color: red;" +
                    "-fx-border-style: solid;" +
                    "-fx-border-width: 2px;" +
                    "-fx-padding: 10px;" +
                    "-fx-text-fill: black;" +
                    "-fx-background-radius: 10px;"
            );
            HBox.setMargin( bouton, new Insets(1d ));
            char finalI = i;
            bouton.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> {
                if(bouton.getOpacity() == 0) return;
                bouton.setOpacity(0);
                handleLettre(finalI);
            });
            boutons.add(bouton);
        }

        primaryStage.setTitle("Jeu du pendu");
        primaryStage.setWidth(500);
        primaryStage.setHeight(550);
        dictionnaire = new Dico();

        // A completer
        // Création d'un conteneur VBox avec ses éléments centrés
        this.root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPrefWidth(400);
        root.setPrefWidth(400);

        // Création de la scene
        Scene scene = new Scene( root );

        // elements
        mot = new Label("Mot");
        vie = new Label("Vous avez " + nbVies + " vies !");
        mot.setFont( Font.font("Courier", FontWeight.BOLD, 45) );
        vie.setFont( Font.font("Courier", FontWeight.NORMAL, 15) );

        motActuel = dictionnaire.getMot();
        System.out.println(motActuel);

        String motBrouille = construitMotBrouille();
        mot.setText(motBrouille);

        // Création d'un composant avec l'image
        iv = new ImageView();
        iv.setImage(pendu7);

        VBox boutonsBox = new VBox();
        HBox boutons2 = new HBox();
        boutons2.setAlignment(Pos.CENTER);
        int o = 0;
        for(int i = 0; i < boutons.size(); ++i) {
            boutons2.getChildren().add(boutons.get(i));
            if(o >= 10 || i == boutons.size()-1) {
                boutonsBox.getChildren().add(boutons2);
                boutons2 = new HBox();
                boutons2.setAlignment(Pos.CENTER);
                o = 0;
            }
            ++o;
        }
        root.getChildren().addAll(vie, mot, iv, boutonsBox);

        // Ajout de la scene à la fenêtre
        primaryStage.setScene( scene );
        primaryStage.show();
    }

    private String construitMotBrouille() {
        StringBuilder motConstruction = new StringBuilder();
        for(int i = 0; i < motActuel.length(); ++i) {
            char l = '*';
            for(int j = 0; j < listePosDecouverte.size(); ++j) {
                if(i == listePosDecouverte.get(j)) l = motActuel.charAt(i);
            }
            motConstruction.append(l);
        }
        return motConstruction.toString();
    }

    private void handleLettre(char c) {
        if(aGagne == true || aPerdu == true) return;

        ArrayList<Integer> positions = dictionnaire.getPositions(c, motActuel);
        if(positions.size() < 1) nbVies--;

        switch (nbVies) {
            case 7:
                iv.setImage(pendu7);
                break;
            case 6:
                iv.setImage(pendu6);
                break;
            case 5:
                iv.setImage(pendu5);
                break;
            case 4:
                iv.setImage(pendu4);
                break;
            case 3:
                iv.setImage(pendu3);
                break;
            case 2:
                iv.setImage(pendu2);
                break;
            case 1:
                iv.setImage(pendu1);
                break;
            case 0:
                iv.setImage(pendu0);
                break;
        }

        if(nbVies <= 0) {
            aPerdu = true;
            vie.setText("Perdu ! C'était " + motActuel);
            boutonRejouer = new Button("Rejouer");
            VBox.setMargin( boutonRejouer, new Insets(5d ));
            boutonRejouer.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-border-radius: 10px;" +
                            "-fx-border-color: blue;" +
                            "-fx-border-style: solid;" +
                            "-fx-border-width: 2px;" +
                            "-fx-padding: 10px;" +
                            "-fx-text-fill: black;" +
                            "-fx-background-radius: 10px;"
            );
            root.getChildren().add(boutonRejouer);
            boutonRejouer.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> rejouer(actionEvent));
            return;
        }

        listePosDecouverte.addAll(positions);

        vie.setText("Vous avez " + nbVies + " vies !");

        String motBrouille = construitMotBrouille();
        mot.setText(motBrouille);

        if(motBrouille.equals(motActuel)) {
            aGagne = true;
            vie.setText("Vous avez gagné !");
            iv.setImage(penduWin);
            boutonRejouer = new Button("Rejouer");
            VBox.setMargin( boutonRejouer, new Insets(5d ));
            boutonRejouer.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-border-radius: 10px;" +
                            "-fx-border-color: blue;" +
                            "-fx-border-style: solid;" +
                            "-fx-border-width: 2px;" +
                            "-fx-padding: 10px;" +
                            "-fx-text-fill: black;" +
                            "-fx-background-radius: 10px;"
            );
            root.getChildren().add(boutonRejouer);
            boutonRejouer.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> rejouer(actionEvent));
            return;
        }
    }

    private void rejouer(Event event) {
        aGagne = false;
        aPerdu = false;
        root.getChildren().remove(boutonRejouer);
        listePosDecouverte = new ArrayList<Integer>();
        motActuel = dictionnaire.getMot();
        String motBrouille = construitMotBrouille();
        mot.setText(motBrouille);
        nbVies = 7;
        iv.setImage(pendu7);
        for(int i = 0; i < boutons.size(); ++i) {
            boutons.get(i).setOpacity(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
