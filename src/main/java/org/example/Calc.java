package org.example;

public class Calc {

    public static int run(String str){

        String[] bits = str.split(" \\+ ");

        int a = Integer.parseInt(bits[0]);
        int b = Integer.parseInt(bits[1]);

        int result = a + b;

        return result;
    }

}
