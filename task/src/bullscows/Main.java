package bullscows;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please, enter the secret code's length:");
        int length = new Scanner(System.in).nextInt();
        System.out.println("Input the number of possible symbols in the code:");
        int numberOfChars = new Scanner(System.in).nextInt();
        if (length > numberOfChars){
            System.out.println("Error: can't generate a secret number with a length of " + length
                    + " because there aren't enough unique digits and letters.");
        }else{
            System.out.println("Okay, let's start a game!");
            String secretNumber = randomNumberGenerator(length, numberOfChars);
            grader(secretNumber);
        }
    }



    public static void grader(String secret) {
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

    public static String randomNumberGenerator(int length, int numberOfChars){
        ArrayList<String> chars = createListOfChars(numberOfChars);
        Random random = new Random(System.nanoTime());
        int index;
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < length; i++) {
            index = random.nextInt(numberOfChars);
            number.append(chars.get(index));
            chars.remove(index);
            numberOfChars--;
        }
        return number.toString();
    }

    private static ArrayList<String> createListOfChars(int numberOfChars) {
        ArrayList<String> availableChars = new ArrayList<>();
        char c = '0';
        if (numberOfChars > 10){
            System.out.print("The secret is prepared: " + "*".repeat(numberOfChars) + " (0-9, ");
        } else {
            System.out.println("The secret is prepared: " + "*".repeat(numberOfChars) + " (0-9).");
        }
        while (numberOfChars > 0 && c <= '9') {
            availableChars.add(String.valueOf(c));
            c++;
            numberOfChars--;
        }
        c = 'a';
        if (numberOfChars > 0){
            System.out.println("a-" + (char)(c + numberOfChars - 1) + ").");
        }
        while (numberOfChars > 0 && c <= 'z')  {
            availableChars.add(String.valueOf(c));
            c++;
            numberOfChars--;
        }
        return availableChars;
    }

    /*public static boolean checkUniqueness(StringBuilder number, int newNumber){
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
    }*/
}

















