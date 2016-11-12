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
import javafx.scene.image.Image;
import modele.Armes.Balle;

/**
 *
 * @author Cedric
 */
public class ViewModelBalle implements PropertyChangeListener{
    
    private Balle model;
    public Balle getBalle(){return model;}
    private final DoubleProperty positionX = new SimpleDoubleProperty();
        public double getPositionX() {return positionX.get();}
        public void setPositionX(double value) {positionX.set(value);}
        public DoubleProperty positionXProperty() {return positionX;}
    
    private final DoubleProperty positionY = new SimpleDoubleProperty();
        public double getPositionY() {return positionY.get();}
        public void setPositionY(double value) {positionY.set(value);}
        public DoubleProperty positionYProperty() {return positionY;}
    
    private final DoubleProperty widht = new SimpleDoubleProperty();
        public double getWidht() {return widht.get();}
        public void setWidht(double value) {widht.set(value);}
        public DoubleProperty widhtProperty() {return widht;}

    private final DoubleProperty height = new SimpleDoubleProperty();
        public double getHeight() {return height.get();}
        public void setHeight(double value) {height.set(value);}
        public DoubleProperty heightProperty() {return height;}
        
    private final ObjectProperty<Image> image = new SimpleObjectProperty<>();
        public Image getImage() {return image.get();}
        public void setImage(Image value) {image.set(value);}
        public ObjectProperty imageProperty() {return image;}
    
    public ViewModelBalle(Balle balle){
        this.model = balle;
        setImage(new Image(model.getCheminImage()));
        setHeight(getImage().getHeight());
        setWidht(getImage().getWidth());
        setPositionX(model.getPositionX());
        setPositionY(model.getPositionY());
        
        model.addPropertyChangeListener(this);
        
        heightProperty().addListener((o, oldV,newV) -> model.setHeight((double) newV));
        widhtProperty().addListener((o, oldV,newV) -> model.setHeight((double) newV));
        positionXProperty().addListener((o, oldV,newV) -> model.setPositionX((double) newV));
        positionYProperty().addListener((o, oldV,newV) -> model.setPositionY((double) newV));
        
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
         if(pce.getPropertyName().equals(model.PROP_HEIGHT)){
             setHeight((double) pce.getNewValue());
         }
         if(pce.getPropertyName().equals(model.PROP_WIDHT)){
             setWidht((double) pce.getNewValue());
         }
         if(pce.getPropertyName().equals(model.PROP_POSITIONX)){
             setPositionX((double) pce.getNewValue());
         }
         if(pce.getPropertyName().equals(model.PROP_POSITIONY)){
             setPositionY((double) pce.getNewValue());
         }
    }
}
