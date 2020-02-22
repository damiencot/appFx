package memory.controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Classe générant l'affichage de notre fenêtre
 */
public class MenuController{

    private int nbrPlayer;
    private int nbrPair;


    /**
     * Constructeur Classe MenuController
     * Retourne notre fenetre
     */
    public MenuController() {

        showNbrPlayer();
        shownbrPair();
        Stage gameStage = new Stage();

        GameController root = new GameController(this.nbrPlayer,this.nbrPair, gameStage);

        gameStage.setScene(new Scene(root));
        gameStage.show();


    }

    /**
     * Définit notre fenêtre nombre de joueurs
     */
    public void showNbrPlayer()
    {
        TextInputDialog textInputDialog = new TextInputDialog("");
        textInputDialog.setTitle("Memory");
        textInputDialog.setHeaderText("Nombre de joueur entre 1 et 4");
        textInputDialog.setContentText("Nombre de joueurs : ");
        textInputDialog.getDialogPane().lookupButton(ButtonType.CANCEL).setOpacity(0.0);
        textInputDialog.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);

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


    /**
     * Définit notre fenêtre nombre de Pair voulus dans notre jeux
     */
    public void shownbrPair()
    {
        TextInputDialog textInputDialog = new TextInputDialog("");
        textInputDialog.setTitle("Memory");
        textInputDialog.setHeaderText("Pair de Cartes");
        textInputDialog.setContentText("Nombre de Pair de Cartes entre  2 et 20: ");
        textInputDialog.getDialogPane().lookupButton(ButtonType.CANCEL).setOpacity(0.0);
        textInputDialog.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);
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
