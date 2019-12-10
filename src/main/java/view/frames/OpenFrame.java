package view.frames;

import view.KanbanBoard;

import javax.swing.*;

import controller.Load;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OpenFrame extends JFrame  implements ActionListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String fileName;
	JTextField tField = new JTextField(30);

    public OpenFrame() {
        JPanel panel = new JPanel();
        JLabel choose = new JLabel("Enter Board Name");
        JButton submit = new JButton("Enter");

        setSize(400,150);
        panel.add(choose);
        panel.add(tField);
        panel.add(submit);
        submit.addActionListener(this);
        add(panel);
    }
    
    public void actionPerformed(ActionEvent event) {
    	new KanbanBoard(tField.getText()).setVisible(true);
    	KanbanBoard.openBoard(Load.loadBoard(tField.getText()));
        this.dispose();
    }

}



