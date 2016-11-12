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
public class Bird extends Obstacle {
    public Bird(double posX, double posY) {
        super(posX, posY - 50);
        super.setCherminImage("/images/obstacles/bird2.png");
    }
}
