package oxygennotincluded;

import com.exception.InvalidApiRequestException;
import interphase.IApi;
import oxygennotincluded.endpoint.AllRecourses;

/**
 * Created by aaron on 6/27/2018.
 */
public class OxygenNotIncludedApi implements IApi {
    public static final String NAME = "OxygenNotIncluded";

    @Override
    public byte[] getByteResponse(String requestURL) throws InvalidApiRequestException {
        String subArray = getSubArray(requestURL, NAME);

        if(subArray.contains("getNames")) {
            return AllRecourses.getAllItemMachineJSON();
        }
        throw new InvalidApiRequestException();
    }
}