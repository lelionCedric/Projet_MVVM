/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import modele.obstacle.Obstacle;

/**
 *
 * @author Cedric
 */
public class ViewModelObstacle implements PropertyChangeListener{

    private final DoubleProperty positionX = new SimpleDoubleProperty();
        public double getPositionX() {return positionX.get();}
        public void setPositionX(double value) {positionX.set(value);}
        public DoubleProperty positionXProperty() {return positionX;}    
    
    private final DoubleProperty positionY = new SimpleDoubleProperty();
        public double getPositionY() {return positionY.get();}
        public void setPositionY(double value) {positionY.set(value);}
        public DoubleProperty positionYProperty() {return positionY;}
    
    private final StringProperty cheminImage = new SimpleStringProperty();
        public String getCheminImage() {return cheminImage.get();}
        public void setCheminImage(String value) {cheminImage.set(value);}
        public StringProperty cheminImageProperty() {return cheminImage;}
    
    private final ObjectProperty<Image> image = new SimpleObjectProperty<>();
        public Image getImage() {return image.get();}
        public void setImage(Image value) {image.set(value);}
        public ObjectProperty imageProperty() {return image;}
    
    private Obstacle myObstacle;
    
    public ViewModelObstacle(Obstacle obstacle){
        myObstacle = obstacle;
        setCheminImage(myObstacle.getCherminImage());
        
        Image image = new Image(myObstacle.getCherminImage());
        myObstacle.setHeight(image.getHeight());
        myObstacle.setWidth(image.getWidth());
        setImage(image);
        setPositionX(myObstacle.getPositionX());
        setPositionY(myObstacle.getPositionY() - getImage().getHeight());
        myObstacle.setPositionY(getPositionY());
        
        myObstacle.addPropertyChangeListener(this);
                
        positionXProperty().addListener((o,oldV,newV) ->{myObstacle.setPositionX((double) newV);});
        positionYProperty().addListener((o,oldV,newV) ->{myObstacle.setPositionY((double) newV);});
        cheminImageProperty().addListener((o,oldV,newV)-> myObstacle.setCherminImage(newV));
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if(pce.getPropertyName().equals(myObstacle.PROP_POSITIONX)){
            setPositionX((double) pce.getNewValue());
        }
        if(pce.getPropertyName().equals(myObstacle.PROP_CHERMINIMAGE)){
            setCheminImage((String) pce.getNewValue());
        }
        if(pce.getPropertyName().equals(myObstacle.PROP_POSITIONY)){
            setPositionY((double) pce.getNewValue());
        }
    }
    
}
