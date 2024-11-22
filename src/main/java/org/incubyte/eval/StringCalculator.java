package org.incubyte.eval;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * StringCalculator - Calculation of numbers from String.
 *
 * @author mmuthusamy
 */
public class StringCalculator {

    /**
     * add - Method to add numbers from the input
     *
     * @param numbers - String
     * @return - Integer
     */
    public int add(String numbers) {

        int result = 0;
        if(numbers == null || numbers.isEmpty() || numbers.trim().isEmpty()) {
            return result; //Use Case 1 - Empty Check
        } else { //Use Case 2 - Single number addition

            //Delimiter collection logic
            String delimiter = ",|\n"; // With comma and new line delimiter
            if (numbers.startsWith("//")) {//Different Delimiters
                String delimiters = getDelimiters(numbers);
                delimiter += "|" + delimiters;
                numbers = numbers.substring(numbers.indexOf("\n") + 1);
            }
            StringBuilder negativeNumbers = new StringBuilder(); // To capture negative numbers
            delimiter = checkReservedCharAndReplace(delimiter);
            boolean isMultiplication = delimiter.endsWith("*");
            /*if(delimiter.endsWith("*")){
                result = Arrays.stream(numbers.split(delimiter))
                        .mapToInt(Integer::parseInt).reduce((v1,v2)-> v1 * v2).getAsInt();
            } else {*/
                //SUM Logic
                result = Arrays.stream(numbers.split(delimiter))
                        .filter(val -> !val.trim().isEmpty())
                        .map(val -> {
                            int num = Integer.parseInt(val);
                            if (num < 0) {
                                negativeNumbers.append(num).append(",");
                            }
                            return num;
                        }).filter(val -> val <= 1000).reduce((v1,v2) -> {
                           if(isMultiplication){
                               return v1 * v2;
                           } else {
                               return v1 + v2;
                           }
                        }).get();
            /*}*/
            //Negative number exception
            if (!negativeNumbers.isEmpty()) {
                throw new RuntimeException("negatives not allowed : "
                        + negativeNumbers.substring(0, negativeNumbers.length() - 1));
            }
            return result;
        }
    }

    /**
     * checkReservedCharAndReplace - To replace reserved char with escape sequence
     * This helps to avoid exception while split the string.
     *
     * @param delimiter
     * @return
     */
    private String checkReservedCharAndReplace(String delimiter) {
        List<String> reservedChar = Arrays.asList("*", "?", "+");
        for (String val : reservedChar) {
            if (delimiter.contains(val)) {
                delimiter = delimiter.replace(val, "\\" + val);
            }
        }
        return delimiter;
    }

    /**
     * getDelimiters - To collect one or multiple delimiters in input
     *
     * @param numbers
     * @return
     */
    private String getDelimiters(String numbers) {
        String delimiter = "";
        if (numbers.matches("//\\[(.*)\\]\\[(.*)\\](.*)\n(.*)")) {
            String delimiterStr = numbers.substring(0, numbers.indexOf("\n"));
            while (!delimiterStr.isEmpty()) {
                int startIdx = delimiterStr.indexOf("[") + 1;
                int endIdx = delimiterStr.indexOf("]");
                delimiter += delimiterStr.substring(startIdx, endIdx) + "|";
                delimiterStr = delimiterStr.substring(endIdx + 1, delimiterStr.length());
            }
        } else if (numbers.matches("//\\[(.*)\\](.*)\n(.*)")) {
            delimiter += numbers.substring(numbers.indexOf("[") + 1, numbers.indexOf("]")) + "|";
        } else if (numbers.matches("//(.*)\n(.*)")) {
            delimiter += numbers.charAt(2) + "|";
        }
        return delimiter.endsWith("|") ? delimiter.substring(0, delimiter.length() - 1) : delimiter;
    }
}
