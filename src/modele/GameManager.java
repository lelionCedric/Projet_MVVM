/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Observer;
import java.util.Observable;
import javafx.application.Platform;

/**
 *
 * @author celelion
 */
public class GameManager implements Observer {

    private Game game;

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Periodicite) {
            Platform.runLater(() ->game.move());
            //game.move();
        }
        if (o instanceof PeriodiciteBird) {
            game.genererRandomBird();
        }
        if (o instanceof PeriodiciteObstacle) {
            game.genererRandomObstacle();
        }
    }

    public GameManager(Game game) {
        this.game = game;
    }
}
