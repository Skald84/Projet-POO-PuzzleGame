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

public class Grille extends Observable{
    
    int lo, la;
    public Case[][] plateauJeu;
    
    
    public Grille(int lo, int la){
        this.la = la;
        this.lo = lo;
        
        this.plateauJeu = new Case[lo][la];
        
        for(int i =0 ; i < lo ; i++){
            for(int j =0 ; j < la ; j++){
                if ((i == 0 && j == 0) || (i == lo-1 && j == la-1)) {
                    this.plateauJeu[i][j] = new CaseSymbole(i,j);
                } else {
                    this.plateauJeu[i][j] = new CaseChemin(i,j);
                }
                
                
            }
        }
    }
    
    
    public boolean puzzleResolu(){
        return false;
    }
    
    public int getLo(){
        return this.lo;
    }
    
    public int getLa(){
        return this.la;
    }
}
