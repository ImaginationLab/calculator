package com.nc;

class Test {
    /**
     * 还有bug 慢慢修
     * @param args
     * @throws Exception
     */
    public static void CalRun(String[] args) throws Exception {
        String example = "(1*3+9-6)*6/4^2";
        System.out.println(CalculationCore.Calculation(example));
    }
}
