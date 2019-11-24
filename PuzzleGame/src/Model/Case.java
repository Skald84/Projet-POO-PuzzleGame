/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Observable;

    /** Représente les cases (i.e. en général) qui composent la grille
     * 
     * @authors
     *          G.FERRAND & A.TOUCHE
     * @version
     *          1.0
     * @since
     *          1.0
     */
public abstract class Case extends Observable{

    protected enum voisin {
        LIBRE, N, E, S, O
    }
    
    protected final int x;
    protected final int y;
    
    protected boolean libre;
    protected voisin voisin1;
    protected String img;
    
    
    protected Case(int cX, int rY){
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
        this.voisin1 = voisin.LIBRE;
    }
    
    @Override
    public String toString(){
        return( getClass().getName() + "@" + Integer.toHexString(hashCode()) +
                " ["
                + this.x + ", "
                + this.y +
                ", libre : " + this.libre +
                ", voisin : " + this.voisin1 + "].");
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
    
    /**
     * Cette méthode permet de déterminer si l'objet est voisin par rapport à une autre Case
     * 
     * @authors
     *          G.FERRAND & A.TOUCHE
     * @param c
     *          La case qui sera comparé à l'objet pour déterminé son éventuel voisinage
     * @return
     *          Un booléen, en fonction de la véracité de la proposition
     * @version
     *          1.0
     * @since
     *          1.0
     */
    protected boolean estVoisinDe(Case c){
        
        return this.estVoisinParLeNord(c) || this.estVoisinParLEst(c) || this.estVoisinParLeSud(c) || this.estVoisinParLOuest(c);
    }
    
    /**
     * Cette méthode permet de déterminer si l'objet est voisin par le Nord par rapport à une autre Case
     * 
     * @authors
     *          G.FERRAND & A.TOUCHE
     * @param c2
     *          La case qui sera comparé à l'objet pour déterminé son éventuel voisinage par le Nord
     * @return
     *          Un booléen, en fonction de la véracité de la proposition
     * @version
     *          1.0
     * @since
     *          1.0
     */
    protected boolean estVoisinParLeNord(Case c2){
        return x == c2.getX() && y-1 == c2.getY();
    }
    
    /**
     * Cette méthode permet de déterminer si l'objet est voisin par l'Est par rapport à une autre Case
     * 
     * @authors
     *          G.FERRAND & A.TOUCHE
     * @param c2
     *          La case qui sera comparé à l'objet pour déterminé son éventuel voisinage par l'Est
     * @return
     *          Un booléen, en fonction de la véracité de la proposition
     * @version
     *          1.0
     * @since
     *          1.0
     */
    protected boolean estVoisinParLEst(Case c2){
        return x+1 == c2.getX() && y == c2.getY();
    }
    
    /**
     * Cette méthode permet de déterminer si l'objet est voisin par le Sud par rapport à une autre Case
     * 
     * @authors
     *          G.FERRAND & A.TOUCHE
     * @param c2
     *          La case qui sera comparé à l'objet pour déterminé son éventuel voisinage par le Sud
     * @return
     *          Un booléen, en fonction de la véracité de la proposition
     * @version
     *          1.0
     * @since
     *          1.0
     */
    protected boolean estVoisinParLeSud(Case c2){
        return x == c2.getX() && y+1 == c2.getY();
    }

    /**
     * Cette méthode permet de déterminer si l'objet est voisin par l'Ouest par rapport à une autre Case
     * 
     * @authors
     *          G.FERRAND & A.TOUCHE
     * @param c2
     *          La case qui sera comparé à l'objet pour déterminé son éventuel voisinage par l'Ouest
     * @return
     *          Un booléen, en fonction de la véracité de la proposition
     * @version
     *          1.0
     * @since
     *          1.0
     */
    protected boolean estVoisinParLOuest(Case c2){
        return x-1 == c2.getX() && y == c2.getY();
    }
    
    /**
     * Cette méthode permet de déterminer le voisinage (premier dans le cas d'une CaseChemin) d'une Case par rapport à une autre
     * 
     * @authors
     *          G.FERRAND & A.TOUCHE
     * @param c
     *          La case qui sera comparé à l'objet pour déterminé son voisinage 
     * @version
     *          1.0
     * @since
     *          1.0
     */
    protected void setVoisin1(Case c){
        if (this.estVoisinParLeNord(c)){
            this.voisin1 = voisin.N;
        }
        else if (this.estVoisinParLEst(c)){
            this.voisin1 = voisin.E;
        }
        else if (this.estVoisinParLeSud(c)){
            this.voisin1 = voisin.S;
        }
        else if (this.estVoisinParLOuest(c)){
            this.voisin1 = voisin.O;
        }
        else {
            this.voisin1 = voisin.LIBRE;
        }
    }
    
    protected void setCaseImg(){
        
    }
}