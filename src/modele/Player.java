/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import modele.Armes.Arme;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Armes.Awp;
import modele.Armes.Eagle;

/**
 *
 * @author celelion
 */
public class Player {

    private double initPosition;
    private boolean isMove = false;

    public double getPositionInit() {return initPosition;}
    
    private String nom;
        public static final String PROP_NOM = "nom";
        public String getNom() {return nom;}
        public void setNom(String nom) {
            String oldNom = this.nom;
            this.nom = nom;
            propertyChangeSupport.firePropertyChange(PROP_NOM, oldNom, nom);
        }

    private Double pointDeVie;
        public static final String PROP_POINTDEVIE = "pointDeVie";
        public Double getPointDeVie() {return pointDeVie;}
        public void setPointDeVie(Double pointDeVie) {
            Double oldPointDeVie = this.pointDeVie;
            this.pointDeVie = pointDeVie;
            propertyChangeSupport.firePropertyChange(PROP_POINTDEVIE, oldPointDeVie, pointDeVie);
        }

    private double widht;
        public static final String PROP_WIDHT = "widht";
        public double getWidht() {
            return widht;}
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
    
    private double positionX;
        public static final String PROP_POSITIONX = "positionX";
        public double getPositionX() {return positionX;}
        public void setPositionX(double positionX) {
            double oldPositionX = this.positionX;
            this.positionX = positionX;
            propertyChangeSupport.firePropertyChange(PROP_POSITIONX, oldPositionX, positionX);
        }

    private Double positionY;
        public static final String PROP_POSITIONY = "positionY";
        public Double getPositionY() {return positionY;}
        public void setPositionY(Double positionY) {
            Double oldPositionY = this.positionY;
            this.positionY = positionY;
            propertyChangeSupport.firePropertyChange(PROP_POSITIONY, oldPositionY, positionY);
        }

    private String cheminImage;
        public static final String PROP_CHEMINIMAGE = "cheminImage";
        public String getCheminImage() {return cheminImage;}
        public void setCheminImage(String cheminImage) {
            String oldCheminImage = this.cheminImage;
            this.cheminImage = cheminImage;
            propertyChangeSupport.firePropertyChange(PROP_CHEMINIMAGE, oldCheminImage, cheminImage);
        }

    private boolean etat;
        public static final String PROP_ETAT = "etat";
        public boolean isEtat() {return etat;}
        public void setEtat(boolean etat) {
            boolean oldEtat = this.etat;
            this.etat = etat;
            propertyChangeSupport.firePropertyChange(PROP_ETAT, oldEtat, etat);
        }

    private boolean isUp;
        public static final String PROP_ISUP = "isUp";
        public boolean isUp() {return isUp;}
        public void setUp(boolean isUp) {
            boolean oldIsUp = this.isUp;
            this.isUp = isUp;
            propertyChangeSupport.firePropertyChange(PROP_ISUP, oldIsUp, isUp);
        }
        
    private List<Arme> listArme;
        public static final String PROP_LISTARME = "listArme";
        public List<Arme> getListArme() {return listArme;}
        public void setListArme(List<Arme> listArme) {
            List<Arme> oldListArme = this.listArme;
            this.listArme = listArme;
            propertyChangeSupport.firePropertyChange(PROP_LISTARME, oldListArme, listArme);
        }

    private boolean inCollision;
        public static final String PROP_INCOLLISION = "inCollision";
        public boolean getInCollision() {return inCollision;}
        public void setInCollision(boolean inCollision) {
            boolean oldInCollision = this.inCollision;
            this.inCollision = inCollision;
            propertyChangeSupport.firePropertyChange(PROP_INCOLLISION, oldInCollision, inCollision);
        }

    private List<Sound> listSound;
        public static final String PROP_LISTSOUND = "listSound";
        public List<Sound> getListSound() {return listSound;}
        public void setListSound(List<Sound> listSound) {
            List<Sound> oldListSound = this.listSound;
            this.listSound = listSound;
            propertyChangeSupport.firePropertyChange(PROP_LISTSOUND, oldListSound, listSound);
        }
        
    private Integer currentWeapon;
        public static final String PROP_CURRENTWEAPON = "currentWeapon";
        public Integer getCurrentWeapon() {return currentWeapon;}
        public void setCurrentWeapon(Integer currentWeapon) {
            Integer oldCurrentWeapon = this.currentWeapon;
            this.currentWeapon = currentWeapon;
            propertyChangeSupport.firePropertyChange(PROP_CURRENTWEAPON, oldCurrentWeapon, currentWeapon);
        }


    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
        public void addPropertyChangeListener(PropertyChangeListener listener) {propertyChangeSupport.addPropertyChangeListener(listener);}
        public void removePropertyChangeListener(PropertyChangeListener listener) {propertyChangeSupport.removePropertyChangeListener(listener);}

    public Player(String nom, Double posX, Double posY, Double pointDeVie) {
        setNom(nom);
        setPointDeVie(pointDeVie);
        setPositionX(posX);
        setPositionY(posY);
        setCheminImage("/images/mario/FaceRight.png");
        setEtat(false);
        setInCollision(false);
        setListsPlayer();
        setCurrentWeapon(0);
    }
    /**
     * Initialisation d'un joueur
     */
    private void setListsPlayer() {
        setListSound(new ArrayList());
       //listSound.add(new Sound(new File("C:\\Users\\Cedric\\Documents\\NetBeansProjects\\ProjetJavaFX\\ressources\\sons\\ouch.wav")));
       //listSound.add(new Sound(new File("C:\\Users\\Cedric\\Documents\\NetBeansProjects\\ProjetJavaFX\\ressources\\sons\\saut.wav")));
        
        
       //listSound.add(new Sound(new File("C:\\Users\\Cedric\\Documents\\NetBeansProjects\\ProjetJavaFX\\ressources\\sons\\ouch.wav")));
       // listSound.add(new Sound(new File("\\home\\etud\\info\\celelion\\NetBeansProjects\\ProjetJavaFX\\sons\\saut.wav")));
        
        setListArme(new ArrayList());
        listArme.add(new Eagle("eagle"));
        listArme.add(new Awp("awp"));
    }
    /**
     * 
     */
    public void saut() {
        if (isEtat()) {
            Thread t = new Thread(new ThreadModificationPlayerPos(this));
            t.start();
            if (isUp()) {
                setCheminImage("/images/mario/upRight.png");
                up();
            }
        } else if (getPositionY() <= this.initPosition) {
            down();
        }
    }
    
    /**
     * Déplacement vers le haut
     */
    private void up() {
        setPositionY(getPositionY() - 3);
    }
    
    /**
     * déplacement vers le bas
     */
    private void down() {
        if (getPositionY() + 4 > this.initPosition) {
            setPositionY(this.initPosition);
        } else {
            setPositionY(getPositionY() + 3.5);
        }
    }
    /**
     * Ajout d'une nouvelle au joueur
     * @param newArme 
     */
    public void addNewArme(Arme newArme) {
        Arme a = newArme;
        listArme.add(a);
        propertyChangeSupport.fireIndexedPropertyChange(PROP_LISTARME, getListArme().size() - 1, null, a);
    }
    /**
     * Initialise la position de référence du joueur
     * @param widht 
     */
    public void setPositionInit(double widht) {
        initPosition = widht;
    }
    /**
     * Initialise la position de référence du joueur
     * @param height 
     */
    public void initPosition(double height) {
        initPosition = height;
    }
    
    /**
     * detection de collision avec les obstacles
     * @return 
     */
    public boolean collision() {
        if (!inCollision) {
            setInCollision(true);
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    deletPointOfLife();
                }
            }, 1);
            return true;
        }
        return false;
    }
    
    /**
     * retrait de point de vie du joueur et période "d'invinvibilité"
     */
    private void deletPointOfLife() {
        setPointDeVie(getPointDeVie() - 10);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        setInCollision(false);
    }
    
    /**
     * déplacement Gauche
     */
    public void moveLeft() {
        if (!isMove) {
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    changeSpriteEndLeft();
                    changeSpriteFirstLeft();
                }
            }, 5);
        }
        setPositionX(getPositionX() - 2);
    }
    /**
     * modification du sprite de déplacement gauche
     */
    private void changeSpriteEndLeft() {
        setCheminImage("/images/mario/leftEnd.png");
        isMove = true;
        try {
            Thread.sleep(150);
        } catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * modification du sprite de déplacement gauche
     */
    private void changeSpriteFirstLeft() {
        setCheminImage("/images/mario/leftFirst.png");
        try {
            Thread.sleep(150);
        } catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        isMove = false;
    }
    /**
     * déplacement droit
     */
    public void moveRight() {
        if (!isMove) {
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    changeSpriteRightEnd();
                    changeSpriteRightFirst();
                }
            }, 5);
        }
        setPositionX(getPositionX() + 2);
    }
    /**
     * modification du sprite de déplacement droit
     */
    private void changeSpriteRightEnd() {
        setCheminImage("/images/mario/rightEnd.png");
        isMove = true;
        try {
            Thread.sleep(150);
        } catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * modification du sprite de déplacement droit
     */
    private void changeSpriteRightFirst() {
        setCheminImage("/images/mario/rightFirst.png");
        try {
            Thread.sleep(150);
        } catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        isMove = false;
    }
    /**
     * Vue par défault du joueur (face)
     */
    public void setDefaultView() {
        setCheminImage("/images/mario/FaceRight.png");
    }
    
    /**
     * 
     */
    public void tirer(){
        Arme myArme = listArme.get(currentWeapon);
        myArme.createNewBullet(this.positionX, this.positionY);
        
        
        //myArme.fire();
    }
    
    public void moveBullets(double height) {
        listArme.get(currentWeapon).fire(height);
    }

}
