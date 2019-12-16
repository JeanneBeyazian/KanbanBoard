package view;
import annotations.ClassAnnotation;
import controller.exceptions.KanbanObjectNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.athaydes.automaton.Swinger;
import view.boardComponents.KanbanColumn;
import view.containers.ScrollContainer;
import view.frames.KanbanBoard;


import static org.junit.Assert.*;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "",
        creationDate = "09/11/2019",
        lastEdit = "22/11/2019"
)
public class KanbanBoardTest {

    KanbanBoard board = new KanbanBoard("KanbanBoardName");
    Swinger swinger;

    @Before
    public void prepare(){
        swinger = Swinger.getUserWith(board);
    }

    @After
    public void remove(){
        board.getBoard().removeAll();
    }


    @Test
    public void addThirtyColumns() {

        for (int i = 0; i < 30; i++) {
            swinger.clickOn("addColumnButton")
                    .type(".Column " + i + "\n");
        }

        assertEquals(30, board.getBoard().getColumns().size());
        assertEquals(30*2, board.getBoard().getComponentCount()); // Includes empty boxes
    }

    @Test
    public void addThirtyCards(){

        swinger.pause(250)
                .clickOn("addColumnButton")
                .pause(250)
                .type("Column\n");

        for (int i = 0; i < 30; i++) {
            swinger.clickOn("addCardButton")
                    .type(".Card " + i +"\n");
        }

        swinger.pause(250);

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
        swinger.pause(250).clickOn("clearButton").pause(500);

        assertEquals(0, board.getBoard().getColumns().size());
        assertEquals(0, board.getBoard().getComponentCount());

    }

    @Test
    public void addColumnsFromMenu() {

        for (int i = 0; i < 4; i++) {
            swinger.clickOn("text:Edit")
                    .pause(250)
                    .clickOn("text:Add")
                    .pause(250)
                    .clickOn("text:Insert new column")
                    .pause(250)
                    .type(".Column " + i +  "\n")
                    .pause(250);
        }

        assertEquals(4, board.getBoard().getColumns().size());
        assertEquals(4*2, board.getBoard().getComponentCount()); // Includes empty boxes

    }


    @Test
    public void cardFromMenu(){

        swinger.clickOn("text:Edit")
                .pause(250)
                .clickOn("text:Add")
                .pause(250)
                .clickOn("text:Insert new column")
                .type("Column\n");


        for (int i = 0; i < 30; i++) {
            swinger.clickOn("text:Edit")
                    .pause(250)
                    .moveTo("text:Add")
                    .pause(250)
                    .moveTo("text:Insert new column")
                    .pause(250)
                    .clickOn("text:Insert new card")
                    .type(".Card " + i +"\n")
                    .pause(250);
        }

        swinger.pause(500);


        KanbanColumn column = null;
        try {
            column = board.getBoard().getColumns().get(0);
        } catch (Exception e) {
            new KanbanObjectNotFoundException();
        }

        assertEquals(30, column.getCards().size());

    }


    @Test
    public void verifyLogActivity(){

        addThirtyColumns();
        swinger.pause(250);
        int activities = board.getEditorPanel().getLogPanel().getActivityLog().getContainer().getComponentCount();

        assertEquals(31, activities); //including board creation

    }




}