package rectangraphic;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * Name:Arnav Madavaram
 * Username:madaa01
 */

public class RectanGraphic {
    public static void main(String[] args) {
        // Define the file path
        String filePath = "rectangles.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Trim the line and split it using regular expression for one or more whitespace characters
                String[] parts = line.trim().split("\\s+");

                // Check if we have the correct number of parts
                if (parts.length != 3) {
                    System.err.println("Invalid format: " + line);
                    continue;
                }

                try {
                    // Parse the data
                    int rows = Integer.parseInt(parts[0]);
                    int cols = Integer.parseInt(parts[1]);
                    boolean filled = parts[2].equalsIgnoreCase("filled");

                    // Create a Rectangle object
                    Rectangle rect = new Rectangle(rows, cols, filled);

                    // Print the graphical representation
                    System.out.println(rect);

                } catch (NumberFormatException e) {
                    System.err.println("Number format error: " + e.getMessage());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Array index error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

