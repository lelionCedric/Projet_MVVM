/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import modele.obstacle.Obstacle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import modele.obstacle.Bird;
import modele.obstacle.Boule;
import modele.obstacle.Pic;
import modele.obstacle.Rock;

/**
 *
 * @author Cedric
 */
public class Game {

    
    private GameManager GM;
    
    private Player player;
        public static final String PROP_PLAYER = "player";
        public Player getPlayer() {return player;}
        public void setPlayer(Player player) {
            Player oldPlayer = this.player;
            this.player = player;
            propertyChangeSupport.firePropertyChange(PROP_PLAYER, oldPlayer, player);
        }

    private List<Obstacle> listObstacle;
        public static final String PROP_LISTOBSTACLE = "listObstacle";
        public static final String PROP_LISTOBSTACLE_REMOVE = "listObstacleRemove";
        public List<Obstacle> getListObstacle() {return listObstacle;}

        public void setListObstacle(List<Obstacle> listObstacle) {
            List<Obstacle> oldListObstacle = this.listObstacle;
            this.listObstacle = listObstacle;
            propertyChangeSupport.firePropertyChange(PROP_LISTOBSTACLE, oldListObstacle, listObstacle);
        }

    private double widht;
        public static final String PROP_WIDHT = "widht";
        public double getWidht() {return widht;}
        public void setWidht(double widht) {
            double oldWidht = this.widht;
            this.widht = widht;
            propertyChangeSupport.firePropertyChange(PROP_WIDHT, oldWidht, widht);
        }
    private double height;
        public static final String PROP_HEIGHT = "height";
        public double getHeight() {return height;}
        public void setHeight(double height) {
            double oldHeight = this.height;
            this.height = height;
            propertyChangeSupport.firePropertyChange(PROP_HEIGHT, oldHeight, height);
        }
    
    private boolean inPause;
        public static final String PROP_INPAUSE = "inPause";
        public boolean isInPause() {return inPause;}
        public void setInPause(boolean inPause) {
            boolean oldInPause = this.inPause;
            this.inPause = inPause;
            propertyChangeSupport.firePropertyChange(PROP_INPAUSE, oldInPause, inPause);
        }

        
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
        public void addPropertyChangeListener(PropertyChangeListener listener) {propertyChangeSupport.addPropertyChangeListener(listener);}
        public void removePropertyChangeListener(PropertyChangeListener listener) {propertyChangeSupport.removePropertyChangeListener(listener);}

        /**
         * 
         * @param heightScreen
         * @param widhtScreen 
         */
    public Game(double heightScreen, double widhtScreen) {
        GM = new GameManager(this);
        Periodicite.getInstance().addObserver(GM);
        PeriodiciteBird.getInstance().addObserver(GM);
        PeriodiciteObstacle.getInstance().addObserver(GM);
        this.height = heightScreen;
        this.widht = widhtScreen;
        setInPause(false);
        player = new Player("Nickname", 0d, heightScreen, 200d);
        listObstacle = new ArrayList<>();
    }
    /**
     * Ajout d'un nouvel obstacle
     * @param obst 
     */
    private void addNewObstacle(Obstacle obst) {
        Obstacle ob = obst;
        listObstacle.add(ob);
        propertyChangeSupport.fireIndexedPropertyChange(PROP_LISTOBSTACLE, listObstacle.size() - 1, null, ob);
    }
    /**
     * Suppresion d'un obstacle passé
     * @param o 
     */
    private void removeObstacle(Obstacle o) {
        propertyChangeSupport.fireIndexedPropertyChange(PROP_LISTOBSTACLE_REMOVE, listObstacle.indexOf(o), o, null);
        listObstacle.remove(listObstacle.indexOf(o));
    }
    /**
     * Deplacement de tout les obstacles
     */
    public void move() {
        List<Obstacle> tempObst = new ArrayList<>();
        for (Obstacle obst : listObstacle) {
            if (obst.getPositionX() <= -50) {
                tempObst.add(obst);
            } else {
               obst.move();
            }
        }
        for(Obstacle obs : tempObst){
             removeObstacle(obs);
        }
        player.moveBullets(height);
    }
    
    /**
     * Controle de collision avec le personnage
     * @return 
     */
    public boolean checkCollision() {
        for (Obstacle o : listObstacle) {
            if (player.getWidht() + player.getPositionX() - 15 > o.getPositionX() && player.getPositionY() + player.getHeight() - 15 >= o.getPositionY() && player.getPositionX() + player.getWidht() - 15 < o.getPositionX() + o.getWidth()) {
                return player.collision();
            }
            if (player.getPositionX() + 15 < o.getPositionX() + o.getWidth() && player.getPositionY() + player.getHeight() - 15 >= o.getPositionY() && player.getPositionX() + 15 > o.getPositionX()) {
                return player.collision();
            }
        }
        return false;
    }

    /**
     * Génération aléatoire d'un oiseau (aléatoire à un instant T)
     */
    public void genererRandomBird() {
        Random random = new Random();
        int r = random.nextInt(1);
        Obstacle obst = genererBird(r);
        if (obst != null) {
            addNewObstacle(obst);
        }
    }
    /**
     * Creation d'un nouvel oiseau
     * @param r
     * @return 
     */
    private Obstacle genererBird(int r) {
        switch (r) {
            case 0:
                return new Bird(widht, height);
            default:
                return null;
        }
    }
    /**
     * Génération aléatoire d'un type d'obstacle
     */
    public void genererRandomObstacle() {
        Random random = new Random();
        int r = random.nextInt(3);
        Obstacle o = genererObstacle(r);
        addNewObstacle(o);
    }
    
    /**
     * Creation d'un nouvel obstacle
     * @param r
     * @return 
     */
    private Obstacle genererObstacle(int r) {
        switch (r) {
            case 0:
                return new Boule(widht, height);
            case 1:
                return new Pic(widht, height);
            case 2:
                return new Rock(widht, height);
            default:
                return new Boule(widht, height);
        }
    }
    /**
     * Mise en pause du jeu
     */
    public void setPause() {
       if(!inPause){
        setInPause(true);
        Periodicite.getInstance().deleteObservers();
        PeriodiciteBird.getInstance().deleteObservers();
        PeriodiciteObstacle.getInstance().deleteObservers();   
       }
       else{
        setInPause(false);
        Periodicite.getInstance().addObserver(GM);
        PeriodiciteBird.getInstance().addObserver(GM);
        PeriodiciteObstacle.getInstance().addObserver(GM);        
       }
    }
}
