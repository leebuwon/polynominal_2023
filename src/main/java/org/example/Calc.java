package org.example;

public class Calc {

    public static int run(String str){

        boolean needToPlus = str.contains("+");
        boolean needToMinus = str.contains("-");

        String[] bits = null;

        if (needToPlus){
            bits = str.split(" \\+ ");
        }else if (needToMinus){
            bits = str.split(" \\- ");
        }

        int a = Integer.parseInt(bits[0]);
        int b = Integer.parseInt(bits[1]);

        if (needToPlus){
            return a + b;
        }else if (needToMinus){
            return a - b;
        }

        throw new RuntimeException("올바른 식이 아닙니다.");
    }
}
