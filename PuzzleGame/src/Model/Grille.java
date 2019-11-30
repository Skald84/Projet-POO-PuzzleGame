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
    
    int lo;
    int la;
    public Case[][] plateauJeu;
    private final String [][] patron1 = {   {"S1","CC","CC","CC"},
                                            {"S2","CC","CC","CC"},
                                            {"CC","CC","CC","CC"},
                                            {"CC","CC","S2","S1"},
                                        
                                        };
    private final String [][] patron2 = {   {"S1","CC","CC","CC"},
                                            {"S2","CC","CC","CC"},
                                            {"CC","CC","CC","CC"},
                                            {"S2","CC","CC","CC"},
                                            {"S1","CC","CC","CC"},
                                        
                                        };
    private final String [][] patron3 = {   {"CC","CC","CC","CC","S2"},
                                            {"CC","CC","CC","CC","CC"},
                                            {"CC","CC","CC","CC","CC"},
                                            {"CC","CC","S1","CC","CC"},
                                            {"CC","CC","CC","CC","CC"},
                                            {"CC","CC","S2","S1","CC"},
                                        
                                        };
    private final String [][] patron4 = {   {"S1","CC","CC","CC"},
                                            {"S2","CC","CC","CC"},
                                            {"CC","CC","CC","CC"},
                                            {"CC","CC","S2","S1"},
                                        
                                        };
    private final String [][] patron5 = {   {"S1","CC","CC","CC"},
                                            {"S2","CC","CC","CC"},
                                            {"CC","CC","CC","CC"},
                                            {"CC","CC","S2","S1"},
                                        
                                        };
    
    
    public Grille(String niveau){
        lo = 1;
        la = 1;
        if(niveau.equals("1")){
            la = patron1[1].length;
            lo = patron1.length;
            
            this.plateauJeu = new Case[lo][la];
        
            for(int i =0 ; i < lo ; i++){
                for(int j =0 ; j < la ; j++){
                    switch (patron1[i][j]) {
                        case "S1":
                            this.plateauJeu[i][j] = new CaseSymbole(i,j);
                            break;
                        case "S2":
                            this.plateauJeu[i][j] = new CaseSymbole(i,j);
                            break;
                        default:
                            this.plateauJeu[i][j] = new CaseChemin(i,j);
                            break;
                    }
                }
            }
        }
        if(niveau.equals("2")){
            la = patron2[1].length;
            lo = patron2.length;
            
            this.plateauJeu = new Case[lo][la];
        
            for(int i =0 ; i < lo ; i++){
                for(int j =0 ; j < la ; j++){
                    switch (patron2[i][j]) {
                        case "S1":
                            this.plateauJeu[i][j] = new CaseSymbole(i,j);
                            break;
                        case "S2":
                            this.plateauJeu[i][j] = new CaseSymbole(i,j);
                            break;
                        default:
                            this.plateauJeu[i][j] = new CaseChemin(i,j);
                            break;
                    }
                }
            }
        }
        if(niveau.equals("3")){
            la = patron3[1].length;
            lo = patron3.length;
            
            this.plateauJeu = new Case[lo][la];
        
            for(int i =0 ; i < lo ; i++){
                for(int j =0 ; j < la ; j++){
                    if (patron3[i][j].equals("S1")) {
                        this.plateauJeu[i][j] = new CaseSymbole(i,j);
                    } else if (patron3[i][j].equals("S2")){
                        this.plateauJeu[i][j] = new CaseSymbole(i,j);
                    } else{
                        this.plateauJeu[i][j] = new CaseChemin(i,j);
                    }
                }
            }
        }
        if(niveau.equals("4")){
            la = patron4[1].length;
            lo = patron4.length;
            
            this.plateauJeu = new Case[lo][la];
        
            for(int i =0 ; i < lo ; i++){
                for(int j =0 ; j < la ; j++){
                    if (patron4[i][j].equals("S1")) {
                        this.plateauJeu[i][j] = new CaseSymbole(i,j);
                    } else if (patron4[i][j].equals("S2")){
                        this.plateauJeu[i][j] = new CaseSymbole(i,j);
                    } else{
                        this.plateauJeu[i][j] = new CaseChemin(i,j);
                    }
                }
            }
        }
        
        
        
    }
    
    public boolean puzzleResolu(){
        for(int i =0 ; i < lo ; i++){
            for(int j =0 ; j < la ; j++){
                if(this.plateauJeu[i][j].estLibre()) return(false);
            }
        }
        return true;
    }
    
    public int getLo(){
        return this.lo;
    }
    
    public int getLa(){
        return this.la;
    }
}
