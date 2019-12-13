package model;

import annotations.ClassAnnotation;
import controller.ColumnRole;
import view.boardComponents.KanbanColumn;
import view.frames.KanbanCard;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "13/12/2019",
        lastEdit = "13/12/2019"
)
public class WeeklyStats {

    private static ArrayList<ArrayList<Change>> weeklyChanges;

    /**
     * Generate a 2D list of weekly changes lists
     * @return
     */
    private static ArrayList<ArrayList<Change>> getWeeklyChanges() {

        ChangeLog log = ChangeLog.getInstance();

        List<Change> changes = log.getChanges();
        LocalDateTime firstDay = changes.get(0).getTimestamp();
        LocalDateTime lastDay = log.getLastChange().getTimestamp();

        // Number of days the board has been existing for
        int daysSinceBoardCreation = lastDay.compareTo(firstDay);

        // Number of weeks the board has been existing for
        ArrayList<ArrayList<Change>> listOfWeeks = new ArrayList<ArrayList<Change>>();

        for (int i=0; i<daysSinceBoardCreation/7; i++) {
            listOfWeeks.add(new ArrayList<Change>());
        }

        // Add all the changes in each week
        if (listOfWeeks.isEmpty()) return listOfWeeks;

        for (Change change : changes ) {
            double date = change.getTimestamp().getDayOfYear();
            int week = (int)Math.ceil(7/date);
            listOfWeeks.get(week-1).add(change);
        }

        return listOfWeeks;
    }



    /**
     * Calculate overall velocity: average number of story points completed per week.
     * @return overall velocity (expressed in SP/week)
     */
    public static int getAverageVelocityPerWeek() {

        weeklyChanges = getWeeklyChanges();

        if (weeklyChanges.isEmpty()) return 0;

        // List to add up all weekly velocities
        ArrayList<Integer> velocityList = new ArrayList<Integer>();

        for (ArrayList<Change> changes : weeklyChanges) {

            int storyPoints = 0;

            for (Change change : changes) {

                Change.ChangeType type = change.getChangeType();

                // Get object and its class
                Object obj = change.getObject();
                Class<?> classType = obj.getClass();

                if(type == Change.ChangeType.MOVE && classType == KanbanCard.class) {
                    KanbanColumn newParentColumn = (KanbanColumn) change.getNewParentType();
                    KanbanColumn oldParentColumn = (KanbanColumn) change.getOldParentType();

                    // Check if new parent column is of type completed, and old parent column wasn't completed
                    if (newParentColumn.getRole() == ColumnRole.COMPLETED
                            && oldParentColumn.getRole() != ColumnRole.COMPLETED) {
                        KanbanCard movedCard = (KanbanCard)obj;
                        storyPoints+= movedCard.getStoryPoints();

                    }

                }

            }
            velocityList.add(storyPoints);
        }

        int avgVelocity = 0;

        for(int i = 0; i < velocityList.size(); i++) {
            avgVelocity += velocityList.get(i);
        }
        avgVelocity /= weeklyChanges.size();

        return avgVelocity;

    }

    // Todo - LEAD TIME : time it takes for an item to get COMPLETED (expressed in weeks)
    public static int getAverageLeadTimePerWeek() {
        // Maybe look at all cards in completed columns, and get their creation date ?
        return 0;
    }

    // Todo - WIP : number of story points in WIP column (expressed in SP)
    public static int getAverageWIPPerWeek() {
        return 0;
    }







    // ---------UNUSED, please keep for now
    private String getWeekOfYear(LocalDateTime timestamp) {

        ZoneOffset zoneOffset = ZoneOffset.from(OffsetDateTime.now());
        OffsetDateTime offsetDateTime = timestamp.atOffset(zoneOffset);
        Date date = Date.from(offsetDateTime.toInstant());

        String weekOfYear = new SimpleDateFormat("w").format(date);

        return weekOfYear;
    }
}
