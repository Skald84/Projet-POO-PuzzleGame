/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Observable;

/**
 *
 * @author et8ge
 */
public abstract class Case extends Observable{

    protected enum voisin {
        LIBRE, N, E, S, O
    }
    
    public final int x;
    public final int y;
    
    public boolean libre;
    protected voisin voisin1;
    private String img;
    
    
    public Case(int cX, int rY){
        this.x = cX;
        this.y = rY;
        this.libre = true;
        this.img = "/images/LIBRE.png";
    }    

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getLibre() {
        return libre;
    }

    public void setLibre(boolean libre) {
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
    
    public String getImage(){
        return this.img;
    }
    
    protected boolean estVoisinDe(Case c){
        
        if(this.estVoisinParLeNord(c) || this.estVoisinParLEst(c) || this.estVoisinParLeSud(c) || this.estVoisinParLOuest(c)) return true;
        return false;
    }
    
    protected boolean estVoisinParLeNord(Case c2){
        if(x == c2.getX() && y-1 == c2.getY()) return true;
        else return false;
    }
    
    protected boolean estVoisinParLEst(Case c2){
        if(x+1 == c2.getX() && y == c2.getY()) return true;
        else return false;
    }
        
    protected boolean estVoisinParLeSud(Case c2){
        if(x == c2.getX() && y+1 == c2.getY())return true;
        else return false;
    }
    
    protected boolean estVoisinParLOuest(Case c2){
        if(x-1 == c2.getX() && y == c2.getY()) return true;
        else return false;
    }
    
    public void setVoisin1(Case c){
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
}
