import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String value = reader.readLine();
        System.out.println(calc(value));
    }

    public static String calc(String input) {
        try {
            String[] values = input.split(" ");
            if (values.length != 3) throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            if (isArabic(values[0]) && isArabic(values[2])) {
                var number1 = Integer.parseInt(values[0]);
                var number2 = Integer.parseInt(values[2]);
                if ((number1 < 0) || (number1 > 10) || (number2 < 0) || (number2 > 10)) {
                    throw new IllegalArgumentException("Только числа от 1 до 10");
                }
                switch (values[1]) {
                    case "+": {
                        return(String.valueOf(number1 + number2));
                    }
                    case "-": {
                        return(String.valueOf(number1 - number2));
                    }
                    case "*": {
                        return(String.valueOf(number1 * number2));
                    }
                    case "/": {
                        return(String.valueOf(number1 / number2));
                    }
                    default:
                        throw new Exception("Допустимые операции: +, -, /, *");
                }

            } else if (!isArabic(values[0]) && !isArabic(values[2])) {
                var number1 = romanToArabic(values[0]);
                var number2 = romanToArabic(values[2]);
                var result = 0;
                if ((number1 < 1) || (number1 > 10) || (number2 < 1) || (number2 > 10)) {
                    throw new IllegalArgumentException("Только числа от 1 до 10");
                }
                switch (values[1]) {
                    case "+": {
                        result = number1 + number2;
                        break;
                    }
                    case "-": {
                        result = number1 - number2;
                        break;
                    }
                    case "*": {
                        result = number1 * number2;
                        break;
                    }
                    case "/": {
                        result = number1 / number2;
                        break;
                    }
                    default : {
                        throw new Exception("Допустимые операции: +, -, /, *");
                    }
                }
                if (result < 0) {
                    throw new Exception("в римской системе нет отрицательных чисел");
                }
                return arabicToRoman(result);
            }
            else {
                throw new Exception("используются одновременно разные системы счисления");
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    static boolean isArabic(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static Integer romanToArabic(String s) {
        Map<String, Integer> romeMap = new HashMap<>();
        romeMap.put("I", 1);
        romeMap.put("II", 2);
        romeMap.put("III", 3);
        romeMap.put("IV", 4);
        romeMap.put("V", 5);
        romeMap.put("VI", 6);
        romeMap.put("VII", 7);
        romeMap.put("VIII", 8);
        romeMap.put("IX", 9);
        romeMap.put("X", 10);
        if (romeMap.containsKey(s)) {
            return romeMap.get(s);
        }
        return null;
    }

    static String arabicToRoman(Integer num) {
        StringBuilder total = new StringBuilder();
        int[] arabArray = new int[]
                {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romeArray = new String[]
                {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arabArray.length; i++) {
            while ((num - arabArray[i]) >= 0) {
                num -= arabArray[i];
                total.append(romeArray[i]);
            }
        }
        return total.toString();
    }
}
