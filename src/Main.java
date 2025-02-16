import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите первое число");
        int number = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число");
        int number2 = new Scanner(System.in).nextInt();
        System.out.print("Сумма: ");
        System.out.println(number + number2);
        System.out.print("Разность: ");
        System.out.println(number - number2);
        System.out.print("Произведение: ");
        System.out.println(number * number2);
        System.out.print("Частное: ");
        System.out.println((double) number / number2);
    }
}
