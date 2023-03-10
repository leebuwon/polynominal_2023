package org.example;

public class Calc {

    public static int run(String str){
        boolean needToMultiple = str.contains("*");
        boolean needToPlus = !needToMultiple;

        if (needToPlus) {
            str = str.replaceAll("- ", "+ -");

            String[] bits = str.split(" \\+ ");

            int sum = 0;

            for (int i = 0; i < bits.length; i++) {
                sum += Integer.parseInt(bits[i]);
            }

            return sum;
        }
        else if (needToMultiple){
            String[] bits = str.split(" \\* ");

            int sum = 1;

            for (int i = 0; i < bits.length; i++){
                sum *= Integer.parseInt(bits[i]);
            }
            return sum;
        }

        throw new RuntimeException("올바른 식이 아닙니다.");
    }
}
