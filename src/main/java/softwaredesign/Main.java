package softwaredesign;

import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        Gson gson = new Gson();

        User user;
        try (Reader readerJSON = new FileReader("src/main/resources/user_data")) {
            user = gson.fromJson(readerJSON, User.class);
            user.pretty_print();
        } catch (IOException e) {
            user = fillUserInformation();
            saveUserData(gson.toJson(user));
        }
    }

    public static User fillUserInformation(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter First Name ");
        String name = scanner.nextLine();

        System.out.println("Enter Age ");
        Integer age = scanner.nextInt();

        System.out.println("Enter Weight ");
        Double weight = scanner.nextDouble();

        System.out.println("Enter Height ");
        Integer height = scanner.nextInt();

        User user = new User(name, age, weight, height, Gender.MALE);
        user.pretty_print();

        return user;
    }

    public static void saveUserData(String compiledJSON) {
        try {
            File myObj = new File("src/main/resources/user_data");
            if (!myObj.createNewFile()) {
                System.out.println("File already exist");
            }
        }
        catch (IOException e) {
            System.out.println("Could not create file 'user_data'");
        }

        try (FileWriter writer = new FileWriter("src/main/resources/user_data")) {
            writer.write(compiledJSON);
        }
        catch (IOException e) {
            System.out.println("Could not write to file 'user_data'");
        }
    }
}
