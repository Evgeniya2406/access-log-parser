import java.io.*;
import java.util.Scanner;

public class Start {
    public static void main(String[] args) throws IOException {
        try {
            courseProject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        public static void courseProject() throws IOException, RuntimeException {
            boolean файлСуществует = false;
            boolean папкаСуществует = false;
            Scanner sc = new Scanner(System.in);
            String path = "";
            File file = new File(path);
            int количествоСуществующихФайлов = 0;

            while (!файлСуществует) {
                System.out.println("Введите полный путь к файлу:  ");
                path = sc.nextLine();
                file = new File(path);
                файлСуществует = file.isFile();
                папкаСуществует = file.isDirectory();
                if (!файлСуществует || папкаСуществует) {
                    System.out.println("Файл не существует или путь является путём к папке, а не к файлу");
                    continue;
                } else if (файлСуществует) {
                    количествоСуществующихФайлов += 1;
                    System.out.println("Путь указан верно");
                }


                FileReader fileReader = new FileReader(path);
                BufferedReader reader =
                        new BufferedReader(fileReader);
                String line;
                line = reader.readLine();
                Integer max = line.length();
                Integer min = line.length();
                Integer count = 1;
                while ((line = reader.readLine()) != null) {
                    count++;
                    int length = line.length();
                    if (length>max) max=length;
                    if (length<min) min=length;
                    if (max>=1024) throw new RuntimeException("строка равна или длиннее 1024 символов");
                }
                System.out.println("Общее количество строк: " + count);
                System.out.println("Длина самой длинной строки: " + max);
                System.out.println("Длина самой короткой строки: " +min);
            }
        }
    }