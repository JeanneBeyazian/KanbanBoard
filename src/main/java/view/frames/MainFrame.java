package view.frames;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import view.KanbanBoard;
import controller.Load;

public class MainFrame extends JFrame {

    private JPanel container;

    public MainFrame() {
        setTitle("Welcome to Kanban Board Indigo");
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initialise();
        pack();
    }

    private void initialise() {

        container = new JPanel();

        JLabel welcomeLabel = new JLabel("Welcome to the Kanban Board Indigo App !");

        //JPanel buttonPanel = new JPanel();
        //buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

        JButton newBoard = new JButton("Create new board");
        newBoard.addActionListener(e-> new CreateFrame().setVisible(true));
        //buttonPanel.add(newBoard);

        //buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        JButton open = new JButton("Open existing board");
        open.addActionListener(e-> showChooser());
        //buttonPanel.add(open);

        container.add(new JLabel(new ImageIcon("src/images/kanban_logo.png")));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(welcomeLabel);
        panel.add(newBoard);
        panel.add(open);
        container.add(new JSeparator());
        container.add(panel);


        add(container);

        pack();

        setResizable(true);

    }

    public void showChooser() {
        JFileChooser chooser = new JFileChooser();

        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("json file (*.json)","json"));
        chooser.setAcceptAllFileFilterUsed(true);

        int response = chooser.showOpenDialog(this);
        String openBoardName = "";
        if(response == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            openBoardName = file.getName().substring(0, file.getName().length() - 5);
        }


        if(!openBoardName.equals("")) {
            new KanbanBoard(openBoardName).setVisible(true);
            KanbanBoard.openBoard(Load.loadBoard(openBoardName));
        }
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}
