package org.softwaredesign;

import org.softwaredesign.enumerators.Sport;
import org.softwaredesign.metrics.Metric;

public class Goal {
    private Metric type;
    private Double progress;
    private Double target;
    private Sport sport;
    private Boolean completed;

    public Goal(Sport sport, Double target, Metric type){
        //constructor for user-created goal
    }
    public Goal(Sport sport, Double target, Double progress, Metric type){
        //constructor for goal read from JSON
    }
    public void calculateProgress(Double newData){
        //update progress with new data
    }
    public Double getProgress(){
        return progress;
    }
    public Boolean isCompleted(){
        return completed;
    }
}
