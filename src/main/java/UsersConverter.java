import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.*;

public class UsersConverter {

    private String serializeUsers() {

        StringBuilder result = new StringBuilder();
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            BufferedReader reader = new BufferedReader(new FileReader("gson.txt"));
            List<User> list = new ArrayList<>();
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = line.split(" ");
                list.add(new User(array[0], Integer.parseInt(array[1])));
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("target/user.json"));
            writer.write(gson.toJson(list));
            writer.close();
            result.append(gson.toJson(list));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public static void main(String[] args) {
        UsersConverter usersToJson = new UsersConverter();
        System.out.println(usersToJson.serializeUsers());

    }
}
