/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VueControleur;

import Model.Case;
import Model.Grille;
import Model.Model;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author et8ge
 */
public class VueController extends Application implements Observer{

    GridPane grille;
    ImageView[][] ConteneurImageCase;
    String niveauChoisi="";
    
    @Override
    public void start(Stage primaryStage) {
        
        Group root = new Group();
        Scene sceneJeu = new Scene(root, 800, 800);
        
        
        //Gestion ecran d'accueil
            Group accueil = new Group();
            Scene sceneAccueil = new Scene(accueil, 800, 800);
            BorderPane ecranAccueil = new BorderPane();
            ecranAccueil.setPrefSize(800, 800);
            accueil.getChildren().add(ecranAccueil);


            // Titre
            Text titre = new Text("PUZZLE GAME");
            titre.setStyle("-fx-background-color:yellow;");
            titre.setFont(new Font("Footlight MT Light",90));
            
            // Choix du niveau
                // Label
                Label choixNiveau = new Label("Niveau");
                choixNiveau.setFont(new Font("Century Gothic",30));

                // ComboBox
                ComboBox listeNiveaux = new ComboBox();
                listeNiveaux.setStyle("-fx-background-color:white;-fx-font: 30px \"Century Gothic\";");
                listeNiveaux.getItems().addAll(
                    "1",
                    "2",
                    "3"
                );   
                listeNiveaux.setValue("1");
                
            // Infos
            Button infos = new Button("à propos de ce programme");
            infos.setOnAction(e-> {
            
                                    Alert dialog = new Alert(AlertType.INFORMATION);
                                    dialog.setTitle("à propos");
                                    dialog.setHeaderText("Ce programme a été développé par Gérome FERRAND & Aymeric TOUCHE\n");
                                    dialog.setContentText("Nous avons utilisé l'IDE  NetBeans pour coder ainsi que gitHub afin de gérer nos différentes version\n"
                                                         + "Ce projet à été réalisé en quelques semaines pour l'UE Algo et Programmation Orientée Objet");
                                    dialog.showAndWait();
            });
            BorderPane.setAlignment(infos,Pos.BOTTOM_CENTER);
            ecranAccueil.setBottom(infos);
            infos.setStyle("-fx-font-size: 15px;-fx-font-family: \"Century Gothic\";-fx-border-radius: 5px;-fx-background-color:black;-fx-text-fill: white;");
           

                HBox niveau = new HBox();
                niveau.getChildren().addAll(choixNiveau,listeNiveaux);
                niveau.setAlignment(Pos.CENTER);
                niveau.setSpacing(40);
                
            // Bouton Jouer
            Button btnJouer = new Button("Jouer !");    
            btnJouer.setPrefSize(140, 70);
            btnJouer.setStyle("-fx-font-size: 30px;-fx-font-family: \"Century Gothic\";-fx-border-radius: 5px;");
            
            // Menu 
            VBox menu = new VBox();
            menu.getChildren().addAll(titre,niveau,btnJouer);
            menu.setAlignment(Pos.CENTER);
            menu.setSpacing(60);

            // click sur bouton jouer qui déclenche la partie !
            btnJouer.setOnAction(e -> { primaryStage.setScene(sceneJeu);
                                        niveauChoisi = String.valueOf(listeNiveaux.getValue()); 
                                        System.out.println("niveau choisi : " + listeNiveaux.getValue());
                                                                               
                                            //Instanciation du modèle
                                            Model m = new Model();
                                            m.addObserver(this); // Obervation du modèle par la vue

                                            //Initialisation de la fenêtre principale
                                            BorderPane border = new BorderPane();

                                            HBox menuHaut = new HBox(100);
                                            border.setTop(menuHaut);

                                            VBox menuGauche = new VBox(100);
                                            border.setLeft(menuGauche);

                                            VBox menuDroit = new VBox(100);
                                            border.setRight(menuDroit);

                                            HBox menuBas = new HBox(100);
                                            border.setBottom(menuBas);

                                            root.getChildren().add(border);

                                            //initialisation de la grille
                                            grille = new GridPane();
                                            border.setCenter(grille);
                                            BorderPane.setAlignment(grille, Pos.CENTER);
                                            //grille.setPrefSize(300,300);


                                            // Création de la Grille en fonction du niveau
                                            int nbLignes; 
                                            int nbColonnes; 
                                                        
                                            m.creerChemin(); // crée chemin vide via modèle


                                                        // instancie une grlle du coté de la vue selon le niveau de jeu choisi
                                                        switch(niveauChoisi) {
                                                            case "1":
                                                                nbLignes = 4;
                                                                nbColonnes = 4;

                                                                // création d'un tableau de conteneur d'image
                                                                ConteneurImageCase = new ImageView[nbLignes][nbColonnes];

                                                                // insertion des images dans le tableau de conteneur d'images + listener Drag & Drop sur chaque conteneur
                                                                    for (int row = 0; row < nbLignes; row++) {
                                                                        for (int column = 0; column < nbColonnes; column++) {

                                                                            final int fColumn = column;
                                                                            final int fRow = row;
                                                                            ImageView imageView;

                                                                            if ((row == 0 && column == 0) || (row == nbLignes -1 && column == nbColonnes -1)) {

                                                                                    // image symbole en début et fin de tableau
                                                                                    // création d'un conteneur d'img + ajout image dedans
                                                                                    imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/S1.png")));
                                                                                    ConteneurImageCase[row][column] = imageView;
                                                                                    grille.add(ConteneurImageCase[row][column], row, column);
                                                                            } else if ((row == 0 && column == 1) || (row == 2 && column == 3)) {

                                                                                    // image symbole en début et fin de tableau
                                                                                    // création d'un conteneur d'img + ajout image dedans
                                                                                    imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/S2.png")));
                                                                                    ConteneurImageCase[row][column] = imageView;
                                                                                    grille.add(ConteneurImageCase[row][column], row, column);
                                                                            } else {

                                                                                // remplissage des autres cases par la même image vide
                                                                                    imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/LIBRE.png")));
                                                                                    ConteneurImageCase[row][column] = imageView;
                                                                                    grille.add(ConteneurImageCase[row][column], row, column);
                                                                            }
                                                                            // Drag & Drop

                                                                                imageView.setOnDragDetected(new EventHandler<MouseEvent>() {
                                                                                    public void handle(MouseEvent event) {
                                                                                        Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
                                                                                        ClipboardContent content = new ClipboardContent();       
                                                                                        content.putString(""); // non utilisÃ© actuellement
                                                                                        db.setContent(content);
                                                                                        event.consume();
                                                                                        m.startDD(fRow, fColumn);
                                                                                    }
                                                                                });

                                                                                imageView.setOnDragEntered(new EventHandler<DragEvent>() {
                                                                                    public void handle(DragEvent event) {
                                                                                        m.parcoursDD(fRow, fColumn);;
                                                                                        event.consume();
                                                                                    }
                                                                                });

                                                                                imageView.setOnDragDone(new EventHandler<DragEvent>() {
                                                                                    public void handle(DragEvent event) {
                                                                                        m.stopDD(fRow, fColumn);
                                                                                    }
                                                                                }
                                                                            );
                                                                        }
                                                                    }
                                                                break;
                                                                
                                                            case "2":
                                                                nbLignes = 4; 
                                                                nbColonnes = 5; // 
                                                                
                                                                // création d'un tableau de conteneur d'image
                                                                ConteneurImageCase = new ImageView[nbLignes][nbColonnes];

                                                                // insertion des images dans le tableau de conteneur d'images + listener Drag & Drop sur chaque conteneur
                                                                    for (int row = 0; row < nbLignes; row++) {
                                                                        for (int column = 0; column < nbColonnes; column++) {

                                                                            final int fColumn = column;
                                                                            final int fRow = row;
                                                                            ImageView imageView;

                                                                            if ((row == 0 && column == 0) || (row == 0 && column == nbColonnes -1)) {

                                                                                    // image symbole en début et fin de tableau
                                                                                    // création d'un conteneur d'img + ajout image dedans
                                                                                    imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/S1.png")));
                                                                                    ConteneurImageCase[row][column] = imageView;
                                                                                    grille.add(ConteneurImageCase[row][column], row, column);
                                                                            } else if ((row == 0 && column == 1) || (row == 0 && column == 3)) {

                                                                                    // image symbole en début et fin de tableau
                                                                                    // création d'un conteneur d'img + ajout image dedans
                                                                                    imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/S2.png")));
                                                                                    ConteneurImageCase[row][column] = imageView;
                                                                                    grille.add(ConteneurImageCase[row][column], row, column);
                                                                            } else {

                                                                                // remplissage des autres cases par la même image vide
                                                                                    imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/LIBRE.png")));
                                                                                    ConteneurImageCase[row][column] = imageView;
                                                                                    grille.add(ConteneurImageCase[row][column], row, column);
                                                                            }
                                                                            // Drag & Drop

                                                                                imageView.setOnDragDetected(new EventHandler<MouseEvent>() {
                                                                                    public void handle(MouseEvent event) {
                                                                                        Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
                                                                                        ClipboardContent content = new ClipboardContent();       
                                                                                        content.putString(""); // non utilisÃ© actuellement
                                                                                        db.setContent(content);
                                                                                        event.consume();
                                                                                        m.startDD(fRow, fColumn);
                                                                                    }
                                                                                });

                                                                                imageView.setOnDragEntered(new EventHandler<DragEvent>() {
                                                                                    public void handle(DragEvent event) {
                                                                                        m.parcoursDD(fRow, fColumn);;
                                                                                        event.consume();
                                                                                    }
                                                                                });

                                                                                imageView.setOnDragDone(new EventHandler<DragEvent>() {
                                                                                    public void handle(DragEvent event) {
                                                                                        m.stopDD(fRow, fColumn);
                                                                                    }
                                                                                }
                                                                            );
                                                                        }
                                                                    }                                                        
                                                                break;
                                                                
                                                            case "3":
                                                                nbLignes = 5; // a modifier
                                                                nbColonnes = 6; // rendre dynamique
                                                                
                                                                // création d'un tableau de conteneur d'image
                                                                ConteneurImageCase = new ImageView[nbLignes][nbColonnes];

                                                                // insertion des images dans le tableau de conteneur d'images + listener Drag & Drop sur chaque conteneur
                                                                    for (int row = 0; row < nbLignes; row++) {
                                                                        for (int column = 0; column < nbColonnes; column++) {

                                                                            final int fColumn = column;
                                                                            final int fRow = row;
                                                                            ImageView imageView;
                                                                            
                                                                            if ((row == 2 && column == 3) || (row == 3  && column == nbColonnes-1)) {

                                                                                    // image symbole en début et fin de tableau
                                                                                    // création d'un conteneur d'img + ajout image dedans
                                                                                    imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/S1.png")));
                                                                                    ConteneurImageCase[row][column] = imageView;
                                                                                    grille.add(ConteneurImageCase[row][column], row, column);
                                                                            } else if (row == nbLignes-1 && column == 0 || (row == 2 && column == nbColonnes-1)) {

                                                                                    // image symbole en début et fin de tableau
                                                                                    // création d'un conteneur d'img + ajout image dedans
                                                                                    imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/S2.png")));
                                                                                    ConteneurImageCase[row][column] = imageView;
                                                                                    grille.add(ConteneurImageCase[row][column], row, column);
                                                                            } else {

                                                                                // remplissage des autres cases par la même image vide
                                                                                    imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/LIBRE.png")));
                                                                                    ConteneurImageCase[row][column] = imageView;
                                                                                    grille.add(ConteneurImageCase[row][column], row, column);

                                                                            }
                                                                            // Drag & Drop

                                                                                imageView.setOnDragDetected(new EventHandler<MouseEvent>() {
                                                                                    public void handle(MouseEvent event) {
                                                                                        Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
                                                                                        ClipboardContent content = new ClipboardContent();       
                                                                                        content.putString(""); // non utilisÃ© actuellement
                                                                                        db.setContent(content);
                                                                                        event.consume();
                                                                                        m.startDD(fRow, fColumn);
                                                                                    }
                                                                                });

                                                                                imageView.setOnDragEntered(new EventHandler<DragEvent>() {
                                                                                    public void handle(DragEvent event) {
                                                                                        m.parcoursDD(fRow, fColumn);;
                                                                                        event.consume();
                                                                                    }
                                                                                });

                                                                                imageView.setOnDragDone(new EventHandler<DragEvent>() {
                                                                                    public void handle(DragEvent event) {

                                                                                        // attention, le setOnDragDone est dÃ©clenchÃ© par la source du Drag&Drop
                                                                                        //|->??

                                                                                        m.stopDD(fRow, fColumn);
                                                                                    }
                                                                                });
                                                                        }
                                                                    }
                                                                break;
                                                              default:
                                                                nbLignes = 5; // a modifier
                                                                nbColonnes = 5; // rendre dynamique
                                                            }
                                                        
                                        m.creerGrille(niveauChoisi); // crée Grille via modele
                                        border.setCenter(grille);
                                        grille.setGridLinesVisible(true);
    
                                    });
            
            btnJouer.setOnMouseEntered(e -> btnJouer.setStyle("-fx-font-size: 25px;-fx-font-family: \"Century Gothic\";-fx-background-color:#830601;-fx-text-fill: white;"));
            btnJouer.setOnMouseExited(e -> btnJouer.setStyle("-fx-font-size: 25px;-fx-font-family: \"Century Gothic\";-fx-background-color:white;-fx-color:white;-fx-text-fill: #830601;"));

            

            BorderPane.setAlignment(menu, Pos.TOP_CENTER);
            ecranAccueil.setCenter(menu);
            
            
                   
        // Gestion de la Fenetre
        primaryStage.setTitle("Puzzle Game !!!!");
        primaryStage.setScene(sceneAccueil);
        primaryStage.setResizable(false);//ne permet plus de redimensionner la fenêtre
        primaryStage.centerOnScreen();//affiche la fenêtre au centre de l'écran
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void update(Observable o, Object arg) {
        
        //on récupère le second argument ("arg"), qu'on cast en case, pour enfin récuperer ses coordonnées
        //le premier argument, "o",  est l'observable, ici "model")
        Case c = (Case) arg;
        int x = c.getX();
        int y = c.getY();
        
        //On peut ainsi rafraîchir la représentation de la case en récupérant le chemin absolu de son imgage, puis
        //On met à jour l'image associé dans le conteneur d'image
        //Gridpane + tableau d'image, est ce vraiment nécessaire ?
        String pathCaseImg = c.getImage();
        ConteneurImageCase[x][y].setImage(new Image(VueController.class.getResourceAsStream(pathCaseImg)));
       
    }
    
}
