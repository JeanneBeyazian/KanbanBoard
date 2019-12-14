package view;
import annotations.ClassAnnotation;
import model.Change;
import org.junit.Test;
import view.boardComponents.ActivityButton;
import view.containers.ScrollContainer;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "22/11/2019",
        lastEdit = "22/11/2019"
)
public class ScrollContainerTest {

    @Test
    public void makeScrollContainer(){
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ScrollContainer scrollContainer = new ScrollContainer();
        frame.getContentPane().add(scrollContainer);
    }

    @Test
    public void addToScrollContainer() {
        ScrollContainer scrollContainer = new ScrollContainer();

        scrollContainer.add(new ActivityButton("Edit", Change.ChangeType.MOVE));
        scrollContainer.add(new ActivityButton("Edit", Change.ChangeType.MOVE));
        scrollContainer.add(new ActivityButton("Edit", Change.ChangeType.MOVE));
        scrollContainer.add(new ActivityButton("Edit", Change.ChangeType.MOVE));
        scrollContainer.add(new ActivityButton("Edit", Change.ChangeType.MOVE));


        assertEquals(5,scrollContainer.getActivityButtons());
    }
}
