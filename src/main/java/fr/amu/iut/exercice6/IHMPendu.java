package fr.amu.iut.exercice6;

import fr.amu.iut.exercice5.Obstacle;
import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class IHMPendu extends Application {
    private int nbVies = 10;
    private VBox root;
    private Dico dictionnaire;
    private String motActuel;
    private TextField lettre;
    private Label mot;
    private Label vie;
    private Button boutonRejouer;
    private ArrayList<Integer> listePosDecouverte = new ArrayList<Integer>();

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

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Jeu du pendu");
        primaryStage.setWidth(500);
        primaryStage.setHeight(550);
        dictionnaire = new Dico();

        // A completer
        // Création d'un conteneur VBox avec ses éléments centrés
        this.root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPrefWidth(400);
        root.setPrefWidth(200);

        // Création de la scene
        Scene scene = new Scene( root );

        // elements
        lettre = new TextField();
        mot = new Label("Mot");
        vie = new Label("Vous avez " + nbVies + " vies !");
        mot.setFont( Font.font("Courier", FontWeight.BOLD, 45) );
        vie.setFont( Font.font("Courier", FontWeight.NORMAL, 15) );

        motActuel = dictionnaire.getMot();
        System.out.print(motActuel);

        String motBrouille = construitMotBrouille();
        mot.setText(motBrouille);
        lettre.setOnAction( actionEvent -> handleLettre(actionEvent) );

        // Création d'un composant avec l'image
        iv = new ImageView();
        iv.setImage(pendu1);

        root.getChildren().addAll(vie, mot, iv, lettre);

        // Ajout de la scene à la fenêtre
        primaryStage.setScene( scene );

        primaryStage.setTitle("C'est le pendu, il faut jouer");
        primaryStage.setWidth(400);
        primaryStage.setHeight(200);

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

    private void handleLettre(Event event) {
        if(nbVies == 0) {
            vie.setText("Perdu !");
            iv.setImage(pendu0);
            return;
        }

        ArrayList<Integer> positions = new ArrayList<Integer>();
        if(lettre.getText().length() > 0) positions = dictionnaire.getPositions(lettre.getText().charAt(0), motActuel);
        listePosDecouverte.addAll(positions);

        String motBrouille = construitMotBrouille();
        mot.setText(motBrouille);

        System.out.println(motBrouille + " " + motActuel);
        if(motBrouille.equals(motActuel)) {
            vie.setText("Vous avez gagné !");
            iv.setImage(pendu7);
            boutonRejouer = new Button("Rejouer");
            root.getChildren().add(boutonRejouer);
            boutonRejouer.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> rejouer(actionEvent) );
            return;
        }

        lettre.setText("");

        vie.setText("Vous avez " + nbVies + " vies !");
        nbVies--;
    }

    private void rejouer(Event event) {
        root.getChildren().remove(boutonRejouer);
        listePosDecouverte = new ArrayList<Integer>();
        motActuel = dictionnaire.getMot();
        String motBrouille = construitMotBrouille();
        lettre.setText("");
        mot.setText(motBrouille);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
