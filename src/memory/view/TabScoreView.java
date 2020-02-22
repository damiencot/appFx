package memory.view;

import javafx.scene.layout.GridPane;
import memory.model.Player;

import java.util.ArrayList;

public class TabScoreView extends GridPane {


    //Création table score
    public TabScoreView(ArrayList<Player> playerArrayList) {
        int ligne =0;
        //Pour chaque joueur, je crée un scoreView
        //Afficher les objets de score
        for (Player player:playerArrayList) {
            ScoreView score = new ScoreView(player);
            this.add(score,0,ligne);
            ligne++;
        }
    }

}
