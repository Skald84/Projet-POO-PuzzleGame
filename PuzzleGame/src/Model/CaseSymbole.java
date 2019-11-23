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
    
    private symbole s;
    private String img;
    private Case.voisin voisin1;
    
    public CaseSymbole(int x, int y) {
      super(x, y);
      this.s = symbole.S1;
      System.out.println("new CaseSymbole");
    }
    
    @Override
    public String toString(){
        return( getClass().getName() + "@" + Integer.toHexString(hashCode()) +
                " ["
                + this.x + ", "
                + this.y +
                ", libre : " + this.libre +
                ", voisin : " + this.voisin1 +
                ", voisin2 : " + this.voisin1 +
                ", Symbole : " + this.s + "].");
    }
        
    @Override
    public String getImage(){
        switch (this.s){
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
