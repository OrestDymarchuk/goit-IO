import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputOutput {

    private String validNumbers() {
        StringBuilder result = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
            String phoneNumbersString;

            while ((phoneNumbersString = reader.readLine()) != null) {
                String validRegex = "(\\d{3}-\\d{3}-\\d{4})|(\\(\\d{3}\\)\\s\\d{3}-\\d{4})";

                ArrayList<String> phoneNumbersList = new ArrayList<>();
                Matcher matcher = Pattern.compile(validRegex).matcher(phoneNumbersString);

                while (matcher.find()) {
                    phoneNumbersList.add(matcher.group());
                }

                for (String s : phoneNumbersList) {
                    result.append(s).append("\n");
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    private String wordsFrequency() {
        StringBuilder result = new StringBuilder();

        try {
            Map<String, Integer> map = new HashMap<>();
            BufferedReader reader = new BufferedReader(new FileReader("words.txt"));
            String words;

            while ((words = reader.readLine()) != null) {
                Scanner scan = new Scanner(words);
                while (scan.hasNext()) {
                    String word = scan.next();
                    if (map.containsKey(word))
                        map.put(word, map.get(word) + 1);
                    else
                        map.put(word, 1);
                }
                scan.close();
            }
            reader.close();
            List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());

            entries.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

            for (Map.Entry<String, Integer> entry :entries) {
                result.append(entry.getKey()).append("\t").append(entry.getValue()).append("\n");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();

    }

    public static void main(String[] args) {
        InputOutput requestedData = new InputOutput();

        System.out.println(requestedData.validNumbers());
        System.out.println(requestedData.wordsFrequency());

    }
}
