package memory.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import memory.model.Card;
import memory.model.Player;
import memory.view.CardView;
import memory.view.GridView;
import memory.view.TabScoreView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;


/**
 * Classe regroupant tout le logique métier pour notre jeu
 */
public class GameController extends GridPane {

    private ArrayList<Player> playerArrayList;
    private TabScoreView tabScoreView;
    private CardView cardActual;
    private EventClickMouse eventClickMouse;
    private int playerActual;
    private int nbrPair;
    private Stage gameStage;
    private int nbrTrouver = 0;
    private CardView tmpCard;
    private boolean modeEchange;
    private GridView gridView;
    private Label statusChange;

    /**
     * Constructeur
     *
     * Initialisation de nos variables de classe
     * Création de notre Stage
     * Gestion de nos différentes cliques de la souris
     *
     * @param nbrPlayer
     * @param nbrPair
     * @param gameStage
     */
    public GameController(int nbrPlayer,int nbrPair, Stage gameStage) {
        super();
        this.nbrPair = nbrPair;
        this.cardActual = null;
        this.gameStage = gameStage;
        this.tmpCard = null;
        this.modeEchange = false;
        this.statusChange = new Label();
        this.add(this.statusChange,0,3);
        ///Création d'un event de la souris
        this.eventClickMouse = new EventClickMouse();
        this.playerActual = 0;
        this.gridView = new GridView(this.eventClickMouse, nbrPair);
        //Grille ajoutée au contrôleur
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

                //Crée un tableau de score pour les joueurs
                GameController.this.tabScoreView = new TabScoreView(GameController.this.playerArrayList);
                GameController.this.add(GameController.this.tabScoreView,1,0);
            }
        });
        this.gameStage.setIconified(true);

        /**
         * Bouton Quitter
         */
        Button btnQuite = new Button("Quitter");
        btnQuite.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        /**
         * Bouton Abandonner
         */
        Button btnAbandon = new Button("Abandonner");
        btnAbandon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert abandon = new Alert(Alert.AlertType.CONFIRMATION,"Vous avez abandonnez");
                abandon.showAndWait();
                System.exit(0);
            }
        });

        /**
         * Bouton Recommencer
         */
        Button btnMelanger = new Button("Recommencer");
        btnMelanger.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameController.this.getChildren().remove(GameController.this.gridView);
                for (Player player: playerArrayList) {
                    player.setTour(false);
                    player.setScore(0);
                }

                playerActual = playerArrayList.size()-1;
                GameController.this.gridView = new GridView(eventClickMouse, nbrPair);
                GameController.this.add(GameController.this.gridView,0,0);
                nextTour();
            }
        });


        /**
         * Joueur Suivant
         */
        Button btnNext = new Button("Joueur Suivant");
        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                nextTour();
            }
        });

        /**
         * Echanger Cartes
         */
        Button btnSwitch = new Button("Echanger Cartes");
        btnSwitch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int scoreMin = Integer.MAX_VALUE;
                Player playerLow = null;
                for (Player player : playerArrayList) {
                    if (player.getScore() < scoreMin) {
                        playerLow = player;
                        scoreMin = player.getScore();
                    }
                }
                if (playerLow.getTour())
                {
                    modeEchange = true;
                    statusChange.setText("Echange en cour");
                }


            }
        });

        //Ajout de nos différents boutons à notre fenêtre et leur position
        this.add(btnQuite,1,1);
        this.add(btnNext,0,1);
        this.add(btnSwitch,2,1);
        this.add(btnAbandon,2,2);
        this.add(btnMelanger,2,3);




        configStage.show();
        this.gameStage.hide();


    }

    //Tour suivant
    public void nextTour()
    {
        //Récupère le joueur actuel dans ma liste
        this.playerArrayList.get(this.playerActual).setTour(false);
        this.playerActual++;
        if (this.playerActual == this.playerArrayList.size())
        {
            this.playerActual = 0;
        }
        //Tour joueur actuel;
        this.playerArrayList.get(this.playerActual).setTour(true);
        //Modifie le tableau de score
        this.getChildren().remove(this.tabScoreView);
        //Recrée le tableau de score
        this.tabScoreView = new TabScoreView(this.playerArrayList);
        this.add(this.tabScoreView,1,0);

    }

    //Clique de notre Souris sur les cartes
    public class EventClickMouse implements EventHandler<MouseEvent>
    {

        @Override
        public void handle(MouseEvent mouseEvent) {
            //Récupère mon objet gridview sur lequelle j'ai cliqué
            CardView cardView = (CardView) mouseEvent.getSource();
            //Si la carte trouver est à null
            if (!cardView.getTrouver()) {
                //Si modeEchange à TRUE
                if (modeEchange) {
                    //Aucune action faite
                    if (tmpCard == null)
                    {
                        tmpCard = cardView;
                    }
                    //Sinon on interverti notre carte cliquer avec la carte temporaire
                    else
                    {
                        gridView.changeCard(cardView.getCard(),tmpCard.getCard());
                        gridView.drawnGrille();
                        tmpCard = null;
                        modeEchange = false;
                        nextTour();
                        statusChange.setText("");
                    }
                }
                //Sinon
                else{
                            //affiche l'image
                            cardView.afficherImage();
                            //Si carte actuelle est différent de null
                            if (GameController.this.cardActual != null) {
                                //On vérifie si elles sont identiques
                                if (cardView.equals(GameController.this.cardActual)) {
                                    System.out.println("OK");
                                    //On modifie le score
                                    GameController.this.playerArrayList.get(GameController.this.playerActual).addScore();
                                    cardView.setTrouver(true);
                                    GameController.this.cardActual.setTrouver(true);
                                    //Incrémente le tour
                                    nbrTrouver++;
                                    //Si le nombre de cartes trouver est identique au nombre du pair indiqué au début du jeu
                                    if (nbrTrouver == nbrPair) {
                                        //Réinitialise les différentes informations
                                        int scoreMax = 0;
                                        Player playerWin = null;
                                        //Parcours notre tableau de joueur qui nous
                                        //permet après d'afficher certaines informations du joueur gagnant
                                        for (Player player : playerArrayList) {
                                            if (player.getScore() > scoreMax) {
                                                playerWin = player;
                                                scoreMax = player.getScore();
                                            }
                                        }
                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bravo :" + playerWin.getName());

                                        ButtonType buttonQuit = new ButtonType("Quitter");
                                        ButtonType buttonReplay = new ButtonType("Rejouer");


                                        alert.getButtonTypes().clear();
                                        alert.getButtonTypes().addAll(buttonReplay, buttonQuit);
                                        Optional<ButtonType> buttonType = alert.showAndWait();
                                        if (buttonType.get() == buttonReplay) {
                                            gameStage.hide();
                                            MenuController menu = new MenuController();
                                        } else {
                                            System.exit(0);
                                        }
                                    }
                                } else {
                                    //Masque les cartes qui ne sont pas bonnes
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
