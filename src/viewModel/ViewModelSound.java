/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modele.Sound;

/**
 *
 * @author Cedric
 */
public class ViewModelSound implements PropertyChangeListener{

    private final ObjectProperty<File> fileSound = new SimpleObjectProperty<>();
        public File getFileSound() {return fileSound.get();}
        public void setFileSound(File value) {fileSound.set(value);}
        public ObjectProperty fileSoundProperty() {return fileSound;}

    private Sound model;
    
    public ViewModelSound(Sound sound){
        model= sound;
        setFileSound(sound.getFileSound());
        
        model.addPropertyChangeListener(this);
        
        fileSoundProperty().addListener((o, oldV,newV) -> model.setFileSound((File) newV));
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if(pce.getPropertyName().equals(model.PROP_FILESOUND)){
            setFileSound((File) pce.getNewValue());
        }
    }
}
