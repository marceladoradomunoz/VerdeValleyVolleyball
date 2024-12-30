public class Player {
    private String name;
    private String lastName;
    private float attack;
    private float defense;
    private boolean hasTeam;

    public Player(String name, String lastName, float attack, float defense) {
        this.name = name;
        this.lastName = lastName;
        this.attack = attack;
        this.defense = defense;
        this.hasTeam = false; //By default, no one has team.
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getDefense() {
        return defense;
    }

    public void setDefense(float defense) {
        this.defense = defense;
    }

    public float getAttack() {
        return attack;
    }

    public void setAttack(float attack) {
        this.attack = attack;
    }

    public boolean hasTeam() {
        return hasTeam;
    }

    public void setHasTeam(boolean hasTeam) {
        this.hasTeam = hasTeam;
    }
}
