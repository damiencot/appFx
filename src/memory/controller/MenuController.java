package memory.controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Optional;

public class MenuController{

    private int nbrPlayer;
    private int nbrPair;


    public MenuController() {

        showNbrPlayer();
        shownbrPair();
        Stage gameStage = new Stage();

        GameController root = new GameController(this.nbrPlayer,this.nbrPair, gameStage);

        gameStage.setScene(new Scene(root));
        gameStage.show();


    }

    public void showNbrPlayer()
    {
        TextInputDialog textInputDialog = new TextInputDialog("");
        textInputDialog.setTitle("Memory");
        textInputDialog.setHeaderText("Nombre de joueur entre 1 et 4");
        textInputDialog.setContentText("Nombre de joueurs : ");

        Optional<String> resultat = textInputDialog.showAndWait();
        resultat.ifPresent(name->{
            //
            if (name.matches("[1-4]"))
            {
                this.nbrPlayer = Integer.valueOf(name);
            }
            //Si jamais nbr != 1 a 4
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING,"Un nombre entre 1 à 4");
                alert.showAndWait();
                showNbrPlayer();
            }
        });


    }


    public void shownbrPair()
    {
        TextInputDialog textInputDialog = new TextInputDialog("");
        textInputDialog.setTitle("Memory");
        textInputDialog.setHeaderText("Pair de Cartes");
        textInputDialog.setContentText("Nombre de Pair de Cartes entre  2 et 20: ");

        Optional<String> resultat = textInputDialog.showAndWait();
        resultat.ifPresent(name->{
            //Regex: Un nombre entre 2 et 9, avec autre chose derriere
            if (name.matches("[0-9]+") && Integer.valueOf(name) < 21 && Integer.valueOf(name) > 1)
            {
                this.nbrPair = Integer.valueOf(name);
            }
            //Si jamais nbr != 2 a 20
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING,"Un nombre entre 2 à 20");
                alert.showAndWait();
                shownbrPair();
            }
        });


    }
}
