/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Observable;


/**
 *
 * @author et8ge
 */
public class Model extends Observable {
    
    int lastC, lastR;
    
    private Chemin chemin;
    private Grille grille;
    
    /**
     * Crée un objet grille
     * @param largeur
     * @param longueur
     */
    public void creerGrille(int largeur, int longueur){
        this.grille = new Grille(largeur, longueur);
        
        for(int i = 0 ; i < longueur ; i++){
            for(int j = 0 ; j < largeur ; j++){
                System.out.println(this.grille.plateauJeu[i][j]);
            }
        }
    }
    
    /**
     * crée nouveau chemin
     */
    public void creerChemin(){
        this.chemin = new Chemin();
    }
    
    /**
     * click debut drag and drop
     * @param c
     * @param r
     */
    public void startDD(int c, int r) {
        System.out.println("taille: " + chemin.size());
        chemin.afficheChemin();
        System.out.println("startDD : " + c + "-" + r);
    }
    
    /**
     * relache click drage and drop
     * @param c
     * @param r
     */
    public void stopDD(int c, int r) {
        // ajouter verif reference != sur getFirst et getLast
        if(!(chemin.isEmpty())){ // si chemin non vide 
            if (chemin.getLast() instanceof CaseSymbole ){ // si dernière case est case symbole
                chemin.afficheChemin();
                System.out.println("chemin valide");
            } else {
                System.out.println("chemin non valide");
                chemin.reinitialisation();
                chemin.afficheChemin();
            }
        } else{
            System.out.println("chemin non valide (chemin vide)");
                chemin.reinitialisation();
                chemin.afficheChemin();
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
        Case casePointee = this.grille.plateauJeu[c][r];
        
        System.out.println("parcoursDD : " + c + "-" + r);
        
        if (chemin.isEmpty()) { // si chemin vide
            if (casePointee instanceof CaseSymbole) { // Si c'est une case symbole
                this.chemin.addLast(casePointee); // Ajoute la case dans le chemin
                chemin.afficheChemin();
            }
            
            else {
                System.out.println("Ne commence pas par un symbole");
            }
        }
        
        else { // Si le chemin n'est pas vide
            if (casePointee.estLibre() == true ){ // si la case libre
                
                if(casePointee.estVoisinDe(chemin.getLast())){ // Si la case pointée est voisine à la case précédemment pointée
                    
                    this.chemin.addLast(casePointee); // Ajoute la case dans le chemin
                    chemin.afficheChemin();
                }
                
                else{
                     chemin.reinitialisation(); // vide le chemin et libère les cases
                     System.out.println("La case n'est pas voisine de la précédente !");
                } 
            }
            
            else { //
                chemin.reinitialisation(); // vide le chemin et libère les cases
                System.out.println("La case est déjà dans un chemin !");
            }

            if (chemin.size()>2){
                setChanged();
                notifyObservers(chemin.get(chemin.size()-2));
            }

            setChanged();
            notifyObservers(casePointee);
        }
    }
}
