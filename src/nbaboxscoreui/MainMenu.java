package nbaboxscoreui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainMenu extends Menu {
    @Override
    protected void show() {
        VBox vBox = new VBox(50);
        vBox.setAlignment(Pos.CENTER);

        Button scheduleBtn = new Button("Schedule");
        scheduleBtn.setOnAction(event -> Menu.switchMenu(UI.ScheduleMenu));
        vBox.getChildren().add(scheduleBtn);

        Button teamsBtn = new Button("Teams");
        teamsBtn.setOnAction(event -> Menu.switchMenu(UI.TeamsMenu));
        vBox.getChildren().add(teamsBtn);

        this.addElement(vBox);
    }
}
