/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.obstacle;

/**
 *
 * @author celelion
 */
public class Pic extends Obstacle{

    public Pic(double posX, double posY) {
        super(posX, posY);
        super.setCherminImage("/images/obstacles/pike2.png");
    }
}
