package view;

import annotations.ClassAnnotation;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.*;

/**
 * This class is responsible for the creation of kanban cards.
 * Each class contains an id, a title, a description and story points.
 * Cards are inserted in to KanbanColumns.
 */
@ClassAnnotation(
        classAuthors = {"Jeanne, Nathan"},
        creationDate = "13/11/2019",
        lastEdit = "22/11/2019"
)
public class KanbanCard extends JFrame {

    // TODO : Give a unique ID to each card
    // TODO : Fix the group layout - or change to a new type of layout

    private static int id;
    private String title;
    private String description;
    private int storyPoints;


    public KanbanCard(String name, String description, int storyPoints) {
        ++id;
        initialiseCard();
    }

    public void initialiseCard() {

        JTextArea titleLabel = new JTextArea(title);
        JTextArea info = new JTextArea(description);
        JTextArea points = new JTextArea(String.valueOf(storyPoints));
        JLabel emp = new JLabel("       ");
        info.setRows(20);
        info.setColumns(30);
//        JButton delete = new JButton("delete");
//        delete.setBounds(1000,10,100,100);
        setSize(600, 600);
        //setLayout(new FlowLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setSize(600,550);
        add(panel);
        JButton delete = new JButton("delete");
        delete.setBounds(500,500,5,5);
        panel.add(titleLabel);
        panel.add(emp);
        panel.add(info);
        panel.add(points);
        panel.add(delete);

//        panel.setLayout(null);
//        panel.add(titleLabel);
//        panel.add(delete);
        setLayout(new FlowLayout());
        //add(titleLabel);
        //add(info);
        //add(delete);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        //getContentPane().setBackground(Color.BLUE);

        //add(new JLabel("This is a card"));

    }



    public void setDescription(String newDes) {
        description = newDes;
    }

    public void setPoint(int newPoint) {
        storyPoints = newPoint;
    }

    public int getId(){
        return id;
    }

}
