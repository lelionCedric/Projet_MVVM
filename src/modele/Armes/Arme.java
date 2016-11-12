/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.Armes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author celelion
 */
public abstract class Arme {

    private String cheminImage;
        public static final String PROP_CHEMINIMAGE = "cheminImage";
        public String getCheminImage() {return cheminImage;}
        public void setCheminImage(String cheminImage) {
            String oldCheminImage = this.cheminImage;
            this.cheminImage = cheminImage;
            propertyChangeSupport.firePropertyChange(PROP_CHEMINIMAGE, oldCheminImage, cheminImage);
        }

    private String nom;
        public static final String PROP_NOM = "nom";
        public String getNom() {return nom;}
        public void setNom(String nom) {
            String oldNom = this.nom;
            this.nom = nom;
            propertyChangeSupport.firePropertyChange(PROP_NOM, oldNom, nom);
        }
        
    private double puissance;
        public static final String PROP_PUISSANCE = "puissance";
        public double getPuissance() {return puissance;}
        public void setPuissance(double puissance) {
            double oldPuissance = this.puissance;
            this.puissance = puissance;
            propertyChangeSupport.firePropertyChange(PROP_PUISSANCE, oldPuissance, puissance);
        }
    
    private double probabiliteSpawn;
        public static final String PROP_PROBABILITESPAWN = "probabiliteSpawn";
        public double getProbabiliteSpawn() {return probabiliteSpawn;}
        public void setProbabiliteSpawn(double probabiliteSpawn) {
            double oldProbabiliteSpawn = this.probabiliteSpawn;
            this.probabiliteSpawn = probabiliteSpawn;
            propertyChangeSupport.firePropertyChange(PROP_PROBABILITESPAWN, oldProbabiliteSpawn, probabiliteSpawn);
        }
        
    private List<Balle> listBalle;
        public static final String PROP_LISTBALLE = "listBalle";
        public static final String PROP_LISTBALLE_REMOVE = "listBalle_Remove";
        public List<Balle> getListBalle() {return listBalle;}
        public void setListBalle(List<Balle> listBalle) {
            List<Balle> oldListBalle = this.listBalle;
            this.listBalle = listBalle;
            propertyChangeSupport.firePropertyChange(PROP_LISTBALLE, oldListBalle, listBalle);
        }
        
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
        public void addPropertyChangeListener(PropertyChangeListener listener) {propertyChangeSupport.addPropertyChangeListener(listener);}
        public void removePropertyChangeListener(PropertyChangeListener listener) {propertyChangeSupport.removePropertyChangeListener(listener);}

    public Arme(String nom) {
        setNom(nom);
        listBalle = new ArrayList();
    }
    
    /**
     * Génération d'une nouvelle lors d'un tire
     * @param positionX
     * @param positionY 
     */
    public void createNewBullet(double positionX, double positionY) {
        Balle balle = new Balle(positionX, positionY);
        listBalle.add(balle);
        propertyChangeSupport.fireIndexedPropertyChange(PROP_LISTBALLE, listBalle.size()-1, null, balle);
    }
    
    public void fire(double height) {
        List<Balle> tempBalle = new ArrayList<>();
        if(listBalle.size() != 0){
            for(Balle b : listBalle)
                if(b.getHeight()+b.getPositionX() >height)
                    tempBalle.add(b);
                else
                    b.moveFire();
        }
        for(Balle b :tempBalle){
             removeBullet(b);
        }
    }
    
    public void removeBullet(Balle bullet){
        if(listBalle.size() != 0){
            int b = listBalle.indexOf(bullet);
            
            listBalle.remove(listBalle.indexOf(bullet));
            propertyChangeSupport.fireIndexedPropertyChange(PROP_LISTBALLE_REMOVE, b, bullet, null);
           
        }
    }
}
