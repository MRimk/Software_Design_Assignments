package org.softwaredesign;

import org.softwaredesign.metrics.Metric;

public interface Observer {
    /**
     * Update of observer takes in 2 parameters, which combined describe the data that needs to be used in observable objects
     * @param metric
     *  metric is the processor of the given data
     * @param activity
     *  activity provides the data to be processed and updated with
     */
    void update(Metric metric, Activity activity);
}
