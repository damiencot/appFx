package memory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import memory.controller.GameController;
import memory.controller.MenuController;

public class Main extends Application {

    /*
        Demarrage de l'application
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

        MenuController menu = new MenuController();
        GameController root = new GameController();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
