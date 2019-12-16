package view;
import annotations.ClassAnnotation;
import model.Change;
import org.junit.Before;
import org.junit.Test;
import view.boardComponents.ActivityButton;
import view.containers.ScrollContainer;

import static org.junit.Assert.assertEquals;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "",
        creationDate = "22/11/2019",
        lastEdit = "15/12/2019"
)
public class ScrollContainerTest {

    ScrollContainer scrollContainer;

    @Before
    public void makeScrollContainer() {
        scrollContainer = new ScrollContainer();

        ActivityButton one = new ActivityButton("One", Change.ChangeType.MOVE,1);
        ActivityButton two = new ActivityButton("Two", Change.ChangeType.MOVE,2);
        ActivityButton three = new ActivityButton("Three", Change.ChangeType.MOVE,3);
        ActivityButton four = new ActivityButton("Four", Change.ChangeType.MOVE,4);
        ActivityButton five = new ActivityButton("Five", Change.ChangeType.MOVE,5);

        // Add all five buttons to scroll container
        scrollContainer.add(one);
        scrollContainer.add(two);
        scrollContainer.add(three);
        scrollContainer.add(four);
        scrollContainer.add(five);
    }


    @Test
    public void addAndRemoveFromScrollContainer() {

        // Remove two buttons from scroll container
        scrollContainer.remove((ActivityButton)scrollContainer.getContainer().getComponent(0));
        scrollContainer.remove((ActivityButton)scrollContainer.getContainer().getComponent(2));

        // Number of buttons inside must be 3
        assertEquals(3,scrollContainer.getActivityButtons());

        // Get first item and remove it until empty
        assertEquals("Two",((ActivityButton)scrollContainer.getContainer().getComponent(0))
                .getActivityButton().getText());
        scrollContainer.remove((ActivityButton)scrollContainer.getContainer().getComponent(0));

        assertEquals("Three",((ActivityButton)scrollContainer.getContainer().getComponent(0))
                .getActivityButton().getText());
        scrollContainer.remove((ActivityButton)scrollContainer.getContainer().getComponent(0));

        assertEquals("Five",((ActivityButton)scrollContainer.getContainer().getComponent(0))
                .getActivityButton().getText());
        scrollContainer.remove((ActivityButton)scrollContainer.getContainer().getComponent(0));

        // Should be empty
        assertEquals(0,scrollContainer.getActivityButtons());
    }

}
