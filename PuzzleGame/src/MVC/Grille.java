/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;

/**
 *
 * @author et8ge
 */

public class Grille {
    boolean resolu;
    static int lo, la;
    public static Case[][] PlateauJeu;
    
    
    public Grille(int lo, int la){
        Grille.la = la;
        Grille.lo = lo;
        Case[][] Grille = null;
        for(int i =0 ; i < lo ; i++){
            for(int j =0 ; j < la ; j++){
               //TODO : instancier une case puis l'insérer à la grille (créer constructeur Case)
            }
        }
        
    }
}
