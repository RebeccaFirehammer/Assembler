package main;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import view.InputWindow;
/*Assembler: 
 * This project uses a GUI upload box to receive a text file containing assembly instructions. 
 * It translates the assembly instructions to op code instructions
 * then outputs a text file containing the op code instructions.
 * */
public class Launcher {
	InputWindow inputWindow = new InputWindow();
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Assembly Uploader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add content to the window.
        frame.add(new InputWindow());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                createAndShowGUI();
            }
        });
    }
}
