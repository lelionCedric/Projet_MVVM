/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Cedric
 */
public class PeriodiciteObstacle extends Observable{
    private static PeriodiciteObstacle periode;
    private Timer timer;

    public static PeriodiciteObstacle getInstance() {
        if (periode == null) {
            periode = new PeriodiciteObstacle();
        }
        return periode;
    }

    private PeriodiciteObstacle() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setChanged();
                notifyObservers();
            }
        }, 5000, 5000);
    }
}
