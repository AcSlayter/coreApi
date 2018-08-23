package oxygennotincluded;

import exception.InvalidApiRequestException;
import interphase.IApi;
import oxygennotincluded.endpoint.AllRecourses;

/**
 * Created by aaron on 6/27/2018.
 */
public class OxygenNotIncludedApi implements IApi {
    public static final String NAME = "OxygenNotIncluded";

    public OxygenNotIncludedApi(){

    }


    @Override
    public byte[] getByteResponse(String requestURL) throws InvalidApiRequestException {
        String subArray = getSubArray(requestURL);

        if(subArray.contains("getNames")) {
            return AllRecourses.getAllItemNamesJSON();
        }
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