package factorio.dataObjects;

/**
 * Created by aaron on 4/26/2018.
 */
public class RecipeItem {
    Item item;
    int  count;

    public RecipeItem(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Item getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }
}

