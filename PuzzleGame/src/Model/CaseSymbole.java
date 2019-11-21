/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author aymer
 */
public class CaseSymbole extends Case{
    
    private enum symbole {
        S1, TRIANGLE, CARRE, CERCLE
    }
    
    
    /**
     *
     * @param x
     * @param y
     * @param s
     */
    public CaseSymbole(int x, int y) {
      super(x, y);
      System.out.println("new CaseSymbole");
      //this.symbole = s;
    }
    
    private symbole s;
    
    private String imageDe(symbole s){
        switch (s){
            case S1 :
                return("/images/S1.png");
            case TRIANGLE :
                return("/images/S1.png");
            case CARRE :
                return("/images/S1.png");
            case CERCLE :
                return("/images/S1.png");
            default :
                return("/images/ERREUR.png");
        }
    }
    
}
