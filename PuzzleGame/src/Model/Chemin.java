/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author aymer
 */
public class Chemin extends LinkedList<Case>{
    
    private Iterator it = this.iterator();
    
    public Chemin(){
        super();
    }
    
    @Override
    public void addLast(Case element){
        super.add(element);
    }
    
    
    public void affiche(){
        while (it.hasNext()){
            System.out.println(it.next());
        }
                
    }
    
    
    
}
