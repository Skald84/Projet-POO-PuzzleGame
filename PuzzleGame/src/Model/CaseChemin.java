/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

    /** Représente les cases chemin qui composent la grille
     * 
     * @authors
     *          G.FERRAND & A.TOUCHE
     * @version
     *          1.0
     * @since
     *          1.0
     */
public class CaseChemin extends Case{
    
    protected voisin voisin1;
    private voisin voisin2;
    
    /** Ce constructeur créé une case chemin à partir de ses position en x et en y
     * 
     * @authors
     *          G.FERRAND & A.TOUCHE
     * @param cX
     *          La position en abscisse (i.e. la colonne) de l'objet
     * @param rY
     *          La position en ordonnée (i.e. la ligne) de l'objet
     * @version
     *          1.0
     * @since
     *          1.0
     */
    protected CaseChemin(int cX, int rY) {
        super(cX, rY);
        this.img = "/images/LIBRE.png";
        this.voisin1 = voisin.LIBRE;
        this.voisin2 = voisin.LIBRE;
    }
    
    @Override
    protected void setLibre(boolean libre) {
        super.setLibre(libre);
        if(libre){
            this.voisin1 = voisin.LIBRE;
            this.voisin2 = voisin.LIBRE;
        }
        this.setCaseImg();
    }
    
    @Override
    public String toString(){
        return( getClass().getName() + "@" + Integer.toHexString(hashCode()) +
                " ["
                + this.x + ", "
                + this.y +
                ", libre : " + this.libre +
                ", voisin : " + this.voisin1 +
                ", voisin2 : " + this.voisin2 +
                ", image : " + this.img + "].");
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
        System.out.println(this.voisin1);
        
        this.setCaseImg();
    }
    
    /**
     * Cette méthode permet de déterminer le second voisinage d'une CaseChemin
     * 
     * @authors
     *          G.FERRAND & A.TOUCHE
     * @param c
     *          La case qui sera comparé à l'objet pour déterminé le second voisinage d'une case chemin
     * @version
     *          1.0
     * @since
     *          1.0
     * @see
     *          Case.setVoisin1() pour plus de détails sur la méthode
     */
    protected void setVoisin2(Case c){
        if(this.estVoisinParLeNord(c)){
            this.voisin2 = voisin.N;
        }
        else if(this.estVoisinParLEst(c)){
            this.voisin2 = voisin.E;
        }
        else if(this.estVoisinParLeSud(c)){
            this.voisin2 = voisin.S;
        }
        else if(this.estVoisinParLOuest(c)){
            this.voisin2 = voisin.O;
        }
        else{
            this.voisin2 = voisin.LIBRE;
        }
        this.setCaseImg();
    }
    
    public void setCaseImg(){
        if (this.voisin1 == voisin.LIBRE && this.voisin2 == voisin.LIBRE) this.img = "/images/LIBRE.png";
        else if (this.voisin1 == voisin.N && this.voisin2 == voisin.LIBRE) this.img = "/images/N.png";
        else if (this.voisin1 == voisin.E && this.voisin2 == voisin.LIBRE) this.img = "/images/E.png";
        else if (this.voisin1 == voisin.S && this.voisin2 == voisin.LIBRE) this.img = "/images/S.png";
        else if (this.voisin1 == voisin.O && this.voisin2 == voisin.LIBRE) this.img = "/images/O.png";
        else if ((this.voisin1 == voisin.N && this.voisin2 == voisin.E) || (this.voisin1 == voisin.E && this.voisin2 == voisin.N)) this.img = "/images/NE.png";
        else if ((this.voisin1 == voisin.N && this.voisin2 == voisin.S) || (this.voisin1 == voisin.S && this.voisin2 == voisin.N)) this.img = "/images/NS.png";
        else if ((this.voisin1 == voisin.N && this.voisin2 == voisin.O) || (this.voisin1 == voisin.O && this.voisin2 == voisin.N)) this.img = "/images/NO.png";
        else if ((this.voisin1 == voisin.E && this.voisin2 == voisin.S) || (this.voisin1 == voisin.S && this.voisin2 == voisin.E)) this.img = "/images/ES.png";
        else if ((this.voisin1 == voisin.E && this.voisin2 == voisin.O) || (this.voisin1 == voisin.O && this.voisin2 == voisin.E)) this.img = "/images/EO.png";
        else if ((this.voisin1 == voisin.S && this.voisin2 == voisin.O) || (this.voisin1 == voisin.O && this.voisin2 == voisin.S)) this.img = "/images/SO.png";
        else this.img = "/images/ERREUR.png";//si voisin1 est libre et voisins2 ne l'est pas par exemple...
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
    
        protected enum voisin {
        LIBRE, N, E, S, O
    }
}
