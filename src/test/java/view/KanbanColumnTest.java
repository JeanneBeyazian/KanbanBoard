package view;

import annotations.ClassAnnotation;
import controller.ColumnRole;
import org.junit.Test;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanCardButton;
import view.boardComponents.KanbanColumn;
import view.frames.KanbanBoard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "",
        creationDate = "05/11/2019",
        lastEdit = "05/11/2019"
)

public class KanbanColumnTest {

    @Test
    public void makeColumn(){
        KanbanColumn col = new KanbanColumn("A Title", ColumnRole.COMPLETED);
        assertEquals("A Title", col.getColumnTitle());
        assertEquals(ColumnRole.COMPLETED, col.getRole());
    }

    @Test
    public void checkIfEmpty() {
        KanbanColumn col = new KanbanColumn("Name", ColumnRole.COMPLETED);
        // Includes Column name label and ScrollContainer
        assertEquals(2, col.getComponentCount());
    }

    @Test
    public void getNumberOfComponentsShouldBe52() {
        KanbanColumn col = new KanbanColumn("Name", ColumnRole.COMPLETED);
        for (int i = 0; i<50; i++) {
            col.add(new KanbanCardButton(col, "Card Name", "Card description", 10));
        }
        // Includes Column name label and ScrollContainer
        assertEquals(52, col.getComponentCount());
    }

    @Test
    public void getNumberOfAddedCardsShouldBe50(){
        KanbanColumn col = new KanbanColumn("Name", ColumnRole.COMPLETED);
        for (int i = 0; i < 50; i++) {
            col.addCard(new KanbanCardButton(col, "Card Name", "Card description", 10));
        }
        assertEquals(50, col.getCards().size());
    }

    @Test
    public void verifyCardAttributes()
    {
        KanbanColumn col = new KanbanColumn("Name", ColumnRole.COMPLETED);
        for (int i = 0; i < 50; i++) {
            col.addCard(new KanbanCardButton(col, "Card " + i, "Card description", 10+i));
        }

        ArrayList<KanbanCardButton> cardsList = col.getCards();

        for (int i = 0; i < 50; i++) {
            KanbanCardButton card = cardsList.get(i);
            assertEquals("Card "+i, card.getCard().getCardTitle());
            assertEquals(10+i, card.getCard().getStoryPoints());
        }

        // With two random cards
        assertEquals("Card 27", cardsList.get(27).getCard().getCardTitle());
        assertEquals(48, cardsList.get(38).getCard().getStoryPoints());

    }

    @Test
    public void verifyBoard(){
        BoardPanel board = new BoardPanel(new KanbanBoard("Test Board"));
        Random rd = new Random();
        ColumnRole[] array = ColumnRole.values();

        for (int i = 0; i < 10; i++) {
            KanbanColumn col = new KanbanColumn("Column "+i, ColumnRole.values()[rd.nextInt(array.length)]);
            board.addColumn(col);
        }

        ArrayList<KanbanColumn> columns = board.getColumns();

        for (KanbanColumn col : columns){
            assertEquals(board, col.getBoard());
        }
    }






}
