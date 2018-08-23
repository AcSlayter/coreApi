package factorio;

import factorio.dataObjects.Item;
import factorio.dataObjects.Names;
import factorio.dataObjects.RecipeItem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;

/**
 * Created by aaron on 4/26/2018.
 */

@XmlRootElement
public class ItemStorage {

    @XmlElement
    HashMap<Names,Item> allItems = null;


    public ItemStorage() {
        this.allItems =  new HashMap<Names, Item>();
        init();
    }

    public void add(Names name, Item item) {
        allItems.put(name, item);
    }


    public Item getItem(Names name) {
        return allItems.get(name);
    }

    public String getJSONString(String itemName, int amount, int time){
        return printNumbersList(Names.valueOf(itemName) ,amount, time);
    }

    public String getJSONString (String itemName) {
        Item item = this.allItems.get(Names.valueOf(itemName));

        Double itemTime = 0.0;
        int amountMade = 0;
        String getItemList = "";

        if(item.getRecipe() != null) {
            itemTime = item.getRecipe().getTime();
            amountMade = item.getRecipe().getAmountMade();
            getItemList = item.getRecipe().getItemJsonString();
        }


        String json_return = "";

        json_return = "{ \"name\" : \"" + itemName + "\" , " +
                "\"time\" : \"" + itemTime + "\" , " +
                "\"amount\" : \"" + amountMade + "\" , " +
                "\"itemlist\" : [" + getItemList + "] }" ;

        printNumbersList(Names.valueOf(itemName),5,1);
        return json_return;
    }

    public void printTreeList(Names name) {
        Item item = getItem(name);
        print_details(item,1);
    }

    private void print_details(Item item, int amount) {
        if(item != null) {
            System.out.println("ITEM TO MAKE : " + item.getName().toString() + " - X" + amount);
            if(item.getRecipe() != null) {
                System.out.println("Total Time   : " + item.getRecipe().getTime());
                System.out.println("Total Count  : " + item.getRecipe().getAmountMade());
                List<RecipeItem> items = item.getRecipe().getItems();
                for (RecipeItem temp : items) {
                    print_details(temp.getItem(), temp.getCount()*amount);
                }
            }
        }
    }

    public String printNumbersList(Names key, int amount, int time) {
        Item item = allItems.get(key);
        String json = "";

        if( item != null && item.getRecipe() != null){
            double need  =  (  (double) amount / (double) time ) * ( item.getRecipe().getTime() / item.getRecipe().getAmountMade() ) ;
            json = "{ \"name\" : \"" + key.toString() + "\"," +
                    "\"amount\" : \"" + need + "\", " +
                    "\"need\" : ";
            String subList = "[";
            List<RecipeItem> items = item.getRecipe().getItems();
            for (int index = 0, leng = items.size() ; index < leng ; index++) {
                String sublistString = printNumbersList(items.get(index).getItem().getName(), items.get(index).getCount() * amount, time);
                if(index > 0){
                    sublistString = ",".concat(sublistString);
                }
                subList = subList.concat(sublistString);
            }
            subList = subList.concat("]}");
            json  = json.concat( subList );
        } else if ( item != null ) {
            json = "{ \"name\" : \"" + key.toString() + "\"," +
                    "\"amount\" : \"" + (double)  amount * time + "\", " +
                    "\"need\" : [] }";

            return json;
        }
        return json;
    }

    public void AddRecipeItem(Names main, Names adding, int amount) {
        allItems.get(main).getRecipe().addItem(allItems.get(adding),amount);
    }

    public void init(){
        add(Names.Iron_ore, new Item(Names.Iron_ore));
        add(Names.Copper_ore ,new Item(Names.Copper_ore));
        add(Names.Coal, new Item(Names.Coal));
        add(Names.Petroleum_gas, new Item(Names.Petroleum_gas));

        add(Names.Iron_plate,  new Item(Names.Iron_plate , 1 , 3.5));
        AddRecipeItem(Names.Iron_plate, Names.Iron_ore, 1);

        add(Names.Copper_plate,  new Item(Names.Copper_plate,1,3.5));
        AddRecipeItem(Names.Copper_plate, Names.Copper_ore, 1);

        add(Names.Iron_gear_wheel,new Item(Names.Iron_gear_wheel,1,.5));
        AddRecipeItem(Names.Iron_gear_wheel, Names.Iron_plate,2);

        add(Names.Transport_belt, new Item( Names.Transport_belt, 2 ,.5));
        AddRecipeItem(Names.Transport_belt, Names.Iron_gear_wheel, 1);
        AddRecipeItem(Names.Transport_belt, Names.Iron_plate, 1);

        add(Names.Science_pack_1 , new Item(Names.Science_pack_1, 1, 5));
        AddRecipeItem(Names.Science_pack_1, Names.Copper_plate, 1);
        AddRecipeItem(Names.Science_pack_1, Names.Iron_gear_wheel, 1);

        add(Names.Copper_cable , new Item(Names.Copper_cable, 2, .5));
        AddRecipeItem(Names.Copper_cable, Names.Copper_plate, 1);

        add(Names.Electronic_circuit , new Item(Names.Electronic_circuit, 1, .5));
        AddRecipeItem(Names.Electronic_circuit, Names.Copper_cable , 3);
        AddRecipeItem(Names.Electronic_circuit, Names.Iron_plate , 1);

        add(Names.Inserter, new Item(Names.Inserter , 1, .5));
        AddRecipeItem(Names.Inserter, Names.Electronic_circuit, 1);
        AddRecipeItem(Names.Inserter, Names.Iron_gear_wheel, 1);
        AddRecipeItem(Names.Inserter, Names.Iron_plate, 1);

        add(Names.Science_pack_2, new Item(Names.Science_pack_2 , 1, 6));
        AddRecipeItem(Names.Science_pack_2, Names.Inserter, 1);
        AddRecipeItem(Names.Science_pack_2, Names.Transport_belt, 1);

        add(Names.Plastic_bar, new Item(Names.Plastic_bar, 2, 1));
        AddRecipeItem(Names.Plastic_bar, Names.Coal, 1);
        AddRecipeItem(Names.Plastic_bar, Names.Petroleum_gas, 20);

        add(Names.Advanced_circuit, new Item(Names.Advanced_circuit,1,6));
        AddRecipeItem(Names.Advanced_circuit, Names.Copper_cable, 4);
        AddRecipeItem(Names.Advanced_circuit, Names.Electronic_circuit, 2);
        AddRecipeItem(Names.Advanced_circuit, Names.Plastic_bar, 2);

        add(Names.Electric_mining_drill, new Item(Names.Electric_mining_drill, 1,2));
        AddRecipeItem(Names.Electric_mining_drill, Names.Electronic_circuit, 3);
        AddRecipeItem(Names.Electric_mining_drill, Names.Iron_gear_wheel, 5);
        AddRecipeItem(Names.Electric_mining_drill, Names.Iron_plate, 10);

        add(Names.Steel_plate, new Item(Names.Steel_plate,1,17.5));
        AddRecipeItem(Names.Steel_plate, Names.Iron_plate, 5);

        add(Names.Pipe, new Item(Names.Pipe,1,.5));
        AddRecipeItem(Names.Pipe, Names.Iron_plate, 1);

        add(Names.Engine_unit, new Item(Names.Engine_unit,1,10));
        AddRecipeItem(Names.Engine_unit, Names.Iron_gear_wheel, 1);
        AddRecipeItem(Names.Engine_unit, Names.Pipe, 2);
        AddRecipeItem(Names.Engine_unit, Names.Steel_plate, 1);

        add(Names.Science_pack_3, new Item(Names.Science_pack_3, 1,12));
        AddRecipeItem(Names.Science_pack_3, Names.Advanced_circuit, 1);
        AddRecipeItem(Names.Science_pack_3, Names.Electric_mining_drill, 1);
        AddRecipeItem(Names.Science_pack_3, Names.Engine_unit, 1);

    }
}