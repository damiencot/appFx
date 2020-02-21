package memory.view;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import memory.model.Player;


public class ScoreView  extends GridPane {
    private Player player;

    public ScoreView(Player player) {
        this.player = player;
        Label lblName = new Label(player.getName());
        Label lblScore = new Label("Score : " + player.getScore());

        this.add(lblName,0,0);
        this.add(lblScore,0,1);

        if (player.getTour())
        {
            this.setStyle("-fx-background-color: yellow;");
        }
    }




}
