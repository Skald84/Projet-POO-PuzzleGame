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
    
    private symbole s;
    
    public CaseSymbole(int x, int y, String typeSymbole) {
      super(x, y);
        System.out.println(typeSymbole);
      switch (typeSymbole) {
            case "S1":
                this.s = symbole.S1;
                break;
            case "S2":
                this.s = symbole.S2;
                break;
            default:
              this.s = null;
        }
        System.out.println(this.s);
      setCaseImg();
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
                ", Symbole : " + this.s +
                ", image : " + this.img + "].");
    }
    
    private void setCaseImg(){
        if (this.s == symbole.S1) img = "/images/S1.png";
        if (this.s == symbole.S2) img = "/images/S2.png";    
    }
    
    private enum symbole {
        S1, S2
    }
}