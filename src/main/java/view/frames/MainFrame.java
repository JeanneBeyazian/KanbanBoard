package view.frames;

import controller.Load;
import view.KanbanBoard;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class MainFrame extends JFrame {

    private JPanel container;

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

    private void initialise() {

        container = new JPanel();
        container.setBackground(Color.black);

        // Buttons
        JButton newBoard = createButton("Create new board");
        JButton open = createButton("Open existing board");
        // Buttons action listeners
        newBoard.addActionListener(e-> new CreateFrame().setVisible(true));
        open.addActionListener(e-> showChooser());

        // Right side
        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(newBoard);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(open);

        JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
        sep.setBackground(Color.white);
        sep.setPreferredSize(new Dimension(1,120));

        container.add(new JLabel(new ImageIcon("src/images/kanban_logo.png")));
        container.add(sep);
        container.add(Box.createRigidArea(new Dimension(10, 0)));;
        container.add(rightPanel);

        add(container);
        //pack();

    }

    private void showChooser() {

        JFileChooser chooser = new JFileChooser();

        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("json file (*.json)", "json"));
        chooser.setAcceptAllFileFilterUsed(true);

        int response = chooser.showOpenDialog(this);

        if(response == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();

            if (file != null) {
                String openBoard = file.getName().substring(0, file.getName().length() - 5);
                new KanbanBoard(openBoard).setVisible(true);
                KanbanBoard.openBoard(Load.loadBoard(openBoard));
                dispose();
            }
        }
    }

    private JButton createButton(String buttonName) {

        JButton button = new JButton(buttonName);
        button.setBackground(new java.awt.Color(21, 34, 59));
        button.setForeground(Color.lightGray);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(210,30));
        button.setBorderPainted(false);

        return button;
    }


    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}
