package factorio.dataObjects;

/**
 * Created by aaron on 4/26/2018.
 */


public class Item {
    Names name;
    Recipe recipe;
    String description;

    public Item(Names name) {
        this.name = name;
    }

    public Item(Names name, int amount, double time) {
        this(name);
        this.recipe = new Recipe(amount, time);
    }

    public Names getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Recipe getRecipe() {
        return this.recipe;
    }

    public void setRecipe ( Recipe recipe){
        this.recipe = recipe;
    }

    public void setDescription (String description){
        this.description = description;
    }


}
