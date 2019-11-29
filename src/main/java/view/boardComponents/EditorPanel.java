package view.boardComponents;

import annotations.ClassAnnotation;

import view.containers.LogPanel;
import view.frames.AddCardFrame;
import view.frames.AddColumnFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.GroupLayout.Alignment.*;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "15/11/2019",
        lastEdit = "22/11/2019"
)
/**
 * The editor panel is a panel which makes all the buttons and user commands directly available
 * on the right side of the kanban board.
 * From the editor panel, we can add and remove columns and cards, as well as see the recent activities log
 * and the board versions history log.
 */

public class EditorPanel extends JPanel {

    BoardPanel currentPanel;

    public EditorPanel(BoardPanel currentPanel) {
        this.currentPanel = currentPanel;
        initialiseEditorPanel();
    }

    /**
     * Initialise the Editor Panel - set up its different components :
     * Title, user commands, log panel and exit button.
     */
    private void initialiseEditorPanel() {

        setBackground(new java.awt.Color(26, 58, 161));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(new Insets(10,10,10,10)));

        // Editor title
        JLabel title = createLabel("EDITOR PANEL");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(title);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(new JSeparator());

        // New group layout to display user commands
        add(createCommandsLayout());
        add(new JSeparator());

        // Add edit buttons
        add(createEditPanel());
        add(Box.createRigidArea(new Dimension(0, 10)));

        // Tabbed pane for activity log and versions history
        add(new LogPanel());
        add(new JSeparator());

        // Create an exit button
        add(createExitButton());
    }

    /**
     * Create a group layout containing the user commands as buttons.
     * Add and Remove buttons, as well as a descriptive label.
     * @return pane containing the group layout
     */
    private JPanel createCommandsLayout() {

        // Create buttons
        JButton addCardButton = createAddCardButton();
        JButton removeCardButton =  createRemoveCardButton();
        JButton addColumnButton = createAddColumnButton();
        JButton removeColumnButton =  createRemoveColumnButton();

        // Create labels
        JLabel addCardLabel = createLabel("Insert card");
        JLabel removeCardLabel = createLabel("Delete card");
        JLabel addColumnLabel = createLabel("Insert column");
        JLabel removeColumnLabel = createLabel("Delete column");

        // Set up panel and group layout
        JPanel pane = new JPanel();
        pane.setOpaque(false);
        GroupLayout group = new GroupLayout(pane);
        pane.setLayout(group);
        group.setAutoCreateGaps(true);
        group.setAutoCreateContainerGaps(true);

        // First, horizontal grouping
        group.setHorizontalGroup(
                group.createSequentialGroup()
                        .addGroup(group.createParallelGroup(LEADING)
                                .addComponent(addCardButton).addComponent(removeCardButton)
                                .addComponent(addColumnButton).addComponent(removeColumnButton))
                        .addGroup(group.createParallelGroup()
                                .addComponent(addCardLabel).addComponent(removeCardLabel)
                                .addComponent(addColumnLabel).addComponent(removeColumnLabel))
        );


        // Second, vertical grouping
        group.setVerticalGroup(
                group.createSequentialGroup()
                        .addGroup(group.createParallelGroup(BASELINE)
                                .addComponent(addCardButton).addComponent(addCardLabel))
                        .addGroup(group.createParallelGroup(BASELINE)
                                .addComponent(removeCardButton).addComponent(removeCardLabel))
                        .addGroup(group.createParallelGroup(BASELINE)
                                .addComponent(addColumnButton).addComponent(addColumnLabel))
                        .addGroup(group.createParallelGroup(BASELINE)
                                .addComponent(removeColumnButton).addComponent(removeColumnLabel))
                        .addGap(10)
        );

        group.linkSize(SwingConstants.HORIZONTAL, addCardButton, removeCardButton, addColumnButton, removeColumnButton);

        return pane;
    }

    private JButton createAddCardButton(){
        JButton addCardButton = createButton(" + ");
        addCardButton.addActionListener(e->new AddCardFrame(currentPanel).setVisible(true));
        return addCardButton;
    }

    private JButton createRemoveCardButton(){
        JButton removeCardButton =  createButton(" - ");
        removeCardButton.setActionCommand("removeCard");
        return removeCardButton;
    }

    private JButton createAddColumnButton(){
        JButton addColumnButton = createButton(" + ");
        addColumnButton.addActionListener(e->new AddColumnFrame(currentPanel).setVisible(true));
        return addColumnButton;
    }

    private JButton createRemoveColumnButton(){
        JButton removeColumnButton =  createButton(" - ");
        removeColumnButton.setActionCommand("removeColumn");
        return removeColumnButton;
    }


    private JPanel createEditPanel(){
        JPanel editPanel = new JPanel();
        editPanel.setOpaque(false);

        JButton editCardButton = createButton("Edit selected card");
        JButton editColumnButton = createButton("Edit selected column");
        editCardButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        editColumnButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        editPanel.add(editCardButton);
        editPanel.add(editColumnButton);

        return editPanel;
    }


    /**
     * Method for uniform labels creation.
     * Set a defined font colour.
     * @param labelName text on label
     * @return label
     */
    private JLabel createLabel(String labelName) {
        JLabel label = new JLabel(labelName);
        label.setForeground(Color.lightGray);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    /**
     * Method for uniform buttons creation.
     * Set a defined colour for font and background.
     * @param buttonName Text on button
     * @return button
     */
    private JButton createButton(String buttonName) {

        JButton button = new JButton(buttonName);
        // Modify font : button.setFont(Font.getFont("arial"));
        button.setBackground(new java.awt.Color(21, 34, 59));
        button.setForeground(Color.lightGray);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBorderPainted(false);
        button.addActionListener(new itemInteraction());
        return button;
    }

    /**
     * Create a red exit button added at the bottom of the editor panel.
     * If clicked, the application is closed.
     * @return exit button
     */
    private JButton createExitButton() {

        JButton exitButton = new JButton("Exit Application");
        exitButton.setToolTipText("Quit Indigo-Kanban?");
        exitButton.addActionListener(e->System.exit(0));
        exitButton.setBackground(new java.awt.Color(250, 105, 128));
        exitButton.setBorderPainted(false);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        return exitButton;

    }


    /**
     * Class for button event handling.
     * It allows addition and deletion of items (cards or columns) on the board.
     */


    class itemInteraction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            /*
            if (event.getActionCommand().equals("addColumn")) {
                currentPanel.addColumn(new KanbanColumn("Test Column", ColumnRole.IN_PROGRESS));
            }
            else if(event.getActionCommand().equals("removeColumn")) {
                // implement here
            }
            else if(event.getActionCommand().equals("addCard")) {
                // implement here
            }
            else if(event.getActionCommand().equals("removeCard")) {
                // implement here
            }
            else {
                JOptionPane.showMessageDialog(null, "Command Not Found", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            */
        }
    }
}


