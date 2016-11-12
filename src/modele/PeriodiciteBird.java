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
public class PeriodiciteBird extends Observable {

    private static PeriodiciteBird periode;
    private Timer timer;

    public static PeriodiciteBird getInstance() {
        if (periode == null) {
            periode = new PeriodiciteBird();
        }
        return periode;
    }

    private PeriodiciteBird() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setChanged();
                notifyObservers();
            }
        }, 8000, 8000);
    }
}
