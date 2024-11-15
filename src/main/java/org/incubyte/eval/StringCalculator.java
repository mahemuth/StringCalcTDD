package org.incubyte.eval;

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
        if(numbers == null || numbers.isEmpty() || numbers.trim().isEmpty()){
            return 0; //Use Case 1
        } else { //Use Case 2 - Single number addition
            return Integer.parseInt(numbers);
        }
    }
}
