import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;

class AccessLogException extends Exception{
    public AccessLogException(String msg){
        super(msg);
    }
}

public class Start {
    public static void main(String[] args) throws IOException {
        try {
            courseProject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void courseProject() throws IOException, AccessLogException {
        boolean файлСуществует = false;
        boolean папкаСуществует = false;
        Scanner sc = new Scanner(System.in);
        String path = "";
        File file = new File(path);
        int количествоСуществующихФайлов = 0;

        while (!файлСуществует) {
            System.out.println("Введите полный путь к файлу:  ");
            //path = sc.nextLine();
            path = "C:\\Users\\Ильзида\\Desktop\\Новая папка\\access3.log";
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
            ArrayList <LogEntry> files = new ArrayList<>();
            files.add(new LogEntry(line));
            Integer count = 1;
            Integer yandexBotCounter =0;
            Integer googleBotCounter =0;
            Statistics statistics = new Statistics();
            while ((line = reader.readLine()) != null) {
                if (parseString(line).getLast().contains("YandexBot")) yandexBotCounter++;
                if (parseString(line).getLast().contains("Googlebot")) googleBotCounter++;
                count++;
                System.out.println(count + "-я строка:");
                int length = line.length();
                if (length >= 1024) throw new AccessLogException("строка равна или длиннее 1024 символов");
                LogEntry logEntry = new LogEntry(line);
                files.add(logEntry);
                statistics.addEntry(logEntry);
            }
            System.out.println("Доля запросов YandexBot: " + Math.round(yandexBotCounter*1.0/count*1000.0)/10.0);
            System.out.println("Доля запросов Googlebot: " + Math.round(googleBotCounter*1.0/count*1000.0)/10.0);
            System.out.println("Общее количество запросов: " + count);
            System.out.println("Период лога с " + statistics.getMinTime() + " до " + statistics.getMaxTime());
            System.out.println("Объём трафика сайта за час " + statistics.getTrafficRate()/1024/1024+ " мегабайт(a)");
            System.out.println("Продолжительность лога " + (Duration.between(statistics.getMinTime(),statistics.getMaxTime()).toHoursPart()+ (int) Duration.between(statistics.getMinTime(),statistics.getMaxTime()).toDaysPart()*24)+" часа(ов)");
            System.out.println("Объем общего трафика за период составил "+ statistics.getTotalTraffic()+ " байт(а)");
        }
    }

    private static ArrayList<String> parseString(String str) {
        ArrayList<String> arr = new ArrayList<>();
        String str1=str.substring(0,str.length()-2);
        int start = str1.lastIndexOf("\"");
        arr.add(str1.substring(start+1));
        return arr;
    }

}

