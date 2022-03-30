package org.softwaredesign;

import io.jenetics.jpx.GPX;
import org.softwaredesign.enumerators.Sport;
import org.softwaredesign.helpers.StringToSportHelper;
import org.softwaredesign.metrics.Metric;

import java.util.List;

public class GoalUpdater {
    private final Activity activityChecked;


    public GoalUpdater(Activity activityChecked) {
        this.activityChecked = activityChecked;
    }

    public void update(Metric metric){
        Integer numGoals = GUI.getUser().getNumGoals();
        List<Goal> goalList = GUI.getUser().getGoalList();

        Sport sport = activityChecked.getSport();
        GPX gpx = activityChecked.getGpx();

        for (int i = 0; i < numGoals; i++) {
            Goal currentGoal = goalList.get(i);
            Sport goalSport = StringToSportHelper.getSport(currentGoal.getSport());
            if (goalSport.equals(sport) && currentGoal.getMetric().equals(metric.getMetricName())) {
                GUI.getUser().updateGoal(metric.calculateMetricTotal(gpx), i);
            }
        }
        GUI.getUser().saveUserData();
    }
}
