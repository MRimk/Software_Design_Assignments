package org.softwaredesign;

public class Goal {
    private final String metric;
    private final String sport;
    private final Double target;
    private Double progress;
    private Boolean completed;

    public Goal(String sport, Double target, String type){
        //constructor for user-created goal
        this.sport = sport;
        this.metric = type;
        this.target = target;
        this.progress = 0.0;
        this.completed = false;
    }
    public Goal(String sport, Double target, Double progress, String type, Boolean completed){
        //constructor for goal read from JSON
        this.sport = sport;
        this.metric = type;
        this.target = target;
        this.progress = progress;
        this.completed = completed;
    }
    public void calculateProgress(Double newData){
        //update progress with new data
        progress += newData;
        completed = progress >= target;
    }
    public Double getProgress() { return progress; }
    public Boolean isCompleted(){ return completed; }
    public String getMetric() { return metric; }
    public String getSport() { return sport; }
    public Double getTarget() { return target; }
}
