/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import modele.Armes.Arme;
import modele.Armes.Balle;

/**
 *
 * @author celelion
 */
public class ViewModelArme implements PropertyChangeListener{
    
    private final StringProperty nom = new SimpleStringProperty();
        public String getNom() {return nom.get();}
        public void setNom(String value) {nom.set(value);}
        public StringProperty nomProperty() {return nom;}
    
    private final StringProperty cheminImage = new SimpleStringProperty();
        public String getCheminImage() {return cheminImage.get();}
        public void setCheminImage(String value) {cheminImage.set(value);}
        public StringProperty cheminImageProperty() {return cheminImage;}
        
    private final DoubleProperty puissance = new SimpleDoubleProperty();
        public double getPuissance() {return puissance.get();}
        public void setPuissance(double value) {puissance.set(value);}
        public DoubleProperty puissanceProperty() {return puissance;}
     
    private final ObjectProperty<Image> image = new SimpleObjectProperty<Image>();
        public Image getImage() {return image.get();}
        public void setImage(Image value) {image.set(value);}
        public ObjectProperty imageProperty() {return image;}
   
    private ObservableList listBulletObs = FXCollections.observableArrayList();
    private final ListProperty<ViewModelBalle> listBullet = new SimpleListProperty<>(listBulletObs);
        public ObservableList getListBullet() {return listBullet.get();}
        public void setListBullet(ObservableList value) {listBullet.set(value);}
        public ListProperty listBulletProperty() {return listBullet;}
    
    
    
    private Arme myArme;
    
    public ViewModelArme (Arme arme){
        myArme = arme;
        setNom(myArme.getNom());
        setCheminImage(myArme.getCheminImage());
        setPuissance(myArme.getPuissance());
        setImage(new Image(cheminImageProperty().get()));
        
        myArme.addPropertyChangeListener(this);
        myArme.getListBalle().forEach(e -> listBullet.add(new ViewModelBalle(e)));
        nomProperty().addListener((o,oldV,newV) -> myArme.setNom(newV));
        cheminImageProperty().addListener((o,odlV,newV)-> myArme.setCheminImage(newV));
        puissanceProperty().addListener((o, oldV, newV) -> myArme.setPuissance((double) newV));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(myArme.PROP_NOM))
        {
            Platform.runLater(() -> setNom((String) evt.getNewValue()));
        }
        if(evt.getPropertyName().equals(myArme.PROP_CHEMINIMAGE))
        {
            Platform.runLater(() ->setCheminImage((String)evt.getNewValue()));
        }
        if(evt.getPropertyName().equals(myArme.PROP_PUISSANCE)){
            setPuissance((double) evt.getNewValue());
        }
        if(evt.getPropertyName().equals(myArme.PROP_LISTBALLE)){
            listBullet.add(((IndexedPropertyChangeEvent) evt).getIndex(), new ViewModelBalle((Balle) evt.getNewValue()));
        }
        if(evt.getPropertyName().equals(myArme.PROP_LISTBALLE_REMOVE)){
            //System.out.println(((IndexedPropertyChangeEvent) evt).getIndex());
            //if(listBullet.size() != 0)
                listBullet.remove(0);
        }
        
    }
       
}
