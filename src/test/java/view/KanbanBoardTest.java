package view;
import annotations.ClassAnnotation;
import org.junit.Before;
import org.junit.Test;
import com.athaydes.automaton.Swinger;
import view.frames.KanbanBoard;

import static org.junit.Assert.assertThat;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
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
    public void addComponentsToBoard() {
        Swinger swinger = Swinger.forSwingWindow();
        swinger.clickOn("addColumnButton")
                .type("Column name");
//        board.getWindows().get("addColumnFrame");
//        swinger.clickOn(submitButton);
    }

}