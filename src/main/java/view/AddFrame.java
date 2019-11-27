package view;
import annotations.ClassAnnotation;
import javax.swing.*;
import java.awt.*;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "26/11/2019",
        lastEdit = "26/11/2019"
)

abstract public class AddFrame extends JFrame {

    protected JPanel container;
    protected static JButton CANCEL = new JButton("Cancel");
    protected static JButton SUBMIT = new JButton("Submit");
    protected JTextField titleField;
    protected JLabel title = new JLabel("Enter a title: ");


    public AddFrame(String type) {
        titleField = new JTextField(20);
        initialise(type);
    }

    protected void initialise(String type) {

        container = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        container.add(title, constraints);

        constraints.gridx = 1;
        container.add(titleField, constraints);


        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        container.add(SUBMIT, constraints);
        constraints.anchor = GridBagConstraints.EAST;
        container.add(CANCEL,constraints);

        container.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                ("Adding a new " + type + " to the board")));
        add(container);

        pack();
        setLocationRelativeTo(null);
    }


}
