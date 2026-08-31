package nbaboxscoreui;

import backend.Match;
import backend.Player;
import backend.PlayerStats;
import backend.TeamStats;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GlobalMatchStats extends MenuWithData<Match> {
    GlobalMatchStats(Menu prevMenu) {
        super(prevMenu);
    }

    @Override
    protected void show() {
        if (this.data == null) {
            System.out.println("Match is null :(");
            Menu.switchMenu(UI.MainMenu);
            return;
        }

        VBox vBox = new VBox();

        ObservableList<TeamStats> stats = FXCollections.observableArrayList(
                this.data.getStats1(), this.data.getStats2()
        );
        System.out.println("Match stats...");
        TableView<TeamStats> tableView = new TableView<TeamStats>(stats);

        TableColumn<TeamStats, String> teamColumn = new TableColumn<TeamStats, String>("TeamName");
        teamColumn.setCellValueFactory(data ->
             new SimpleStringProperty(data.getValue().getTeam().getName())
        );

        DecimalFormat formatter = new DecimalFormat("###.#");
        formatter.setRoundingMode(RoundingMode.DOWN);

        TableColumn<TeamStats, String> ptsColumn = new TableColumn<>("PTS");
        ptsColumn.setCellValueFactory(data ->
                new SimpleStringProperty(formatter.format(data.getValue().getPoints()))
        );
        TableColumn<TeamStats, String> rebColumn = new TableColumn<>("REB");
        rebColumn.setCellValueFactory(data ->
                new SimpleStringProperty(formatter.format(data.getValue().getRebounds()))
        );
        TableColumn<TeamStats, String> astColumn = new TableColumn<>("REB");
        astColumn.setCellValueFactory(data ->
                new SimpleStringProperty(formatter.format(data.getValue().getAssists()))
        );

        tableView.getColumns().addAll(teamColumn, ptsColumn, rebColumn, astColumn);
        tableView.setMaxHeight(80);

        vBox.getChildren().add(tableView);

        HBox hBox = new HBox();

        VBox player1VBox = new VBox();
        Label player1Label = new Label(this.data.getTeam1().getName() + " players");
        player1Label.setPadding(new Insets(10, 0, 0, 0));
        player1VBox.getChildren().add(player1Label);
        List<PlayerStats> players1 = this.data.getPlayerStats1();
        List<String> player1List = new ArrayList<String>();
        for (PlayerStats player : players1) {
            player1List.add("Player #" + player.getPlayer().getJerseyNumber());
        }
        ObservableList<String> players1Observable = FXCollections.observableList(player1List);
        ListView<String> players1ListView = new ListView<String>(players1Observable);
        players1ListView.setMaxHeight(130);
        players1ListView.setOnMouseClicked(event -> {
            String playerStr = players1ListView.getSelectionModel().getSelectedItem();
            String numberStr = playerStr.replace("Player #", "");
            int jerseyNumber = Integer.parseInt(numberStr);
            PlayerStats player =
                    players1.stream()
                            .filter(p -> p.getPlayer().getJerseyNumber() == jerseyNumber)
                            .toArray(PlayerStats[]::new)[0];
            UI.PlayerStatMenu.switchHere(player.getPlayer());
        });
        player1VBox.getChildren().add(players1ListView);
        hBox.getChildren().add(player1VBox);


        VBox player2VBox = new VBox();
        Label player2Label = new Label(this.data.getTeam2().getName() + " players");
        player2Label.setPadding(new Insets(10, 0, 0, 0));
        player2VBox.getChildren().add(player2Label);

        List<PlayerStats> players2 = this.data.getPlayerStats2();
        System.out.println("Players2: " + players2.size());
        List<String> player2List = new ArrayList<String>();
        for (PlayerStats player : players2) {
            System.out.println("Player #" + player.getPlayer().getJerseyNumber());
            player2List.add("Player #" + player.getPlayer().getJerseyNumber());
        }
        ObservableList<String> players2Observable = FXCollections.observableList(player2List);
        ListView<String> players2ListView = new ListView<String>(players2Observable);
        players2ListView.setMaxHeight(130);
        players2ListView.setOnMouseClicked(event -> {
            String playerStr = players2ListView.getSelectionModel().getSelectedItem();
            String numberStr = playerStr.replace("Player #", "");
            int jerseyNumber = Integer.parseInt(numberStr);
            PlayerStats player =
                    players2.stream()
                            .filter(p -> p.getPlayer().getJerseyNumber() == jerseyNumber)
                            .toArray(PlayerStats[]::new)[0];
            UI.PlayerStatMenu.switchHere(player.getPlayer());
        });
        player2VBox.getChildren().add(players2ListView);
        hBox.getChildren().add(player2VBox);

        vBox.getChildren().add(hBox);

        this.addElement(vBox);
    }
}
