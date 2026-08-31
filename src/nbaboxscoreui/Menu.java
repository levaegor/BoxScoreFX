package nbaboxscoreui;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public abstract class Menu {
    private final Menu prevMenu;

    Menu() {
        this.prevMenu = null;
    }
    Menu(Menu prevMenu) {
        this.prevMenu = prevMenu;
    }

    protected void addElement(Node element) {
        Menu.getContentPane().getChildren().add(element);
    }

    protected abstract void show();
    protected static void clearAll() {
        Menu.getTopPane().getChildren().clear();
        Menu.getContentPane().getChildren().clear();
    }

    protected void showWithBack() {
        if (this.prevMenu != null) {
            Button backBtn = new Button("Back");
            backBtn.setOnAction(event -> Menu.switchMenu(this.prevMenu));

            System.out.println("Back button");
            Menu.getTopPane().setLeft(backBtn);
            Menu.getTopPane().setVisible(true);
            Menu.getContentPane().setTranslateY(Menu.getTopPane().getPrefHeight());
            backBtn.toFront();
            Menu.getTopPane().toFront();
            Menu.getContentPane().toBack();
        }
        else {
            System.out.println(false);
            Menu.getTopPane().setVisible(false);
            Menu.getContentPane().setTranslateY(0);
        }

        show();
    }

    protected static StackPane getContentPane() {
        return UI.contentPane;
    }
    protected static BorderPane getTopPane() {
        return UI.topPane;
    }

    public static void switchMenu(Menu menu) {
        Menu.clearAll();
        menu.showWithBack();
    }
}