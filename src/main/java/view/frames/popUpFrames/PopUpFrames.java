package view.frames.popUpFrames;

import annotations.ClassAnnotation;

import javax.swing.*;
import java.awt.*;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "09/12/2019",
        lastEdit = "09/12/2019"
)

/**
 * Abstract class for pop up frames used in the application : warnings, help frames.
 */
public abstract class PopUpFrames extends JDialog {

    protected JPanel containerPanel;
    protected JPanel buttonPanel;

    private static final int WIDTH = 650;
    private static final int HEIGHT = 300;

    public PopUpFrames(String title) {

        setTitle(title);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.add(createText());

        add(containerPanel);
        iniatilise();
    }


    private void iniatilise() {

        buttonPanel = new JPanel();

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(createGoBackButton());
        buttonPanel.add(Box.createRigidArea(new Dimension(30, 0)));

        add(buttonPanel, BorderLayout.SOUTH);
    }


    protected abstract JTextArea createText();

    protected JButton createGoBackButton(){

        JButton cancel = new JButton("Go back");
        cancel.addActionListener(e->this.dispose());
        //cancel.setBackground(new java.awt.Color(212, 171, 168));
        cancel.setBorderPainted(false);
        cancel.setAlignmentX(Component.CENTER_ALIGNMENT);

        return cancel;
    }


}
