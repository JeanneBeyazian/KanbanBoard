package view;
import org.junit.Test;
import com.athaydes.automaton.Swinger;


public class KanbanBoardTest {

    @Test
    public void testApp(){
        new KanbanBoard();
        Swinger swinger = Swinger.forSwingWindow();
        swinger.pause(5000);
    }
}