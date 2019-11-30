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
public class Model extends Observable {
    
    int lastC, lastR;
    
    private Chemin chemin;
    public Grille grille;
    
    /**
     * Crée un objet grille
     * @param niveau
     */
    public void creerGrille(String niveau){
        this.grille = new Grille(niveau);
        
        for(int i = 0 ; i < grille.getLo() ; i++){
            for(int j = 0 ; j < grille.getLa() ; j++){
                System.out.println(this.grille.plateauJeu[i][j]);
            }
        }
    }
    
    /**
     * crée nouveau chemin
     */
    public void creerChemin(){
        this.chemin = new Chemin();
    }
    
    /**
     * click debut drag and drop
     * @param c
     * @param r
     */
    public void startDD(int c, int r) {
        chemin.afficheChemin();
        System.out.println("startDD : " + c + "-" + r);
    }
    
    /**
     * relache click drag and drop
     * @param c
     * @param r
     */
    public void stopDD(int c, int r) {
        // ajouter verif reference != sur getFirst et getLast
        if(!(chemin.isEmpty())){ // si chemin non vide 
            if (chemin.getLast() instanceof CaseSymbole ){ // si dernière case est case symbole
                chemin.afficheChemin();
                System.out.println("chemin valide");
                if(grille.puzzleResolu()){
                    System.out.println("Puzzle Résolu");
                }
                else{
                    System.out.println("Des cases sont encore vides !");
                }
            } else {
                System.out.println("chemin non valide");
                reinitialisation(chemin);
                chemin.afficheChemin();
            }
        } else{
            System.out.println("chemin non valide (chemin vide)");
                reinitialisation(chemin);
                chemin.afficheChemin();
        }
        
        System.out.println("stopDD : " + c + "-" + r + " -> " + lastC + "-" + lastR);
    }
    
    /**
     * pendant drag and drop
     * @param c
     * @param r
     */
    public void parcoursDD(int c, int r) {
        
        lastC = c;
        lastR = r;
        Case casePointee = this.grille.plateauJeu[r][c];
        
        System.out.println("parcoursDD : " + r + "-" + c);
        
////--------------------------------------------------------------------------------------v1
//        if (chemin.isEmpty()) { // si chemin vide
//            if (casePointee instanceof CaseSymbole) { // Si c'est une case symbole
//                this.chemin.addLast(casePointee); // Ajoute la case dans le chemin
//                chemin.afficheChemin();
//            }
//            
//            else {
//                System.out.println("Ne commence pas par un symbole");
//            }
//        }
//        
//        else { // Si le chemin n'est pas vide
//            if (casePointee.estLibre() == true ){ // si la case libre
//                
//                if(casePointee.estVoisinDe(chemin.getLast())){ // Si la case pointée est voisine à la case précédemment pointée
//                    
//                    this.chemin.addLast(casePointee); // Ajoute la case dans le chemin
//                    chemin.afficheChemin();
//                }
//                
//                else{
//                     reinitialisation(chemin); // vide le chemin et libère les cases
//                     System.out.println("La case n'est pas voisine de la précédente !");
//                } 
//            }
//            
//            else { //
//                reinitialisation(chemin); // vide le chemin et libère les cases
//                System.out.println("La case est déjà dans un chemin !");
//            }
//
////---------------------------------------------------------------------------------------------------------------------------------------
        
//-----------------------------------------------------------------------------------------------------------v3
        if(casePointee.estLibre()){
            if(chemin.isEmpty()){
                if(casePointee instanceof CaseSymbole){
                    chemin.addLast(casePointee);
                }
                else{//le chemin ne commence pas par une case symbole
                    System.out.println("Le chemin doit commencer par une case symbole !");
                    reinitialisation(chemin);
                }
            }
            else{//Le chemin n'est pas vide
                if(casePointee instanceof CaseChemin){
                    //------------------------------------------------------------------------FACTORISABLE ?
                    if(chemin.getLast() instanceof CaseSymbole){
                        chemin.getLast().setVoisin1(casePointee);
                    }
                    else{//la case précédente est une case chemin
                        CaseChemin CaseCheminPrecedente = (CaseChemin) chemin.getLast(); // on est obligé de précaste en case-chemin !
                        CaseCheminPrecedente.setVoisin2(casePointee);
                    }
                    casePointee.setVoisin1(chemin.getLast());
                    chemin.addLast(casePointee);
                    //------------------------------------------------------------------------
                }
                else{//la case pointée est une case symbole, et donc potentiellement la derniere du chemin
                    CaseSymbole caseSymbolePointee = (CaseSymbole)casePointee; // on est obligé de précaste en case-symbole !
                    CaseSymbole caseSymboleInitiale = (CaseSymbole)chemin.getFirst(); // on est obligé de précaste en case-symbole !
                    if(caseSymbolePointee.getSymbole() == caseSymboleInitiale.getSymbole()){
                        //------------------------------------------------------------------------FACTORISABLE ?
                        if(chemin.getLast() instanceof CaseSymbole){
                            chemin.getLast().setVoisin1(casePointee);
                        }
                        else{//la case précédente est une case chemin
                            CaseChemin CaseCheminPrecedente = (CaseChemin) chemin.getLast(); // on est obligé de précaste en case-chemin !
                            CaseCheminPrecedente.setVoisin2(casePointee);
                        }
                        casePointee.setVoisin1(chemin.getLast());
                        chemin.addLast(casePointee);
                        //------------------------------------------------------------------------
                    }
                    else{
                        System.out.println("La première et cette case n'ont pas le même symbole !");
                        reinitialisation(chemin);
                    }
                }
            }
        }
        else{//la case est déjà affectée à une chemin
            System.out.println("La case est déjà affectée à un chemin !");
            reinitialisation(chemin);
        }
        chemin.afficheChemin();

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
////------------------------------------------------------------------------------------------------------------v2
//        System.out.println("taille du chemin : "+chemin.size());
//        if (chemin.isEmpty()) { // si chemin vide
//            if (casePointee instanceof CaseSymbole) { // Si c'est une case symbole
//                System.out.println("est une case symbole");
//        
//                int tailleDuChemin = chemin.size();
//
//                casePointee.setLibre(false);
//
//                if(tailleDuChemin == 0) {//si le chemin est vide
//                    System.out.println("ici");
//                    chemin.addLast(casePointee);
//                }
//                else{// si le chemin n'est pas vide
//                    Case casePrecedente = chemin.get(tailleDuChemin-1);
//                    casePointee.setVoisin1(casePrecedente);//la case courante est avoisinée avec la case précédente
//
//                    if(tailleDuChemin > 1){//si le chemin contient au moins 3 cases
//
//                            CaseChemin CaseCheminPrecedente = (CaseChemin)casePrecedente;//on est obligé de la caster avant, sinon ca ne fonctionne pas...
//                            CaseCheminPrecedente.setVoisin2(casePointee);//la case précédente est avoisinée avec la case courante
//                    }
//                    System.out.println("on est la");
//                    chemin.addLast(casePointee);//une fois tout les voisins mis en place, on peut ajouter la case courante dans le chemin
//                }
//                chemin.afficheChemin();
//            }
//            
//            else {
//                System.out.println("Ne commence pas par un symbole");
//            }
//        }
//        else { // Si le chemin n'est pas vide
//            System.out.println("le chemin n'est pas vide");
//            if (casePointee.estLibre() == true ){ // si la case libre
//                
//                if(casePointee.estVoisinDe(chemin.getLast())){ // Si la case pointée est voisine à la case précédemment pointée
//
//                    int tailleDuChemin = chemin.size();
//
//                    casePointee.setLibre(false);
//
//                    if(tailleDuChemin == 0) {//si le chemin est vide
//                        chemin.addLast(casePointee);
//                    }
//                    else{// si le chemin n'est pas vide
//                        Case casePrecedente = chemin.get(tailleDuChemin-1);
//                        casePointee.setVoisin1(casePrecedente);//la case courante est avoisinée avec la case précédente
//
//                        if(tailleDuChemin > 1){//si le chemin contient au moins 3 cases
//
//                                CaseChemin CaseCheminPrecedente = (CaseChemin)casePrecedente;//on est obligé de la caster avant, sinon ca ne fonctionne pas...
//                                CaseCheminPrecedente.setVoisin2(casePointee);//la case précédente est avoisinée avec la case courante
//                        }
//                        chemin.addLast(casePointee);//une fois tout les voisins mis en place, on peut ajouter la case courante dans le chemin
//                    }
//                    chemin.afficheChemin();
//                }
//                
//                else{
//                     reinitialisation(chemin); // vide le chemin et libère les cases
//                     System.out.println("La case n'est pas voisine de la précédente !");
//                } 
//            }
//            
//            else { //
//                reinitialisation(chemin); // vide le chemin et libère les cases
//                System.out.println("La case est déjà dans un chemin !");
//            }
//      }
////-----------------------------------------------------------------------------------------------------------------------------------------------------
            if (chemin.size()>2){
                setChanged();
                notifyObservers(chemin.get(chemin.size()-2));
            }

            setChanged();
            notifyObservers(casePointee);
        //}
    }
    
    private void reinitialisation(Chemin chemin){
        for(Case i : chemin){
            i.setLibre(true);
            setChanged();
            notifyObservers(i);
        }
        chemin.reinitialisation();
    }
}
