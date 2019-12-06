package view;
import annotations.ClassAnnotation;
import controller.ColumnRole;
import org.junit.BeforeClass;
import org.junit.Test;
import view.boardComponents.KanbanCardButton;
import view.boardComponents.KanbanColumn;
import view.frames.KanbanCard;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "13/11/2019",
        lastEdit = "05/11/2019"
)
public class KanbanCardTest {

//    @BeforeClass
//    public void prepare(){
//        JFrame frame = new JFrame();
//    }

    @Test
    public void testApp() {
        KanbanCardButton card = new KanbanCardButton(new KanbanColumn
                ("Name", ColumnRole.IN_PROGRESS), "Name", "Description", 50);
        JFrame frame = new JFrame();
        frame.add(card);
        assertEquals(1,frame.getComponentCount());
    }
}