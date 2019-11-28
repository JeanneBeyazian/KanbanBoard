package view;

import annotations.ClassAnnotation;

import javax.swing.*;
import java.awt.*;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "21/11/2019",
        lastEdit = "22/11/2019"
)
public class ScrollContainer extends JScrollPane {

    private JPanel container;

    public ScrollContainer(){
        container = new JPanel();
        initialiseScrollContainer();
    }


    /**
     * Create ScrollContainer and its container
     */
    private void initialiseScrollContainer() {
        //container.setLayout(new GridLayout(8,1));
        //JPanel pane = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        //pane.add(container);
        //getViewport().add(pane, null);

        // Trying out flow pane
        JPanel pane = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        pane.add(container);
        getViewport().add(container,null);

    }

    public void add(JComponent button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
    }

    public void remove(JComponent button){
        container.remove(button);
    }

    public int getActivityButtons(){
        return container.getComponentCount();
    }
}
