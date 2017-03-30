package com.carlos.luke.math;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * Hello world!
 *
 */
public class BigDecimalTest {
    
    @Test
    public void test_transferToL() {
        System.out.println(transferToL(42743).doubleValue());
    }
    
    /**
     * 油耗转换，从毫升转换成升
     */
    public static BigDecimal transferToL(double fuel) {
        BigDecimal originMileage = BigDecimal.valueOf(fuel);
        return originMileage.divide(new BigDecimal("1000")).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * BigDecimal进行四舍五入的除法
     */
    public static double divideHalf(double dividend, double divisor) {
        BigDecimal dividenDecimal = BigDecimal.valueOf(dividend);
        BigDecimal divisorDecimal = BigDecimal.valueOf(divisor);
        return dividenDecimal.divide(divisorDecimal, 2,
                BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    @Test
    public void test_divideHalf() {
        System.out.println(divideHalf(34761*100, 183000));
    }
}
