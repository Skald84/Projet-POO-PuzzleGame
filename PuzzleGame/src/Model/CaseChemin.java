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
        System.out.println("new CaseChemin");
    }
    
    @Override
    protected void setLibre(boolean libre) {
        this.libre = libre;
        this.img = "/images/LIBRE.png";
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
                ", image : " + this.img + "].");
    }
    
    @Override
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
    
    @Override
    protected void setCaseImg(){
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
        else this.img = "/images/ERREUR.png";
    }
    
    @Override
    public String getImage(){
        return this.img;
    }
}
