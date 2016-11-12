/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

/**
 *
 * @author Cedric
 */
public class Sound {

    private File fileSound;
        public static final String PROP_FILESOUND = "fileSound";
        public File getFileSound() {return fileSound;}
        public void setFileSound(File fileSound) {
            File oldFileSound = this.fileSound;
            this.fileSound = fileSound;
            propertyChangeSupport.firePropertyChange(PROP_FILESOUND, oldFileSound, fileSound);
        }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
        public void addPropertyChangeListener(PropertyChangeListener listener) {propertyChangeSupport.addPropertyChangeListener(listener);}
        public void removePropertyChangeListener(PropertyChangeListener listener) {propertyChangeSupport.removePropertyChangeListener(listener);}

    public Sound(File fileSound) {
        setFileSound(fileSound);
    }
}
