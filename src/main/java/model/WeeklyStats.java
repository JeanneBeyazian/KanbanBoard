package model;

import annotations.ClassAnnotation;
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

    private  ChangeLog log;
    private static ArrayList<ArrayList<Change>> weeklyChanges;

    public WeeklyStats() {
        log = ChangeLog.getInstance();
        weeklyChanges = getWeeklyChanges();
    }

    /**
     * Generate a 2D list of weekly changes lists
     * @return
     */
    private ArrayList<ArrayList<Change>> getWeeklyChanges() {

        List<Change> changes = log.getChanges();
        LocalDateTime firstDay = changes.get(0).getTimestamp();
        LocalDateTime lastDay = log.getLastChange().getTimestamp();

        // Number of days the board has been existing for
        int daysSinceBoardCreation = lastDay.compareTo(firstDay);

        // Number of weeks the board has been existing for
        ArrayList<ArrayList<Change>> listOfWeeks = new ArrayList();

        for (int i=0; i<daysSinceBoardCreation/7; i++) {
            listOfWeeks.add(new ArrayList<Change>());
        }

        // Add all the changes in each week
        for (Change change : changes ) {
            double date = change.getTimestamp().getDayOfYear();
            int week = (int)Math.ceil(7/date);
            listOfWeeks.get(week-1).add(change);
        }

        return listOfWeeks;
    }


    // Todo - VELOCITY : StoryPoints COMPLETED per week (expressed in SP/week)
    public static int getAverageVelocityPerWeek() {

        ArrayList<Integer> velocityList = new ArrayList<>();

        for (ArrayList<Change> changes : weeklyChanges) {

            int storyPoints = 0;

            for (Change change : changes) {

                Change.ChangeType type = change.getChangeType();

                // Get object and its class
                Object obj = change.getObject();
                Class<?> classType = obj.getClass();

                if(type == Change.ChangeType.MOVE && classType == KanbanCard.class) {
                    // TODO : CHECK IF NEW PARENT .getRole()==ColumnRole.COMPLETED
                    // if yes : KanbanCard movedCard = (KanbanCard)obj;
                    // storyPoints+= movedCard.getStoryPoints();

                }

            }
            velocityList.add(storyPoints);
        }

        int avgVelocity = 0;

        for(int i = 0; i < velocityList.size(); i++) {
            avgVelocity += velocityList.get(i);
        }

        return avgVelocity;

    }

    // Todo - LEAD TIME : time it takes for an item to get COMPLETED (expressed in weeks)
    public static int getAverageLeadTimePerWeek() {
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
