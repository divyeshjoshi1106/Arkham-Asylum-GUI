import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;

/**
 * This is a test GUI class , this will used to implement GUI for the game Joker's Great Escape
 *
 * @author (Divyesh Joshi & Hussam Kayed)
 * @version (6.1.20)
 */

public class GUI extends JFrame
{
    private JPanel panel,panel1,panel2,panel3;
    private ImageIcon image1;
    private JTextArea info; 
    private JTextField commandField; 
    private Game game;
    private Image image; 
    private JLabel ilabel; 
    private Image scaledImage;
    private JButton submit; 
    //private JButton clear; 

    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        game = new Game();
        add(setUpPanel());
        setTitle("Joker's Great Escape");
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent ev)
                {
                    setVisible(false);
                }
            });
        pack();

        info.append("Welcome to the Arkham Asylum!\n");
        info.append("Arkham Asylum, incredibly interesting adventure game.\n");
        info.append("Your goal is to collect item which will help you in escaping\n");
        info.append("You have to collect 3 items\n");
        info.append("Two items are of weapons and one is to diguise yourself to fool the guards\n");
        info.append("Good Luck.\n");
        info.append("Type 'help' if you need help.\n");
        displayRoomDetails();
        getRootPane().setDefaultButton(submit);
        setVisible(true);

    }

    private Container setupInfoArea() {
        // Set up the area where text will be displayed.
        info = new JTextArea(10,50);
        info.setEditable(false);
        //info.setLineWrap(true);

        JScrollPane scroll =
            new JScrollPane(info,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener(){
                public void adjustmentValueChanged(AdjustmentEvent e){
                    info.select(info.getHeight()+100000,0);
                }});
        panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.add(scroll, BorderLayout.CENTER);

        return panel3;
    }

    private Container setUpUserInput()
    {
        Box commandLabel = Box.createHorizontalBox();
        commandLabel.add(new JLabel("Enter command",JLabel.LEFT));
        commandLabel.add(Box.createGlue());
        commandField = new JTextField(50);
        Box commandArea = Box.createVerticalBox();
        commandArea.add(commandLabel);
        commandArea.add(commandField);

        submit = new JButton("OK");
        //clear = new JButton("Clear");

        submit.addActionListener(e->{
                //info.append(commandField.getText()+"\n");
                Parser p = new Parser(commandField.getText());
                Command command = p.getCommand();
                //boolean finished = game.processCommand(command);
                String s = game.processCommand(command);
                if(s.equals("")){
                    setImage(game.getCurrentImage());
                    displayRoomDetails();
                }
                else if(s.equals("won"))
                {
                    setImage(game.getCurrentImage());
                    info.append("Congratulations, you won...");
                    disableButtons();
                }
                else if(s.equals("lost"))
                {
                    setImage("lost.jpg");
                    info.append("Batman caught you, you lost...");
                    disableButtons();
                }
                else if(s.equals("inventory"))
                {
                    displayInventory();
                    displayRoomDetails();
                }
                else if(s.equals("help"))
                {
                    info.append("You are Joker. You are trapped. You try to escape \n the Asylum. \n Your command words are: ");
                    String[] help = game.printHelp();
                    for(int i=0; i < help.length; i++)
                    {
                        info.append(help[i]+" ");
                    }
                    info.append("\n");
                }
                else if(s.equals("quit"))
                {
                    info.append(s+"\n");
                    disableButtons();
                    setVisible(false);
                }
                else
                {
                    info.append(s+"\n");
                    
                }
                commandField.setText("");
            }
        );
        //clear.addActionListener(e->{commandField.setText("");});

        panel2 = new JPanel();
        panel2.add(commandArea,BorderLayout.NORTH);
        panel2.add(submit, BorderLayout.SOUTH);
        //panel2.add(clear);
        return panel2;
    }

    private Container setUpImage()
    {
        image = null;
        ilabel =  new JLabel();
        ilabel.setSize(600,490);
        setImage(game.getCurrentImage());

        panel1 = new JPanel();
        panel1.add(ilabel, BorderLayout.CENTER);

        return panel1;
    }

    private Container setUpPanel()
    {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(setUpImage(), BorderLayout.NORTH);
        panel.add(setUpUserInput(), BorderLayout.CENTER);
        panel.add(setupInfoArea(), BorderLayout.SOUTH);
        return panel;
    }

    private void setImage(String s)
    {
        try
        {image = ImageIO.read(GUI.class.getResource("/resources/"+s));}
        catch(IOException ex)
        {ex.printStackTrace();}

        scaledImage = image.getScaledInstance(ilabel.getWidth(), ilabel.getHeight(), Image.SCALE_SMOOTH);

        image1 = new ImageIcon(scaledImage);
        ilabel.setIcon(image1);
    }

    private void displayRoomDetails()
    {
        ArrayList<String> itemsRoom = game.getCurrentRoomItems();
        info.append("Items found in Room:\n");
        for(String st : itemsRoom)
        {
            info.append(st+"\n");
        }
        info.append(game.getCurrentRoomDescription()+"\n");
    }

    private void displayInventory()
    {
        ArrayList<String> items = game.getInventory();
        info.append("Items found in Room:\n");
        for(String it : items)
        {
            info.append(it+"\n");
        }
        info.append(game.getCurrentRoomDescription()+"\n");
    }

    private void disableButtons()
    {
        submit.setEnabled(false);
        commandField.setEnabled(false);
        //clear.setEnabled(false);
    }
}
