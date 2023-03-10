package org.example;

public class Calc {

    public static int run(String str){

        str = str.replaceAll("- ", "+ -");

        String[] bits = str.split(" \\+ ");

        int sum = 0;

        for (int i = 0; i < bits.length; i++){
            sum += Integer.parseInt(bits[i]);
        }

        return sum;
    }
}
