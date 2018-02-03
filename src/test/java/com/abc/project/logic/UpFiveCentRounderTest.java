package com.abc.project.logic;

import com.abc.project.logic.rounder.UpFiveCentRounder;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

public class UpFiveCentRounderTest {
    /************************
     ROUND UP TO THE NEAREST FIVE CENT
     *************************/

    //Goal: 4.1985 rounded to 4.20
    @Test //Input: { 4.1985 } - ExpectedOutput: { 4.20 }
    public void roundNearestFiveCent_Input_ExpectedOutput01(){
        //Setup
        BigDecimal input = new BigDecimal("4.1985");
        BigDecimal expectedResult = new BigDecimal("4.20");
        UpFiveCentRounder e = new UpFiveCentRounder();

        //Execute
        BigDecimal result = e.round(input);

        //Verify
        assertEquals(expectedResult,result);

    }

    //Goal: 0.5625 rounded to 0.60
    @Test //Input: { 0.5625 } - ExpectedOutput: { 0.60 }
    public void roundNearestFiveCent_Input_ExpectedOutput02(){
        //Setup
        BigDecimal input = new BigDecimal("0.5625");
        BigDecimal expectedResult = new BigDecimal("0.60");
        UpFiveCentRounder e = new UpFiveCentRounder();

        //Execute
        BigDecimal result = e.round(input);

        //Verify
        assertEquals(expectedResult,result);

    }

}
