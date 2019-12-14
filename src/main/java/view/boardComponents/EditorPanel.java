package view.boardComponents;

import annotations.ClassAnnotation;

import view.containers.LogPanel;
import view.frames.editBoardFrames.AddCardFrame;
import view.frames.editBoardFrames.AddColumnFrame;
import view.frames.editBoardFrames.RemoveCardFrame;
import view.frames.editBoardFrames.RemoveColumnFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.OptionPanes.errorPane;
import static javax.swing.GroupLayout.Alignment.*;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "15/11/2019",
        lastEdit = "10/12/2019"
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
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(createLabel("EDITOR PANEL"));
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(new JSeparator());

        // New group layout to display user commands
        add(createCommandsLayout());
        add(new JSeparator());

        // WIP limit
        add(createWIPLimitBox());
        add(new JSeparator());

        // Activity log
        add(createLabel("Activity Log"));
        add(new LogPanel());
        add(new JSeparator());

        add(createClearButton());
        // Create an exit button
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(createExitButton());
    }

    /**
     * Create a group layout containing the user commands as buttons.
     * Add and Remove buttons, as well as a descriptive label.
     * @return pane containing the group layout
     */
    private JPanel createCommandsLayout() {

        // Create buttons
        JButton addCardButton = createButton(" + ");
        addCardButton.addActionListener(e->new AddCardFrame(currentPanel).setVisible(true));
        addCardButton.setName("addCardButton");

        JButton removeCardButton =  createButton(" - ");
        removeCardButton.addActionListener(e->new RemoveCardFrame(currentPanel).setVisible(true));
        removeCardButton.setName("removeCardButton");

        JButton addColumnButton = createButton(" + ");
        addColumnButton.addActionListener(e->new AddColumnFrame(currentPanel).setVisible(true));
        addColumnButton.setName("addColumnButton");

        JButton removeColumnButton =  createButton(" - ");
        removeColumnButton.addActionListener(e->new RemoveColumnFrame(currentPanel).setVisible(true));
        removeColumnButton.setName("removeColumnButton");

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


    /**
     * Method for uniform labels creation. Set a defined font colour.
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
     * Method for uniform buttons creation. Set a defined colour for font and background.
     * @param buttonName Text on button
     * @return button
     */
    private JButton createButton(String buttonName) {

        JButton button = new JButton(buttonName);
        button.setBackground(new java.awt.Color(21, 34, 59));
        button.setForeground(Color.lightGray);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBorderPainted(false);

        return button;
    }

    private JPanel createWIPLimitBox() {

        JPanel wipPanel = new JPanel();
        wipPanel.setOpaque(false);

        // Components for wipPanel
        JLabel wipLabel = createLabel( "Work In Progress Limit: ");
        JComboBox<Integer> wipBox = new JComboBox<Integer>();
        ActionListener wipAction = (e-> {
                int selectedLimit = wipBox.getSelectedIndex();
                if (selectedLimit < currentPanel.getWIPcount()) {
                    errorPane("The entered WIP limit is lower than the current WIP count : " +
                                    currentPanel.getWIPcount() + ".", "WIP Limit Too Low");
                    return;
                }
                currentPanel.setWIPlimit(selectedLimit); });
        wipBox.addActionListener(wipAction);

        // Making of wipBox
        int max = 500;
        for (int i = 0; i <= max; i++) wipBox.addItem(i);
        wipBox.setSelectedIndex(50); // Default

        wipPanel.add(wipLabel);
        wipPanel.add(wipBox);

        return wipPanel;
    }

    /**
     * @return button to close the application
     */
    private JButton createExitButton() {

        JButton exitButton = createButton("Exit Application");
        exitButton.setName("exitButton");
        exitButton.setToolTipText("Quit Indigo-Kanban?");
        exitButton.addActionListener(e->System.exit(0));
        exitButton.setBackground(new java.awt.Color(250, 105, 128));

        return exitButton;

    }


    /**
     * @return button to clear the board : remove all columns
     */
    private JButton createClearButton() {

        JButton clear = createButton("Clear board");
        clear.setName("clearButton");
        clear.setToolTipText("Remove everything from your board?");
        clear.addActionListener(e->currentPanel.clearBoard());
        clear.setBackground(new java.awt.Color(142, 140, 250));

        return clear;

    }

}


