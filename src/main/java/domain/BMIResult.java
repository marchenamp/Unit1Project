/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author march
 */
import java.io.Serializable;

public class BMIResult implements Serializable {
    private double bmiValue;
    private String bmiMeaning;

    public BMIResult(double bmiValue, String bmiMeaning) {
        this.bmiValue = bmiValue;
        this.bmiMeaning = bmiMeaning;
    }

    public double getBmiValue() {
        return bmiValue;
    }

    public void setBmiValue(double bmiValue) {
        this.bmiValue = bmiValue;
    }

    public String getBmiMeaning() {
        return bmiMeaning;
    }

    public void setBmiMeaning(String bmiMeaning) {
        this.bmiMeaning = bmiMeaning;
    }
}

