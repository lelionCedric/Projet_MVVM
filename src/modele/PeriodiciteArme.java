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
 * @author celelion
 */
public class PeriodiciteArme extends Observable {

    private static PeriodiciteArme periode;
    private Timer timer;

    public static PeriodiciteArme getInstance() {
        if (periode == null) {
            periode = new PeriodiciteArme();
        }
        return periode;
    }

    private PeriodiciteArme() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setChanged();
                notifyObservers();
            }
        }, 1000, 10000);
    }
}
