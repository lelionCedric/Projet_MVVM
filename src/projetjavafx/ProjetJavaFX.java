/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjavafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author celelion
 */
public class ProjetJavaFX extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setOnCloseRequest(e -> {
            Platform.exit(); 
            System.exit(0);
        });
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/fenetrePrincipal.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
}
