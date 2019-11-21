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
    
    public boolean estVoisinDe(Case c){
        if (this.getX() == c.getX() || this.getY() == c.getY()) return(true);
        else return(false);
    }
}
