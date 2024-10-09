package lesson22;

import java.util.Calendar;

public class Match {
    private Calendar date;
    private String team1, result, team2, stadium;
    private int watcher;

    public Match(Calendar date, String team1, String result, String team2, String stadium, int watcher) {
        this.date = date;
        this.team1 = team1;
        this.result = result;
        this.team2 = team2;
        this.stadium = stadium;
        this.watcher = watcher;
    }

    public Calendar getDate() {
        return date;
    }

    public String getTeam1() {
        return team1;
    }

    public String getResult() {
        return result;
    }

    public String getTeam2() {
        return team2;
    }

    public String getStadium() {
        return stadium;
    }

    public int getWatcher() {
        return watcher;
    }
}
