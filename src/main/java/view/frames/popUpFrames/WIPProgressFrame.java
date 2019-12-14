package view.frames.popUpFrames;

import annotations.ClassAnnotation;
import view.KanbanBoard;

import javax.swing.*;
import java.awt.*;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "",
        creationDate = "10/12/2019",
        lastEdit = "10/12/2019"
)
public class WIPProgressFrame extends PopUpFrames {

    private KanbanBoard currentBoard;
    private JProgressBar bar;
    private JLabel progress;

    public WIPProgressFrame(KanbanBoard currentBoard) {

        super("Current Work In Progress...");
        this.currentBoard = currentBoard;

        progress = new JLabel();
        progress.setFont(new Font("Arial", Font.BOLD, 12));

        containerPanel.add(progress);
        containerPanel.add(createBar());
        buttonPanel.add(createRefreshButton());

    }

    @Override
    protected JTextArea createText() {

        JTextArea expl = new JTextArea();
        String text = "Work in Progress Graph Viewer.\n\n" +
                "WIP stands for Work In Progress. It determines the story points contained in\n" +
                "an 'In Progress' column. You can set a limit for the WIP in the editor panel.\n\n"+
                "This graph shows the percentage of story points that are in progress before\n" +
                "reaching the limit.";

        expl.setText(text);
        expl.setMargin(new Insets(10,10,10,10));
        expl.setEditable(false);
        return expl;

    }

    private JButton createRefreshButton() {

        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(e->updateBar());
        refresh.setBorderPainted(false);

        return refresh;
    }

    private JProgressBar createBar() {

        bar = new JProgressBar();
        bar.setMinimum(0);
        bar.setMaximumSize(new Dimension(250,75));
        bar.setStringPainted(true);

        updateBar();

        return bar;

    }

    private void updateBar() {
        int max = currentBoard.getBoard().getWIPlimit();
        int current = currentBoard.getBoard().getWIPcount();

        progress.setText("Current Progress: " + current + " story points in progress out of " + max);
        bar.setMaximum(max);
        bar.setValue(current);

        revalidate();
        repaint();
    }

}
