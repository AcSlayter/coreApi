package guildwars;

import com.exception.InvalidApiRequestException;
import eft.networkConnector.GetETFAPI;
import interphase.IApi;

/**
 * Created by aaron on 2/9/2019.
 */
public class GuildWarsApi implements IApi{

    public static final String NAME = "GUILDWARS";

    @Override
    public byte[] getByteResponse(String requestURL) throws InvalidApiRequestException {
        String subArray = getSubArray(requestURL, NAME);

        String itemName = subArray.split("/")[subArray.split("/").length - 1];
        itemName = itemName.split("\\?")[0];
        if(subArray.contains("getAchievementsDailyDetails")){
            GetGuildWarsAPI getGuildWarsAPI = new GetGuildWarsAPI("guildwars2/Items/");
            String results = getGuildWarsAPI.getPublicJson(itemName);
            return results.getBytes();
        }

        if(subArray.contains("getItems")){
            GetGuildWarsAPI getGuildWarsAPI = new GetGuildWarsAPI("");
            String results = getGuildWarsAPI.getPublicJson(subArray);
            return results.getBytes();
        }

//        http://guildwars2.ac-local.com:2845/guildwars2/Items/getItems?ids=68149

//        else if (subArray.contains("item/slots")){
//            GetGuildWarsAPI getGuildWarsAPI = new GetGuildWarsAPI("/etf/item/slots?name=");
//            String results = getGuildWarsAPI.getPublicJson(itemName);
//            return results.getBytes();
//        }

        throw new InvalidApiRequestException();
    }
}
