package view.containers;

import annotations.ClassAnnotation;
import controller.ActivityType;
import model.Change;
import model.ChangeLog;
import view.boardComponents.ActivityButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

@ClassAnnotation(
        classAuthors = {"Jeanne", "Petra"},
        creationDate = "22/11/2019",
        lastEdit = "08/12/2019"
)
/**
 * Log Panel is a tabbed pane made of two JPanels for activity log and versions history.
 * The Log Panel is located inside the Editor Panel.
 */
public class LogPanel extends JTabbedPane {

    private static final int LOG_WIDTH = 100;
    private static final int LOG_HEIGHT = 330;

    private ScrollContainer activityLog;
    private JTextArea logText;

    public LogPanel(){
        ChangeLog log = ChangeLog.getInstance();
        initialiseLogPanel();
//        logText.setEditable(false);


        //observer design pattern
        log.addListener(this); // add this panel to ChangeLog listener list
    }

    /**
     * Initialise the panel and create its components : activityLog and versionsLog.
     */
    public void initialiseLogPanel() {

        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        setPreferredSize(new Dimension(LOG_WIDTH,LOG_HEIGHT));
        setOpaque(false);

        activityLog = new ScrollContainer();

        addTab("Activity log", null, makeChangeLog() ,"See recent changes");
        addTab("Recent files", null, makeVersionsLog(), "See history");
    }


    /**
     * Create the versions history panel - second panel of the tabbed panels.
     * @return panel with button to access history
     */
    private JPanel makeVersionsLog() {

        JPanel panel = new JPanel();
        JButton seeVersions = new JButton("See Full Board history");
        panel.setBorder(new EmptyBorder(50, 10, 50, 10));
        panel.add(seeVersions);
        return panel;
    }

    private JPanel makeChangeLog(){

        JPanel panel = new JPanel(new BorderLayout());

//        logText = new JTextArea();
//        logText.setLineWrap(true);
//        logText.setWrapStyleWord(true);
//        JScrollPane scr = new JScrollPane(logText);
//        panel.add(scr);

        panel.add(activityLog);
        return panel;
    }

    /**
     * Triggered by ChangeLog (observer design pattern).
     * Update the list of log entries.
     */
    public void updateLog(){
        ChangeLog log = ChangeLog.getInstance();
//        String logEntries = "";
//        for(Change c : log.getChanges()){
//            try {
//                logEntries += "> " + c.formatAsString() + "\n";
//            } catch (Exception e){
//                System.out.println("Failed to convert log entry");
//            }
//        }
//        logText.setText(logEntries);


        String u = "";
        Change c = log.getLastChange();
        try {
            u = c.formatAsString();
        } catch (Exception e) {
            System.out.println("Failed to convert log entry");
        }

        ActivityButton button = new ActivityButton(u, c.getChangeType());
        activityLog.add(button);

        revalidate();
        repaint();


    }

}
