import com.ApiHandler;
import com.exception.*;
import eft.EFTApi;
import eft2.EFT2Api;
import factorio.FactorioApi;
import guildwars.GuildWarsApi;
import interphase.IApi;
import munchkin.MunchkinApi;
import oxygennotincluded.OxygenNotIncludedApi;

/**
 * Created by aaron on 5/27/2018.
 */
public class ApiAssigner extends ApiHandler {
    private FactorioApi factorioApi;
    private MunchkinApi munchkinApi;
    private EFTApi eftApi;
    private EFT2Api eft2Api;
    private GuildWarsApi guildWarsApi;

    private OxygenNotIncludedApi oxygenNotIncludedApi;


    public ApiAssigner() {
        this.factorioApi  =  new FactorioApi();
        this.munchkinApi = new MunchkinApi();
        this.eftApi = new EFTApi();
        this.eft2Api = new EFT2Api();
        this.guildWarsApi = new GuildWarsApi();
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
            case EFTApi.NAME : return this.eftApi;
            case EFT2Api.NAME : return this.eft2Api;
            case OxygenNotIncludedApi.NAME : return this.oxygenNotIncludedApi;
            case GuildWarsApi.NAME : return this.guildWarsApi;
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
