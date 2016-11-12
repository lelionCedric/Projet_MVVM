/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.Armes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Cedric
 */
public class Balle {
    
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
        
    private String cheminImage;
        public static final String PROP_CHEMINIMAGE = "cheminImage";
        public String getCheminImage() {return cheminImage;}
        public void setCheminImage(String cheminImage) {
            String oldCheminImage = this.cheminImage;
            this.cheminImage = cheminImage;
            propertyChangeSupport.firePropertyChange(PROP_CHEMINIMAGE, oldCheminImage, cheminImage);
        }
        
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
        public void addPropertyChangeListener(PropertyChangeListener listener) {propertyChangeSupport.addPropertyChangeListener(listener);}
        public void removePropertyChangeListener(PropertyChangeListener listener) {propertyChangeSupport.removePropertyChangeListener(listener);}

    public Balle(double positionX, double positionY) {
        setPositionX(positionX);
        setPositionY(positionY+10);
        setCheminImage("/images/weapons/bullet.png");
    }

    public void moveFire() {
        setPositionX(getPositionX()+5);
    }
}
