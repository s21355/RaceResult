package RaceResult;

public class RaceSubscriber implements Client {
    private String name;

    public RaceSubscriber(String name) {
        this.name = name;
    }

    public void receive(Message message) {
        if (message instanceof Race) {
            Race race = (Race) message;
            System.out.println(name + " received message: " + race.getRaceName() + ", Winner: " + race.getWinner());
        }
    }
}