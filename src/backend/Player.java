package backend;

public class Player {
    private int jerseyNumber;
    private Team team;

    public Player(int jerseyNumber, Team team) {
        this.jerseyNumber = jerseyNumber;
        this.team = team;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public Team getTeam() {
        return team;
    }
}
