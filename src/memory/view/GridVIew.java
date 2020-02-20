package memory.view;

import javafx.scene.layout.GridPane;
import memory.model.Card;

import java.util.ArrayList;

public class GridVIew extends GridPane {

    ArrayList<poubelle> poubelles = new ArrayList<>();

    public GridVIew() {
        Card card1 = new Card();
        card1.setId(1);
        card1.setPath("image/1.jpeg");
        CardVIew cv1 = new CardVIew(card1);

        Card card2 = new Card();
        card2.setId(1);
        card2.setPath("image/1.jpeg");

        Card card3 = new Card();
        card2.setId(2);
        card2.setPath("image/2.jpeg");

        Card card4 = new Card();
        card2.setId(2);
        card2.setPath("image/2.jpeg");


        poubelles.ad
    }


}
