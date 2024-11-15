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
            String delimiter = ",|\n";
            result = Arrays.stream(numbers.split(delimiter))
                    .filter(val -> !val.trim().isEmpty()).map(Integer::parseInt).reduce(0, Integer::sum);
            return result;
        }
    }
}
