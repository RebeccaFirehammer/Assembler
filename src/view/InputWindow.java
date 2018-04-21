package view;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Translator;
 
@SuppressWarnings("serial")
public class InputWindow extends JPanel implements ActionListener {
	Translator translator;
    static private final String newline = "\n";
    JButton openButton;
    JTextArea log;
    JFileChooser fc;
    FileNameExtensionFilter fileFilter;
 
    public InputWindow() {
        super(new BorderLayout());
 
        //Create the log first, because the action listeners need to refer to it.
        log = new JTextArea(20,40);
        log.setMargin(new Insets(20,20,20,20));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
 
        //Create a file chooser
        UIManager.put("FileChooser.readOnly", Boolean.TRUE);
        fc = new JFileChooser();
        fileFilter = new FileNameExtensionFilter("txt", "txt");
        fc.setFileFilter(fileFilter);
 
        //Create the open button.
        openButton = new JButton("Open a File...");
        openButton.addActionListener(this);
 
        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(openButton);
 
        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }
 
    public void actionPerformed(ActionEvent e) {
 
        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(InputWindow.this);
 
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                log.append("Opening: " + file.getName() + "." + newline);
                try {
					translator = new Translator(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
 
        }
    }
 
}
