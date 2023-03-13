package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {

    public static int run(String str){
        // 단항이 입력되면 그대로 리턴한다.
        if (!str.contains(" ")) return Integer.parseInt(str);

        boolean needToMultiple = str.contains("*");
        boolean needToPlus = str.contains("+");

        boolean needToCompound = needToMultiple && needToPlus;

        if (needToCompound){
            String[] bits = str.split(" \\+ ");

            String newStr = Arrays.stream(bits)
                    .mapToInt(Calc::run)
                    .mapToObj(e -> e + "")
                    .collect(Collectors.joining(" + "));

            return run(newStr);
        }

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
