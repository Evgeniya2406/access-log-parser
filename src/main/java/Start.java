public class Start {
    public static void main(String[] args) {
        System.out.println("Задание 1: Создание экземпляров дробей: ");
        Fraction f1 = new Fraction(1,3);
        Fraction f2 = new Fraction(2,5);
        Fraction f3 = new Fraction(7,8);

        System.out.println(f1);
        System.out.println(f2);

        System.out.println("Задание 2: Посчитать f1.sum(f2).sum(f3).minus(5), где f1 это одна треть, f2 две пятых, а f3 это семь восьмых: ");
        Fraction result1 = new Fraction( f1.add(f2).add(f3).substract(5));
        System.out.println(result1);
        System.out.println("Проверка: ");
        Fraction result2 = new Fraction(result1.substract(f2).substract(f3).add(5));
        System.out.println(result2);
    }
}
