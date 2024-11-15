package org.incubyte.eval;

import java.util.Arrays;

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
    public int add(String numbers){

        int result = 0;
        if(numbers == null || numbers.isEmpty() || numbers.trim().isEmpty()){
            return result; //Use Case 1
        } else { //Use Case 2 - Single number addition
            String delimiter = ",|\n"; // With new line delimiter
            if(numbers.startsWith("//")){//Different Delimiters
                String delimiters = getDelimiters(numbers);
                delimiter += "|" + delimiters;
                numbers = numbers.substring(numbers.indexOf("\n")+1);
            }
            StringBuilder negativeNumbers = new StringBuilder();
            result = Arrays.stream(numbers.split(delimiter))
                    .filter(val -> !val.trim().isEmpty())
                    .map(val -> {
                        int num = Integer.parseInt(val);
                        if (num < 0) {
                            negativeNumbers.append(num).append(",");
                        }
                        return num;
                    }).reduce(0, Integer::sum);
            if(!negativeNumbers.isEmpty()){
                throw  new RuntimeException("negatives not allowed : "
                        + negativeNumbers.substring(0,negativeNumbers.length()-1));
            }
            return result;
        }
    }

    private String getDelimiters(String numbers) {
        String delimiter = "";
        if(numbers.matches("//(.*)\n(.*)")){
            delimiter += numbers.charAt(2) + "|";
        }
        return delimiter.endsWith("|") ? delimiter.substring(0, delimiter.length()-1) : delimiter;
    }
}
