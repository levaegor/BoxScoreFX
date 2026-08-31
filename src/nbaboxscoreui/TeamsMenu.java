package nbaboxscoreui;

import backend.Manager;
import backend.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class TeamsMenu extends Menu{
    public TeamsMenu(Menu mainMenu) {
        super(mainMenu);
    }

    @Override
    public void show() {
        List<Team> teams = Manager.getTeams();
        List<String> teamsList = new ArrayList<String>();
        for (Team team : teams) {
            teamsList.add(team.getName());
        }
        ObservableList<String> names = FXCollections.observableList(teamsList);
        ListView<String> listView = new ListView<String>(names);



        listView.setOnMouseClicked(event -> {
            String currentTeamName = listView.getSelectionModel().getSelectedItem();
            Team t =
                    teams.stream()
                            .filter(team -> team.getName().equals(currentTeamName))
                            .toArray(Team[]::new)[0];
            System.out.println(t.getName());
            UI.TeamStatMenu.switchHere(t);
        });

        this.addElement(listView);
    }
}
