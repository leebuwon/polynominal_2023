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
            int splitPointIndex = findSplitPointIndex(str);

            String firstStr = str.substring(0, splitPointIndex);
            String secondStr = str.substring(splitPointIndex + 1);

            char operationCode = str.charAt(splitPointIndex);

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

    private static int findSplitPointIndex(String str) {
        int index = findSplitPointIndexBy(str, '+');

        if (index >= 0) return index;

        return findSplitPointIndexBy(str, '*');
    }

    private static int findSplitPointIndexBy(String str, char findChar) {
        int bracketCount = 0;

        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);

            if (c == '('){
                bracketCount++;
            }
            else if (c == ')'){
                bracketCount--;
            } else if ( c == findChar) {
                if (bracketCount == 0) return i;
            }
        }
        return -1;
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
