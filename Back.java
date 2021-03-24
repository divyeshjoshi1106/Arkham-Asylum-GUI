import java.util.Stack;
import java.util.ArrayList;
/**
 * This is the Back class of the "Joker's Great Escape" game. This is a very interesting game
 * which envolves the escape of the joker from an asylum in gotham city called "The Arkham Asylum"
 * This class is used to store data of the previos room in which player was to be used with Back command
 * @author (Divyesh Joshi and Hussam Kayed)
 * @version (04.05.2020)
 */
public class Back
{
    // instance variables 
    private Room room;
    private ArrayList<String> itemsList;

    /**
     * Constructor for objects of class Back
     */
    public Back(Room room, ArrayList<String> itemsList)
    {
        // initialise instance variables
        this.room = room;
        this.itemsList = itemsList;
    }

    
    /**
     * Method getRoom
     *
     * @return returns reference to the room stored in state
     */
    public Room getRoom()
    {
        return room;
    }
    
    
    /**
     * Method getItemsList
     *
     * @return returns the arraylist of the items
     */
    public ArrayList<String> getItemsList()
    {
        return itemsList;
    }
    
    /**
     * Method getPrevSize
     *
     * @return returns the size of the itemsList
     */
    public int getPrevSize()
    {
        return itemsList.size();
    }
}
