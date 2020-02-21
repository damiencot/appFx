package memory.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import memory.controller.GameController;
import memory.model.Card;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/*
Classe pour la vue de la Card
 */
public class CardView extends Pane {

    private Card card;
    //Ajoute une image
    private ImageView imageView;
    private GameController.EventClickMouse eventClickMouse;
    private Boolean bool = false;
    private Boolean trouver = false;



    /**
     * @param card
     * @param clickMouse
     */
    public CardView(Card card, GameController.EventClickMouse clickMouse) {
        this.card = card;
        this.eventClickMouse = clickMouse;

        Rectangle rectangle = new Rectangle(50,50);
        rectangle.setFill(null);
        rectangle.setStroke(Color.BLACK);
        this.getChildren().add(rectangle);

        try {
            FileInputStream getImage = new FileInputStream(card.getPath());
            //Creation d'une image
            Image image = new Image(getImage);
            this.imageView = new ImageView(image);
            this.imageView.setVisible(this.bool);
            //Rattacher au cardView
            this.setOnMouseClicked(this.eventClickMouse);
            //Ajout à notre Pane notre image
            this.getChildren().add(this.imageView);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Card getCard()
    {
        return this.card;
    }

    //Compare nos cartes
    public boolean equals(CardView card)
    {
        return this.card.getId() == card.getCard().getId();
    }

    //Recup mon ImageView = Image de la carte
    public ImageView getImageView() {
        return imageView;
    }

    public Boolean imageEstAffiche() {
        return bool;
    }

    public void afficherImage()
    {
        this.imageView.setVisible(true);
        this.bool = true;

    }

    public void masquerImage()
    {
        this.imageView.setVisible(false);
        this.bool = false;


    }

    public Boolean getTrouver() {
        return trouver;
    }

    public void setTrouver(Boolean trouver) {
        this.trouver = trouver;
    }


}
