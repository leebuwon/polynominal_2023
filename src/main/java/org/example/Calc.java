package org.example;

import java.security.UnrecoverableEntryException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {

    public static int run(String str){
        str = str.trim();
        str = StripOutBracket(str);

        // 단항이 입력되면 그대로 리턴한다.
        if (!str.contains(" ")) return Integer.parseInt(str);

        boolean needToMultiple = str.contains("*");
        boolean needToPlusAndMinus = str.contains("+") || str.contains("-");

        boolean needToCompound = needToMultiple && needToPlusAndMinus;

        boolean needToBracket = str.contains("(") || str.contains(")");

        if (needToBracket){
            int bracketCount = 0;
            int splitPointIndex = -1;

            for (int i = 0; i < str.length(); i++){
                if (str.charAt(i) == '('){
                    bracketCount++;
                }else if (str.charAt(i) == ')'){
                    bracketCount--;
                }

                if (bracketCount == 0) {
                    splitPointIndex = i;
                    break;
                }
            }

            String firstStr = str.substring(0, splitPointIndex + 1);
            String secondStr = str.substring(splitPointIndex + 4);

            char operationCode = str.charAt(splitPointIndex + 2);

            str = Calc.run(firstStr) + " " + operationCode + " " + Calc.run(secondStr);

            return Calc.run(str);
        }
        else if (needToCompound){
            String[] bits = str.split(" \\+ ");

            String newStr = Arrays.stream(bits)
                    .mapToInt(Calc::run)
                    .mapToObj(e -> e + "")
                    .collect(Collectors.joining(" + "));

            return run(newStr);
        }

        if (needToBracket){
            str = str.replaceAll("( )", " ");

            String[] bits = str.split(" \\+ ");

        }

        if (needToPlusAndMinus) {
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

    // 괄호를 없애는 연산
    private static String StripOutBracket(String str) {
        int outerBracketCount = 0;

        while (str.charAt(outerBracketCount) == '(' && str.charAt(str.length() - 1 - outerBracketCount) == ')') {
            outerBracketCount++;
        }

        if ( outerBracketCount == 0) return str;

        return str.substring(outerBracketCount, str.length() - outerBracketCount);
    }
}
