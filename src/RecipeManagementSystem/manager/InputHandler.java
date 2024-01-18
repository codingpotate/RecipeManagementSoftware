package RecipeManagementSystem.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputHandler {
    private final BufferedReader reader;

    public InputHandler() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }

    public String getStringInput(String prompt) {
        try {
            System.out.print(prompt);
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading input.");
            return "";
        }
    }

    public String getLineInput(String prompt) {
        try {
            System.out.print(prompt);
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading input.");
            return "";
        }
    }

    public void closeReader() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add the nextLine method
    public String nextLine(String prompt) {
        try {
            System.out.print(prompt);
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading input.");
            return "";
        }
    }
}
