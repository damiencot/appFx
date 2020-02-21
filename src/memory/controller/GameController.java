package memory.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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

    public GameController() {
        super();
        this.cardActual = null;
        ///Crée l'event et on le charge
        this.eventClickMouse = new EventClickMouse();
        this.playerActual = 0;
        GridView gridView = new GridView(this.eventClickMouse);
        //Grille dans mon controlleur
        this.add(gridView,0,0);
        this.playerArrayList = new ArrayList<>();

        Player player1 = new Player();
        player1.setName("J1");
        player1.setTour(true);

        Player player2 = new Player();
        player2.setName("J2");

        this.playerArrayList.add(player1);
        this.playerArrayList.add(player2);

        ///Crée un tab de score pour les joueurs
        this.tabScoreView = new TabScoreView(this.playerArrayList);
        this.add(this.tabScoreView,1,0);
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
