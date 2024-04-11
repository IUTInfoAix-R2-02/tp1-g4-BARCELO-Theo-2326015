package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FenetreLogiciel extends Application {

    @Override
    public void start(Stage primaryStage) {
        // code de l'exercice 1
        VBox vbox = new VBox();
        HBox.setHgrow(vbox, Priority.ALWAYS);

        // barre de menu
        VBox top = new VBox();
        HBox.setHgrow( top, Priority.ALWAYS );

        Menu file = new Menu("File");
        MenuItem n = new MenuItem("New");
        MenuItem o = new MenuItem("Open");
        MenuItem s = new MenuItem("Save");
        MenuItem c0 = new MenuItem("Close");
        file.getItems().addAll(n, o, s, c0);

        Menu edit = new Menu("Edit");
        MenuItem c = new MenuItem("Cut");
        MenuItem c1 = new MenuItem("Copy");
        MenuItem p = new MenuItem("Paste");
        edit.getItems().addAll(c, c1, p);

        Menu help = new Menu("Help");

        MenuBar menuBar = new MenuBar(file, edit, help);

        top.getChildren().add(menuBar);

        // centre
        HBox center = new HBox();
        VBox.setVgrow(center, Priority.ALWAYS);

        // gauche
        VBox left = new VBox();
        VBox.setVgrow(left, Priority.ALWAYS);
        left.setAlignment(Pos.CENTER);

        Label l1 = new Label("Boutons :");
        Button b1 = new Button("Bouton 1");
        Button b2 = new Button("Bouton 2");
        Button b3 = new Button("Bouton 3");
        VBox.setMargin( l1, new Insets(5.0d) );
        VBox.setMargin( b1, new Insets(5.0d) );
        VBox.setMargin( b2, new Insets(5.0d) );
        VBox.setMargin( b3, new Insets(5.0d) );

        left.getChildren().addAll(l1, b1, b2, b3);

        // droite
        VBox right = new VBox();
        right.setAlignment(Pos.CENTER);
        HBox.setHgrow(right, Priority.ALWAYS);
        VBox.setVgrow(right, Priority.ALWAYS);

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        VBox.setMargin( gp, new Insets(5.0d) );

        gp.setVgap(10);
        gp.setHgap(10);

        Label lf0 = new Label("Name :");
        TextField tf0 = new TextField();

        Label lf1 = new Label("Email :");
        TextField tf1 = new TextField();

        Label lf2 = new Label("Password :");
        TextField tf2 = new TextField();

        gp.add(lf0, 0,0);
        gp.add(tf0, 1,0);

        gp.add(lf1, 0,1);
        gp.add(tf1, 1,1);

        gp.add(lf2, 0,2);
        gp.add(tf2, 1,2);

        Button bb1 = new Button("Submit");
        Button bb2 = new Button("Cancel");

        HBox boutons = new HBox();
        boutons.setAlignment(Pos.CENTER);
        boutons.getChildren().addAll(bb1, bb2);
        HBox.setMargin( bb1, new Insets(5.0d) );
        HBox.setMargin( bb2, new Insets(5.0d) );

        right.getChildren().addAll(gp, boutons);

        // centre separation et ajout
        Separator separation = new Separator(Orientation.VERTICAL);
        center.getChildren().addAll(left, separation, right);

        // bas
        VBox bottom = new VBox();
        Label bas = new Label("Ceci est un label de bas de page");
        bottom.setAlignment(Pos.CENTER);
        HBox.setHgrow(bottom, Priority.ALWAYS);
        bottom.getChildren().add(bas);

        // ajout a la vbox principale
        Separator separation1 = new Separator(Orientation.HORIZONTAL);
        vbox.getChildren().addAll(top, center, separation1, bottom);

        // Ajout du conteneur à la scene
        Scene scene = new Scene(vbox);

        // Ajout de la scene à la fenêtre et changement de ses paramètres (dimensions et titre)
        primaryStage.setScene( scene );
        primaryStage.setWidth( 800 );
        primaryStage.setHeight( 600 );
        primaryStage.setTitle("Premier exemple manipulant les conteneurs");

        // Affichage de la fenêtre
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}

