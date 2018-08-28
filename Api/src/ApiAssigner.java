import com.ApiHandler;
import com.exception.*;
import factorio.FactorioApi;
import interphase.IApi;
import munchkin.MunchkinApi;
import oxygennotincluded.OxygenNotIncludedApi;

/**
 * Created by aaron on 5/27/2018.
 */
public class ApiAssigner extends ApiHandler {
    private FactorioApi factorioApi;
    private MunchkinApi munchkinApi;

    private OxygenNotIncludedApi oxygenNotIncludedApi;


    public ApiAssigner() {
        this.factorioApi  =  new FactorioApi();
        this.munchkinApi = new MunchkinApi();
        this.oxygenNotIncludedApi = new OxygenNotIncludedApi();
    }

    public byte[] getByteResponse(String request) throws Exception {
            String apiName = getApiString(request);
            return getApi(apiName).getByteResponse(request);
    }

    public IApi getApi(String apiName) throws ApiNotFoundException {
        switch (apiName) {
            case FactorioApi.NAME : return this.factorioApi;
            case MunchkinApi.NAME : return this.munchkinApi;
            case OxygenNotIncludedApi.NAME : return this.oxygenNotIncludedApi;
        }
        throw new ApiNotFoundException(apiName);
    }


    private String getApiString(String requestURL) throws ParameterException {
        String[] api = requestURL.split("api");
        if (api.length == 1 ) {
            throw new ParameterApiNameNotPassed(requestURL);
        }

        String[] path = api[1].split("/");
        if (path.length == 0) {
            throw new ParameterApiNameNotPassed(requestURL);
        }

        return path[1];
    }
}
