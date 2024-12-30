import java.util.ArrayList;
import java.util.List;

public class Team {
    private int id;
    private List<Player> players = new ArrayList<>();

    public Team(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }
}
