package com;

import com.exception.ApiNotFoundException;

public class ApiHandler {
    public byte[] getByteResponse(String requestURL) throws ApiNotFoundException, Exception {
        throw new ApiNotFoundException(requestURL);
    }
}
