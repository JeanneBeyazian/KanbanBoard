package view;

import javax.swing.*;

/**
 * This class is responsible for the creation of kanban cards.
 * Each class contains an id, a title, a description and story points.
 * Cards are inserted in to KanbanColumns.
 */
public class KanbanCard extends JFrame {

    // TODO : Give a unique ID to each card
    // TODO : Fix the group layout - or change to a new type of layout

    private static int id;
    private String description;
    private int storyPoints;
    private String title;

    public KanbanCard(String name, String description, int storyPoints) {

        super(name);
        JLabel titleLabel = new JLabel(name);
        JTextArea info = new JTextArea(description);
        JLabel points = new JLabel(String.valueOf(storyPoints));
//        JButton delete = new JButton("delete");
//        delete.setBounds(1000,10,100,100);
        setSize(600, 600);
        setLayout(new FlowLayout());
        JPanel panel = new JPanel();
        panel.setSize(600,550);
        add(panel);
        JButton delete = new JButton("delete");
        delete.setBounds(5,5,5,5);
        panel.add(delete);
        panel.add(titleLabel);
        panel.add(info);
//        panel.setLayout(null);
//        panel.add(titleLabel);
//        panel.add(delete);
        setLayout(new FlowLayout());
        //add(titleLabel);
        //add(info);
        //add(delete);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        getContentPane().setBackground(Color.BLUE);
/**
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setVerticalGroup(layout.createSequentialGroup()
                        .addComponent(title)
                        .addComponent(info)
                        .addComponent(points)
        );*/

        add(new JLabel("This is a card"));

    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public void setDescription(String newDes) {
        description = newDes;
    }

    public void setPoint(int newPoint) {
        storyPoints = newPoint;
    }
}
