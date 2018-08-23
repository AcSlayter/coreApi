package oxygennotincluded.endpoint;

import oxygennotincluded.dataobject.gameitems.Machine;

/**
 * Created by aaron on 7/31/2018.
 */
public class AllRecourses {



    public static byte[] getAllItemMachineJSON() {
            Machine[] values = Machine.values();

            String list = "";
            for ( Machine name : values) {
                if(list.equals("")){
                    list = "\"" + name.toString() + "\"";
                } else {
                    list = list +  ", \"" + name.toString() + "\"";
                }
            }

            String Machine =  "{ \"name_key\" : ["+ list + "] }";
            byte[] item = Machine.getBytes();

            return item;
    }
}
