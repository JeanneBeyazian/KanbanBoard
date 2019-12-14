package view.frames;

import annotations.ClassAnnotation;
import controller.Load;
import view.KanbanBoard;
import view.containers.OpenFileChooser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

@ClassAnnotation(
        classAuthors = {"Nathan, Jeanne"},
        creationDate = "08/12/2019",
        lastEdit = "12/12/2019"
)
public class MainFrame extends JFrame {

    private JPanel mainContainer;
    private JPanel bottomContainer;
    private JPanel buttonsPanel;

    public MainFrame() {

        // PLEASE KEEP THIS.

//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//        }

        setTitle("Welcome to Kanban Board Indigo");
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initialise();
    }


    /**
     * Setting up of the mainContainer which assembles each part of the frame
     */
    private void initialise() {

        mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setBackground(Color.black);

        bottomContainer = makeBottomPanel();
        bottomContainer.setVisible(false);
        JPanel topContainer = makeTopPanel();

        mainContainer.add(topContainer);
        mainContainer.add(bottomContainer);
        add(mainContainer);

        setVisible(true);

    }


    /**
     * Bottom container appears when the user wants to create a new board.
     * Asks for an input name.
     * @return bottomContainer
     */
    private JPanel makeBottomPanel() {

        JPanel bottomContainer = new JPanel(new GridBagLayout());
        bottomContainer.setOpaque(false);

        // Create components for bottom container
        JTextField nameField = new JTextField(30);
        nameField.setName("nameField");
        JLabel nameLabel = new JLabel("Enter a name for your board : ");
        nameLabel.setName("nameLabel");
        nameLabel.setForeground(Color.lightGray);

        JButton submit = createButton("Create");
        submit.setName("submitButton");
        submit.setBackground(new java.awt.Color(133, 113, 240));
        submit.addActionListener(e->{
            new KanbanBoard(nameField.getText()).setVisible(true);
            dispose();
        });


        // Set up Layout and ad components
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 0;
        bottomContainer.add(nameLabel, constraints);
        constraints.gridy = 1;
        bottomContainer.add(nameField,constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.EAST;
        bottomContainer.add(submit,constraints);

        return bottomContainer;
    }

    /**
     * The top container contains the logo, and the buttons to create or open a file.
     * @return topContainer
     */
    private JPanel makeTopPanel(){

        JPanel topContainer = new JPanel();
        topContainer.setOpaque(false);

        // Buttons
        JButton newBoard = createButton("Create new board");
        newBoard.setName("newButton");
        JButton open = createButton("Open existing board");
        open.setName("openButton");
        // Buttons action listeners
        newBoard.addActionListener(e->bottomContainer.setVisible(true));
        open.addActionListener(e->{
            new OpenFileChooser();
            if(Frame.getFrames().length>2)dispose();
        });

        // Right side : Buttons
        buttonsPanel= new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(newBoard);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonsPanel.add(open);

        // Decorative separator
        JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
        sep.setBackground(Color.white);
        sep.setPreferredSize(new Dimension(1,120));

        // Add left side (logo) and right side (buttons) to container
        topContainer.add(new JLabel(new ImageIcon("src/images/kanban_logo.png")));
        topContainer.add(sep);
        topContainer.add(Box.createRigidArea(new Dimension(10, 0)));;
        topContainer.add(buttonsPanel);

        return topContainer;
    }


    /**
     * Create and set up uniform buttons
     * @param buttonName (String)
     * @return button
     */
    private JButton createButton(String buttonName) {

        JButton button = new JButton(buttonName);
        button.setBackground(new java.awt.Color(21, 34, 59));
        button.setForeground(Color.lightGray);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(210,30));
        button.setBorderPainted(false);

        return button;
    }

    /**
     * @return bottom container
     */
    public JPanel getBottomContainer(){return bottomContainer;}

    /**
     * @return buttons panel (right side)
     */
    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}
