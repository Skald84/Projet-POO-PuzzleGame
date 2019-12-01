/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

    /** Représente les cases (i.e. en général) qui composent la grille
     * 
     * @authors
     *          G.FERRAND & A.TOUCHE
     * @version
     *          1.0
     * @since
     *          1.0
     */
public abstract class Case{
    
    protected final int x;
    protected final int y;
    
    protected boolean libre;
    protected String img = "/images/LIBRE.png";
    
    
    protected Case(int rY, int cX){
        this.x = cX;
        this.y = rY;
        this.libre = true;
    }    

    /**
     * Cette méthode permet d'accéder à l'abscisse de la case (i.e. la colonne)
     * 
     * @authors
     *          G.FERRAND & A.TOUCHE
     * @return
     *          L'attribut x, qui est la colonne de la case
     * @version
     *          1.0
     * @since
     *          1.0
     */
    public int getX() {
        return x;
    }

    /**
     * Cette méthode permet d'accéder à l'ordonnée de la case (i.e. la ligne)
     * 
     * @authors
     *          G.FERRAND & A.TOUCHE
     * @return
     *          L'attribut y, qui est la ligne de la case
     * @version
     *          1.0
     * @since
     *          1.0
     */
    public int getY() {
        return y;
    }

    /**
     * Cette méthode permet d'accéder à la disponibilité d'une case pour former un chemin
     * 
     * @authors
     *          G.FERRAND & A.TOUCHE
     * @return
     *          L'attribut libre, qui représente l'appartenance éventuelle de la case à un chemin
     * @version
     *          1.0
     * @since
     *          1.0
     */
    protected boolean estLibre() {
        return libre;
    }

    /**
     * Cette méthode permet d'affecter la disponibilité d'une case pour de former un chemin
     * 
     * @authors
     *          G.FERRAND & A.TOUCHE
     * @param libre
     *          Le booléen qui remplacera l'attribut libre de l'objet
     * @version
     *          1.0
     * @since
     *          1.0
     */
    protected void setLibre(boolean libre) {
        this.libre = libre;
    }
    
    @Override
    public String toString(){
        return( getClass().getName() + "@" + Integer.toHexString(hashCode()) +
                " ["
                + this.x + ", "
                + this.y +
                ", libre : " + this.libre + "].");
    }
    
    /**
     * Cette méthode permet de retourner, sous la forme d'une chaîne de caractère, le chemin absolu vers l'image représentant l'état actuel de l'objet
     * Cette méy=thode dispose d'une portée publique car elle est appellée dans le controller
     * 
     * @authors
     *          G.FERRAND & A.TOUCHE
     * @return
     *          L'attribut img, qui est le chemin absolu vers l'image représentant l'état actuel de l'objet
     * @version
     *          1.0
     * @since
     *          1.0
     */
    public String getImage(){
        return this.img;
    }
}