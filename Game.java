import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * This is the main class of the "Joker's Great Escape" game. This is a very interesting game
 * which envolves the escape of the joker from an asylum in gotham city called "The Arkham Asylum"
 * The game has items that will help you escape from the Asylum but remember, when you are choosing
 * the items, choose wisely because you are only allowed to carry around max 3 items and having
 * the suitable three items is your winning condition in this game
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *  
 *  
 * 
 * @author  Divyesh Joshi and Hussam Kayed
 * @version  June 2020
 */

public class Game 
{
    private Room currentRoom;
    private Inventory bag;
    private Stack<Back> states = new Stack();

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        bag = new Inventory();

    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room jokerCell, isolationCell,mrFreezerCell,ratLab,hallWay,kitchen,storageRoom,diningHall,therapyCenter,watchRoom,secretRoom,finalRoom;
        Item lockPickTool, gun, claw, rope, mrFreezerGun, guardOutFit, watchRoomKey;

        // create the rooms
        jokerCell = new Room("Joker's cell you start here in your cell", "jokerscell.jpg");
        isolationCell = new Room("isolation Cell punishment for bad behavior", "isolationcell.jpg");
        mrFreezerCell = new Room("Mr. Freezer's room, your mate's room", "freezerscell.jpg");
        ratLab= new Room("RatLab you are trapped forever", "ratlabroom.jpg");
        hallWay = new Room("Hallway way to other rooms", "hallway.jpg");
        kitchen = new Room("Kitchen. food is cooked here, your kind of place?", "kitchen.jpg");
        storageRoom = new Room("Storage Room.you can find useful items here", "storageroom.jpg");
        diningHall = new Room("Dinner Hall. you can eat here enjoy!", "dininghall.jpg");
        therapyCenter = new Room("Therapy Center. patient's get therapy here", "therapyroom.jpg");
        watchRoom = new Room("Watch room. guard's are watching becareful", "watchroom.jpg");
        secretRoom = new Room("Secret room. no one knows what goes on here", "secretroom.jpg");
        finalRoom = new Room("Escape", "escaperoom.jpg");

        // create the items
        lockPickTool = new Item("LockPick");
        gun = new Item("Gun");
        claw = new Item("Claw");
        rope = new Item("Rope");
        mrFreezerGun = new Item("Freezergun");
        guardOutFit = new Item("Guardoutfit");
        watchRoomKey = new Item("Watchroomkey");

        //putting items in room
        jokerCell.addItem(lockPickTool);
        watchRoom.addItem(gun);
        watchRoom.addItem(claw);
        storageRoom.addItem(rope);
        storageRoom.addItem(guardOutFit);
        mrFreezerCell.addItem(mrFreezerGun);
        kitchen.addItem(watchRoomKey);

        // initialise room exits
        jokerCell.setExit("south", hallWay);
        isolationCell.setExit("east", mrFreezerCell);
        isolationCell.setExit("north", ratLab);
        mrFreezerCell.setExit("west", isolationCell);
        mrFreezerCell.setExit("south", kitchen);
        hallWay.setExit("north", jokerCell);
        hallWay.setExit("east", kitchen);
        hallWay.setExit("south", diningHall);
        kitchen.setExit("north",mrFreezerCell);
        kitchen.setExit("west",hallWay);
        kitchen.setExit("south",therapyCenter);
        storageRoom.setExit("east", diningHall);
        storageRoom.setExit("south", watchRoom);
        diningHall.setExit("north",hallWay);
        diningHall.setExit("west",storageRoom);
        therapyCenter.setExit("north", kitchen);
        watchRoom.setExit("north",storageRoom);
        watchRoom.setExit("east", secretRoom);
        secretRoom.setExit("west", watchRoom);
        secretRoom.setExit("south", finalRoom);

        currentRoom = jokerCell;  // start game joker's cell
    }

    /**
     * Method getter for currents rooms description
     *
     * @return returns a string which contains current room's description
     */
    public String getCurrentRoomDescription()
    {
        return currentRoom.getLongDescription();
    }

    /**
     * Method getCurrentRoomItems
     *
     * @return an arraylist containing items in current room
     */
    public ArrayList<String> getCurrentRoomItems()
    {
        return currentRoom.getItemsInRoom();
    }
    
    /**
     * Method getInventory
     *
     * @return an arraylist containing items in player's inventory
     */
    public ArrayList<String> getInventory()
    {
        return bag.getInventory();
    } 
    
    /**
     * Method getCurrentImage
     *
     * @return returns name of the image for current room
     */
    public String getCurrentImage()
    {
        return currentRoom.getImage();
    }
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    public String processCommand(Command command) 
    {
        String print;

        if(command.isUnknown()) {
            print = "I don't know what you mean..."; 
            return print;

        }

        String commandWord = command.getCommandWord();
        
        if (commandWord.equals("help")) {
            print = "help";
            return print;
        }
        else if (commandWord.equals("go")) {
            print = goRoom(command);
            return print;
        }
        else if (commandWord.equals("pick")) {
            print = pickItem(command);
            return print;
        }
        else if (commandWord.equals("drop")) {
            print = dropItem(command);
            return print;
        }
        else if (commandWord.equals("inventory")) {
            print = playerInventory();
            return print;
        }
        else if (commandWord.equals("back")){
            print = back(command);
            return print;
        }
        else if (commandWord.equals("quit")) {
            print = quit(command);
            return print;//quit(command);
        }
        else
        {
            return "";
        }
        // else command not recognised.
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    public String[] printHelp() 
    {
        CommandWords c = new CommandWords();
        return c.showAll();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private String goRoom(Command command) 
    {
        boolean endGame = false;
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            return "Go where?";

        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            return "There is no door!";
        }
        else if(nextRoom.getShortDescription().equals("Escape")) //checks if we are in the final room
        {
            Back back = new Back(currentRoom,bag.getStateItems());
            states.push(back);
            currentRoom = nextRoom;
            String s = winConditionCheck();
            return s;
        }
        else
        {
            Back back = new Back(currentRoom,bag.getStateItems());
            states.push(back);
            currentRoom = nextRoom;
            return "";
        }
    }

    /**
     * Method winConditionCheck
     * checks if we have necessary items to win or not, if yes player wins the game if not , lost
     */
    private String  winConditionCheck()
    {
        Win win = new Win();
        Collections.sort(bag.getInventory());
        if(win.compareArrayList(bag.getInventory()))
        {
            return "won";
        }
        else
        {
            return "lost";
        }

    }

    /**
     * Method pickItem try to pick up an item from the room to player's inventory
     *
     * @param command Command give us the item we need to pick up
     */
    private String pickItem(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to pick...
            return "pick what?";
        }

        if(bag.getSize() >=3)
        {
            return "You have reached maximum limit to store items, to pick up this item, remove an exisiting item from your inventory.";

        }
        else
        {
            String item = command.getSecondWord();
            bag.addItem(item);
            currentRoom.removeItem(item);
            return "";
        }
    }

    /**
     * Method dropItem try to drop an item from the player's inventory to the room 
     *
     * @param command Command give us the item we need to drop
     */
    private String dropItem(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            return "drop what?";

        }

        if(currentRoom.getSize() >=3)
        {
            return "Room already has at maximum capacity, you cannot drop this item here.";
        }
        else
        {
            String item = command.getSecondWord();
            bag.removeItem(item);

            currentRoom.addItemByName(item);

            return "";
        }
    }

    /**
     * Method playerInventory
     * prints the items found in player's inventory
     */
    private String playerInventory()
    {
        return "inventory";
    }

    /**
     * Method back Take the player to it's previous state of room and item
     *
     * @param command A parameter
     */
    private String back(Command command)
    {
        if(command.hasSecondWord()) {
            return "back what?";
        }

        if(states.empty()) // checks if the stack is empty 
        {
            return "Can't go back, you just started.";
        }
        else
        {
            Back previousState = states.pop();
            if(bag.getSize() > previousState.getPrevSize()) //checks if player picked any item from room, if yes put it back to room before back command
            {
                for(int i = bag.getSize() ; i> previousState.getPrevSize() ; i--)
                {
                    currentRoom.addItem(new Item(bag.getByindex(i-1)));
                }
            }
            else if(bag.getSize() < previousState.getPrevSize()) //checks if player dropped any item from room, if yes remove it from room before back command
            {
                for(int i= previousState.getPrevSize(); i > bag.getSize()  ; i--)
                {
                    currentRoom.removeItem(bag.getByindex(i-1));
                }
            }

            currentRoom = previousState.getRoom(); 
            bag.previousItems(previousState.getItemsList());
            return "";
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private String quit(Command command) 
    {
        if(command.hasSecondWord()) {
            return "Quit what?";
        }
        else {
            return "quit";  // signal that we want to quit
        }
    }
}
