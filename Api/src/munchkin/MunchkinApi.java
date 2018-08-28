package munchkin;

import com.exception.InvalidApiRequestException;
import com.exception.PlayerNotFound;
import interphase.IApi;

/**
 * Created by aaron on 5/29/2018.
 */
public class MunchkinApi implements IApi {

    public static final String NAME = "Munchkin";
    CurrentPlayers currentPlayers = new CurrentPlayers();


    @Override
    public byte[] getByteResponse(String requestURL) throws InvalidApiRequestException {

        String subArray = getSubArray(requestURL, NAME);

        if(subArray.contains("getUserData")){
            return currentPlayers.getAllUsersJson();
        } else if (subArray.contains("addPlayer")){
            return currentPlayers.addPlayer(getArgument(1, subArray));
        } else if (subArray.contains("clearAll")) {
            return currentPlayers.clearPlayers();
        } else if (subArray.contains("levelUp")) {
            try {
                return currentPlayers.levelUp(getArgument(1, subArray), getArgument(2, subArray));
            } catch (PlayerNotFound playerNotFound) {
                throw new InvalidApiRequestException(playerNotFound);
            }
        }

        throw new InvalidApiRequestException();
    }

    private String getArgument(int i, String subArray) {
        String[] split = subArray.split("/");

        return split[i+1].split("\\?")[0];
    }


//    public String getSubArray(String requestURL, String Name) {
//        String[] pullingApartApi = requestURL.split(NAME);
//
//        if(pullingApartApi.length > 1 ){
//            return pullingApartApi[1];
//        }
//
//        return "";
//    }
}
