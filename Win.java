import java.util.ArrayList;
/**
 * This is the class of the "Joker's Great Escape" game. This is a very interesting game
 * which envolves the escape of the joker from an asylum in gotham city called "The Arkham Asylum".
 * THe win condition for the game is stored here.
 * @author (Divyesh Joshi and Hussam Kayed)
 * @version (04.05.2020)
 */
public class Win
{
    // instance variables 
    private ArrayList<String> condition;

    /**
     * Constructor for objects of class Win
     */
    public Win()
    {
        condition = new ArrayList<>();
        
        condition.add("Freezergun");
        condition.add("Guardoutfit");
        condition.add("Gun");
    }

    /**
     * Method compareArrayList
     *
     * @param items A parameter of ArrayList<String> type
     * @return returns true if two ArrayList matches otherwise false
     */
    public boolean compareArrayList(ArrayList<String> items)
    {
        return condition.equals(items);
    }
}
