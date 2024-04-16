package fr.amu.iut.exercice5;

import javafx.scene.shape.Rectangle;

public class Obstacle extends Rectangle {
    public Obstacle(
        int x, int y,
        int width, int height
    ) {
        this.setX(x * Personnage.LARGEUR_PERSONNAGE);
        this.setY(y * Personnage.LARGEUR_PERSONNAGE);
        this.setWidth(width * Personnage.LARGEUR_PERSONNAGE);
        this.setHeight(height * Personnage.LARGEUR_PERSONNAGE);
    }
}
