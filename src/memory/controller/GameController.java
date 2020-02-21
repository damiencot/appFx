package memory.controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import memory.model.Player;
import memory.view.CardView;
import memory.view.GridView;
import memory.view.TabScoreView;

import java.util.ArrayList;


/*
Grille de jeux(etc...)
 */
public class GameController extends GridPane {

    private ArrayList<Player> playerArrayList;
    private TabScoreView tabScoreView;
    private  CardView cardActual;
    private EventClickMouse eventClickMouse;
    private int playerActual;
    private int nbrPair;
    private Stage gameStage;

    public GameController(int nbrPlayer,int nbrPair, Stage gameStage) {
        super();
        this.nbrPair = nbrPair;
        this.cardActual = null;
        this.gameStage = gameStage;
        ///Crée l'event et on le charge
        this.eventClickMouse = new EventClickMouse();
        this.playerActual = 0;
        GridView gridView = new GridView(this.eventClickMouse, nbrPair);
        //Grille dans mon controlleur
        this.add(gridView,0,0);
        this.playerArrayList = new ArrayList<>();

        Stage configStage = new Stage();
        configStage.setScene(new Scene(new PlayerController(nbrPlayer,playerArrayList,configStage)));

        configStage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {

                GameController.this.gameStage.show();
                GameController.this.gameStage.setIconified(false);

                Screen screen = Screen.getPrimary();
                Rectangle2D bounds = screen.getVisualBounds();

                GameController.this.gameStage.setX(bounds.getMinX());
                GameController.this.gameStage.setY(bounds.getMinY());
                GameController.this.gameStage.setWidth(bounds.getWidth());
                GameController.this.gameStage.setHeight(bounds.getHeight());

                ///Crée un tab de score pour les joueurs
                GameController.this.tabScoreView = new TabScoreView(GameController.this.playerArrayList);
                GameController.this.add(GameController.this.tabScoreView,1,0);
            }
        });
        this.gameStage.setIconified(true);

        configStage.show();
        this.gameStage.hide();


    }

    public void nextTour()
    {
        //Recup le joueur actuelle dans ma liste
        this.playerArrayList.get(this.playerActual).setTour(false);
        this.playerActual++;
        if (this.playerActual == this.playerArrayList.size())
        {
            this.playerActual = 0;
        }
        //Tour joueur actuel;
        this.playerArrayList.get(this.playerActual).setTour(true);
        //Modifie le tab de score
        this.getChildren().remove(this.tabScoreView);
        //Recrée le tab de score a sa place
        this.tabScoreView = new TabScoreView(this.playerArrayList);
        this.add(this.tabScoreView,1,0);

    }

    //Clique de notre Souris sur les cartes
    public class EventClickMouse implements EventHandler<MouseEvent>
    {

        @Override
        public void handle(MouseEvent mouseEvent) {
            //Recup mon objet gridView sur lequelle j'ai clique
            CardView cardView = (CardView) mouseEvent.getSource();

            if (!cardView.getTrouver()) {
                //Au clique on masque l'image
                if (cardView.imageEstAffiche()) {
                    cardView.masquerImage();
                } else {
                    cardView.afficherImage();
                    if (GameController.this.cardActual != null) {
                        if (cardView.equals(GameController.this.cardActual)) {
                            System.out.println("OK");
                            GameController.this.playerArrayList.get(GameController.this.playerActual).addScore();
                            cardView.setTrouver(true);
                            GameController.this.cardActual.setTrouver(true);
                        } else {
                            //Masque les cartes qui ne sont pas bonne
                            cardView.masquerImage();
                            GameController.this.cardActual.masquerImage();
                            System.out.println("Deux cartes masqués");

                        }
                        GameController.this.cardActual = null;
                        GameController.this.nextTour();
                    } else {
                        GameController.this.cardActual = cardView;
                    }
                }
            }
        }
    }


}
