/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;

/**
 *
 * @author et8ge
 */
public abstract class Case {
    
    private boolean caseLibre, caseSymbole;
    
    public Case(boolean caseSymbole){
        
        if(caseSymbole == true){
            this.caseLibre = false;
            this.caseSymbole = true;
        } else {
            this.caseLibre = true;
            this.caseSymbole = false;
        }     
    }
    
    public boolean getCaseLibre(){
        return this.caseLibre;
    }
    
    public boolean getCaseSymbole(){
        return this.caseSymbole;
    }
    
    public void setCaseLibre(boolean caseLibre){
        this.caseLibre = caseLibre;
    }
    
    
    
}
