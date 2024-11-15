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

    //Use Case 2: Single Number Validation
    @Test
    public void testSingleNumberWithoutDelimiter() {
        assertEquals(1, stringCalculator.add("1"));
        assertEquals(0, stringCalculator.add("0"));
        assertEquals(1, stringCalculator.add("01"));
    }

}
