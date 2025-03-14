package xjtlu.cpt111.assignment.quiz.test;

import org.junit.Test;
import xjtlu.cpt111.assignment.quiz.my_util.InputUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class InputUtilTest {
    @Test
    public void testGetValidOption_ValidInput() {
        String input = "2\n"; // Mock input
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in); // Redirect standard input

        Scanner scanner = new Scanner(System.in);
        int result = InputUtil.getValidOption(scanner, "Choose option (1-3): ", 3);
        assertEquals(2, result);
    }

    @Test
    public void testGetValidOption_InvalidInput() {
        String input = "abc\n1\n"; // Mock input with invalid followed by valid
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        int result = InputUtil.getValidOption(scanner, "Choose option (1-3): ", 3);
        assertEquals(1, result);//assert if 1, because abc is invalid
    }

    @Test
    public void testGetValidOption_OutOfRangeInput() {
        String input = "4\n2\n"; // Mock input with out-of-range followed by valid
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        int result = InputUtil.getValidOption(scanner, "Choose option (1-3): ", 3);
        assertEquals(2, result);//assert if 2, because 4 is out of range
    }
}


/*
Valid Input: Returns the selected option when the user inputs a valid choice.
Invalid Input: Ignores invalid characters and waits for a valid option.
Out-of-Range Input: Ignores out-of-range options and returns the next valid choice.
*/