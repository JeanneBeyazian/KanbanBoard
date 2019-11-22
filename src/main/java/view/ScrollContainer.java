package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ScrollContainer extends JScrollPane {

    private JPanel container;

    public ScrollContainer(){
        container = new JPanel();
        initialiseScrollContainer();
    }

    private void initialiseScrollContainer() {
        container.setLayout(new GridLayout(2,1));
        JPanel pane = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pane.add(container);
        getViewport().add(pane, null);

    }


    public void add(ActivityButton button) {
        container.add(button);
    }

    public int getActivityButtons(){
        return container.getComponentCount();
    }
}
