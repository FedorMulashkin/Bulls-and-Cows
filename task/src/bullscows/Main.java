package bullscows;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Random random = new Random();
        //int secretNumber = random.nextInt(1000, 10000);
        int secretNumber = 9305;
        System.out.println(grader(secretNumber));
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
}