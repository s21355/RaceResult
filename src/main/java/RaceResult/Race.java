package RaceResult;

public class Race implements Message {
    private String raceName;
    private String winner;

    public Race(String raceName, String winner) {
        this.raceName = raceName;
        this.winner = winner;
    }

    public String getRaceName() {
        return raceName;
    }

    public String getWinner() {
        return winner;
    }
}
