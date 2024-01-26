package RecipeManagementSystem.manager;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandlerTest {

    public static void main(String[] args) {
        testGetIntInput();
        testGetLineInput();
    }

    private static void testGetIntInput() {
        InputHandler inputHandler = new InputHandler();

        // Test valid integer input
        simulateUserInput("42");
        int validInput = inputHandler.getIntInput("Enter a number: ");
        assert validInput == 42 : "Failed to get valid integer input.";

        // Test invalid input (non-integer)
        simulateUserInput("InvalidInput");
        try {
            inputHandler.getIntInput("Enter a number: ");
            // The above line should throw an InputMismatchException
            assert false : "Failed to handle invalid input.";
        } catch (InputMismatchException e) {
            System.out.println("Test: Get Int Input - Passed");
        }
    }

    private static void testGetLineInput() {
        InputHandler inputHandler = new InputHandler();

        // Test valid line input
        simulateUserInput("TestLineInput");
        String validInput = inputHandler.getLineInput("Enter a line: ");
        assert validInput.equals("TestLineInput") : "Failed to get valid line input.";

        System.out.println("Test: Get Line Input - Passed");
    }

    private static void simulateUserInput(String simulatedInput) {
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
    }
}
