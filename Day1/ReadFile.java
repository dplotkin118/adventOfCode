import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.numberOfLeadingZeros;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class ReadFile {

    public static ArrayList<String> readFile() throws IOException {
        ArrayList<String> words = new ArrayList<>(25);
        Files.lines(new File("C:\\Users\\d117340\\adventday1\\src\\input.txt").toPath())
                .forEach(words::add);

        return words;
    }

    public static int readArray(ArrayList<String> stringArrayList) {
        int total = 0;
        for(int i = 0; i < stringArrayList.size(); i++) {
            ArrayList<String> word = new ArrayList<>();
            for (int j = 0; j < stringArrayList.get(i).length(); j++) {

                if (stringArrayList.get(i).charAt(j) >= '0' && (stringArrayList.get(i).charAt(j) <= '9')) {
                    word.add(valueOf(stringArrayList.get(i).charAt(j)));
                }
            }
            if (word.size() > 1) {
                total += parseInt(word.get(0) + word.get(word.size() - 1));
            }
            else {
                total += parseInt(word.get(0) + word.get(0));
            }
        }
        return total;
    }

    public static List<String> regexMatch(String input) {
        Pattern p = Pattern.compile("\\d|oneight|threeight|fiveight|nineight|twone|sevenine|eightwo|eighthree|one|two|three|four|five|six|seven|eight|nine");
        Matcher m = p.matcher(input);
        return Arrays.asList(m.results().map(MatchResult::group).toArray(String[]::new));
    }

    public static int problem_two(ArrayList<String> stringArrayList) {
        List<String> weirdVals = Arrays.asList("oneight", "threeight", "fiveight", "nineight", "twone", "sevenine", "eightwo", "eighthree");

        int total = 0;
        for(int i = 0; i < stringArrayList.size(); i++) {
            ArrayList<List<String>> word = new ArrayList<>();
            for (int j = 0; j < stringArrayList.get(i).length(); j++) {
                word.add(regexMatch(stringArrayList.get(i)));

            }
            List<String> finalString = convertToNumbers(word.get(0), createWordToNumberMap());
            System.out.println(Arrays.toString(finalString.toArray()));
            // convert list to numeric list
            if (finalString.size() > 1) {
                total += parseInt((finalString.get(0)) + finalString.get(finalString.size() - 1));
            }
            else {
                total += parseInt(finalString.get(0) + finalString.get(0));
            }
            word.remove(0);
        }
        return total;
    }

    private static Map<String, String> createWordToNumberMap() {
        Map<String, String> map = new HashMap<>();
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");
        map.put("five", "5");
        map.put("six", "6");
        map.put("seven", "7");
        map.put("eight", "8");
        map.put("nine", "9");
        map.put("oneight", "18");
        map.put("threeight", "38");
        map.put("fiveight", "58");
        map.put("nineight", "98");
        map.put("twone", "21");
        map.put("sevenine", "79");
        map.put("eightwo", "82");
        map.put("eighthree", "83");
        return map;
    }

    private static List<String> convertToNumbers(List<String> words, Map<String, String> wordToNumberMap){
        List<String> numericValues = new ArrayList<>();
        for (String word: words) {
            if (word.matches("\\d")) {
                numericValues.add(word);
            }
            else {
                String numericValue = wordToNumberMap.get(word.toLowerCase());
                if (parseInt(numericValue) > 9) {
                    numericValues.add(String.valueOf(numericValue.charAt(0)));
                    numericValues.add(String.valueOf(numericValue.charAt(1)));
                }
                else if (parseInt(numericValue) <= 9) {
                    numericValues.add(numericValue);
                }
            }
        }
        return numericValues;
    }
}
