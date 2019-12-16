package view;

import annotations.ClassAnnotation;
import com.athaydes.automaton.Speed;
import com.athaydes.automaton.Swinger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.frames.MainFrame;

import java.awt.*;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "",
        creationDate = "13/12/2019",
        lastEdit = "13/12/2019"
)
public class MainFrameTest {

    MainFrame mainFrame;
    Swinger swinger;

    @Before
    public void prepare() {
        mainFrame = new MainFrame();
        swinger = Swinger.forSwingWindow();
        //swinger = Swinger.getUserWith(mainFrame);
        swinger.setDEFAULT(Speed.FAST);
    }


    @Test
    public void addComponentsToBoard() {
        swinger.clickOn("newButton");
        Component field = swinger.getAt("nameField");
        swinger.moveTo(field).type("My New Kanban Board").clickOn("text:Create").pause(5000);

    }

    @After
    public void close() {

    }

}