package memory.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import memory.model.Player;
import memory.view.ScoreView;

import java.util.ArrayList;

public class PlayerController extends GridPane {

    private ArrayList<Player> playerArrayList;
    private Stage configStage;

    public PlayerController(int nbrPlayer, ArrayList<Player> playerArrayList,Stage configStage ) {
        int ligne =0;
        this.playerArrayList = playerArrayList;
        this.configStage = configStage;
        //Pour chaque joueur, je crée un scoreView
        //Afficher les objets le score
        for(int i = 1; i<(nbrPlayer+1); i++)
        {
            Label lblName = new Label("Player " + i + " : ");
            TextField saisiePlayer = new TextField();


            this.add(lblName,0,ligne);
            this.add(saisiePlayer,1,ligne);
            ligne++;
        }

        Button btnSave = new Button("Confirmer");
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            private boolean isOk = true;
            @Override
            public void handle(ActionEvent actionEvent) {
                for (int i = 1; i<(nbrPlayer*2); i= i+2)
                {
                    //Recup le champ saisir de la ligne actuelle
                    TextField textField = (TextField) PlayerController.this.getChildren().get(i);
                    if (textField.getText().equals(""))
                    {
                        Alert alert = new Alert(Alert.AlertType.WARNING,"Remplir les champs vides");
                        alert.showAndWait();
                        playerArrayList.clear();
                        isOk = false;
                        break;
                    }
                    else
                    {
                        Player player = new Player();
                        player.setName(textField.getText());
                        playerArrayList.add(player);
                    }
                }
                if (isOk)
                {
                    PlayerController.this.configStage.hide();
                }
            }
        });
        this.add(btnSave,0,ligne);
    }
}
