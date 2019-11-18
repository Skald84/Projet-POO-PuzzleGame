/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
        
        BorderPane border = new BorderPane();
        Text affichage = new Text("Puzzle Game");
        affichage.setFont(Font.font("Calibri", 30));
        affichage.setFill(Color.RED);
        border.setTop(affichage);
        
        MenuBar mb = new MenuBar();
        Menu menu = new Menu();
        MenuItem mi1 = new MenuItem();
        MenuItem mi2 = new MenuItem();
        MenuItem mi3 = new MenuItem();
        
        int longueurGrille = 4, largeurGrille = 4;
        
                
        
        GridPane grille = new GridPane();
        grille.setPrefSize(300,300);
        
        ImageView[][] tabImageView = new ImageView[longueurGrille][largeurGrille];
        
        
        for (int column = 0; column < longueurGrille; column++) {
            for (int row = 0; row < largeurGrille; row++) {
                
                final int fColumn = column;
                final int fRow = row;
                
                ImageView imageView;
                
                if ((column == 0 && row == 0) || (column == 3 && row == 3)) {
                    // symbole en début et fin de tableau
                        //création d'un conteneur d'img + ajout image dedans
                        imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/S1.png")));
                        Pane root = new Pane();
                        root.getChildren().add(imageView);
                        tabImageView[column][row] = imageView;
                        grille.add(tabImageView[column][row], column, row);
                } else {
                    
                    //remplissage des case par la même image
                    imageView = new ImageView(new Image(VueController.class.getResourceAsStream("/images/vide.png")));
                    Pane root = new Pane();
                    root.getChildren().add(imageView);
                    tabImageView[column][row] = imageView;
                    grille.add(tabImageView[column][row], column, row);
                }
                
                
                
                
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
                    });
                
                
                
            }
            
        }
        
      
              
             
        border.setCenter(grille);
        grille.setGridLinesVisible(true);
        
        Scene scene = new Scene(border, 600, 600);
        
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
        launch(args);
    }
    
}
