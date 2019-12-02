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
    private Chemin cheminAide;
    public Grille grille;
    
    /**
     * CrÃ©e un objet grille
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
     * crÃ©e trois nouveaux chemins
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
            if (chemin.getLast() instanceof CaseSymbole ){ // si derniÃ¨re case est case symbole
                chemin.afficheChemin();
                System.out.println("chemin valide");
                if(grille.puzzleResolu()){
                    Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                                    dialog.setTitle("Bravo !");
                                    dialog.setHeaderText("FÃ©licitation vous avez rÃ©ussi le Puzzle");
                                    dialog.showAndWait();
                    System.out.println("Puzzle RÃ©solu");
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
        
        // copie le chemin effectuÃ© au bon endroit
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
                    if(chemin.getLast() instanceof CaseChemin){
                    //alors la case précédente est avoisiné à la case pointée
                        CaseChemin CaseCheminPrecedente = (CaseChemin) chemin.getLast();
                        CaseCheminPrecedente.setVoisin2(casePointee);
                    }
                    //la case pointée est avoisinée à la case précédente
                    CaseChemin caseCheminPointee = (CaseChemin) casePointee;
                    caseCheminPointee.setVoisin1(chemin.getLast());
                    chemin.addLast(casePointee);
                }
                
                else{//la case pointÃ©e est une case symbole, et donc potentiellement la derniere du chemin
                    CaseSymbole caseSymbolePointee = (CaseSymbole)casePointee;
                    CaseSymbole caseSymboleInitiale = (CaseSymbole)chemin.getFirst();
                    
                    if(caseSymbolePointee.getSymbole() == caseSymboleInitiale.getSymbole()){
                        if(chemin.getLast() instanceof CaseChemin){
                            CaseChemin CaseCheminPrecedente = (CaseChemin) chemin.getLast(); // on est obligÃ© de prÃ©caste en case-chemin !
                            CaseCheminPrecedente.setVoisin2(casePointee);
                        }
                        chemin.addLast(casePointee);
                    }
                    else{
                        System.out.println("La premiÃ¨re et cette case n'ont pas le mÃªme symbole !");
                        reinitialisation(chemin);
                    }
                }
            }
        }
        else{//la case est dÃ©jÃ  affectÃ©e Ã  un chemin
            System.out.println("La case est dÃ©jÃ  affectÃ©e Ã  un chemin !");
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
        System.out.println("reinit...");
        
        for(Case i : chemin){
            System.out.println(i.toString());
            if(i instanceof CaseChemin){
                CaseChemin cI = (CaseChemin)i;
                cI.setLibre(true);
                System.out.println(cI.toString());
            }
            else{
                i.setLibre(true);
                System.out.println(i.toString());
            }
                        
            setChanged();
            notifyObservers(i);
        }
        
        System.out.println("fin reinit chemin");
        chemin.reinitialisation();
    }
    
    public void aide(String niveau){
        cheminAide = new Chemin();
        cheminAide.aide(niveau);
        
        for(Case i : cheminAide){
                    
            setChanged();
            notifyObservers(i);
        }
        
    }
    
    public void reinitialisationCheminAide(){
        System.out.println("reinit...");
        
        for(Case i : cheminAide){
            System.out.println(i.toString());
            if(i instanceof CaseChemin){
                CaseChemin cI = (CaseChemin)i;
                cI.setLibre(true);
                System.out.println(cI.toString());
            }
            else{
                i.setLibre(true);
                System.out.println(i.toString());
            }
                        
            setChanged();
            notifyObservers(i);
        }
        
        System.out.println("fin reinit chemin");
    }
    
 
}
