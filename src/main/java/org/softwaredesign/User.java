package org.softwaredesign;

import com.google.gson.Gson;
import org.softwaredesign.enumerators.Gender;
import java.io.*;
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

    public void saveUserData() {
        Gson gson = new Gson();
        try {
            FileWriter writer = new FileWriter("src/main/resources/user_data", false);
            writer.write(gson.toJson(this));
            writer.close();
        } catch (IOException e) {
            System.out.println("Cant save user");
        }
    }

    public void addGoal(Goal newGoal) {
        goalList.add(newGoal);
        saveUserData();
    }

    public void deleteGoal(Integer index) {
        goalList.remove(goalList.get(index));
        saveUserData();
    }

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
