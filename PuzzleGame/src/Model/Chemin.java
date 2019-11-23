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
            System.out.println("le chemin est vide donc la premiere case est une case symbole et elle est ajoutée au chemin");
            super.addLast(casePointee);
        }
        else{// si le chemin n'est pas vide
            System.out.println("le chemin nest pas vide");
            Case casePrecedente = this.get(tailleDuChemin-1);
            casePointee.setVoisin1(casePrecedente);//la case courante est avoisinée avec la case précédente
            
            if(tailleDuChemin > 1){//si le chemin contient au moins 3 cases
                System.out.println("il y a au moins 1 case dans le chemin");
                
                if(casePointee instanceof CaseChemin){//si la case pointée est une case chemin
                    CaseChemin CaseCheminPrecedente = (CaseChemin)casePrecedente;//on est obligé de la caster avant, sinon ca ne fonctionne pas...
                    CaseCheminPrecedente.setVoisin2(casePointee);//la case précédente est avoisinée avec la case courante
                }
                
                else{//il s'agit alors d'une case symbole, la derniere case du chemin
                    System.out.println("cette case est une case symbole, et ce doit être la derniere du chemin, elle est avoisinée avec la case précédente");
                    casePointee.setVoisin1(casePrecedente);
                }   
            }
            System.out.println("la case courante est ajoutée au chemin");
            super.addLast(casePointee);//une fois tout les voisins mis en place, on peut ajouter la case courante dans le chemin
        }
        
        
        
        
        
        
        
//        if(tailleDuChemin == 0)super.add(casePointee);//si le chemin est vide
//        
//        else{//si le chemin n'est pas vide
//            Case casePrecedente = getLast();
//            
//            if (tailleDuChemin > 2){
//                CaseChemin caseCheminPrecedente = (CaseChemin)casePrecedente;
//                caseCheminPrecedente.setVoisin2(casePointee);
//            }
//            else if (tailleDuChemin > 1){
//                casePointee.setVoisin1(casePrecedente);
//            }
//            super.add(casePointee);
//        }
    }
    
    public void affiche(){
        System.out.println("debut liste:--------------------------");
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i).toString());
        }
        System.out.println("fin liste-----------------------------");          
    }
    
    /**
     *rend les cases libres et vide le chemin
     */
    public void setFreeAllCasesAndClear(){
        for (int i = 0; i < this.size(); i++) {
            this.get(i).setLibre(true);
        }
        super.clear();
    }    
   
    
}
