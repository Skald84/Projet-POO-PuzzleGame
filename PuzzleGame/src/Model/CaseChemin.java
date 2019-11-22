/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author aymer
 */
public class CaseChemin extends Case{

    private enum caseImg {
        LIBRE, NE, EN, NS, SN, NO, ON, ES, SE, EO, OE, SO, OS
    }
    
    private caseImg c = caseImg.LIBRE;
    
    public CaseChemin(int cX, int rY) {
        super(cX, rY);
        System.out.println("new CaseChemin");
    }
    
    public void changeImage(){
        
        //
        
        setChanged();
        notifyObservers();
    }

    private String imageDe(caseImg c){
        if(c == caseImg.LIBRE) return("/images/LIBRE.png");
        else if (c == caseImg.NE || c == caseImg.EN) return("/images/NE.png");
        else if (c == caseImg.NS || c == caseImg.SN) return("/images/NS.png");
        else if (c == caseImg.NO || c == caseImg.ON) return("/images/NO.png");
        else if (c == caseImg.ES || c == caseImg.SE) return("/images/ES.png");
        else if (c == caseImg.EO || c == caseImg.OE) return("/images/EO.png");
        else if (c == caseImg.SO || c == caseImg.OS) return("/images/SO.png");
        else return("/images/ERREUR.png");
    }
}
