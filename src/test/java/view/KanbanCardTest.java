package view;
import annotations.ClassAnnotation;
import org.junit.Test;
import view.containers.KanbanCard;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "13/11/2019",
        lastEdit = "22/11/2019"
)
public class KanbanCardTest {

    @Test
    public void testApp(){
        KanbanCard card = new KanbanCard("Name", "Description", 50);
        card.setVisible(true);
    }
}