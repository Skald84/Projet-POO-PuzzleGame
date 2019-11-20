/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import VueControleur.VueController;
import java.util.Observable;


/**
 *
 * @author et8ge
 */
public class Model extends Observable {
    
    int lastC, lastR;
    
    public void startDD(int c, int r) {
        // TODO
        Chemin chemin = new Chemin();
        
        // si case symbole ok, sinon c'est mort  ==> on ne peut cliquer que sur des cases symbole instanceof()
        
        System.out.println("startDD : " + c + "-" + r);
        setChanged();
        notifyObservers();
    }
    
    public void stopDD(int c, int r) {
        // TODO
        
        // mémoriser le dernier objet renvoyé par parcoursDD pour connaitre la case de relachement
        
        System.out.println("stopDD : " + c + "-" + r + " -> " + lastC + "-" + lastR);
        setChanged();
        notifyObservers();
    }
    
    public void parcoursDD(int c, int r) {
        // TODO : comment ordonner à la vue de changer l'image de la case survolée ?
        lastC = c;
        lastR = r;
        
        System.out.println("parcoursDD : " + c + "-" + r);
        setChanged();
        notifyObservers();
    }
   

}
