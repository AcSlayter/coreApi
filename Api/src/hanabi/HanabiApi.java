package hanabi;

import exception.InvalidApiRequestException;
import interphase.IApi;

/**
 * Created by aaron on 5/30/2018.
 */
public class HanabiApi implements IApi{

    public static final String NAME = "Hanabi";

    @Override
    public byte[] getByteResponse(String requestURL) throws InvalidApiRequestException {
        throw new InvalidApiRequestException();
    }

    @Override
    public String getSubArray(String requestURL) {
        String[] pullingApartApi = requestURL.split(NAME);

        if(pullingApartApi.length > 1 ){
            return pullingApartApi[1];
        }

        return "";
    }
}
