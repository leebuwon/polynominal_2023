package org.example;

public class Calc {

    public static int run(String str){

        str = str.replaceAll("- ", "+ -");

        String[] bits = str.split(" \\+ ");

        int a = Integer.parseInt(bits[0]);
        int b = Integer.parseInt(bits[1]);
        int c = 0;

        if (bits.length > 2){
            c = Integer.parseInt(bits[2]);
        }

        return a + b + c;
    }
}
