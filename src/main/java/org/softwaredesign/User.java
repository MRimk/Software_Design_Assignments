package org.softwaredesign;

public class User {
    String name;
    Integer age;
    Double weight;
    Integer height;
    Gender gender;

    public User(String name, Integer age, Double weight, Integer height, Gender gender) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
    }

    public void pretty_print() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Weight: " + weight);
        System.out.println("Height: " + height);
        System.out.println("Gender: " + gender);
    }

}
