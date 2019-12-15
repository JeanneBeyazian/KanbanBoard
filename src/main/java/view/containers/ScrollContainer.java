package view.containers;

import annotations.ClassAnnotation;

import javax.swing.*;
import java.awt.*;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "",
        creationDate = "21/11/2019",
        lastEdit = "15/12/2019"
)
/**
 * Container extending JScrollPane for different types of components
 * Used for KanbanColumn and LogPanel
 */
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
        setOpaque(false);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JViewport viewport = new JViewport();
        viewport.setView(new JPanel());
        viewport.setOpaque(false);
        setViewport(viewport);

        container.setOpaque(false);
        getViewport().setOpaque(false);
        getViewport().add(container,null);


    }

    /**
     * Add a JComponent to the scrollPane
     * @param button
     */
    public void add(JComponent button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
    }

    /**
     * Remove a JComponent from the scrollPane
     * @param button
     */
    public void remove(JComponent button){
        container.remove(button);
    }

    /**
     * Get the number of components inserted in the scroll pane
     * @return number of buttons
     */
    public int getActivityButtons(){
        return container.getComponentCount();
    }
}
