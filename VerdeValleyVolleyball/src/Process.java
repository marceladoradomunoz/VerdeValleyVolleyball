import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Process {
    private List<Player> players;
    private List<Player> playersCopy;
    private Scanner scanner = new Scanner(System.in);

    public Process(){}

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void readListPlayers(){
        showMenuDatafiles();
        String fileName = scanner.next();
        while (!isCorrectReadingFile(fileName)){
            showMenuDatafiles();
            fileName = scanner.next();
        }

        String path = String.format("files/%s.txt", fileName);
        try (FileReader fr = new FileReader(path)) {
            BufferedReader br = new BufferedReader(fr);
            String line;
            players = new ArrayList<>(); //Initialize here because if you want to read new file, it must be a new list

            while((line=br.readLine())!=null) {
                String[] separatedElements = getStringArrayFromReadLine(line);
                String name = separatedElements[0];
                String lastName = separatedElements[1];
                float attack = Float.parseFloat(separatedElements[2]);
                float defense = Float.parseFloat(separatedElements[3]);
                Player player = new Player(name, lastName, attack, defense);
                players.add(player);
            }
            playersCopy = players; //Create a copy to avoid loss data from original list
            System.out.println(fileName + " file read successfully.");
        }
        catch(Exception e){
            System.out.println("Error: El nombre de archivo proporcionado es incorrecto.");
            e.printStackTrace();
        }
    }

    /**
     * Show the three best attack players of the list.
     * Clone original player list to it doesn't affect the original list with sort method.
     */
    public void showTheBestAttackPlayers(){
        if (existsListPlayers()){
            List<Player> bestAttackPlayers = players;
            bestAttackPlayers.sort((p1, p2) -> Float.compare(p2.getAttack(), p1.getAttack()));

            for (int i = 0; i < 3; i++){
                Player player = bestAttackPlayers.get(i);
                System.out.println(player.getName() + " " + player.getLastName() + " " + player.getAttack());
            }
        }
    }

    /**
     * Show the best defense players of the list.
     * Clone original player list to it doesn't affect the original list with sort method.
     */
    public void showTheBestDefensePlayers(){
        if (existsListPlayers()){
            List<Player> bestDefensePlayers = players;
            bestDefensePlayers.sort((p1, p2) -> Float.compare(p2.getDefense(), p1.getDefense()));

            for (int i = 0; i < 3; i++){
                Player player = bestDefensePlayers.get(i);
                System.out.println(player.getName() + " " + player.getLastName() + " " + player.getAttack());
            }
        }
    }

    /**
     * Create teams using Team Object and his type Player Arraylist.
     */
    public void createAndShowPracticeTeams(){
        List<Team> teams = new ArrayList<>();
        if (existsListPlayers()){
            int numberOfTeams = determinateNumberOfTeams(); //get

            for (int id = 1; id <= numberOfTeams; id++){
                Team team = new Team(id);
                team.getPlayers().addAll(theBestThreeAttackPlayers());
                team.getPlayers().addAll(theBestThreeDefensePlayers());
                teams.add(team);
            }
            showTeams(teams);
        }
    }

    /**
     * Create an empty list to select three best attack players. First, applying sort method according to attack value to
     * get in the first places the best players.
     * Then add player in this list and set true when this belongs to some team.
     */
    private List<Player> theBestThreeAttackPlayers(){
        List<Player> bestThreeAttackPlayers = new ArrayList<>();
        playersCopy.sort((p1, p2) -> Float.compare(p2.getAttack(), p1.getAttack()));
        int indexPlayersCopy = 0;
        int countCandidatesAdded = 0;
        while (countCandidatesAdded < 3){
            if (!playersCopy.get(indexPlayersCopy).hasTeam()){
                bestThreeAttackPlayers.add(playersCopy.get(indexPlayersCopy));
                playersCopy.get(indexPlayersCopy).setHasTeam(Boolean.TRUE);
                countCandidatesAdded++;
            }
            indexPlayersCopy++;
        }
        return bestThreeAttackPlayers;
    }

    /**
     * Create an empty list to select three best defense players. First, applying sort method according to defense value to
     * get in the first places the best players.
     * Then add player in this list and set true when this belongs to some team.
     */
    private List<Player> theBestThreeDefensePlayers(){
        List<Player> bestThreeDefensePlayers = new ArrayList<>();
        playersCopy.sort((p1, p2) -> Float.compare(p2.getDefense(), p1.getDefense()));
        int indexPlayersCopy = 0;
        int countCandidatesAdded = 0;
        while (countCandidatesAdded < 3){
            if (!playersCopy.get(indexPlayersCopy).hasTeam()){
                bestThreeDefensePlayers.add(playersCopy.get(indexPlayersCopy));
                playersCopy.get(indexPlayersCopy).setHasTeam(Boolean.TRUE);
                countCandidatesAdded++;
            }
            indexPlayersCopy++;
        }
        return bestThreeDefensePlayers;
    }

    /**
     * Create an empty string to concat with values related to players and team id.
     */
    private void showTeams(List<Team> teams){
        String data = "";
        for (Team team : teams){
            data = data.concat("Equipo " + team.getId() + ":\n");
            for (Player player : team.getPlayers()){
                data = data.concat(player.getName() + " " + player.getLastName() + "\n");
            }
            data = data.concat("\n");
        }
        System.out.println(data);
    }

    private void showMenuDatafiles(){
        System.out.println("Ingrese nombre de archivo de jugadores, sin agregar extensión.");
    }

    /**
     * Validate if it will be possible read the file according to file name.
     */
    private boolean isCorrectReadingFile(String fileName){
        File file = new File("files/".concat(fileName).concat(".txt"));
        boolean condition = file.exists() && !file.isDirectory() && file.length() > 0;
        if (!condition){
            System.out.println("El archivo es incorrecto o no existe.");
        }
        return condition;
    }

    /**
     * Get values from reading line. Values in the line come separated by whitespaces.
     */
    private String[] getStringArrayFromReadLine(String line){
        return line.split(" ");
    }

    /**
     * Validate if player list was initialized correctly and it has elements inside.
     */
    private boolean existsListPlayers(){
        if (Objects.nonNull(players) && !players.isEmpty()){
            return true;
        } else {
            System.out.println("Por favor, abre una lista de jugadores");
            return false;
        }
    }

    /**
     * Determinate number of teams that it could be generated.
     */
    private Integer determinateNumberOfTeams(){
        return players.size() / 6;
    }
}