package softwaredesign;



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
}
