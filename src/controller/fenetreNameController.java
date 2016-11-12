/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewModel.ViewModelGame;

/**
 *
 * @author celelion
 */
public class fenetreNameController {
    
    private ViewModelGame gameVM;
    private fenetrePrincipalController fpc;
    
    @FXML
    private TextField txtFName;
    
    @FXML
    private Button btnValider;
     
    public fenetreNameController(ViewModelGame gameVM, fenetrePrincipalController FPC){
        this.gameVM = gameVM;
        this.fpc = FPC;
    }
    
    public void initialize() {
          txtFName.textProperty().bindBidirectional(gameVM.getPlayer().nomProperty());
    }
    
    @FXML
    private void clickValider(){
        fpc.launchGame();
        Stage ceStage=(Stage) btnValider.getScene().getWindow();
        ceStage.close();
    }
    
}
