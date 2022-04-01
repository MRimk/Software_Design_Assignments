package org.softwaredesign;

import com.google.gson.Gson;
import org.softwaredesign.enumerators.Gender;
import java.io.*;
import java.util.Base64;
import java.util.List;

public class User {
    String name;
    Integer age;
    Double weight;
    Integer height;
    Gender gender;
    List<Goal> goalList;

    public User(String name, Integer age, Double weight, Integer height, Gender gender, List<Goal> goalList) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.goalList = goalList;
    }

    /**
     * Put the user object into an encoded JSON file
     */
    public void saveUserData() {
        Gson gson = new Gson();
        try {
            FileWriter writer = new FileWriter("src/main/resources/user_data", false);
            String data = gson.toJson(this);
            byte[] encodedData = Base64.getEncoder().encode(data.getBytes());
            writer.write(new String(encodedData));
            writer.close();
        } catch (IOException e) {
            System.err.println("Cant save user");
        }
    }

    /**
     * Add a goal to the user goal list and save the user data to a file
     * @param newGoal
     * Goal object that is saved in the list
     */
    public void addGoal(Goal newGoal) {
        goalList.add(newGoal);
        saveUserData();
    }

    /**
     * Remove a goal from the list
     * @param index
     * Goal will be removed at index
     */
    public void deleteGoal(Integer index) {
        goalList.remove(goalList.get(index));
        saveUserData();
    }

    /**
     * Update goal progress by giving new data
     * @param progress
     * The new addition to the goal's progress in Double format
     * @param index
     * Index shows which goal needs to be updated
     */
    public void updateGoal(Double progress, Integer index) {
        goalList.get(index).calculateProgress(progress);
    }
    public Integer getNumGoals() {
        return goalList.size();
    }
    public List<Goal> getGoalList() {
        return goalList;
    }
    public Double getWeight(){
        return weight;
    }
}
