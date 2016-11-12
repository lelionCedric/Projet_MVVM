/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.Armes;

/**
 *
 * @author celelion
 */
public class Eagle  extends Arme{
    public Eagle(String nom) {
        super(nom);
        super.setPuissance(10);
        super.setCheminImage("/images/weapons/eagle2.png");
        super.setProbabiliteSpawn(0.5);
    }
    
}
