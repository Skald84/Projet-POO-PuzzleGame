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
      setCaseImg();
      System.out.println("new CaseSymbole");
    }

    /**
     * Cette méthode permet d'accéder au symbole de l'objet
     * 
     * @authors
     *          G.FERRAND & A.TOUCHE
     * @return
     *          Le symbole de l'objet
     * @version
     *          1.0
     * @since
     *          1.0
     */
    protected symbole getSymbole(){
        return this.s;
    }
    
    @Override
    public String toString(){
        return( getClass().getName() + "@" + Integer.toHexString(hashCode()) +
                " ["
                + this.x + ", "
                + this.y +
                ", libre : " + this.libre +
                ", voisin : " + this.voisin1 +
                ", Symbole : " + this.s +
                ", image : " + this.img + "].");
    }
    
    private void setCaseImg(){
        if (this.s == symbole.S1) img = "/images/S1.png";
        if (this.s == symbole.S1) img = "/images/S1.png";
        if (this.s == symbole.S1) img = "/images/S1.png";
        if (this.s == symbole.S1) img = "/images/S1.png";
        else img = "/images/ERREUR.png";
        System.out.println("setcaseimg casesymbole");
        System.out.println(this);
    }
}
