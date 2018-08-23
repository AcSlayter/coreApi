package oxygennotincluded.endpoint;

import oxygennotincluded.dataobject.Names;

/**
 * Created by aaron on 7/31/2018.
 */
public class AllRecourses {



    public static byte[] getAllItemNamesJSON() {
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
