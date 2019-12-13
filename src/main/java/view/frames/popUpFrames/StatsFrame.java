package view.frames.popUpFrames;

import annotations.ClassAnnotation;

import view.KanbanBoard;

import javax.swing.*;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "13/12/2019",
        lastEdit = "13/12/2019"
)
public class StatsFrame extends JFrame {


    public StatsFrame(KanbanBoard board) {

        setTitle("Board Statistics");
        setSize(600,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }



    private void createInfoPanel() {

    }

    private void createInfoGraph(){

    }



}
