package bullscows;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please, enter the secret code's length:");
        int length = new Scanner(System.in).nextInt();
        if (length > 10){
            System.out.println("Error: can't generate a secret number with a length of " + length
                    + " because there aren't enough unique digits.");
        }else{
            System.out.println("Okay, let's start a game!");
            int secretNumber = randomNumberGenerator(length);
            grader(secretNumber);
        }
    }



    public static void grader(int secret) {
        int counter = 1;
        while(true){
            System.out.println("Turn " + counter + ":");
            ++counter;
            String code = new Scanner(System.in).next();
            String[] stringCode = String.valueOf(code).split("");
            String[] secretNumber = String.valueOf(secret).split("");
            int bulls = 0;
            int cows = 0;
            for (int i = 0; i < secretNumber.length; i++) {
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
                System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s)");
            }else if (bulls != 0 && cows == 0){
                System.out.println("Grade: " + bulls + " bull(s).");
                if(bulls == secretNumber.length){
                    System.out.println("Congratulations! You guessed the secret code.");
                    break;
                }
            }else if (bulls == 0 && cows != 0){
                System.out.println("Grade: " + cows + " cow(s).");
            } else{
                System.out.println("Grade: None.");
            }
        }
    }

    public static int randomNumberGenerator(int length){
        Random random = new Random(System.nanoTime());
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
        return Integer.parseInt(number.toString());
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

















