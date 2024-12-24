package org.mhr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgeCalculatorTest {

    private AgeCalculator AgeCalculator;

    @Test
    public void testSetUpCheck() {
        AgeCalculator ageCalculator = new AgeCalculator();
        String result = ageCalculator.setUpCheck();
        assertEquals("Hello from the setup", result);
    }


}
