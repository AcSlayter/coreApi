package munchkin.datacontainers;

/**
 * Created by aaron on 5/29/2018.
 */
public class Player {
    private String Name;
    private int level;

    public Player(String name) {
        Name = name;
        this.level = 1;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addLevel(){
        this.changeLevel(1);
    }

    public void removeLevel() {
        this.changeLevel(-1);
    }

    public void changeLevel (int numberOfLevels){
        this.level = this.level + numberOfLevels;
    }

    public String getJson() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append(" \"name\" : \"").append(this.getName()).append("\"");
        json.append(",");
        json.append(" \"level\" : \"").append(this.getLevel()).append("\"");
        json.append("}");
        return json.toString();

    }
}
