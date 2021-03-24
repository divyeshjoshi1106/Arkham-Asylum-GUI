
/**
 * class Item is part of "Joker's Great Escape" game. This is a very interesting game
 * which envolves the escape of the joker from an asylum in gotham city called "The Arkham Asylum"
 * Class stores item to be used for the game.
 * @author (Divyesh Joshi and Husaam Kayed)
 * @version (04.05.2020)
 */
public class Item
{
    // instance variables 
    private String itemName; // stores name of the item

    /**
     * Constructor for objects of class Item
     */
    public Item(String name)
    {
        // initialise instance variables
        itemName = name;
        
    }
    
    /**
     * Method getItemName returns the name of the item
     *
     * @return returns a string containing name
     */
    public String getItemName()
    {
        return itemName;
    }
    
}
