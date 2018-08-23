package munchkin;

import exception.PlayerNotFound;
import munchkin.datacontainers.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 5/29/2018.
 */
public class CurrentPlayers {
    private List<Player> players;

    public CurrentPlayers() {
        this.players =  new ArrayList<>();
    }

    public byte[] getAllUsersJson() {
        StringBuilder jsonReturn = new StringBuilder("");
        jsonReturn.append("[");
        for (int index = 0, length =  players.size() ; index < length ; index ++){
            if (index != 0) {
                jsonReturn.append(", ");
            }
            jsonReturn.append(players.get(index).getJson());
        }
        jsonReturn.append("]");
        return jsonReturn.toString().getBytes();
    }

    public byte[] addPlayer(String name) {
        for (Player player : this.players ){
            if ( player.getName().contains(name)) {
                return "{ \"status\" : false }".getBytes();
            }
        }
        this.players.add(new Player(name));

        return "{ \"status\" : true }".getBytes();
    }

    public byte[] levelUp(String name, String levelchange) throws PlayerNotFound {
        int levelChange = Integer.parseInt(levelchange);

        for (Player player : this.players ){
            if ( player.getName().contains(name)) {
                player.changeLevel(levelChange);
                return player.getJson().getBytes();
            }
        }

        throw new PlayerNotFound( name );

    }

    public byte[] clearPlayers() {
        this.players.clear();
        return "{}".getBytes();
    }
}
