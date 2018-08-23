package factorio.dataObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 4/26/2018.
 */
public class Recipe {
    List<RecipeItem> items;
    double time;
    int amountMade;

    public void setItems(List<RecipeItem> items) {
        this.items = items;
    }

    public Recipe(int amount, double time) {
        setAmountMade(amount);
        setTime(time);
    }

    public List<RecipeItem> getItems() {
        return items;
    }

    public String getItemJsonString () {
        String items ="";
        for(RecipeItem recipeItem : this.items){

            if(items.equals("")){
                items= "{ \"item\" : \"" + recipeItem.getItem().getName().toString()  + "\" , \"count\" : "+ recipeItem.getCount()+" }";
            } else {
                items = items + ", " +  "{ \"item\" : \"" + recipeItem.getItem().getName().toString()  + "\" , \"count\" : "+ recipeItem.getCount()+" }";
            }
        }
        return items;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getAmountMade() {
        return amountMade;
    }

    public void setAmountMade(int amountMade) {
        this.amountMade = amountMade;
    }

    public void addItem(Item item, int amount) {
        if (this.items == null) {
            items = new ArrayList<>();
        }
        items.add(new RecipeItem(item, amount));
    }
}
