package backend;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static ArrayList<Day> schedule;
    public static void main(String[] args) {
        Manager.initTeams();
        schedule = Manager.initSchedule();

    }
}