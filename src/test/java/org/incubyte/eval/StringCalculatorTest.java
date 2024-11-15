package org.incubyte.eval;

import org.incubyte.eval.StringCalculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for String Calculator
 *
 * @author mmuthusamy
 */
public class StringCalculatorTest {

    StringCalculator stringCalculator;

    @BeforeEach
    public void init() {
        stringCalculator = new StringCalculator();
    }

    @AfterEach
    public void destroy() {
        stringCalculator = null;
    }

    //Use Case 1: Empty String Validation
    @Test
    public void testEmptyString() {
        assertEquals(0, stringCalculator.add(""));
    }

    //Use Case 2a: Single Number Validation
    @Test
    public void testSingleNumberWithoutDelimiter() {
        assertEquals(1, stringCalculator.add("1"));
        assertEquals(0, stringCalculator.add("0"));
        assertEquals(1, stringCalculator.add("01"));
    }

    //Use Case 2b: Multiple Number Validation
    @Test
    public void testMultipleNumberWithDefaultDelimiter() {
        assertEquals(6, stringCalculator.add("1,2,3"));
        assertEquals(7, stringCalculator.add("0,3,4"));
        assertEquals(9, stringCalculator.add("01,8"));
        assertEquals(6, stringCalculator.add("2, ,4"));
    }

    //Use Case 3: With new line delimiter Validation
    @Test
    public void testNewLineDelimiter() {
        assertEquals(6, stringCalculator.add("1\n2,3"));
        assertEquals(7, stringCalculator.add("0,3\n4"));
        assertEquals(9, stringCalculator.add("01\n8"));
        assertEquals(6, stringCalculator.add("2, \n4"));
    }

    //Use Case 4: With different line delimiter Validation
    @Test
    public void testDifferentDelimiter() {
        assertEquals(3, stringCalculator.add("//;\n1;2"));
        assertEquals(8, stringCalculator.add("//;\n1,2;5"));
        assertEquals(20, stringCalculator.add("//;\n1;2;6;7,4"));
    }

    //Use Case 4: With negative number delimiter Validation
    @Test
    public void testNegativeNumber() {
        try {
            stringCalculator.add("//;\n-11;2");
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "negatives not allowed : -11");
        }
        try {
            stringCalculator.add("//;\n1,2;-5");
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "negatives not allowed : -5");
        }
        try {
            stringCalculator.add("//;\n-1,-2;-5,-8,-3");
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "negatives not allowed : -1,-2,-5,-8,-3");
        }
    }
}
