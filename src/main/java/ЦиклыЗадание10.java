import java.util.Scanner;
public class ЦиклыЗадание10 {
    public static void main(String[] args) {
        guessGame();
    }
    public static void guessGame() {
        int счетчик =0;
        int randomNum = 3;
        Scanner sc = new Scanner(System.in);
        int x;
        do {
            System.out.println("What number am I thinking (0 to 9)? :");
            x = sc.nextInt();
            счетчик+=1;
            if (x != randomNum) {
                System.out.println("No, try again");
            } else {
                System.out.println("Yes, it`s " + randomNum);
            }
        } while (randomNum!=x);
        System.out.println("Количество попыток: " + счетчик);
    }
}
