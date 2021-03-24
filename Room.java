import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Joker's Great Escape" application. 
 * "Joker's Great Escape" is a very simple and interesting, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * Once the Game class is instansiated, different Room objects are created according to the 
 * play() method in the Game class. These objects keep reference according to the returned 
 * object in the createRooms() method in the Game class which is in our case "JokerCell"
 * 
 * @author  Divyesh Joshi and Hussam Kayed
 * @version 27/04/2020
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> items;
    private String image;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String iName) 
    {
        this.description = description;
        this.image = iName;
        exits = new HashMap<>();
        items = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are in " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    public int getSize()
    {
        return items.size();
    }
    
    public void addItem(Item item)
    {
        items.add(item);
    }
    
    public void addItemByName(String item)
    {
        items.add(new Item(item));
    }
    
    public void removeItem(String itemName)
    {
        Iterator<Item> it = items.iterator();
        while(it.hasNext())
        {
            if(it.next().getItemName().equals(itemName)){
                it.remove();// removes the item instance found here by name
                
            }
        }
    }
    
    public void itemsInRoom()
    {
        System.out.println("Items found in room : ");
        if(items.size()==0)
            System.out.println("Room is Empty");
            
        for(Item item : items)
        {
            System.out.println(item.getItemName()+" ");
        }
    }
    
    public ArrayList<String> getItemsInRoom()
    {
        ArrayList<String> it = new ArrayList();
        for(Item item : items)
        {
            it.add(item.getItemName());
        }
        
        return it;
    }
    
    public String getImage()
    {
        return image;
    }
}

