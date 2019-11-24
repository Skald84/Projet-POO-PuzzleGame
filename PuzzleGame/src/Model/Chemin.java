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
        
        int tailleDuChemin = size();
        
        casePointee.setLibre(false);
        
        if(tailleDuChemin == 0) {//si le chemin est vide
            super.addLast(casePointee);
        }
        else{// si le chemin n'est pas vide
            Case casePrecedente = this.get(tailleDuChemin-1);
            casePointee.setVoisin1(casePrecedente);//la case courante est avoisinée avec la case précédente
            
            if(tailleDuChemin > 1){//si le chemin contient au moins 3 cases
                
                    CaseChemin CaseCheminPrecedente = (CaseChemin)casePrecedente;//on est obligé de la caster avant, sinon ca ne fonctionne pas...
                    CaseCheminPrecedente.setVoisin2(casePointee);//la case précédente est avoisinée avec la case courante
            }
            super.addLast(casePointee);//une fois tout les voisins mis en place, on peut ajouter la case courante dans le chemin
        }
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
        for (int i = 0; i < this.size(); i++) {
            this.get(i).setLibre(true);
        }
        super.clear();
    }    
   
    
}
