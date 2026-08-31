package nbaboxscoreui;

import backend.Player;
import backend.PlayerStats;
import backend.TeamStats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GlobalPlayerStats extends MenuWithData<Player> {
    GlobalPlayerStats(Menu prevMenu) {
        super(prevMenu);
    }

    @Override
    protected void show() {
        Player player = this.data;
        if (player == null) {
            System.out.println("Player is null :(");
            Menu.switchMenu(UI.MainMenu);
            return;
        }
        VBox vbox = new VBox();
        System.out.println("Player stats...");

        Label teamNameLabel = new Label("Player #" + player.getJerseyNumber() + " - " + player.getTeam().getName());
        vbox.getChildren().add(teamNameLabel);

        DecimalFormat formatter = new DecimalFormat("###.#");
        formatter.setRoundingMode(RoundingMode.DOWN);
        double pts = PlayerStats.pointsPerGame(player);
        double reb = PlayerStats.reboundsPerGame(player);
        double apg = PlayerStats.assistsPerGame(player);
        String avgStr = "AVERAGE: ";
        avgStr += formatter.format(pts) + " PTS";
        avgStr += ", " + formatter.format(reb) + " REB";
        avgStr += ", " + formatter.format(apg) + " AST";
        Label avgLabel = new Label(avgStr);
        vbox.getChildren().add(avgLabel);


        Label matchesLabel = new Label("Matches: ");
        matchesLabel.setPadding(new Insets(10, 0, 0, 0));
        vbox.getChildren().add(matchesLabel);

        List<PlayerStats> teamStats = PlayerStats.getPlayerMatchStats(player);
        List<String> teamStatsList = new ArrayList<String>();
        for (PlayerStats stat : teamStats) {
            String result = "";
            result += stat.getMatch().getTeam1().getName();
            result += "\t| ";
            result += stat.getMatch().getStats1().getPoints();
            result += "\t| ";
            result += stat.getMatch().getTeam2().getName();
            result += "\t| ";
            result += stat.getMatch().getStats2().getPoints();

            teamStatsList.add(result);
        }
        ObservableList<String> matchesObservable = FXCollections.observableList(teamStatsList);
        ListView<String> listViewMatches = new ListView<String>(matchesObservable);

        listViewMatches.setOnMouseClicked(event -> {
            int matchIndex = listViewMatches.getSelectionModel().getSelectedIndex();
            PlayerStats playerStat = teamStats.get(matchIndex);
            UI.MatchStatMenu.switchHere(playerStat.getMatch());
        });
//        listViewMatches.setMaxHeight(100);
        //this.addElement(listViewMatches);
        vbox.getChildren().add(listViewMatches);

        this.addElement(vbox);
    }
}
