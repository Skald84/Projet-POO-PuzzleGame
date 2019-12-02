/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VueControleur;

import Model.Case;
import Model.Model;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author et8ge
 */
public class VueController extends Application implements Observer{

    GridPane grille;
    ImageView[][] ConteneurImageCase;
    String niveauChoisi="";
    Text txtPrompt;
    public String prompt ="";
    
    @Override
    public void start(Stage primaryStage) {
        
        //Gestion ecranJeu d'accueil
            Group accueil = new Group();
            Scene sceneAccueil = new Scene(accueil, 900, 1000);
            BorderPane ecranAccueil = new BorderPane();
            ecranAccueil.setPrefSize(900, 1000);
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
                                    dialog.setContentText("Nous avons utilisé l'IDE  NetBeans pour coder ainsi que gitHub afin de gérer nos différentes versions\n"
                                                          + "Ce projet à été réalisé en quelques semaines dans le cadre de l'UE Algo et Programmation Orientée Objet de la L3 Info");
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
            btnJouer.setStyle("-fx-font-size: 30px;-fx-font-family: \"Century Gothic\";-fx-border-radius: 5px;-fx-background-color:white;-fx-text-fill: #830601;");
            
            // Menu 
            VBox menu = new VBox();
            menu.getChildren().addAll(titre,niveau,btnJouer);
            menu.setAlignment(Pos.CENTER);
            menu.setSpacing(60);
            
            btnJouer.setOnMouseEntered(e -> btnJouer.setStyle("-fx-font-size: 25px;-fx-font-family: \"Century Gothic\";-fx-background-color:#830601;-fx-text-fill: white;"));
            btnJouer.setOnMouseExited(e -> btnJouer.setStyle("-fx-font-size: 25px;-fx-font-family: \"Century Gothic\";-fx-background-color:white;-fx-color:white;-fx-text-fill: #830601;"));

            

            BorderPane.setAlignment(menu, Pos.CENTER);
            ecranAccueil.setCenter(menu);

            // click sur bouton jouer qui déclenche la partie (appelle la fonction lancerPartie avec en param le niveau choisi)
            btnJouer.setOnAction(e -> {
                                        niveauChoisi = String.valueOf(listeNiveaux.getValue()); 
                                        System.out.println("niveau choisi : " + listeNiveaux.getValue());
                                        lancerPartie(primaryStage, niveauChoisi);
                                      });
                                                       
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
        
        if (arg instanceof Case) {
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
        } else if (arg instanceof String){
            
           prompt =  arg + prompt;
            
           txtPrompt.setText((String) prompt);
        }
    }
    
    public void lancerPartie(Stage primaryStage, String niveauChoisi){
        
        //Initialisation de la fenêtre principale
        prompt ="";
        
            //Instanciation du modèle
            Model m = new Model();
            m.addObserver(this); // Obervation du modèle par la vue

            Group jeu = new Group();
            Scene sceneJeu = new Scene(jeu, 900, 1000);
            primaryStage.setScene(sceneJeu);
            BorderPane ecranJeu = new BorderPane();
            ecranJeu.setPrefSize(900, 1000);
            jeu.getChildren().add(ecranJeu);
            
                       
            Text titre = new Text("Puzzle niveau" + niveauChoisi);
            titre.setFont(new Font("Footlight MT Light",70));
            
            BorderPane.setAlignment(titre,Pos.BOTTOM_CENTER);
            ecranJeu.setTop(titre);

            //initialisation de la grilleVue
            GridPane grilleVue = new GridPane();
            
            VBox node = new VBox();
            node.getChildren().add(grilleVue);
            node.setAlignment(Pos.CENTER);
            BorderPane.setMargin(node,new Insets(30, 30, 30, 250));
            BorderPane.setAlignment(node, Pos.CENTER);
            ecranJeu.setCenter(node);
            
            
            // Text Label Prompt
            txtPrompt = new Text("Bonne chance !!!");
            txtPrompt.setTextAlignment(TextAlignment.CENTER);
            ScrollPane scroll = new ScrollPane(txtPrompt);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            scroll.setMaxHeight(100);
            scroll.setMaxWidth(300);
            scroll.setMinHeight(100);
            scroll.setMinWidth(300);
            
            
   
            
            // Bouton affichage des règles
            Button btnRègles = new Button("Règles du jeu");
            btnRègles.setStyle("-fx-font-size: 15px;-fx-font-family: \"Century Gothic\";-fx-border-radius: 5px;-fx-background-color:#830601;-fx-text-fill: white;");
            btnRègles.setOnMouseEntered(e -> btnRègles.setStyle("-fx-font-size: 15px;-fx-font-family: \"Century Gothic\";-fx-background-color:white;-fx-text-fill: #830601;"));
            btnRègles.setOnMouseExited(e -> btnRègles.setStyle("-fx-font-size: 15px;-fx-font-family: \"Century Gothic\";-fx-background-color:#830601;-fx-color:white;-fx-text-fill: white;"));
            btnRègles.setOnMouseClicked(e -> {
                                                Alert dialog = new Alert(AlertType.INFORMATION);
                                                dialog.setTitle("Règles du jeu");
                                                dialog.setHeaderText("Les règles sont les suivantes:\n"+"\t\t- Vous devez relier chaque paire de symbole\n" 
                                                                     + "\t\t- Chaque case doit être utilisée\n" +"\t\t- Les chemins ne peuvent pas se croiser\n\n");
                                                dialog.setContentText("Vous pouvez demander de l'aide en cliquant sur \"aidez moi\", un chemin apparaitra tant que vous maintenez le clic !\n\n\n"+ 
                                                                      "\t\t\t\t\tBonne chance !");
                                                dialog.show();
                                             });
            // Bouton retour page accueil
            Button btnRetourAccueil = new Button("Retour au menu");
            btnRetourAccueil.setStyle("-fx-font-size: 15px;-fx-font-family: \"Century Gothic\";-fx-border-radius: 5px;-fx-background-color:#830601;-fx-text-fill: white;");
            btnRetourAccueil.setOnMouseEntered(e -> btnRetourAccueil.setStyle("-fx-font-size: 15px;-fx-font-family: \"Century Gothic\";-fx-background-color:white;-fx-text-fill: #830601;"));
            btnRetourAccueil.setOnMouseExited(e -> btnRetourAccueil.setStyle("-fx-font-size: 15px;-fx-font-family: \"Century Gothic\";-fx-background-color:#830601;-fx-color:white;-fx-text-fill: white;"));
            btnRetourAccueil.setOnMouseClicked(e -> start(primaryStage));
            
            
            // Bouton retour page accueil
            Button btnAide = new Button("Aidez moi");
            btnAide.setStyle("-fx-font-size: 15px;-fx-font-family: \"Century Gothic\";-fx-border-radius: 5px;-fx-background-color:#1F2AFF;-fx-text-fill: white;");
            btnAide.setOnMouseEntered(e -> btnAide.setStyle("-fx-font-size: 15px;-fx-font-family: \"Century Gothic\";-fx-background-color:white;-fx-text-fill: #1F2AFF;"));
            btnAide.setOnMouseExited(e -> btnAide.setStyle("-fx-font-size: 15px;-fx-font-family: \"Century Gothic\";-fx-background-color:#1F2AFF;-fx-text-fill: white;"));
            btnAide.setOnMousePressed(e -> m.aide(niveauChoisi));
            btnAide.setOnMouseReleased(e -> m.reinitialisationCheminAide());
            
            // Bouton réinitialiser grille
            Button btnReinit = new Button("Réinitialiser");
            btnReinit.setStyle("-fx-font-size: 15px;-fx-font-family: \"Century Gothic\";-fx-border-radius: 5px;-fx-background-color:#1F2AFF;-fx-text-fill: white;");
            btnReinit.setOnMouseEntered(e -> btnReinit.setStyle("-fx-font-size: 15px;-fx-font-family: \"Century Gothic\";-fx-background-color:white;-fx-text-fill: #1F2AFF;"));
            btnReinit.setOnMouseExited(e -> btnReinit.setStyle("-fx-font-size: 15px;-fx-font-family: \"Century Gothic\";-fx-background-color:#1F2AFF;-fx-text-fill: white;"));
            btnReinit.setOnMouseClicked(e-> lancerPartie(primaryStage, niveauChoisi));
            
            
            


            // Menu regroupant les deux boutons
            VBox options = new VBox();
            options.getChildren().addAll(scroll,btnRègles,btnAide,btnReinit,btnRetourAccueil);
            options.setAlignment(Pos.CENTER);
            options.setSpacing(25);
            options.setPadding(new Insets(0, 0, 50, 0));
            BorderPane.setAlignment(options, Pos.TOP_CENTER);
            ecranJeu.setBottom(options);

            

            

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
                                                    grilleVue.add(ConteneurImageCase[row][column], row, column);
                                            } else if ((row == 0 && column == 1) || (row == 2 && column == 3)) {

                                                    // image symbole en début et fin de tableau
                                                    // création d'un conteneur d'img + ajout image dedans
                                                    imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/S2.png")));
                                                    ConteneurImageCase[row][column] = imageView;
                                                    grilleVue.add(ConteneurImageCase[row][column], row, column);
                                            } else {

                                                // remplissage des autres cases par la même image vide
                                                    imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/LIBRE.png")));
                                                    ConteneurImageCase[row][column] = imageView;
                                                    grilleVue.add(ConteneurImageCase[row][column], row, column);
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
                                                    grilleVue.add(ConteneurImageCase[row][column], row, column);
                                            } else if ((row == 0 && column == 1) || (row == 0 && column == 3)) {

                                                    // image symbole en début et fin de tableau
                                                    // création d'un conteneur d'img + ajout image dedans
                                                    imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/S2.png")));
                                                    ConteneurImageCase[row][column] = imageView;
                                                    grilleVue.add(ConteneurImageCase[row][column], row, column);
                                            } else {

                                                // remplissage des autres cases par la même image vide
                                                    imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/LIBRE.png")));
                                                    ConteneurImageCase[row][column] = imageView;
                                                    grilleVue.add(ConteneurImageCase[row][column], row, column);
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
                                                    grilleVue.add(ConteneurImageCase[row][column], row, column);
                                            } else if (row == nbLignes-1 && column == 0 || (row == 2 && column == nbColonnes-1)) {

                                                    // image symbole en début et fin de tableau
                                                    // création d'un conteneur d'img + ajout image dedans
                                                    imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/S2.png")));
                                                    ConteneurImageCase[row][column] = imageView;
                                                    grilleVue.add(ConteneurImageCase[row][column], row, column);
                                            } else {

                                                // remplissage des autres cases par la même image vide
                                                    imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/LIBRE.png")));
                                                    ConteneurImageCase[row][column] = imageView;
                                                    grilleVue.add(ConteneurImageCase[row][column], row, column);

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
        
        
        grilleVue.setGridLinesVisible(true);

    }
}

