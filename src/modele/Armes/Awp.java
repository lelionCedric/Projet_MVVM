/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.Armes;

/**
 *
 * @author Cedric
 */
public class Awp extends Arme{
    
    public Awp(String nom) {
        super(nom);
        super.setPuissance(20);
        super.setCheminImage("/images/weapons/awp.png");
        super.setProbabiliteSpawn(0.2);
    }
    
}
