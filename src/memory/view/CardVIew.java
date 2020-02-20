package memory.view;

import javafx.scene.layout.Pane;
import memory.model.Card;

public class CardVIew extends Pane {
    private Card card;

    public CardVIew(Card card) {
        this.card = card;

    }
}
