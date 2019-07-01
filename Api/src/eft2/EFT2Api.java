package eft2;

import com.exception.InvalidApiRequestException;
import eft.networkConnector.GetETFAPI;
import interphase.IApi;
import mine.com.Item;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by aaron on 2/9/2019.
 */
public class EFT2Api implements IApi{

    public static final String NAME = "EFT2";

    @Override
    public byte[] getByteResponse(String requestURL) throws InvalidApiRequestException {
        String subArray = getSubArray(requestURL, NAME);

        String itemName = subArray.split("/")[subArray.split("/").length - 1];
        itemName = itemName.split("\\?")[0];
        if(subArray.contains("item/details")){
            GetETFAPI getETFAPI = new GetETFAPI("/etf/item/details?name=");
            String results = getETFAPI.getPublicJson(itemName);
            return results.getBytes();
        } else if (subArray.contains("item/slots")){
            GetETFAPI getETFAPI = new GetETFAPI("/etf/item/slots?name=");
            String results = getETFAPI.getPublicJson(itemName);
            return results.getBytes();
        } else if (subArray.contains("item")){
            GetETFAPI getETFAPI = new GetETFAPI("/etf/item?name=");
            String results = getETFAPI.getPublicJson(itemName);
            return results.getBytes();
        }

        throw new InvalidApiRequestException();
    }

}
