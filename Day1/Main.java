import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
            ArrayList<String> words = ReadFile.readFile();
            int total = ReadFile.readArray(words);

            System.out.println("Problem 1 total: " + total);
            System.out.println("Problem 2 total: " + ReadFile.problem_two(words));
        }


    }