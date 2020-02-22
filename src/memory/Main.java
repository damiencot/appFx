package memory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import memory.controller.GameController;
import memory.controller.MenuController;

public class Main extends Application {

    /**
     * Démarrage de l'application
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

        //Création de notre fenêtre
        MenuController menu = new MenuController();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
