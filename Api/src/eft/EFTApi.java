package eft;

import com.exception.InvalidApiRequestException;
import interphase.IApi;
import mine.com.Item;


import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by aaron on 2/9/2019.
 */
public class EFTApi implements IApi{

    public static final String NAME = "EFT";



    @Override
    public byte[] getByteResponse(String requestURL) throws InvalidApiRequestException {
        String subArray = getSubArray(requestURL, NAME);

        if(subArray.contains("getItem")){
            return getItem(subArray.split("getItem\\?")[1]).toString().getBytes();
        }
        if(subArray.contains("availableItems")){
            return getCacheFileNames().getBytes();
        }

        throw new InvalidApiRequestException();
    }

    private Item getItem(String s) throws InvalidApiRequestException {
        String itemName = getItemName("item", s);
        if(itemName.charAt(0) != '/'){
            itemName = "/".concat(itemName);
        }
        return new Item(itemName);
    }

    private String getItemName(String item, String s) throws InvalidApiRequestException {
        String[] split = s.split("=");
        if(split[0].contains(item)) {
            String itemName = split[1];
            return  itemName.split("&")[0];
        }
        throw new InvalidApiRequestException();
    }

    private String getCacheFileNames(){
        StringBuilder fileNames = new StringBuilder();
        File folder = new File("Cache");
        //Implementing FilenameFilter to retrieve only txt files
        FilenameFilter txtFileFilter = new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                if(name.endsWith(".json"))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        };
        //Passing txtFileFilter to listFiles() method to retrieve only txt files
        fileNames.append("{ \"fileNames\" : [");
        File[] files = folder.listFiles(txtFileFilter);

        for (File file : files)
        {
            fileNames.append("\"").append(file.getName().substring(0,file.getName().length()-5)).append("\"").append(",");
        }
        fileNames.deleteCharAt(fileNames.length()-1);
        fileNames.append(" ] }");
        return fileNames.toString();
    }
}
