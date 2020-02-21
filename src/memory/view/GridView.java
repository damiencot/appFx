package memory.view;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import memory.controller.GameController;
import memory.model.Card;

import java.util.ArrayList;



public class GridView extends GridPane {

    /*
    *
    */
    private ArrayList<CardView> cardView = new ArrayList<>();


    public GridView(GameController.EventClickMouse t) {


        Card card1 = new Card();
        card1.setId(1);
        card1.setPath("image/1.jpeg");
        CardView cv1 = new CardView(card1, t);

        Card card2 = new Card();
        card2.setId(1);
        card2.setPath("image/1.jpeg");
        CardView cv2 = new CardView(card2, t);


        Card card3 = new Card();
        card3.setId(2);
        card3.setPath("image/2.jpeg");
        CardView cv3 = new CardView(card3, t);


        Card card4 = new Card();
        card4.setId(2);
        card4.setPath("image/2.jpeg");
        CardView cv4 = new CardView(card4,t);


        /*
        1er:Colonne
        2e:Ligne
         */
        this.add(cv1,0,0);
        this.add(cv2,0,1);
        this.add(cv3,1,0);
        this.add(cv4,1,1);




    }



}
