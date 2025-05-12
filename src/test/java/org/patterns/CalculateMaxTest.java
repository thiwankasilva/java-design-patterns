package org.patterns;

import org.junit.jupiter.api.Test;
import org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CalculateMaxTest {
    @Test
    public void should_ReturnMax_When_Array_IsGiven()
    {
        CalculatorMax calculateMax = new CalculatorMax((ArrayList) Arrays.asList(23,54,343,345));
        assertThat(calculateMax.calculate(),is(345));
    }

}
