/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import VueControleur.VueController;
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
        for(int i =0 ; i < longueur ; i++){
            for(int j =0 ; j < largeur ; j++){
               
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
        chemin.affiche();
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
                chemin.affiche();
                System.out.println("chemin valide");
            } else {
                System.out.println("chemin non valide");
                chemin.setFreeAllCasesAndClear();
                chemin.affiche();
            }
        } else{
            System.out.println("chemin non valide");
                chemin.setFreeAllCasesAndClear();
                chemin.affiche();
        }
        
        System.out.println("stopDD : " + c + "-" + r + " -> " + lastC + "-" + lastR);
        
    }
    
    /**
     * pendant drag and drop
     * @param c
     * @param r
     */
    public void parcoursDD(int c, int r) {
        // TODO : comment ordonner à la vue de changer l'image de la case survolée ?
        lastC = c;
        lastR = r;
        System.out.println("parcoursDD : " + c + "-" + r);
        
        
        if (chemin.isEmpty()) { // si chemin non vide
            if (this.grille.plateauJeu[c][r] instanceof CaseSymbole) { // verif que 1ere case soit un symbole
                this.chemin.addLast(this.grille.plateauJeu[c][r]); // Ajoute la case dans le chemin
                this.grille.plateauJeu[c][r].setLibre(false); // rend la non libre
                chemin.affiche();
            } else {
                System.out.println("ne commence pas par un symbole");
            }
        } else { // si première case déjà présente
            if (this.grille.plateauJeu[c][r].getLibre() == true ){ // si case libre
                if((this.grille.plateauJeu[c][r].getX() == chemin.getLast().getX()) ||(this.grille.plateauJeu[c][r].getY() == chemin.getLast().getY())){ // verifie que les cases soient voisines
                    this.chemin.addLast(this.grille.plateauJeu[c][r]); // Ajoute la case dans le chemin
                    chemin.affiche();
                } else{
                     chemin.setFreeAllCasesAndClear(); // vide le chemin et rend libre les cases
                     System.out.println("pas voisin !");
                } 
            } else {
                chemin.setFreeAllCasesAndClear(); // vide le chemin
                System.out.println("case non libre");
            }  
        }
        
    }
   
}
