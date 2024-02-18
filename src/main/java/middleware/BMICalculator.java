/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package middleware;

import domain.BMIResult;
import domain.Person;

/**
 *
 * @author march
 */
public class BMICalculator {
    public static BMIResult calculateBMI(Person person) {
        double bmiValue = person.getWeight() / Math.pow(person.getHeight(), 2);
        String bmiMeaning = getBMIMeaning(bmiValue);
        return new BMIResult(bmiValue, bmiMeaning);
    }

    private static String getBMIMeaning(double bmiValue) {
        if (bmiValue < 18.5) {
            return "Thin";
        } else if (bmiValue >= 18.5 && bmiValue <= 24.9) {
            return "Healthy";
        } else if (bmiValue >= 25 && bmiValue <= 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}

