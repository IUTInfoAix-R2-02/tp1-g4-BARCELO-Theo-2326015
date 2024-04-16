package fr.amu.iut.exercice4;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Label label;
    private Pane panneau;
    private HBox bas;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Création d'un conteneur VBox avec ses éléments centrés
        this.root = new BorderPane();
        root.setPrefWidth(400);
        root.setPrefWidth(200);

        // Création de la scene
        Scene scene = new Scene( root );

        this.vert = new Button("Vert");
        this.rouge = new Button("Rouge");
        this.bleu = new Button("Bleu");
        HBox.setMargin( vert, new Insets(10.0d, 5.0d, 10.0d, 5.0d));
        HBox.setMargin( rouge, new Insets(10.0d, 5.0d, 10.0d, 5.0d));
        HBox.setMargin( bleu, new Insets(10.0d, 5.0d, 10.0d, 5.0d));

        vert.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> handleVert(actionEvent) );
        rouge.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> handleRouge(actionEvent) );
        bleu.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> handleBleu(actionEvent) );

        this.panneau = new Pane();

        this.bas = new HBox();
        bas.setAlignment( Pos.CENTER );
        bas.getChildren().addAll(vert, rouge, bleu);

        HBox haut = new HBox();

        this.label = new Label("Couleur choisie");
        label.setFont( Font.font("Courier", FontWeight.NORMAL, 18) );
        haut.setAlignment(Pos.CENTER);
        haut.getChildren().add(label);

        root.setTop(haut);
        root.setCenter(panneau);
        root.setBottom(bas);

        // Ajout de la scene à la fenêtre
        primaryStage.setScene( scene );

        primaryStage.setTitle("Choisi la couleur !!");
        primaryStage.setWidth(400);
        primaryStage.setHeight(200);
        primaryStage.show();
    }

    private void handleVert(Event event) {
        nbVert++;
        panneau.setStyle("-fx-background-color: green");
        label.setText("Vert choisi " + nbVert + " fois");
    }

    private void handleRouge(Event event) {
        nbRouge++;
        panneau.setStyle("-fx-background-color: red");
        label.setText("Rouge choisi " + nbRouge + " fois");
    }

    private void handleBleu(Event event) {
        nbBleu++;
        panneau.setStyle("-fx-background-color: blue");
        label.setText("Bleu choisi " + nbBleu + " fois");
    }
}

