/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Observable;
import javafx.scene.control.Alert;


/**
 *
 * @author et8ge
 */
public class Model extends Observable {
    
    int lastC, lastR;
    
    private Chemin chemin;
    private Chemin chemin1;
    private Chemin chemin2;
    public Grille grille;
    
    /**
     * Crée un objet grille
     * @param niveau
     */
    public void creerGrille(String niveau){
        this.grille = new Grille(niveau);
        
        for(int i = 0 ; i < grille.getLo() ; i++){
            for(int j = 0 ; j < grille.getLa() ; j++){
                System.out.println(this.grille.plateauJeu[i][j]);
            }
        }
    }
    
    /**
     * crée trois nouveaux chemins
     */
    public void creerChemin(){
        this.chemin1 = new Chemin();
        this.chemin2 = new Chemin();
        this.chemin = new Chemin();
    }
    
    /**
     * click debut drag and drop
     * @param c
     * @param r
     */
    public void startDD(int c, int r) {
        
        chemin = chemin1;
        if (!chemin.isEmpty()) {
            if (chemin.getFirst().getImage().equals(chemin.getLast().getImage())) {
                chemin = chemin2;
            }
        chemin.afficheChemin();
        System.out.println("startDD : " + c + "-" + r);
        }
    }
    
    /**
     * relache click drag and drop
     * @param c
     * @param r
     */
    public void stopDD(int c, int r) {
        // ajouter verif reference != sur getFirst et getLast
        if(!(chemin.isEmpty())){ // si chemin non vide 
            if (chemin.getLast() instanceof CaseSymbole ){ // si dernière case est case symbole
                chemin.afficheChemin();
                System.out.println("chemin valide");
                if(grille.puzzleResolu()){
                    Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                                    dialog.setTitle("Bravo !");
                                    dialog.setHeaderText("Félicitation vous avez réussi le Puzzle");
                                    dialog.showAndWait();
                    System.out.println("Puzzle Résolu");
                    chemin1.afficheChemin();
                    chemin2.afficheChemin();
                }
                else{
                    System.out.println("Des cases sont encore vides !");
                    chemin1.afficheChemin();
                    chemin2.afficheChemin();
                }
            } else {
                System.out.println("chemin non valide");
                reinitialisation(chemin);
                chemin1.afficheChemin();
                chemin2.afficheChemin();
            }
        } else{
            System.out.println("chemin non valide (chemin vide)");
                reinitialisation(chemin);
                chemin.afficheChemin();
        }
        
        // copie le chemin effectué au bon endroit
        if (chemin == chemin1) {
                chemin1 = chemin;
            }else{
                chemin2 = chemin;
            }
        
        System.out.println("stopDD : " + c + "-" + r + " -> " + lastC + "-" + lastR);
    }
    
    /**
     * pendant drag and drop
     * @param c
     * @param r
     */
    public void parcoursDD(int c, int r) {
        
        lastC = c;
        lastR = r;
        Case casePointee = this.grille.plateauJeu[r][c];
        
        System.out.println("parcoursDD : " + r + "-" + c);
                        
        if(casePointee.estLibre()){
            if(chemin.isEmpty()){
                if(casePointee instanceof CaseSymbole){
                    chemin.addLast(casePointee);
                }
                else{//le chemin ne commence pas par une case symbole
                    System.out.println("Le chemin doit commencer par une case symbole !");
                    reinitialisation(chemin);
                    
                }
            }
            else{//Le chemin n'est pas vide
                if(casePointee instanceof CaseChemin){
                    
                    //------------------------------------------------------------------------FACTORISABLE ?
                    if(chemin.getLast() instanceof CaseSymbole){
                        chemin.getLast().setVoisin1(casePointee);
                    }
                    else{//la case précédente est une case chemin
                        CaseChemin CaseCheminPrecedente = (CaseChemin) chemin.getLast(); // on est obligé de précaste en case-chemin !
                        CaseCheminPrecedente.setVoisin2(casePointee);
                    }
                    casePointee.setVoisin1(chemin.getLast());
                    chemin.addLast(casePointee);
                    //------------------------------------------------------------------------
                }
                else{//la case pointée est une case symbole, et donc potentiellement nbColonnes derniere du chemin
                    CaseSymbole caseSymbolePointee = (CaseSymbole)casePointee; // on est obligé de précaste en case-symbole !
                    CaseSymbole caseSymboleInitiale = (CaseSymbole)chemin.getFirst(); // on est obligé de précaste en case-symbole !
                    if(caseSymbolePointee.getSymbole() == caseSymboleInitiale.getSymbole()){
                        //------------------------------------------------------------------------FACTORISABLE ?
                        if(chemin.getLast() instanceof CaseSymbole){
                            chemin.getLast().setVoisin1(casePointee);
                        }
                        else{//la case précédente est une case chemin
                            CaseChemin CaseCheminPrecedente = (CaseChemin) chemin.getLast(); // on est obligé de précaste en case-chemin !
                            CaseCheminPrecedente.setVoisin2(casePointee);
                        }
                        casePointee.setVoisin1(chemin.getLast());
                        chemin.addLast(casePointee);
                        //------------------------------------------------------------------------
                    }
                    else{
                        System.out.println("La première et cette case n'ont pas le même symbole !");
                        reinitialisation(chemin);
                    }
                }
            }
        }
        else{//la case est déjà affectée à une chemin
            System.out.println("La case est déjà affectée à un chemin !");
            reinitialisation(chemin);
        }
        chemin.afficheChemin();

            if (chemin.size()>2){
                setChanged();
                notifyObservers(chemin.get(chemin.size()-2));
            }

            setChanged();
            notifyObservers(casePointee);
        
    }
    
    private void reinitialisation(Chemin chemin){
        for(Case i : chemin){
            i.setLibre(true);
            setChanged();
            notifyObservers(i);
        }
        chemin.reinitialisation();
    }
}
