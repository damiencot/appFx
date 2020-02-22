package memory.view;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import memory.model.Player;

/**
 * Classe pour l'affichage du Score
 */
public class ScoreView  extends GridPane {

    private Player player;

    /**
     * Constructeur de notre joueur
     * @param player
     */
    public ScoreView(Player player) {
        this.player = player;
        Label lblName = new Label(player.getName());
        Label lblScore = new Label("Score : " + player.getScore());

        //Positionnement de nos labels
        this.add(lblName,0,0);
        this.add(lblScore,0,1);

        if (player.getTour())
        {
            this.setStyle("-fx-background-color: yellow;");
        }
    }




}
