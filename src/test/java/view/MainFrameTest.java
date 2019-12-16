package view;

import annotations.ClassAnnotation;
import com.athaydes.automaton.Speed;
import com.athaydes.automaton.Swinger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.frames.MainFrame;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

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
        swinger.setDEFAULT(Speed.FAST);
    }


    @Test
    public void addComponentsToBoard() {
        swinger.clickOn("newButton")
                .clickOn("text:Create")
                .pause(250);

        ArrayList<Frame> activeFrames = new ArrayList<>();
        for (Frame c : Frame.getFrames()) {
            if (c.isActive())  activeFrames.add(c);
        }

        assertEquals(1,(activeFrames).size());

    }


}