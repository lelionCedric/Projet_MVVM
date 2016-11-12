package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.DoubleStringConverter;
import viewModel.ViewModelArme;
import viewModel.ViewModelBalle;
import viewModel.ViewModelGame;
import viewModel.ViewModelObstacle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author celelion
 */
public class fenetrePrincipalController {

    @FXML
    private Button btnStart;

    @FXML
    private Label labelNom;

    @FXML
    private Label labelPointVie;

    @FXML
    private Label labelForce;

    @FXML
    private ListView<ViewModelArme> listviewArme;

    @FXML
    private Pane myPane;

    @FXML
    private ListView<ViewModelObstacle> listViewObstacle;

    @FXML
    private List<ViewModelObstacle> listViewObstacleTemp;
    @FXML
    private List<ViewModelBalle> listViewBulletTemp;

    @FXML
    private ImageView imgViewPause;
    
    @FXML
    private ImageView inPause;
    
    @FXML
    private ListView<ViewModelBalle> listViewBullet;
    
    private ViewModelGame gameVM;
    private Media media;
    private MediaPlayer mediaPlayer;
    private ImageView imgImageView;
    private FXMLLoader fxml = new FXMLLoader(getClass().getResource("/view/FenetreNom.fxml"));

    public void initialize() {
        gameVM = new ViewModelGame(myPane.getHeight(), myPane.getWidth());
       
        listViewObstacleTemp = new ArrayList();
        listViewBulletTemp = new ArrayList();
    }
    
    private ObjectProperty<Double> puissance;

    @FXML
    private void clickStart() throws IOException, InterruptedException {
        showWindowNikname();
        imgImageView = new ImageView();
        //playSoundJump();
        bindingElement();
        listenerItemProperty();
        listenerIndexProperty();
        
        imgViewPause.setOnMouseClicked(e -> {
            gameVM.setPause();
            inPause.toFront();
        });
    }
    private void showWindowNikname() throws IOException{
        myPane.getScene().getWindow().hide();
        fxml.setController(new fenetreNameController(gameVM, this));
        Parent root = fxml.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //stage.setOnCloseRequest(event -> event.consume());
    }
    private void listenerItemProperty(){
        listviewArme.getSelectionModel().selectedItemProperty().addListener((o, oldV, newV) ->{
                if(oldV != null){
                    labelForce.textProperty().unbindBidirectional(puissance);
                    listViewBullet.itemsProperty().unbind();
                }
                if(newV != null){
                    puissance = newV.puissanceProperty().asObject();
                    labelForce.textProperty().bindBidirectional(puissance,new DoubleStringConverter());                   
                }
        });
    }
    private void listenerIndexProperty(){
        
        listviewArme.getSelectionModel().selectedIndexProperty().addListener((o, oldV, newV) -> {
                if(newV != null){
                  gameVM.getPlayer().setIndexWeapon((Integer) newV);
                  bindListBullet();
                }
        });
    }

    public void launchGame() {
        ((Stage) myPane.getScene().getWindow()).show();
        myPane.requestFocus();
        gameVM.setHeight(myPane.getHeight());
        gameVM.setWidht(myPane.getWidth());
        btnStart.setVisible(false);
        gameVM.changeInitPosition(myPane.getHeight() - 60, myPane.getWidth());
        prepareActionHandlers();
        myPane.getChildren().add(imgImageView);
        loopTimerAnimation();
    }

    private void loopTimerAnimation() {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                checkcollision();
                gameVM.getPlayer().sauter();
                movePlayer();
                //Ce champs ce débind a cause des threads
                labelPointVie.textProperty().bindBidirectional(gameVM.getPlayer().pointDeVieProperty().asObject(), new DoubleStringConverter());
            }
        };
        gameLoop.start();
    }

    private void movePlayer() {
        if (getCurrentlyActiveKeys().contains("LEFT")) {
            gameVM.movePlayerLeft();
        } else if (getCurrentlyActiveKeys().contains("RIGHT")) {
            gameVM.movePlayerRight();
        } else {
            gameVM.getPlayer().defaultView();
        }
    }

    private void bindingElement() {

        labelNom.textProperty().bind(gameVM.getPlayer().nomProperty());
        labelPointVie.textProperty().bindBidirectional(gameVM.getPlayer().pointDeVieProperty().asObject(), new DoubleStringConverter());
        listviewArme.itemsProperty().bindBidirectional(gameVM.getPlayer().listArmeProperty());
        
        imgImageView.xProperty().bindBidirectional(gameVM.getPlayer().positionXProperty());
        imgImageView.yProperty().bindBidirectional(gameVM.getPlayer().positionYProperty());
        imgImageView.imageProperty().bindBidirectional(gameVM.getPlayer().imageProperty());
        
        inPause.visibleProperty().bind(gameVM.inPauseProperty());
        
        bindListObstacle();
        bindListViewArme();
        bindListBullet();
    }

    private void bindListObstacle() {
        listViewObstacle.itemsProperty().bindBidirectional(gameVM.listObstacleProperty());

        listViewObstacle.getItems().addListener((ListChangeListener.Change<? extends ViewModelObstacle> c) -> {
            ViewModelObstacle item = c.getList().get(c.getList().size() - 1);
            if (!listViewObstacleTemp.contains(item)) {
                addNewImageViewObstacle(item);
            }
        });
    }
    private void addNewImageViewObstacle(ViewModelObstacle item) {
        ImageView myImageView = new ImageView();
        myImageView.xProperty().bind(item.positionXProperty());
        myImageView.yProperty().bind(item.positionYProperty());
        myImageView.imageProperty().bind(item.imageProperty());
        listViewObstacleTemp.add(item);
        Platform.runLater(() -> addIntoPane(myImageView));
    }
    
    private void bindListBullet(){
       listViewBullet.itemsProperty().bind(gameVM.getPlayer().getCurrentArme().listBulletProperty());
       
       listViewBullet.getItems().addListener((ListChangeListener.Change<? extends ViewModelBalle> c) -> {
            ViewModelBalle item = c.getList().get(c.getList().size() - 1);
            if (!listViewBulletTemp.contains(item)) {
                addNewImageViewBullet(item);
            }
        });
    }

    private void addNewImageViewBullet(ViewModelBalle item) {
        ImageView myImageView = new ImageView();
        myImageView.xProperty().bind(item.positionXProperty());
        myImageView.yProperty().bind(item.positionYProperty());
        myImageView.imageProperty().bind(item.imageProperty());
        listViewBulletTemp.add(item);
        Platform.runLater(() -> addIntoPane(myImageView));
    }
    

    private void bindListViewArme() {
        listviewArme.itemsProperty().bindBidirectional(gameVM.getPlayer().listArmeProperty());

        listviewArme.setCellFactory(param -> {
            return new ListCell<ViewModelArme>() {

                private ImageView imgView = new ImageView();

                @Override
                protected void updateItem(ViewModelArme item, boolean empty) {
                    super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                    if (!empty) {
                        imgView.imageProperty().bind(item.imageProperty());
                        setText(item.getNom());
                        setGraphic(imgView);
                    }
                    if (empty && item != null) {
                        imgView.imageProperty().unbind();
                    }
                }
            };
        });
    }

    private void removeIntoPane(ImageView imgView) {
        myPane.getChildren().remove(imgView);
    }

    private void addIntoPane(ImageView imgView) {
        myPane.getChildren().add(imgView);
    }

    private HashSet currentlyActiveKeys = new HashSet<String>();

    public HashSet getCurrentlyActiveKeys() {
        return currentlyActiveKeys;
    }

    public void prepareActionHandlers() {
        myPane.setOnKeyPressed((KeyEvent event) -> {
            currentlyActiveKeys.add(event.getCode().toString());
            if (!gameVM.getPlayer().etatProperty().get() && event.getCode().toString().equals("SPACE")) {
                gameVM.getPlayer().setEtat(true);
                playSoundJump();
            }
            if(event.getCode().toString().equals("ESCAPE")){
                gameVM.setPause();
                inPause.toFront();
            }
            if(event.getCode().toString().equals("TAB")){                
                gameVM.getPlayer().tirer();
            }
        });
        myPane.setOnKeyReleased((KeyEvent event) -> {
            currentlyActiveKeys.remove(event.getCode().toString());
        });
    }
   
    
    private void checkcollision() {
        if (gameVM.checkCollision()) {
            URL url = getClass().getResource("/sons/ouch.wav");
            media = new Media(url.toString());;
            //media = new Media(gameVM.getPlayer().getFileByID(0).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setStopTime(Duration.seconds(10));
            
            mediaPlayer.play();
            //mediaPlayer.autoPlayProperty().bind(gameVM.getPlayer().inCollisionProperty());
            changeBackground();
            return;
        }
        myPane.setBackground(new Background(new BackgroundFill(new Color(1,1,1, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void playSoundJump() {
        URL url = getClass().getResource("/sons/saut.wav");
        media = new Media(url.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setStopTime(Duration.seconds(10));
        mediaPlayer.play();
       // mediaPlayer.autoPlayProperty().bind(gameVM.getPlayer().etatProperty());
    }

    private void changeBackground() {
        myPane.setBackground(new Background(new BackgroundFill(new Color(0.9, 0, 0, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));
    }

}
