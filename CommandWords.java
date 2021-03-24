/**
 * This class is part of the "Joker's Great Escape" application. 
 * "Joker's Great Escape" is a very simple and interesting, text based adventure game. 
 * 
 * This class is responsible for checking whether the first word in the user input was a command
 * word or an unknown word. Once done, the method returns either the corresponding word to that 
 * command or returns a String that shows that this word is an unknown word
 *
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Divyesh Joshi and Hussam Kayed
 * @version 27/04/2020
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "go", "quit", "help", "pick", "drop", "inventory", "back"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Print all valid commands to System.out.
     */
    public String[] showAll() 
    {
        return validCommands;
    }
}
