package nbaboxscoreui;

import backend.*;
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

public class GlobalTeamStats extends MenuWithData<Team> {
    GlobalTeamStats(Menu prevMenu) {
        super(prevMenu);
    }

    @Override
    protected void show() {
        Team team = this.data;
        if (team == null) {
            System.out.println("Team is null :(");
            Menu.switchMenu(UI.MainMenu);
            return;
        }
        VBox vbox = new VBox();
        System.out.println("Team stats...");

        Label teamNameLabel = new Label(team.getName());
        vbox.getChildren().add(teamNameLabel);

        ArrayList<Integer> record = TeamStats.getRecord(team);
        String recordStr = "RECORD: ";
        recordStr += record.get(0);
        recordStr += "-";
        recordStr += record.get(1);
        Label recordLabel = new Label(recordStr);
        vbox.getChildren().add(recordLabel);
//        this.addElement(vbox);

        DecimalFormat formatter = new DecimalFormat("###.#");
        formatter.setRoundingMode(RoundingMode.DOWN);
        double pts = TeamStats.pointsPerGame(team);
        double reb = TeamStats.reboundsPerGame(team);
        double apg = TeamStats.assistsPerGame(team);
        String avgStr = "AVERAGE: ";
        avgStr += formatter.format(pts) + " PTS";
        avgStr += ", " + formatter.format(reb) + " REB";
        avgStr += ", " + formatter.format(apg) + " AST";
        Label avgLabel = new Label(avgStr);
        vbox.getChildren().add(avgLabel);

        List<Player> players = team.getPlayers();
        List<String> playerList = new ArrayList<String>();
        for (Player player : players) {
            playerList.add("Player #" + player.getJerseyNumber());
        }
        ObservableList<String> playersObservable = FXCollections.observableList(playerList);
        ListView<String> playersListView = new ListView<String>(playersObservable);
        playersListView.setMaxHeight(100);
        playersListView.setOnMouseClicked(event -> {
            String playerStr = playersListView.getSelectionModel().getSelectedItem();
            String numberStr = playerStr.replace("Player #", "");
            int jerseyNumber = Integer.parseInt(numberStr);
            Player player =
                    players.stream()
                            .filter(p -> p.getJerseyNumber() == jerseyNumber)
                            .toArray(Player[]::new)[0];
            UI.PlayerStatMenu.switchHere(player);
        });
        vbox.getChildren().add(playersListView);

        Label matchesLabel = new Label("Matches: ");
        matchesLabel.setPadding(new Insets(10, 0, 0, 0));
        vbox.getChildren().add(matchesLabel);

        List<TeamStats> teamStats = TeamStats.getTeamMatchStats(team);
        List<String> teamStatsList = new ArrayList<String>();
        for (TeamStats stat : teamStats) {
            String result = stat.getResult()? "WIN" : "LOSE";
            result += "\t| ";
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
            TeamStats teamStat = teamStats.get(matchIndex);
            UI.MatchStatMenu.switchHere(teamStat.getMatch());
        });
        listViewMatches.setMaxHeight(100);
        //this.addElement(listViewMatches);
        vbox.getChildren().add(listViewMatches);

        this.addElement(vbox);
    }
}
