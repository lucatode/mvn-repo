package com.abc.project.logic.rounder;

import java.math.BigDecimal;

public class UpFiveCentRounder implements DecimalRounder{

    public BigDecimal round(BigDecimal value) {
        double doubleResult = Math.ceil( value.doubleValue() * 20.00)/20.00;
        BigDecimal result = BigDecimal.valueOf(doubleResult)
                .setScale(2,  BigDecimal.ROUND_HALF_UP);
        return result;
    }
}
