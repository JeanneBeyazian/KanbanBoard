package view;

import com.athaydes.automaton.Swinger;
import org.junit.Before;
import org.junit.Test;
import view.frames.KanbanBoard;
import view.frames.editBoardFrames.AddCardFrame;
import view.frames.editBoardFrames.EditorFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EditorFramesTest {

    EditorFrame frame;
    Swinger swinger;

    @Before
    public void prepare(){
        KanbanBoard kanbanBoard = new KanbanBoard("Test Board");
        frame = new AddCardFrame(kanbanBoard.getBoard());
        swinger = Swinger.getUserWith(frame);
    }


    @Test
    public void addComponentsToBoard() {
        Swinger swinger = Swinger.forSwingWindow();

        List<Component> buttons = swinger.getAll("type:JButton");

        for (Component button : buttons) {
            System.out.println(button.getName());
        }
        //swinger.clickOn("submitButton");
//        board.getWindows().get("addColumnFrame");
//        swinger.clickOn(submitButton);
    }
}
