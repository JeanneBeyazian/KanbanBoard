package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LogPanel extends JTabbedPane {

    // TODO : class hierarchy for scrollpane containing buttons

    private JPanel activityLog;

    public LogPanel(){
        initialiseLogPane();
    }

    public void initialiseLogPane() {

        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        setPreferredSize(new Dimension(100,300));
        activityLog = new JPanel();

        addTab("Activity log", null, makeActivityLog(), "See recent changes");
        //activity.setMnemonicAt(0, KeyEvent.VK_1);

        addTab("Recent files", null, makeVersionsLog(), "See history");
        //activity.setMnemonicAt(1, KeyEvent.VK_2);
    }

    /**
     * TESTING PURPOSE : create a few buttons into the scrollpane
     */
    private JScrollPane makeActivityLog(){

        int x = 8;
        activityLog.setLayout(new GridLayout(x, 1));
        for (int i = 0; i < x ; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setPreferredSize(new Dimension(100, 100));
            activityLog.add(button);
        }
        JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        container.add(activityLog);
        JScrollPane activityScroll = new JScrollPane(container);


        return activityScroll;
    }

    private JPanel makeVersionsLog() {
        JPanel panel = new JPanel();
        JButton seeVersions = new JButton("See Full Board history");
        panel.setBorder(new EmptyBorder(50, 10, 50, 10));
        panel.add(seeVersions);
        return panel;
    }

}
