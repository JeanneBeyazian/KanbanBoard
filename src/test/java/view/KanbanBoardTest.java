package view;
import annotations.ClassAnnotation;
import org.junit.Before;
import org.junit.Test;
import com.athaydes.automaton.Swinger;
import view.frames.KanbanCard;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "09/11/2019",
        lastEdit = "22/11/2019"
)
public class KanbanBoardTest {

    @Before
    public void prepare(){
        new KanbanBoard("KanbanBoardName");
    }



    @Test
    public void addComponentsToBoard() {
        Swinger swinger = Swinger.forSwingWindow();

        swinger.clickOn("addColumnButton")
                .moveTo("name:addColumnFrame")
                .clickOn("titleField")
                .type("Column One");

    }

}