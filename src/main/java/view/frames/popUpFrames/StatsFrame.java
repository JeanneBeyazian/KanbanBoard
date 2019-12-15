package view.frames.popUpFrames;

import annotations.ClassAnnotation;

import model.WeeklyStats;
import view.frames.KanbanBoard;

import javax.swing.*;
import java.awt.*;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "",
        creationDate = "13/12/2019",
        lastEdit = "15/12/2019"
)
/**
 * Frame that shows all the current statistics of the board :
 *      - Overall Velocity per Week,
 *      - Average Lead Time per Week,
 *      - Average WIP per Week.
 *
 *  Should be responsible for displaying a graph with these statistics.
 */
public class StatsFrame extends JFrame {

    private KanbanBoard currentBoard;


    public StatsFrame(KanbanBoard board) {

        currentBoard = board;
        setTitle("Board Statistics for board " + board.getBoardName());
        setSize(600,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        createInfoPanel();
    }


    /**
     * Sets up the panel displaying the statistics in labels.
     */
    private void createInfoPanel() {

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JLabel avgVelocityLabel = new JLabel("Overall Velocity Per Week : " +
                WeeklyStats.getAverageVelocityPerWeek());
        JLabel avgLeadTime = new JLabel("Average Lead Time Per Week : " +
                WeeklyStats.getAverageLeadTimePerWeek(currentBoard));
        JLabel avgWIP = new JLabel("Average Work In Progress Per Week : " + WeeklyStats.getAverageWIPPerWeek());

        container.add(avgVelocityLabel);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(avgLeadTime);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(avgWIP);

        add(container);

    }

    /**
     * Creation of the visual representation of the statistics
     */
    private void createInfoGraph(){

    }



}
