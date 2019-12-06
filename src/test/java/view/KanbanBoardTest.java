package view;
import annotations.ClassAnnotation;
import org.junit.Test;
import com.athaydes.automaton.Swinger;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "09/11/2019",
        lastEdit = "22/11/2019"
)
public class KanbanBoardTest {

    @Test
    public void testApp(){
        new KanbanBoard("Name");
        Swinger swinger = Swinger.forSwingWindow();
        swinger.pause(5000);
    }
}