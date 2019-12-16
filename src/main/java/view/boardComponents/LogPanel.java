package view.boardComponents;

import annotations.ClassAnnotation;
import model.Change;
import model.ChangeLog;
import view.containers.ScrollContainer;

import javax.swing.*;
import java.awt.*;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "Jeanne, Petra",
        creationDate = "22/11/2019",
        lastEdit = "15/12/2019"
)
/**
 * Log Panel is a tabbed pane made of two JPanels for activity log and versions history.
 * The Log Panel is located inside the Editor Panel.
 */
public class LogPanel extends JPanel {

    // Predefined size
    private static final int LOG_WIDTH = 100;
    private static final int LOG_HEIGHT = 330;

    // Container for activity buttons
    private ScrollContainer activityLog;


    /** Constructor for LogPanel*/
    public LogPanel() {

        ChangeLog log = ChangeLog.getInstance();
        initialiseLogPanel();

        log.addListener(this);      // add this panel to ChangeLog listener list
        updateLog();
    }

    /**
     * Initialise the panel and create its components : activityLog and versionsLog.
     */
    public void initialiseLogPanel() {

        setPreferredSize(new Dimension(LOG_WIDTH,LOG_HEIGHT));
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        activityLog = new ScrollContainer();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(activityLog);
        add(panel);
    }


    /**
     * Triggered by ChangeLog (observer design pattern).
     * Update the list of log entries.
     */
    public void updateLog() {

        ChangeLog log = ChangeLog.getInstance();

        String logEntry = "";
        Change c = log.getLastChange();
        try {
            logEntry = c.formatAsString();
        } catch (Exception e) {
            System.out.println("Failed to convert log entry");
        }

        // Create and add a new Activity button to the Log panel
        ActivityButton button = new ActivityButton(logEntry, c.getChangeType(), c.getId());
        activityLog.add(button);

        revalidate();
        repaint();


    }

}
