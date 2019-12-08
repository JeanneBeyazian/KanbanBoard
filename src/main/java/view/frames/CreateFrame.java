package view.frames;

import view.KanbanBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateFrame extends JFrame  implements ActionListener{

    private static String fileName;

    public CreateFrame() {
        JPanel panel = new JPanel();
        JLabel choose = new JLabel("Choose new board name");
        JTextField tField = new JTextField(30);
        JButton submit = new JButton("Enter");

        setSize(400,150);
        panel.add(choose);
        panel.add(tField);
        tField.addActionListener(e->fileName = tField.getText());
        panel.add(submit);
        submit.addActionListener(this);
        add(panel);
    }

    public void actionPerformed(ActionEvent event) {
        new KanbanBoard(fileName).setVisible(true);
        this.dispose();
    }

}
