package view.frames.popUpFrames;

import annotations.ClassAnnotation;

import javax.swing.*;
import java.awt.*;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "",
        creationDate = "09/12/2019",
        lastEdit = "15/12/2019"
)

/**
 * Abstract class for pop up frames used in the application : warnings, help frames.
 */
public abstract class PopUpFrames extends JDialog {

    // Frame components
    protected JPanel containerPanel;
    protected JPanel buttonPanel;

    // Pre-defined size
    private static final int WIDTH = 650;
    private static final int HEIGHT = 300;

    public PopUpFrames(String title) {

        setTitle(title);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.add(createText());

        add(containerPanel);
        initialise();
    }


    /**
     * Set up the frame and its components
     */
    private void initialise() {

        buttonPanel = new JPanel();

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(createGoBackButton());
        buttonPanel.add(Box.createRigidArea(new Dimension(30, 0)));

        add(buttonPanel, BorderLayout.SOUTH);
    }


    /** Abstract method that sets up the text displayed on the pop-up frame */
    protected abstract JTextArea createText();

    /**
     * Create a 'Go Back' button to dispose the frame
     * @return JButton 'Go back'
     */
    protected JButton createGoBackButton(){

        JButton cancel = new JButton("Go back");
        cancel.addActionListener(e->this.dispose());
        cancel.setBorderPainted(false);
        cancel.setAlignmentX(Component.CENTER_ALIGNMENT);

        return cancel;
    }


}
