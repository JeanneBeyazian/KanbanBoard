package view;
import annotations.ClassAnnotation;
import controller.ActivityType;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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

        scrollContainer.add(new ActivityButton(ActivityType.BOARD_RESET));
        scrollContainer.add(new ActivityButton(ActivityType.COLUMN_ADD));
        scrollContainer.add(new ActivityButton(ActivityType.CARD_REMOVE));
        scrollContainer.add(new ActivityButton(ActivityType.COLUMN_REMOVE));
        scrollContainer.add(new ActivityButton(ActivityType.CARD_ADD));
        scrollContainer.add(new ActivityButton(ActivityType.CARD_ADD));
        scrollContainer.add(new ActivityButton(ActivityType.BOARD_RESET));
        scrollContainer.add(new ActivityButton(ActivityType.COLUMN_REMOVE));
        scrollContainer.add(new ActivityButton(ActivityType.CARD_REMOVE));
    }

    @Test
    public void testScrollContainer(){
        ScrollContainer scrollContainer = new ScrollContainer();

        scrollContainer.add(new ActivityButton(ActivityType.BOARD_RESET));
        scrollContainer.add(new ActivityButton(ActivityType.COLUMN_ADD));
        scrollContainer.add(new ActivityButton(ActivityType.CARD_REMOVE));
        scrollContainer.add(new ActivityButton(ActivityType.COLUMN_REMOVE));
        scrollContainer.add(new ActivityButton(ActivityType.CARD_ADD));

        assertEquals(5,scrollContainer.getActivityButtons());
    }
}
