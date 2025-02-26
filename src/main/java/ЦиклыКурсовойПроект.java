import java.io.File;
import java.util.Scanner;

public class ЦиклыКурсовойПроект {
    public static void main(String[] args) {
        courseProject();
    }

    public static void courseProject() {
        boolean файлСуществует = false;
        boolean папкаСуществует = false;
        Scanner sc = new Scanner(System.in);
        String путь = "";
        File file = new File(путь);
        int количествоСуществующихФайлов=0;

        while (true) {
            System.out.println("Введите полный путь к файлу: ");
            путь= sc.nextLine();
            file = new File(путь);
            файлСуществует=file.isFile();
            папкаСуществует=file.isDirectory();
            if (!файлСуществует || папкаСуществует) {
                System.out.println("Файл не существует или путь является путём к папке, а не к файлу");
                continue;
            } else if (файлСуществует) {
                количествоСуществующихФайлов+=1;
                System.out.println("Путь указан верно");
                System.out.println("Это файл номер: " + количествоСуществующихФайлов);
            }
        }
    }
}