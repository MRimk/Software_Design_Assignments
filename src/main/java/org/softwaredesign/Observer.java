package org.softwaredesign;

public interface Observer {
    /**
     * Update of observer takes in 2 parameters, which combined describe the data that needs to be used in observable objects
     * @param data
     * this is the data that the updated object will get
     * @param parser
     * this is the optional parser or extractor of the data
     */
    void update(Object data, Object parser);
}
