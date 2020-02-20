package memory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import memory.controller.GameController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        GameController root = new GameController();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
