package factorio;

import com.exception.InvalidApiRequestException;
import factorio.endpoint.ItemRecipeRetriever;
import factorio.endpoint.NamesRetreiver;
import interphase.IApi;

/**
 * Created by aaron on 5/21/2018.
 */
public class FactorioApi implements IApi {

    public final static String NAME = "Factorio";
    public NamesRetreiver namesRetreiver = null;
    public ItemRecipeRetriever itemRecipeRetriever = null;

    public FactorioApi() {
        this.namesRetreiver = new NamesRetreiver();
        this.itemRecipeRetriever = new ItemRecipeRetriever();

    }

    public byte[] getByteResponse (String arguments) throws InvalidApiRequestException {
        String subArray = getSubArray(arguments, NAME);

        if(subArray.contains("getNames")) {
            return namesRetreiver.getAllItemNamesJSON();
        }else if (subArray.contains("getItemRecipe")) {
            return itemRecipeRetriever.getItemRecipe(subArray);
        }else if (subArray.contains("getBreakDown")) {
            return itemRecipeRetriever.getBreakDown(subArray);
        }
        throw new InvalidApiRequestException();


    }
}
