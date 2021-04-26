package com.nc;

import static org.junit.jupiter.api.Assertions.*;

class CalculationCoreTest {

    @org.junit.jupiter.api.Test
    void calculation() throws Exception {
        String example = "(1*3+9-6)*6/4^2";
        System.out.println(CalculationCore.Calculation(example));
    }
}