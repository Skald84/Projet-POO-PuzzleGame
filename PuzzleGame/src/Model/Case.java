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
        
    public int x;
    public int y;
    
    public boolean libre;
    
    
    public Case(int cX, int rY){
        this.x = cX;
        this.y = rY;
        this.libre = true;
    }    
    
}
