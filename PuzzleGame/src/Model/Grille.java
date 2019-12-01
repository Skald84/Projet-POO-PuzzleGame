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
    
    int nbLignes;
    int nbColonnes;
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
    
    
    /**
     * Crée un objet grille selon le niveau choisi
     * @param niveau
     */
    public Grille(String niveau){
        nbLignes = 1;
        nbColonnes = 1;
        if(niveau.equals("1")){
            nbColonnes = patron1[1].length;
            nbLignes = patron1.length;
            
            this.plateauJeu = new Case[nbLignes][nbColonnes];
        
            for(int i =0 ; i < nbLignes ; i++){
                for(int j =0 ; j < nbColonnes ; j++){
                    switch (patron1[i][j]) {
                        case "S1":
                            this.plateauJeu[i][j] = new CaseSymbole(i,j,patron1[i][j]);
                            break;
                        case "S2":
                            this.plateauJeu[i][j] = new CaseSymbole(i,j,patron1[i][j]);
                            break;
                        default:
                            this.plateauJeu[i][j] = new CaseChemin(i,j);
                            break;
                    }
                }
            }
        }
        if(niveau.equals("2")){
            nbColonnes = patron2[1].length;
            nbLignes = patron2.length;
            
            this.plateauJeu = new Case[nbLignes][nbColonnes];
        
            for(int i = 0 ; i < nbLignes ; i++){
                for(int j = 0 ; j < nbColonnes ; j++){
                    switch (patron2[i][j]) {
                        case "S1":
                            this.plateauJeu[i][j] = new CaseSymbole(i,j,patron2[i][j]);
                            break;
                        case "S2":
                            this.plateauJeu[i][j] = new CaseSymbole(i,j,patron2[i][j]);
                            break;
                        default:
                            this.plateauJeu[i][j] = new CaseChemin(i,j);
                            break;
                    }
                }
            }
        }
        if(niveau.equals("3")){
            nbColonnes = patron3[1].length;
            nbLignes = patron3.length;
            
            this.plateauJeu = new Case[nbLignes][nbColonnes];
        
            for(int i =0 ; i < nbLignes ; i++){
                for(int j =0 ; j < nbColonnes ; j++){
                    switch (patron3[i][j]) {
                        case "S1":
                            this.plateauJeu[i][j] = new CaseSymbole(i,j,patron3[i][j]);
                            break;
                        case "S2":
                            this.plateauJeu[i][j] = new CaseSymbole(i,j,patron3[i][j]);
                            break;
                        default:
                            this.plateauJeu[i][j] = new CaseChemin(i,j);
                            break;
                    }
                }
            }
        }
    }
    
    public boolean puzzleResolu(){
        for(int i =0 ; i < nbLignes ; i++){
            for(int j =0 ; j < nbColonnes ; j++){
                if(this.plateauJeu[i][j].estLibre()) return(false);
            }
        }
        return true;
    }
    
    public int getLo(){
        return this.nbLignes;
    }
    
    public int getLa(){
        return this.nbColonnes;
    }
}
