/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

/**
 *
 * @author et8ge
 */
public class VueController extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Model m = new Model();
        
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
        
        //Initialisation du titre de la page
        Text affichage = new Text("Puzzle Game");
        affichage.setFont(Font.font("Calibri", 30));
        affichage.setFill(Color.RED);
        border.setTop(affichage);
        BorderPane.setAlignment(affichage, Pos.CENTER);
        
        //initialisation du menu gauche
        Button recommencerPuzzle = new Button("recommencer");
        border.setLeft(recommencerPuzzle);
        menuGauche.getChildren().add(recommencerPuzzle);
        
        Text choixDuNiveau = new Text("Choix du niveau :");
        border.setLeft(choixDuNiveau);
        menuGauche.getChildren().add(choixDuNiveau);
        
        Button btnNiveau1 = new Button("Niveau 1");
        border.setLeft(btnNiveau1);
        menuGauche.getChildren().add(btnNiveau1);
        
        Button btnNiveau2 = new Button("Niveau 2");
        border.setLeft(btnNiveau2);
        menuGauche.getChildren().add(btnNiveau2);
        
        Button btnNiveau3 = new Button("Niveau 3");
        border.setLeft(btnNiveau3);
        menuGauche.getChildren().add(btnNiveau3);
        
        menuGauche.setSpacing(10);
        menuGauche.setPadding(new Insets(15, 15, 15, 15));
        border.setLeft(menuGauche);
        
        //fin initialisation du menu gauche
        
        //initialisation de la grille
        GridPane grille = new GridPane();
        border.setCenter(grille);
        BorderPane.setAlignment(grille, Pos.CENTER);
        //grille.setPrefSize(300,300);
        
        // fixe les dimensions du tableau en fonction de l'objet statique Grille (possibilité de fusionner avec déclaration suivante)
        int longueurGrille = Grille.lo, largeurGrille = Grille.la;
        
        // création d'un tableau de conteneur d'image
        ImageView[][] tabImageView = new ImageView[longueurGrille][largeurGrille];
        
        // insertion des images dans le tableau de conteneur d'images + listener Drag & Drop sur chaque conteneur
            for (int column = 0; column < longueurGrille; column++) {
                for (int row = 0; row < largeurGrille; row++) {

                    final int fColumn = column;
                    final int fRow = row;

                    ImageView imageView;

                    if ((column == 0 && row == 0) || (column == longueurGrille -1 && row == largeurGrille -1)) {
                        
                        // image symbole en début et fin de tableau
                            //création d'un conteneur d'img + ajout image dedans
                            imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/S1.png")));
                            Pane root = new Pane();
                            root.getChildren().add(imageView);
                            tabImageView[column][row] = imageView;
                            grille.add(tabImageView[column][row], column, row);
                    } else {

                        //remplissage des autres cases par la même image vide
                            imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/vide.png")));
                            Pane root = new Pane();
                            root.getChildren().add(imageView);
                            tabImageView[column][row] = imageView;
                            grille.add(tabImageView[column][row], column, row);
                            
                    }
                    
                    //TODO : instancier le plateau de jeu selon une grille prédéfinie (grille statique pour le moment)


                    //Drag & Drop

                        imageView.setOnDragDetected(new EventHandler<MouseEvent>() {
                            public void handle(MouseEvent event) {

                                Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
                                ClipboardContent content = new ClipboardContent();       
                                content.putString(""); // non utilisÃ© actuellement
                                db.setContent(content);
                                event.consume();
                                m.startDD(fColumn, fRow);
                            }
                        });

                        imageView.setOnDragEntered(new EventHandler<DragEvent>() {
                            public void handle(DragEvent event) {

                                m.parcoursDD(fColumn, fRow);
                                event.consume();
                            }
                        });

                        imageView.setOnDragDone(new EventHandler<DragEvent>() {
                            public void handle(DragEvent event) {

                                // attention, le setOnDragDone est dÃ©clenchÃ© par la source du Drag&Drop

                                m.stopDD(fColumn, fRow);
                            }
                        }
                    );
                }
            }
            
        border.setCenter(grille);
        grille.setGridLinesVisible(true);
        
        Scene scene = new Scene(border, 800, 800);
        
        primaryStage.setTitle("Puzzle Game !");
        primaryStage.setScene(scene);
        primaryStage.show();
    /**
     *
     */
    }
    
    public static void changeImg(){
        ImageView imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/horizontal.png")));
        
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Création du plateau de jeu via objet statique de la classe Grille
        Grille plateauJeu = new Grille(5,3);
        launch(args);
    }
    
}
