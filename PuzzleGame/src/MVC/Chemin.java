/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;

import java.util.ArrayList;

/**
 *
 * @author et8ge
 */
public class Chemin {
    
    public ArrayList<Case> chemin;
    
    public Chemin(Case caseDebut){
        chemin.add(caseDebut);
    }
    
    public void ajouterAuChemin(Case c){
        chemin.add(c);
    }
    
}
