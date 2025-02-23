package madlib;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;

/*
 * Name:Arnav Madavaram
 * Username:madaa01
 */

public class MadLib {

    public static void main(String[] args) throws IOException {
        System.out.println("""
                Welcome to Mad Libs! 
                You will be prompted to enter words to fill in the blanks of a story. 
                Once you have entered all the words, your Mad Lib will be saved to a file. 
                Let's get started!
                """);

        // Count how many mad libs are in the directory "madlibs"
        File folder = new File("madlibs"); // Folder containing mad libs
        File[] madLibFiles = folder.listFiles();
        int libCount = madLibFiles.length / 2; // Each mad lib has two files

        // Sort the madLibFiles
        Arrays.sort(madLibFiles, (f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()));

        // Select a random mad lib
        int libNum = (int) (Math.random() * libCount);
        File wordsFile = madLibFiles[libNum * 2];
        File libFile = madLibFiles[libNum * 2 + 1]; // Word and madlib files are next to each other

        // Sanity check that you have the expected files
        System.out.println("Mad lib: " + libFile.getName());
        System.out.println("Word file: " + wordsFile.getName() + "\n");

        // Count how many words are in the mad lib words file
        long blankCount = Files.lines(wordsFile.toPath()).count();
        String[] words = new String[(int) blankCount];

        // Prompt user for each word
        Scanner sc = new Scanner(System.in);
        Files.lines(wordsFile.toPath()).forEach(line -> {
            System.out.print(line + ": ");
            words[Arrays.asList(words).indexOf(null)] = sc.nextLine();
        });

        // Create a PrintWriter to write the result to madlib-result.txt
        PrintWriter writer = new PrintWriter("madlib-result.txt", "UTF-8");

        // Read the template and replace <> with the user's input
        String[] madLibText = Files.readAllLines(libFile.toPath()).toArray(new String[0]);
        int wordIndex = 0;

        for (String line : madLibText) {
            while (line.contains("<>")) {
                line = line.replaceFirst("<>", words[wordIndex]);
                wordIndex++;
            }
            writer.println(line);
        }

        // Close the PrintWriter
        writer.close();
        System.out.println("View your Mad Lib in madlib-result.txt!");

        // Close the scanner
        sc.close();
    }
}