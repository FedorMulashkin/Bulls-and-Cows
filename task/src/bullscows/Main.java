package bullscows;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int length = new Scanner(System.in).nextInt();
        randomNumberGenerator(length);
        //System.out.println(grader(secretNumber));
    }



    public static String grader(int secret) {
        int code = new Scanner(System.in).nextInt();
        String[] stringCode = String.valueOf(code).split("");
        String[] secretNumber = String.valueOf(secret).split("");
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < 4; i++) {
            if (secretNumber[i].equals(stringCode[i])) {
                bulls++;
            } else {
                int finalI = i;
                if (Arrays.stream(stringCode).anyMatch(number -> number.equals(secretNumber[finalI]))) {
                    cows++;
                }
            }
        }
        if (bulls != 0 && cows != 0){
            return "Grade: " + bulls + " bull(s) and " + cows + " cow(s). The secret code is " + secret;
        }else if (bulls != 0 && cows == 0){
            return "Grade: " + bulls + " bull(s). The secret code is " + secret;
        }else if (bulls == 0 && cows != 0){
            return "Grade: " + cows + " cow(s). The secret code is " + secret;
        } else{
            return "Grade: None. the secret code is " + secret;
        }
    }

    public static void randomNumberGenerator(int length){
        Random random = new Random(System.nanoTime());
        if(length > 10){
            System.out.println("Error: can't generate a secret number with a length of " + length
                    + " because there aren't enough unique digits.");
        }else{
            int temp_number;
            StringBuilder number = new StringBuilder();
            for (int i = 0; i < length; i++) {
                temp_number = random.nextInt(10);
                while (!checkUniqueness(number, temp_number)){
                    temp_number = random.nextInt(10);
                }
                number.append(temp_number);
            }
            System.out.println("The random secret number is " + number);
        }
    }

    public static boolean checkUniqueness(StringBuilder number, int newNumber){
        if (number.length() == 0){
            return true;
        } else if(number.charAt(0) == '0'){
            return false;
        }
        int temp;
        for (int i = 0; i < number.length(); i++) {
            temp = number.charAt(i) - '0';
            if (temp == newNumber){
                return false;
            }
        }
        return true;
    }
}

















