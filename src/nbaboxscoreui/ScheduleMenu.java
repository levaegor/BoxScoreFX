package nbaboxscoreui;

import backend.Manager;
import backend.Match;
import backend.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class ScheduleMenu extends Menu {
    public ScheduleMenu(Menu mainMenu) {
        super(mainMenu);
    }

    @Override
    public void show(){
        List<Match> matches = Manager.getMatches();
        List<String> matchesListStr = new ArrayList<String>();
        for (Match match : matches) {
            matchesListStr.add(match.getDay().getDay().toString() + "\t|\t" + match.getTeam1().getName() + "\t|\t" + match.getTeam2().getName());
        }
        ObservableList<String> matchesObservable = FXCollections.observableList(matchesListStr);
        ListView<String> matchesListView = new ListView<String>(matchesObservable);
//        matchesListView.setMaxHeight(100);
        matchesListView.setOnMouseClicked(event -> {
            int matchIndex = matchesListView.getSelectionModel().getSelectedIndex();
            Match match = matches.get(matchIndex);
            UI.MatchStatMenu.switchHere(match);
        });
        this.addElement(matchesListView);
    }
}
