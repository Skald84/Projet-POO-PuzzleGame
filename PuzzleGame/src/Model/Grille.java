/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author et8ge
 */

public class Grille {
    
    static int lo, la;
    public static Case[][] PlateauJeu;
    
    
    public Grille(int lo, int la){
        Grille.la = la;
        Grille.lo = lo;
       
        for(int i =0 ; i < lo ; i++){
            for(int j =0 ; j < la ; j++){
                if ((i == 0 && j == 0) || (i == lo-1 && j == la-1)) {
                    Grille.PlateauJeu[i][j] = new CaseSymbole(i,j);
                } else {
                    Grille.PlateauJeu[i][j] = new CaseChemin(i,j);
                }
            }
        }
        
    }
    
    public boolean puzzleResolu(){
        return false;
    }
    
    public static int getLo(){
        return Grille.lo;
    }
    
    public static int getLa(){
        return Grille.la;
    }
}
