package interphase;

import exception.InvalidApiRequestException;

/**
 * Created by aaron on 5/27/2018.
 */
public interface IApi {
    byte [] getByteResponse (String requestURL) throws InvalidApiRequestException;
    default String getSubArray(String requestURL, String name) {
        return requestURL.split(name)[1];
    }
}
