/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import modele.Armes.Arme;
import modele.Player;
import modele.Sound;

/**
 *
 * @author celelion
 */
public class ViewModelPlayer implements PropertyChangeListener{

    private Player model;
    private double initPosition;
    
    private final DoubleProperty pointDeVie = new SimpleDoubleProperty();
        public double getPointDeVie() {return pointDeVie.get();}
        public void setPointDeVie(double value) {pointDeVie.set(value);}
        public DoubleProperty pointDeVieProperty() {return pointDeVie;}
    
    private final StringProperty nom = new SimpleStringProperty();
        public String getNom() {return nom.get();}
        public void setNom(String value) {nom.set(value);}
        public StringProperty nomProperty() {return nom;}
    
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

    private ObservableList<ViewModelArme> listArmeObs = FXCollections.observableArrayList();
    private final ListProperty<ViewModelArme> listArme = new ReadOnlyListWrapper<ViewModelArme>(listArmeObs);
        public ObservableList getListArme() {return listArme.get();}
        public void setListArme(ObservableList value) {listArme.set(value);}
        public ListProperty listArmeProperty() {return listArme;}

    private final ObjectProperty<Image> image = new SimpleObjectProperty<>();
        public Image getImage() {return image.get();}
        public void setImage(Image value) {image.set(value);}
        public ObjectProperty imageProperty() {return image;}
    
    private final BooleanProperty etat = new SimpleBooleanProperty();
        public boolean isEtat() {return etat.get();}
        public void setEtat(boolean value) {etat.set(value);}
        public BooleanProperty etatProperty() {return etat;}
    
    private final BooleanProperty inCollision = new SimpleBooleanProperty();
        public boolean isInCollision() {return inCollision.get();}
        public void setInCollision(boolean value) {inCollision.set(value);}
        public BooleanProperty inCollisionProperty() {return inCollision;}
    
    
    private ObservableList<ViewModelSound> listViewModelSoundObs = FXCollections.observableArrayList();
    private final ListProperty<ViewModelSound> listViewModelSound = new SimpleListProperty<>(listViewModelSoundObs);
        public ObservableList getListViewModelSound() {return listViewModelSound.get();}
        public void setListViewModelSound(ObservableList value) {listViewModelSound.set(value);}
        public ListProperty listViewModelSoundProperty() {return listViewModelSound;}
   
        
    private final IntegerProperty indexWeapon = new SimpleIntegerProperty();
        public int getIndexWeapon() {return indexWeapon.get();}
        public void setIndexWeapon(int value) {indexWeapon.set(value);}
        public IntegerProperty currentIndexProperty() {return indexWeapon;}
     
    private final ObjectProperty<ViewModelArme> currentArme = new SimpleObjectProperty<>();
        public ViewModelArme getCurrentArme() {return currentArme.get();}
        public void setCurrentArme(ViewModelArme value) {currentArme.set(value);}
        public ObjectProperty currentArmeProperty() {return currentArme;}

    
        
    public ViewModelPlayer(Player player) {
        model = player;
        setProperty();
        setAndAddListenerPropertyImageJoueur();
        model.addPropertyChangeListener(this);
        addAllListener();
    }
    private void setAndAddListenerPropertyImageJoueur(){
        Image imageP = new Image(model.getCheminImage());
        model.setWidht(imageP.getWidth());
        model.setHeight(imageP.getHeight());
        imageP.widthProperty().addListener((o, oldV, newV) -> model.setWidht((double) newV));
        imageP.heightProperty().addListener((o, oldV, newV) -> model.setHeight((double) newV));
        setImage(imageP);
    }
    
    private void setProperty(){
        setPositionX(model.getPositionX());
        setPositionY(model.getPositionY());
        setCheminImage(model.getCheminImage());
        setNom(model.getNom());
        setPointDeVie(model.getPointDeVie());
        setInCollision(model.getInCollision());
        setIndexWeapon(model.getCurrentWeapon());
        setCurrentArme(new ViewModelArme(model.getListArme().get(getIndexWeapon())));
    }
    
    private void addAllListener() {
        model.getListArme().forEach(e -> listArmeObs.add(new ViewModelArme(e)));
        model.getListSound().forEach(e -> listViewModelSoundObs.add(new ViewModelSound(e)));
        positionXProperty().addListener((o, oldV, newV) -> model.setPositionX((Double) newV));
        positionYProperty().addListener((o, oldV, newV) -> model.setPositionY((Double) newV));
        nomProperty().addListener((o, oldV, newV) -> model.setNom(newV));
        cheminImageProperty().addListener((o, oldV, newV) -> model.setCheminImage(newV));
        pointDeVieProperty().addListener((o, oldV, newV) -> model.setPointDeVie((Double) newV));
        etatProperty().addListener((o, oldV, newV) -> model.setEtat(newV));
        inCollisionProperty().addListener((o, oldV, newV) -> model.setInCollision(newV));
        currentIndexProperty().addListener((o, oldV, newV) -> model.setCurrentWeapon((Integer) newV));
    }
    
    
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(model.PROP_NOM)) {
            setNom((String) evt.getNewValue());
        }
        if (evt.getPropertyName().equals(model.PROP_POINTDEVIE)) {
            Platform.runLater(() -> setPointDeVie((Double) evt.getNewValue()));
        }
        if (evt.getPropertyName().equals(model.PROP_POSITIONX)) {
            setPositionX((double) evt.getNewValue());
            setImage(new Image(model.getCheminImage()));
        }
        if (evt.getPropertyName().equals(model.PROP_POSITIONY)) {
            setPositionY((double) evt.getNewValue());
            setImage(new Image(model.getCheminImage()));
            if (initPosition == (double) evt.getNewValue()) {
                model.setCheminImage("/images/mario/FaceRight.png");
                setImage(new Image(model.getCheminImage()));
            }
        }
        //Le temps du thread ne permet pas de passer par la notification de changement de property
        if (evt.getNewValue().equals(model.PROP_CHEMINIMAGE)) {
            setImage(new Image((String) evt.getNewValue()));
        }
        if (evt.getPropertyName().equals(model.PROP_ETAT)) {
            setEtat((boolean) evt.getNewValue());
        }
        if (evt.getPropertyName().equals(model.PROP_LISTARME)) {
            listArme.add(((IndexedPropertyChangeEvent) evt).getIndex(), new ViewModelArme((Arme) evt.getNewValue()));
        }
        if (evt.getPropertyName().equals(model.PROP_INCOLLISION)) {
            setInCollision((boolean) evt.getNewValue());
        }
        if (evt.getPropertyName().equals(model.PROP_LISTSOUND)) {
            listViewModelSound.add(((IndexedPropertyChangeEvent) evt).getIndex(), new ViewModelSound((Sound) evt.getNewValue()));
        }
        if (evt.getPropertyName().equals(model.PROP_CURRENTWEAPON)) {
            setIndexWeapon((Integer) evt.getNewValue());
            setCurrentArme(new ViewModelArme(model.getListArme().get(getIndexWeapon())));
        }

    }

    public void changePosition(int positionPos) {
        model.setPositionX(model.getPositionX() + positionPos);
    }

    public void sauter() {
        model.saut();
    }

    public void initPos(double height, double widht) {
        this.initPosition = height;
        model.initPosition(height);
        model.setPositionX(widht / 3);
        model.setPositionY(height);
    }

    public void moveRight() {
        model.moveRight();
    }

    public void moveLeft() {
        model.moveLeft();
    }

    public void defaultView() {
        model.setDefaultView();
    }

    public File getFileByID(int index) {
        return listViewModelSound.get(index).getFileSound();
    }
    
    public void tirer(){
        model.tirer();
    }

}
