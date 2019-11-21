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
public class Case extends Observable{
        
    public final int x;
    public final int y;
    
    public boolean libre;
    
    
    public Case(int cX, int rY){
        this.x = cX;
        this.y = rY;
        this.libre = true;
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
    }
    
    protected boolean estVoisinDe(Case c){
        if (this.getX() == c.getX()) {
            if(this.getY() == c.getY()-1 || this.getY() == c.getY()+1) return(true);
            else return(false);
        }
        else if (this.getY() == c.getY()){
            if(this.getX() == c.getX()-1 || this.getX() == c.getX()+1) return(true);
            else return(false);
        }
        else return(true);
    }
}
