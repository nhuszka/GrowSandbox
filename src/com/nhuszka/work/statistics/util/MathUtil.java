package com.nhuszka.work.statistics.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtil {

    public static BigDecimal computePercentage(int part, int whole) {
        BigDecimal partBD = getBigDecimalOf(part);
        BigDecimal wholeBD = getBigDecimalOf(whole);

        return partBD.divide(wholeBD, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2);
    }

    public static BigDecimal getBigDecimalOf(Integer intValue) {
        return new BigDecimal(intValue);
    }
}
