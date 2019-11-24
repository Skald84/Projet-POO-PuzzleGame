/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

    /** Représente les cases symboles dans la grille
     * 
     * @authors
     *          G.FERRAND & A.TOUCHE
     * @version
     *          1.0
     * @since
     *          1.0
     */
public class CaseSymbole extends Case{
    
    private enum symbole {
        S1, TRIANGLE, CARRE, CERCLE
    }
    
    private symbole s;
    
    protected CaseSymbole(int x, int y) {
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
                ", Symbole : " + this.s + "].");
    }
    
    @Override
    public void setCaseImg(){
        switch (this.s){
            case S1 :
                img = "/images/S1.png";
            case TRIANGLE :
                img = "/images/S1.png";
            case CARRE :
                img = "/images/S1.png";
            case CERCLE :
                img = "/images/S1.png";
            default :
                img = "/images/ERREUR.png";
        }
    }
}
