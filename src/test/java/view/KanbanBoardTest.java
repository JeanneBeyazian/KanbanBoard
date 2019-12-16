package view;
import annotations.ClassAnnotation;
import controller.exceptions.KanbanObjectNotFoundException;
import org.junit.Before;
import org.junit.Test;
import com.athaydes.automaton.Swinger;
import view.boardComponents.KanbanColumn;
import view.frames.KanbanBoard;

import javax.swing.*;
import java.awt.*;
import java.util.jar.JarFile;

import static org.junit.Assert.*;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "",
        creationDate = "09/11/2019",
        lastEdit = "22/11/2019"
)
public class KanbanBoardTest {

    KanbanBoard board;
    Swinger swinger;

    @Before
    public void prepare(){

        board = new KanbanBoard("KanbanBoardName");
        swinger = Swinger.getUserWith(board);
    }


    @Test
    public void addThirtyColumns() {

        for (int i = 0; i < 30; i++) {
            swinger.clickOn("addColumnButton")
                    .type(".Column " + i + "\n");
        }

        assertEquals(30, board.getBoard().getColumns().size());
        assertEquals(30*2, board.getBoard().getComponentCount()); // Includes labels


//        for(Component f : board.getFrames() ) {
//            System.out.println(f.getName());
//        }
//        board.getWindows().get("addColumnFrame");
//        swinger.clickOn(submitButton);
    }

    @Test
    public void addThirtyCards(){

        swinger.clickOn("addColumnButton")
                .type("Column\n");

        for (int i = 0; i < 30; i++) swinger.clickOn("addCardButton").type(".Card " + i + "\n");

        KanbanColumn column = null;
        try {
            column = board.getBoard().getColumnByTitle("Column");
        } catch (Exception e) {
            new KanbanObjectNotFoundException();
        }

        assertEquals(30, column.getCards().size());

    }

    @Test
    public void clearBoardOfThirtyColumns(){
        addThirtyColumns();
        swinger.clickOn("clearButton");

        assertEquals(0, board.getBoard().getColumns().size());

    }

}