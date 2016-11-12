/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.Game;
import modele.obstacle.Obstacle;

/**
 *
 * @author Cedric
 */
public class ViewModelGame implements PropertyChangeListener{

    private final ObjectProperty<ViewModelPlayer> player = new SimpleObjectProperty<>();
        public ViewModelPlayer getPlayer() {return player.get();}
        public void setPlayer(ViewModelPlayer value) {player.set(value);}
        public ObjectProperty playerProperty() {return player;}
    
    private ObservableList<ViewModelObstacle> listObstacleObs = FXCollections.observableArrayList();
    private final ListProperty<ViewModelObstacle> listObstacle = new SimpleListProperty<>(listObstacleObs);
        public ObservableList getListObstacle() {return listObstacle.get();}
        public void setListObstacle(ObservableList value) {listObstacle.set(value);}
        public ListProperty listObstacleProperty() {return listObstacle;}
    
    private final DoubleProperty widht = new SimpleDoubleProperty();
        public double getWidht() {return widht.get();}
        public void setWidht(double value) {widht.set(value);}
        public DoubleProperty widhtProperty() {return widht;}
   
    private final DoubleProperty height = new SimpleDoubleProperty();
        public double getHeight() {return height.get();}
        public void setHeight(double value) {height.set(value);}
        public DoubleProperty heightProperty() {return height;}
        
    private final BooleanProperty inPause = new SimpleBooleanProperty();
        public boolean isInPause() {return inPause.get();}
        public void setInPause(boolean value) {inPause.set(value);}
        public BooleanProperty inPauseProperty() {return inPause;}
           
    private Game game;
    
    public ViewModelGame(double heightScreen, double widhtScreen){
        game = new Game(heightScreen,widhtScreen);
        setPlayer(new ViewModelPlayer(game.getPlayer()));
        setInPause(game.isInPause());
        game.addPropertyChangeListener(this);
        
        game.getListObstacle().forEach(e -> listObstacle.add(new ViewModelObstacle(e)));
        widhtProperty().addListener((o, oldV,newV)-> game.setWidht((double) newV));
        heightProperty().addListener((o, oldV,newV)-> game.setHeight((double) newV));
        inPauseProperty().addListener((o, oldV, newV) -> game.setInPause(newV));
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if(pce.getPropertyName().equals(game.PROP_PLAYER)){
            setPlayer((ViewModelPlayer) (pce.getNewValue()));
        }        
        if(pce.getPropertyName().equals(game.PROP_LISTOBSTACLE)){
            listObstacle.add(((IndexedPropertyChangeEvent)pce).getIndex(),new ViewModelObstacle((Obstacle) pce.getNewValue()));
        }
        if(pce.getPropertyName().equals(game.PROP_LISTOBSTACLE_REMOVE)){
            listObstacle.remove(((IndexedPropertyChangeEvent)pce).getIndex());
        }
        if(pce.getPropertyName().equals(game.PROP_INPAUSE)){
            setInPause((boolean) pce.getNewValue());
        }
    }
    

    public void changeInitPosition(double height,double widht) {
        player.get().initPos(height,widht/3);
    }
    
    /*public void addNewObstacle(double widht,double height){
        game.genererRandomObstacle(widht, height);
    }*/
    

    public void playerJump() {
        player.get().sauter();
    }

    public boolean checkCollision() {
        return game.checkCollision();
    }

    public void movePlayerLeft() {
        if(player.get().getPositionX() > 0){
            player.get().moveLeft();
        }
    }

    public void movePlayerRight() {
        if(player.get().getPositionX() < game.getWidht() - getPlayer().getImage().getWidth()){
            player.get().moveRight();
        }
    }
    public void setPause(){
        game.setPause();
    }
}
