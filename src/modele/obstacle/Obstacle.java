/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.obstacle;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author celelion
 */
public abstract class Obstacle {

    private String cherminImage;
        public static final String PROP_CHERMINIMAGE = "cherminImage";
        public String getCherminImage() {return cherminImage;}
        public void setCherminImage(String cherminImage) {
            String oldCherminImage = this.cherminImage;
            this.cherminImage = cherminImage;
            propertyChangeSupport.firePropertyChange(PROP_CHERMINIMAGE, oldCherminImage, cherminImage);
        }
    private double positionX;
        public static final String PROP_POSITIONX = "positionX";
        public double getPositionX() {return positionX;}
        public void setPositionX(double positionX) {
            double oldPositionX = this.positionX;
            this.positionX = positionX;
            propertyChangeSupport.firePropertyChange(PROP_POSITIONX, oldPositionX, positionX);
        }

    private double positionY;
        public static final String PROP_POSITIONY = "positionY";
        public double getPositionY() {return positionY;}
        public void setPositionY(double positionY) {
            double oldPositionY = this.positionY;
            this.positionY = positionY;
            propertyChangeSupport.firePropertyChange(PROP_POSITIONY, oldPositionY, positionY);
        }
    
    private double width;
        public static final String PROP_WIDTH = "width";
        public double getWidth() {return width;}
        public void setWidth(double width) {
            double oldWidth = this.width;
            this.width = width;
            propertyChangeSupport.firePropertyChange(PROP_WIDTH, oldWidth, width);
        }
    private double height;
        public static final String PROP_HEIGHT = "height";
        public double getHeight() {return height;}
        public void setHeight(double height) {
            double oldHeight = this.height;
            this.height = height;
            propertyChangeSupport.firePropertyChange(PROP_HEIGHT, oldHeight, height);
        }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
        public void addPropertyChangeListener(PropertyChangeListener listener) {propertyChangeSupport.addPropertyChangeListener(listener);}
        public void removePropertyChangeListener(PropertyChangeListener listener) {propertyChangeSupport.removePropertyChangeListener(listener);}

    public Obstacle(double posX, double posY) {
        setPositionX(posX);
        setPositionY(posY);
    }
    public void move() {
        setPositionX(getPositionX() - 1);
    }
}
