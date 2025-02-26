package backend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Day {
    private LocalDate day;
    private ArrayList<Match> matches;


    public Day(LocalDate day) {
        this.day = day;
        this.matches = new ArrayList<Match>();
    }

    public LocalDate getDay() {
        return day;
    }

    public void addMatch(Match match) {
        this.getMatches().add(match);
    }

    public List<Match> getMatches() {
        return matches;
    }

}
