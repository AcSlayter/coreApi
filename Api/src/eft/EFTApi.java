package eft;

import com.exception.InvalidApiRequestException;
import interphase.IApi;
import mine.com.Item;

/**
 * Created by aaron on 2/9/2019.
 */
public class EFTApi implements IApi{

    public static final String NAME = "EFT";



    @Override
    public byte[] getByteResponse(String requestURL) throws InvalidApiRequestException {
        String subArray = getSubArray(requestURL, NAME);

        if(subArray.contains("getItem")){
            return getItem(subArray.split("getItem\\?")[1]).toString().getBytes();
        }

        throw new InvalidApiRequestException();
    }

    private Item getItem(String s) throws InvalidApiRequestException {
        String itemName = getItemName("item", s);
        if(itemName.charAt(0) != '/'){
            itemName = "/".concat(itemName);
        }
        return new Item(itemName);
    }

    private String getItemName(String item, String s) throws InvalidApiRequestException {
        String[] split = s.split("=");
        if(split[0].contains(item)) {
            return split[1];
        }
        throw new InvalidApiRequestException();
    }
}
