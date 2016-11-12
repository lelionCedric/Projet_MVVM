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
public class Periodicite extends Observable {

    private static Periodicite periode;
    private Timer timer;

    public static Periodicite getInstance() {
        if (periode == null) {
            periode = new Periodicite();
        }
        return periode;
    }

    private Periodicite() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setChanged();
                notifyObservers();
            }
        }, 15, 15);
    }
}
