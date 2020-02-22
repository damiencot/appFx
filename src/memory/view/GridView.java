package memory.view;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import memory.controller.GameController;
import memory.model.Card;

import java.util.ArrayList;
import java.util.Collections;


public class GridView extends GridPane {

    private ArrayList<CardView> cardView = new ArrayList<>();
    private ArrayList<Card> cards;
    private GameController.EventClickMouse eventClickMouse;

    /**
     * Constructeur de notre Grid
     * @param eventClickMouse
     * @param nbrPair
     */
    public GridView(GameController.EventClickMouse eventClickMouse, int nbrPair) {

        this.eventClickMouse = eventClickMouse;
        this.cards = new ArrayList<>();

        /**
        * Boucle sur les images
        **/
        for (int i = 1; i < nbrPair + 1; i++) {
            Card card1 = new Card();
            card1.setId(i);
            card1.setPath("image/"+i+".jpg");

            cards.add(card1);


            Card card2 = new Card();
            card2.setId(i);
            card2.setPath("image/"+i+".jpg");

            cards.add(card2);


        }

        Collections.shuffle(cards);

        drawnGrille();

    }

    /**
     * Échange nos cartes de places
     * @param card1
     * @param card2
     */
    public void changeCard(Card card1, Card card2)
    {
        Collections.swap(this.cards, this.cards.indexOf(card1), this.cards.indexOf(card2));
    }

    public void drawnGrille()
    {
        //efface tous les cardView de la grille
        this.getChildren().clear();

        int ligne = 0;
        int colonne = 0;

        for (Card card: cards) {
            CardView cv = new CardView(card, this.eventClickMouse);

            if (colonne == 4){
                colonne =0;
                ligne++;
            }
            /*
            1er:Colonne
            2e:Ligne
             */
            this.add(cv,colonne,ligne);
            colonne++;
        }
    }


}
