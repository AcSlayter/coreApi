package factorio.endpoint;

import factorio.ItemStorage;

/**
 * Created by aaron on 5/5/2018.
 */
public class ItemRecipeRetriever {
    ItemStorage itemStorage = null;

    public ItemRecipeRetriever() {
        this.itemStorage = new ItemStorage();
    }

    public byte[] getItemRecipe(String requestURL){

        String[] parameters = requestURL.split("/");
        String item = parameters[2].split("\\?")[0];

        String names = this.itemStorage.getJSONString(item);


        return names.getBytes();
    }


    public byte[] getBreakDown(String requestURL) {
        String[] parameters = requestURL.split("\\?")[1].split("\\&");
        String item = "",
               amount = "",
               second = "";
        for(String tmp : parameters) {

            if(tmp.contains("item")){
                item = tmp.split("=")[1];
            } else if (tmp.contains("amount")){
                amount = tmp.split("=")[1];
            } else if (tmp.contains("second")){
                second = tmp.split("=")[1];
            }
        }

        String names = this.itemStorage.getJSONString(item,Integer.parseInt(amount),Integer.parseInt(second));

        return names.getBytes();
    }
}
