import java.util.Scanner;

public class calcFinal {
    public static void main(String[] args) throws Exception {
        System.out.print("Введите значения от 1[I] до 10[X] в формате:\n\t\"Число 1\" \"знак\" \"Число 2\"\n\n" +
                "Примечание: недопустимо использовать арабские и римские числа одновременно!\n\nВведите выражение: ");
        Scanner scanner = new Scanner(System.in);
        calc(scanner.nextLine().replace(" ", "").toUpperCase());
    }
    public static String calc(String input) throws Exception {
        String roman[] = {
                "null", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX",
                "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        String sign[] = {"+", "-", "/", "*"};
        String signDell[] = {"\\+", "-", "/", "\\*"};
        String elem = input;

        int actionSign = -1;
        for(int i = 0; i < sign.length; i++) {
            if(elem.contains(sign[i])) {
                actionSign = i;
                //System.out.println(sign[i]);
                break;
            }
        }
        if(actionSign < 0 || actionSign > 3) throw new Exception("Строка не является математической операцией");

        String finalElem[] = elem.split(signDell[actionSign]);

        int numberOne = 0,
                numberTwo = 0;
        boolean checkRomanOne = false,
                checkRomanTwo = false;

        for(int i = 0; i <= 10; i++) {
            if(finalElem[0].equals(roman[i])) {
                numberOne = i++;
                checkRomanOne = true;
                break;
            }
        }
        for(int i = 0; i <= 10; i++) {
            if(finalElem[1].equals(roman[i])) {
                numberTwo = i++;
                checkRomanTwo = true;
                break;
            }
        }
        if(checkRomanOne == false && checkRomanTwo == false) {
            numberOne = Integer.parseInt(finalElem[0]);
            numberTwo = Integer.parseInt(finalElem[1]);
        }

        if(checkRomanOne == true || checkRomanTwo == true || numberOne > 10 || numberTwo > 10) {
            if (!finalElem[0].equals(roman[numberOne]) || !finalElem[1].equals(roman[numberTwo]))
                throw new Exception("Используются одновременно разные системы счисления или числа больше 10-ти!");
        }

        String answer = "";

        switch (actionSign) {
            case 0:
                numberOne = numberOne + numberTwo;
                if(checkRomanOne == true && checkRomanTwo == true) answer = roman[numberOne];
                else answer = Integer.toString(numberOne);
                break;
            case 1:
                if(checkRomanOne == true && checkRomanTwo == true) {
                    if(numberOne < numberTwo) throw new Exception("В римской системе нет отрицательных чисел");
                    numberOne = numberOne - numberTwo;
                    answer = roman[numberOne];
                } else {
                    numberOne = numberOne - numberTwo;
                    answer = Integer.toString(numberOne);
                }
                break;
            case 2:
                if(numberTwo == 0) throw new Exception("На 0 делить нельзя!");
                numberOne = numberOne / numberTwo;
                if(checkRomanOne == true && checkRomanTwo == true) {
                    answer = roman[numberOne];
                } else answer = Integer.toString(numberOne);
                break;
            case 3:
                numberOne = numberOne * numberTwo;
                if(checkRomanOne == true && checkRomanTwo == true) {
                    answer = roman[numberOne];
                } else answer = Integer.toString(numberOne);
                break;
            default: throw new Exception("Такого знака выражения нет!");
        }
        System.out.println("Ответ: " + answer);
        return answer;
    }
}
