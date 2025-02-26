package backend;

import java.util.ArrayList;

public class TeamStats {
    private Team team;
    private Match match;
    private int points;
    private int rebounds;
    private int assists;
    private boolean result;

    public TeamStats(Team team, Match match){
        this.team = team;
        this.match = match;
        this.result = false;
    }

    public Team getTeam() {
        return team;
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

    public static ArrayList<TeamStats> getTeamMatchStats(Team team) {
        ArrayList<TeamStats> teamStatsList = new ArrayList<TeamStats>();
        for (Day day : Manager.schedule) {
            for (Match match : day.getMatches()) {
                if (match.getTeam1().equals(team)) {
                    teamStatsList.add(match.getStats1());
                } else if (match.getTeam2().equals(team)) {
                    teamStatsList.add(match.getStats2());
                }
            }
        }
        return teamStatsList;
    }

    public static double pointsPerGame(Team team) {
        ArrayList<TeamStats> teamStatsList = getTeamMatchStats(team);
        double totalPoints = 0;
        for (TeamStats stats: teamStatsList) {
            totalPoints += stats.getPoints();
        }
        return totalPoints / teamStatsList.size();
    }

    public static double reboundsPerGame(Team team) {
        ArrayList<TeamStats> teamStatsList = getTeamMatchStats(team);
        double totalRebounds = 0;
        for (TeamStats stats: teamStatsList) {
            totalRebounds += stats.getRebounds();
        }
        return totalRebounds / teamStatsList.size();
    }

    public static double assistsPerGame(Team team) {
        ArrayList<TeamStats> teamStatsList = getTeamMatchStats(team);
        double totalAssists = 0;
        for (TeamStats stats: teamStatsList) {
            totalAssists += stats.getAssists();
        }
        return totalAssists / teamStatsList.size();
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public static ArrayList<Integer> getRecord(Team team) {
        ArrayList<TeamStats> teamStatsList = getTeamMatchStats(team);
        ArrayList<Integer> record = new ArrayList<Integer>();
        int wins = 0;
        int losses = 0;
        for (TeamStats stats: teamStatsList) {
            if (stats.getResult()) {
                wins++;
            } else {
                losses++;
            }
        }
        record.add(wins);
        record.add(losses);
        return record;
    }
}
