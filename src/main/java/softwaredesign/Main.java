package softwaredesign;

import com.google.gson.Gson;

public class Main {
    public static void main (String[] args){
        System.out.println("Welcome to Software Design");

        Gson gson = new Gson();

        User user = gson.fromJson("src/main/resources/user-data", User.class);

    }
}
