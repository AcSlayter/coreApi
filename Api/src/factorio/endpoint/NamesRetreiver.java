package factorio.endpoint;

import factorio.dataObjects.Names;

/**
 * Created by aaron on 4/29/2018.
 */
public class NamesRetreiver {

    public byte[] getAllItemNamesJSON(){

        Names[] values = Names.values();

        String list = "";
        for ( Names name : values) {
            if(list.equals("")){
                list = "\"" + name.toString() + "\"";
            } else {
                list = list +  ", \"" + name.toString() + "\"";
            }

        }

        String names =  "{ \"name_key\" : ["+ list + "] }";
        byte[] item = names.getBytes();

        return item;
    }
}
