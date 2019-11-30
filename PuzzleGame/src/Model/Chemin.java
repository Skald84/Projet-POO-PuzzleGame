/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author aymer
 */
public class Chemin extends LinkedList<Case>{
    
    public Chemin(){
        super();
        System.out.println(this);
    }
 
    @Override
    public void addLast(Case casePointee){
        super.addLast(casePointee);
        casePointee.setLibre(false);
    }
    
    public void afficheChemin(){
        System.out.println("debut liste:--------------------------");
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i).toString());
        }
        System.out.println("fin liste-----------------------------");          
    }
    
    /**
     *rend les cases libres et vide le chemin
     */
    public void reinitialisation(){
        
        super.clear();
    }    
   
    
}
