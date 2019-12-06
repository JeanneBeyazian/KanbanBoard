package view.frames;

import view.KanbanBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateFrame extends JFrame  {

    private String fileName;

    public CreateFrame() {
        JPanel panel = new JPanel();
        JLabel choose = new JLabel("Choose new board name");
        JTextField tField = new JTextField(30);
        JButton submit = new JButton("Enter");

        setSize(400,150);
        panel.add(choose);
        panel.add(tField);
        tField.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                fileName = tField.getText();
            }
        });
        panel.add(submit);
        submit.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 KanbanBoard board = new KanbanBoard(fileName);
                 board.setVisible(true);
             }
        });
        add(panel);
    }

//    public static void main(String[] args) {
//
//        CreateFrame frame = new CreateFrame();
//        frame.setVisible(true);
//    }

}
