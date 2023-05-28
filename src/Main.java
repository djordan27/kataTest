import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input: ");
        String lineInput = scanner.nextLine();
        String output = calc(lineInput);
        System.out.println("Output:\n" + output);
    }

    public static String calc(String input) {
        String[] array = input.split(" ");
        int count = 0;
        int reternNum = 0;
        String[] nameOfNumeral = new String[2];
        int countForNameOfNumeral = 0;
        ArrayList<Integer> num = new ArrayList<>();
        int sizeOfMathematicalOperators = 0;
        int sizeOfNum = 0;
        for (int i = 0; i < array.length; i++) {
            if (i % 2 == 0) sizeOfNum++;
            else sizeOfMathematicalOperators++;
        }
        if (sizeOfMathematicalOperators > 1 || sizeOfNum > 2) {
            try {
                throw new IOException();
            } catch (IOException e) {
                return "throws Exception //т.к. формат математической операции не удовлетворяет заданию " +
                        "- два операнда и один оператор (+, -, /, *)";
            }
        } else if (sizeOfMathematicalOperators < 1 || sizeOfNum < 2) {
            try {
                throw new IOException();
            } catch (IOException e) {
                return "throws Exception //т.к. строка не является математической операцией";
            }
        }
        for (int i = 0; i < 2; i++) {
            if (stringArabToInt(array[count]) > 0 || array[count].equals("0")) {
                if (array[count].equals("0")) num.add(0);
                else num.add(stringArabToInt(array[count]));
                nameOfNumeral[countForNameOfNumeral] = "Arab";
                countForNameOfNumeral++;
                count += 2;
            } else if (array[0].charAt(0) == '/' || array[0].charAt(0) == ('*')) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    return "throws Exception //т.к. строка не является математической операцией";
                }
            } else if (romanNumberToArabic(array[count]) >= 0) {
                num.add(romanNumberToArabic(array[count]));
                nameOfNumeral[countForNameOfNumeral] = "Roman";
                countForNameOfNumeral++;
                count += 2;
            } else if (stringArabToInt(array[count]) > 10 || romanNumberToArabic(array[count]) > 10) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    return "throws Exception //т.к. число больше 10";
                }
            } else if (stringArabToInt(array[count]) < 0) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    return "throws Exception //т.к. допустимо чило от 0 до 10";
                }

            }
        }
        if (!nameOfNumeral[0].equals(nameOfNumeral[1])) {
            try {
                throw new IOException();
            } catch (IOException e) {
                return "throws Exception //т.к. используются одновременно разные системы счисления";
            }

        }
        switch (array[1]) {
            case "+" -> reternNum = num.get(0) + num.get(1);
            case "-" -> reternNum = num.get(0) - num.get(1);
            case "*" -> reternNum = num.get(0) * num.get(1);
            case "/" -> reternNum = num.get(0) / num.get(1);
        }
        if (nameOfNumeral[0].equals("Roman") && reternNum < 0) {
            try {
                throw new IOException();
            } catch (IOException e) {
                return "throws Exception //т.к. в римской системе нет отрицательных чисел";
            }
        } else if (num.get(0) > 10 || num.get(1) > 10) {
            try {
                throw new IOException();
            } catch (IOException e) {
                return "throws Exception //т.к. число больше 10";
            }
        }
        String stringReturnNum;
        if (nameOfNumeral[0].equals("Roman")) {
            stringReturnNum = printRomaNum(reternNum);
        } else {
            stringReturnNum = Integer.toString(reternNum);
        }

        return stringReturnNum;
    }

    static int stringArabToInt(String input) {
        int returnNumber = 0;
        try {
            returnNumber = Integer.parseInt(input);

        } catch (IllegalArgumentException exception) {
        }

        return returnNumber;
    }

    static String printRomaNum(int arabResult) {
        String result = "0";
        if (arabResult <= 10) {
            ArabNumeral arabNumeral = ArabNumeral.valueOf("NUM" + arabResult);
            result = arabNumeral.getRomanNumeral();
        } else if (arabResult < 40) {
            int countRetern = arabResult / 10;
            int remainder = arabResult % 10;
            ArabNumeral arabNumeral = ArabNumeral.NUM10;
            String romaNum = "X";
            for (int i = 0; i < countRetern - 1; i++) {
                romaNum += arabNumeral.getRomanNumeral();
            }
            if (remainder > 0) {
                ArabNumeral arabNumeral2 = ArabNumeral.valueOf("NUM" + remainder);
                romaNum += arabNumeral2.getRomanNumeral();
            }
            result = romaNum;
        } else if (arabResult < 50) {
            int remainder = arabResult % 10;
            result = "XL";
            ArabNumeral arabNumeral1 = ArabNumeral.valueOf("NUM" + remainder);
            result += arabNumeral1;
        } else if (arabResult == 50) {
            result = "L";
        } else if (arabResult < 90) {
            int countRetern = (arabResult - 50) / 10;
            int remainder = arabResult % 10;
            ArabNumeral arabNumeral1 = ArabNumeral.NUM10;
            String romaNum = "L";
            for (int i = 0; i < countRetern; i++) {
                romaNum += arabNumeral1.getRomanNumeral();
            }
            if (remainder > 0) {
                ArabNumeral arabNumeral2 = ArabNumeral.valueOf("NUM" + remainder);
                result = romaNum + arabNumeral2.getRomanNumeral();
            }
        } else if (arabResult < 100) {
            int remainder = arabResult % 10;
            String romaNum = "XC";
            if (remainder > 0) {
                ArabNumeral arabNumeral2 = ArabNumeral.valueOf("NUM" + remainder);
                romaNum = romaNum + arabNumeral2.getRomanNumeral();
                result = romaNum;
            }
        } else if (arabResult == 100) {
            result = "C";
        }
        return result;
    }


    static int romanNumberToArabic(String input) {
        int[] romanNumeral = new int[input.length()];
        char[] stringRoman = input.toCharArray();
        String[] myStringRoma = new String[stringRoman.length];
        RomanNumeral romanNumeral1;
        for (int i = 0; i < stringRoman.length; i++) {
            myStringRoma[i] = String.valueOf(stringRoman[i]);
            romanNumeral1 = RomanNumeral.valueOf(myStringRoma[i]);
            romanNumeral[i] = romanNumeral1.getArabicNumeral();
        }

        int returnNumber = romanNumeral[romanNumeral.length - 1];

        if (input.length() > 1 && romanNumeral[(romanNumeral.length - 1)] > romanNumeral[romanNumeral.length - 2]) {
            for (int i = romanNumeral.length - 1; i > 0; i--) {
                returnNumber -= romanNumeral[i - 1];
            }
        } else if (input.length() > 1) {
            for (int i = romanNumeral.length - 1; i > 0; i--) {
                returnNumber = returnNumber + romanNumeral[i - 1];
            }
        }
        return returnNumber;
    }
}
