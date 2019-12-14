package view.frames.popUpFrames;

import annotations.ClassAnnotation;

import model.WeeklyStats;
import view.KanbanBoard;

import javax.swing.*;
import java.awt.*;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "",
        creationDate = "13/12/2019",
        lastEdit = "13/12/2019"
)
public class StatsFrame extends JFrame {

    private KanbanBoard currentBoard;

    public StatsFrame(KanbanBoard board) {

        setTitle("Board Statistics for board " + board.getBoardName());
        currentBoard = board;
        setSize(600,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        createInfoPanel();
    }


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

    private void createInfoGraph(){

    }



}
