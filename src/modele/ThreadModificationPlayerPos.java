/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cedric
 */
public class ThreadModificationPlayerPos implements Runnable {

    private Player p;

    public ThreadModificationPlayerPos(Player p) {
        this.p = p;
    }

    @Override
    public void run() {
        p.setUp(true);
        try {
            Thread.sleep(450);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadModificationPlayerPos.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setUp(false);
        setFalse();
    }

    private void setFalse() {
        p.setEtat(false);
    }
}
