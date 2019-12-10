package view.frames;

import view.KanbanBoard;

import javax.swing.*;

import controller.Load;
import view.boardComponents.BoardPanel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class OpenFrame extends JFrame  implements ActionListener{

	private static final long serialVersionUID = 1L;
	JTextField nameField = new JTextField(30);

    public OpenFrame() {

//        JPanel panel = new JPanel();
//        JLabel choose = new JLabel("Enter Board Name");


//          JButton submit = new JButton("Enter");
//          JFileChooser chooser = new JFileChooser();
//          chooser.setCurrentDirectory(new java.io.File("/load.java"));
//          chooser.setDialogTitle("Choose a file");
//          chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        JTextField tf = new JTextField();
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String fileName = f.getAbsolutePath();
        tf.setText(fileName);



          //        setSize(400,150);
//        panel.add(choose);
//        panel.add(nameField);
//        panel.add(submit);
//        submit.addActionListener(this);
//        add(panel);
    }

    /**
     *
     * @param event
     */
    public void actionPerformed(ActionEvent event) {

        this.dispose();

        String openBoardName = "";
        if (!nameField.getText().isEmpty())  openBoardName = nameField.getText();
        else openBoardName = "Unnamed Kanban Board";

        new KanbanBoard(openBoardName).setVisible(true);
        KanbanBoard.openBoard(Load.loadBoard(openBoardName));
    }

}



