package backend;

import java.util.ArrayList;

public class PlayerStats {
    private Player player;
    private Match match;
    private int points;
    private int rebounds;
    private int assists;

    public PlayerStats(Player player, Match match) {
        this.player = player;
        this.match = match;
    }

    public Player getPlayer() {
        return player;
    }

    public Match getMatch() {
        return match;
    }

    public int getPoints() {
        return points;
    }

    public int getRebounds() {
        return rebounds;
    }

    public int getAssists() {
        return assists;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public static ArrayList<PlayerStats> getPlayerMatchStats(Player player) {
        ArrayList<PlayerStats> playerStatsList = new ArrayList<PlayerStats>();
        for (Day day: Manager.schedule) {
            for (Match match: day.getMatches()) {
                ArrayList<PlayerStats> totalStats = match.getPlayerStats1();
                totalStats.addAll(match.getPlayerStats2());
                for (PlayerStats stats: totalStats) {
                    if (stats.getPlayer().equals(player)) {
                        playerStatsList.add(stats);
                    }
                }
            }
        }
        return playerStatsList;
    }

    public static double pointsPerGame(Player player) {
        ArrayList<PlayerStats> playerStatsList = getPlayerMatchStats(player);
        double totalPoints = 0;
        for (PlayerStats stats: playerStatsList) {
            totalPoints += stats.getPoints();
        }
        return totalPoints / playerStatsList.size();
    }

    public static double reboundsPerGame(Player player) {
        ArrayList<PlayerStats> playerStatsList = getPlayerMatchStats(player);
        double totalRebounds = 0;
        for (PlayerStats stats: playerStatsList) {
            totalRebounds += stats.getRebounds();
        }
        return totalRebounds / playerStatsList.size();
    }

    public static double assistsPerGame(Player player) {
        ArrayList<PlayerStats> playerStatsList = getPlayerMatchStats(player);
        double totalAssists = 0;
        for (PlayerStats stats: playerStatsList) {
            totalAssists += stats.getAssists();
        }
        return totalAssists / playerStatsList.size();
    }

}
