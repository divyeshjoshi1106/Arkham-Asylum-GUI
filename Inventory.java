import java.util.ArrayList;
/**
 * class Inventory is part of"Joker's Great Escape" game. This is a very interesting game
 * which envolves the escape of the joker from an asylum in gotham city called "The Arkham Asylum"
 * class Inventory holds all the item that player has collected in the game
 * @author  Divyesh Joshi and Hussam Kayed
 * @version 04/05/2020
 */
public class Inventory
{
    // instance variables 
    private ArrayList<String> backPack;

    /**
     * Constructor for objects of class Inventory
     */
    public Inventory()
    {
        // initialise instance variables
        backPack = new ArrayList<>();
    }

    /**
     * Method addItem adds item into the inventory
     *
     * @param item A parameter of type String item to be added in inventory
     */
    public void addItem(String item)
    {
        backPack.add(item);
    }

    /**
     * Method removeItem removes item from the inventory
     *
     * @param item A parameter of type String, item to be removed from the inventory
     */
    public void removeItem(String item)
    {
        backPack.remove(item);
    }

    /**
     * Method playerInventory
     * prints the items stored in player's inventory
     */
    public void playerInventory()
    {
        System.out.println("Items you have : ");
        if(backPack.size()==0)
        {
            System.out.println("Inventory is empty.");
        }
        else
        {
            for(String item : backPack)
            {
                System.out.println(item+", ");
            }  
        }

    }
    
    /**
     * Method getSize getter returns the size of the inventory
     *
     * @return The return value returns integer value size of inventory
     */
    public int getSize()
    {
        return backPack.size();
    }

    /**
     * Method getInventory
     *
     * @return The return value returns the player's inventory
     */
    public ArrayList<String> getInventory()
    {
        return backPack;
    }

    /**
     * Method getStateItems
     *
     * @return returns ArrayList for the previous room 
     */
    public ArrayList<String> getStateItems()
    {
        ArrayList<String> stateItems = new ArrayList<>();  
        for(String item: backPack)
        {
            stateItems.add(item);
        }
        return stateItems;
    }

    /**
     * Method previousItems
     *
     * @param previousItems A parameter when we change room using back command this values are stored in player's inventory
     */
    public void previousItems(ArrayList<String> previousItems)
    {
        backPack = previousItems;
    }
    
    /**
     * Method getByindex
     *
     * @param index A parameter value of array to be accessed using index
     * @return The return value returns value of the array found on that index
     */
    public String getByindex(int index)
    {
        return backPack.get(index);
    }
}
