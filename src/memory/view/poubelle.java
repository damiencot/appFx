package memory.view;

import com.sun.javafx.menu.MenuItemBase;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import memory.controller.CardController;


public class poubelle extends StackPane {

    private static MenuItemBase quit;
    private static MenuItemBase restart;
    private ImageView image ;
    private int identifiant ;
    private static poubelle selected = null;



    public poubelle(ImageView imageView, int id) {
        Rectangle border = new Rectangle(100,100);
        border.setFill(new ImagePattern(new Image("image/card-background-1.png")));
        border.setStroke(Color.BLACK);




        image=imageView;
        image.setFitHeight(100);
        image.setFitWidth(100);


        getChildren().addAll(border,imageView);
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (isOpen() || CardController.getClickCount() == 0)
                    return;

                CardController.setClickCount(CardController.getClickCount()-1);

                if (CardController.getClickCount() == 0) {
                    poubelle.this.selected = (poubelle) mouseEvent.getSource();
                    open(() -> {});


                }
                else {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), image);
                    ft.setToValue(1);
                    ft.setOnFinished(e -> {
                        if (!CardController.hasSameValue(selected)) {
                            selected.close();
                            close();
                        }
                        selected = null;
                        CardController.setClickCount(2);
                    });

                    ft.play();

                    open(() -> {

                    });
                    //label1.setText(label1.getText() + " "+ scoreJoueur());
                }
            }
        });
        identifiant=id;
        close();
    }




    public static Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(400, 400);
        HBox hbox = new HBox();
        VBox hbox2 = new VBox();
        hbox.setPadding(new Insets(10));
        hbox2.setAlignment(Pos.TOP_CENTER);
        hbox2.setPadding(new Insets(30));
        hbox2.setSpacing(50);
        hbox.setStyle("-fx-border-color: blue;"+"-fx-border-width: 5");
        hbox2.setStyle("-fx-border-color: black;"+"-fx-border-width: 5");

        quit.setOnAction(event -> {
            try {
                Platform.exit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        Button Nouveau = new Button("Nouveau Joueur");
        Label label2 = new Label("Joueur2");
        Label label3 = new Label("Joueur3");
        Label label4 = new Label("Joueur4");


        List<poubelle> tiles = new ArrayList<>();

        for(int i=0 ; i < 5 ; i++){
            tiles.add(new poubelle(new ImageView(new Image("image/" +(i+1)+".jpeg")),i+1));
            tiles.add(new poubelle(new ImageView(new Image("image/" +(i+1)+".jpeg")),i+1));


        }

        Collections.shuffle(tiles);

        for (int i = 0 ; i < tiles.size() ; i++){
            poubelle poubelle = tiles.get(i);
            poubelle.setTranslateX(100 * (i % 5));
            poubelle.setTranslateY(100 * (i / 5));
            root.getChildren().add(poubelle);
        }

        restart.setOnAction(actionEvent -> {
            createContent();
        });

        HBox.setMargin(hbox2,new Insets(0,0,0,500));
        hbox2.getChildren().addAll(label2,label3,label4);
        hbox.getChildren().addAll(root,hbox2);
        return hbox ;

    }


    public boolean isOpen(){
        return image.getOpacity()==1;
    }

    public void close() {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), image);
        ft.setToValue(0);
        ft.play();
    }
    public void open(Runnable action) {


    }


}
