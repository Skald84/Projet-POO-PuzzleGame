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
//        for (int i = 0; i < this.size(); i++) {
//            this.get(i).setLibre(true);
//        }
        super.clear();
    }    
    
    public void aide(String niveau){
        if (niveau.equals("1")) {
            CaseSymbole case1=new CaseSymbole(0,0,"S1") ;
            CaseChemin case2= new CaseChemin(0,1);
            CaseChemin case3=new CaseChemin(0,2);
            CaseChemin case4=new CaseChemin(0,3);
            CaseChemin case5=new CaseChemin(1,3);
            CaseChemin case6=new CaseChemin(2,3);
            CaseSymbole case7=new CaseSymbole(3,3,"S1");
            this.addLast(case1);
            this.addLast(case2);
            this.addLast(case3);
            this.addLast(case4);
            this.addLast(case5);
            this.addLast(case6);
            this.addLast(case7);  
            
            for(Case i : this){
                if (this.indexOf(i)>0) {
                    if (this.get(this.indexOf(i)-1) instanceof CaseChemin) {
                        if(i instanceof CaseChemin){
                            System.out.println(i.toString());
                            CaseChemin CaseCheminPrecedente = (CaseChemin) this.get(this.indexOf(i)-1);
                            CaseCheminPrecedente.setVoisin2(this.get(this.indexOf(i)));
                            CaseChemin caseCheminPointee = (CaseChemin) i;
                            caseCheminPointee.setVoisin1(this.get(this.indexOf(i)-1));
                            
                        } else {
                            if ((this.get(this.indexOf(i)-1) instanceof CaseChemin)) {
                                CaseChemin CaseCheminPrecedente = (CaseChemin) this.get(this.indexOf(i)-1); // on est obligÃ© de prÃ©caste en case-chemin !
                                CaseCheminPrecedente.setVoisin2(this.get(this.indexOf(i)));
                            }
                        }
                    } else{
                        if (i instanceof CaseChemin) {
                            CaseChemin caseCheminPointee = (CaseChemin) i;
                            caseCheminPointee.setVoisin1(this.get(this.indexOf(i)-1));
                        }
                    }
                }
                this.afficheChemin();
            }
        } else if(niveau.equals("2")){
            CaseSymbole case1=new CaseSymbole(0,0,"S1") ;
            CaseChemin case2= new CaseChemin(0,1);
            CaseChemin case3=new CaseChemin(0,2);
            CaseChemin case4=new CaseChemin(0,3);
            CaseChemin case5=new CaseChemin(1,3);
            CaseChemin case6=new CaseChemin(2,3);
            CaseChemin case7=new CaseChemin(3,3);
            CaseChemin case8=new CaseChemin(4,3);
            CaseChemin case9=new CaseChemin(4,2);
            CaseChemin case10=new CaseChemin(4,1);
            CaseSymbole case11=new CaseSymbole(4,0,"S1");
            this.addLast(case1);
            this.addLast(case2);
            this.addLast(case3);
            this.addLast(case4);
            this.addLast(case5);
            this.addLast(case6);
            this.addLast(case7);  
            this.addLast(case8);  
            this.addLast(case9);  
            this.addLast(case10);  
            this.addLast(case11);  
            
            for(Case i : this){
                if (this.indexOf(i)>0) {
                    if (this.get(this.indexOf(i)-1) instanceof CaseChemin) {
                        if(i instanceof CaseChemin){
                            System.out.println(i.toString());
                            CaseChemin CaseCheminPrecedente = (CaseChemin) this.get(this.indexOf(i)-1);
                            CaseCheminPrecedente.setVoisin2(this.get(this.indexOf(i)));
                            CaseChemin caseCheminPointee = (CaseChemin) i;
                            caseCheminPointee.setVoisin1(this.get(this.indexOf(i)-1));
                            
                        } else {
                            if ((this.get(this.indexOf(i)-1) instanceof CaseChemin)) {
                                CaseChemin CaseCheminPrecedente = (CaseChemin) this.get(this.indexOf(i)-1); // on est obligÃ© de prÃ©caste en case-chemin !
                                CaseCheminPrecedente.setVoisin2(this.get(this.indexOf(i)));
                            }
                        }
                    } else{
                        if (i instanceof CaseChemin) {
                            CaseChemin caseCheminPointee = (CaseChemin) i;
                            caseCheminPointee.setVoisin1(this.get(this.indexOf(i)-1));
                        }
                    }
                }
                this.afficheChemin();
            }
        }else if(niveau.equals("3")){
            CaseSymbole case1=new CaseSymbole(5,3,"S1") ;
            CaseChemin case2= new CaseChemin(5,4);
            CaseChemin case3=new CaseChemin(4,4);
            CaseChemin case4=new CaseChemin(4,3);
            CaseChemin case5=new CaseChemin(3,3);
            CaseChemin case6=new CaseChemin(3,4);
            CaseChemin case7=new CaseChemin(2,4);
            CaseChemin case8=new CaseChemin(2,3);
            CaseChemin case9=new CaseChemin(2,2);
            CaseSymbole case10=new CaseSymbole(3,2,"S1");
            this.addLast(case1);
            this.addLast(case2);
            this.addLast(case3);
            this.addLast(case4);
            this.addLast(case5);
            this.addLast(case6);
            this.addLast(case7);  
            this.addLast(case8);  
            this.addLast(case9);   
            this.addLast(case10);   
            
            for(Case i : this){
                if (this.indexOf(i)>0) {
                    if (this.get(this.indexOf(i)-1) instanceof CaseChemin) {
                        if(i instanceof CaseChemin){
                            System.out.println(i.toString());
                            CaseChemin CaseCheminPrecedente = (CaseChemin) this.get(this.indexOf(i)-1);
                            CaseCheminPrecedente.setVoisin2(this.get(this.indexOf(i)));
                            CaseChemin caseCheminPointee = (CaseChemin) i;
                            caseCheminPointee.setVoisin1(this.get(this.indexOf(i)-1));
                            
                        } else {
                            if ((this.get(this.indexOf(i)-1) instanceof CaseChemin)) {
                                CaseChemin CaseCheminPrecedente = (CaseChemin) this.get(this.indexOf(i)-1); // on est obligÃ© de prÃ©caste en case-chemin !
                                CaseCheminPrecedente.setVoisin2(this.get(this.indexOf(i)));
                            }
                        }
                    } else{
                        if (i instanceof CaseChemin) {
                            CaseChemin caseCheminPointee = (CaseChemin) i;
                            caseCheminPointee.setVoisin1(this.get(this.indexOf(i)-1));
                        }
                    }
                }
                this.afficheChemin();
            }
        }
    
    }   
    
}
