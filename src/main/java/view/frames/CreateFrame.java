package view.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateFrame extends JFrame  {

    public CreateFrame() {
        JPanel panel = new JPanel();
        JLabel choose = new JLabel("Choose new file name");
        JTextField tField = new JTextField(30);
        JButton submit = new JButton("Submit");

        setSize(400,200);
        panel.add(choose);
        panel.add(tField);
        tField.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                String in = tField.getText();
            }
        });
        panel.add(submit);
        submit.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                
             }
        });
        add(panel);
    }

}
