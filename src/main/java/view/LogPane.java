package view;

import javax.swing.*;
import java.awt.*;

public class LogPane extends JTabbedPane {

    private JPanel activityLog;
    private JPanel historyLog;

    public LogPane(){
        initialiseLogPane();
    }

    public void initialiseLogPane() {

        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        setPreferredSize(new Dimension(100,300));
        activityLog = new JPanel();
        historyLog = new JPanel();

        addTab("Activity log", null, activityLog, "See recent changes");
        //activity.setMnemonicAt(0, KeyEvent.VK_1);

        addTab("Recent files", null, historyLog, "See history");
        //activity.setMnemonicAt(1, KeyEvent.VK_2);
    }

}
