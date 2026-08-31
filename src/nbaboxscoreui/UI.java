/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbaboxscoreui;

import backend.Main;
import backend.Match;
import backend.Player;
import backend.Team;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Frankenstein
 */
public class UI extends Application {
    static final int WIDTH = 500;
    static final int HEIGHT = 300;

    static BorderPane root = new BorderPane();
    static BorderPane topPane = new BorderPane();
    static StackPane contentPane = new StackPane();

    static Menu MainMenu = new MainMenu();
    static Menu ScheduleMenu = new ScheduleMenu(MainMenu);
    static Menu TeamsMenu = new TeamsMenu(MainMenu);
    static MenuWithData<Team> TeamStatMenu = new GlobalTeamStats(TeamsMenu);
    static MenuWithData<Player> PlayerStatMenu = new GlobalPlayerStats(TeamStatMenu);
    static MenuWithData<Match> MatchStatMenu = new GlobalMatchStats(MainMenu);

    @Override
    public void start(Stage primaryStage) {
        topPane.setVisible(false);
        topPane.setPrefHeight(70);
        topPane.setStyle("-fx-background-color: lightgrey; -fx-padding: 5;");

        contentPane.setPadding(new Insets(-topPane.getPrefHeight(), 0, 0, 0));
        contentPane.setMaxSize(WIDTH, HEIGHT- topPane.getPrefHeight());

        root.setTop(topPane);
        root.setCenter(contentPane);
        topPane.toFront();
        contentPane.toBack();

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Box Score");
        primaryStage.setScene(scene);
        primaryStage.show();

        Menu.switchMenu(MainMenu);
        Main.initAll();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
