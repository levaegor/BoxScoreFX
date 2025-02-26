/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbaboxscoreui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

/**
 *
 * @author Frankenstein
 */
public class NBABoxScoreUI extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        VBox vBox = new VBox(50);

        Button scheduleBtn = new Button("Schedule");
        vBox.setAlignment(Pos.CENTER);
        scheduleBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        vBox.getChildren().add(scheduleBtn);
        
        StackPane root = new StackPane();
        root.getChildren().add(vBox);
        
        Scene scene = new Scene(root, 500, 250);
        
        primaryStage.setTitle("Box Score");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */

    static void switchMenu() {


    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
