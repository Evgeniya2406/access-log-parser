import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

enum Rest {
    GET,POST,PUT,PATCH
}

public class LogEntry {
    final String ipAddress;
    final LocalDateTime queryDate;
    final Rest queryMethod;
    final String queryPath;
    final int codeResponce;
    final int dataSize;
    final String referer;
    final UserAgent userAgent;

    public LogEntry(String ipAddress, LocalDateTime queryDate, Rest queryMethod, String queryPath, int codeResponce, int dataSize, String referer, UserAgent userAgent) {
        this.ipAddress = ipAddress;
        this.queryDate = queryDate;
        this.queryMethod = queryMethod;
        this.queryPath = queryPath;
        this.codeResponce = codeResponce;
        this.dataSize = dataSize;
        this.referer = referer;
        this.userAgent = userAgent;
    }

    public LogEntry(String str) {
      //  String[] s = str.split(" ");
        Rest queryMethod1 = null;
        String str1=str.substring(0,str.length()-1);
        int start = str1.lastIndexOf("\"");
        this.userAgent=new UserAgent(str1.substring(start+1,str1.length()));

        this.ipAddress=str.substring(0,str.indexOf(" "));

        this.queryDate = convert (str.substring(str.indexOf("[")+1,str.indexOf("]")));

        start = str1.indexOf("\"");
        str1=str.substring(start+1,str.length()-1);
        switch (str1.substring(0,str1.indexOf(" "))){
            case ("GET"):
                queryMethod1 = Rest.GET;
                break;
            case ("POST"):
                queryMethod1 = Rest.POST;
                break;
            case ("PUT"):
                queryMethod1 = Rest.PUT;
                break;
            case ("PATCH"):
                queryMethod1 = Rest.PATCH;
                break;
        }
        this.queryMethod = queryMethod1;


        this.queryPath=str1.substring(str1.indexOf("/"),str1.indexOf("\""));

         start = str1.indexOf("\"")+2;
          str1=str1.substring(start,str1.length()-1);
        this.codeResponce = Integer.parseInt(str1.substring(0,str1.indexOf(" ")));
       // if(codeResponce!=200 && codeResponce!=404) System.out.println(str);
       // if (str.contains("404") && codeResponce!=404) System.out.println(str);


        start = str1.indexOf(" ")+1;
        str1=str1.substring(start,str1.length()-1);
        this.dataSize = Integer.parseInt(str1.substring(0,str1.indexOf(" ")));

        str1=str1.substring(str1.indexOf("\"")+1,str1.length()-1);
        if (str1.contains("\"")){
            this.referer = str1.substring(0,str1.indexOf("\""));
        } else {
            this.referer="null";
        }

      //  System.out.println(this.ipAddress+ " " + this.queryDate+ " " +this.queryMethod+ " " +this.queryPath+ " " +this.codeResponce+ " " +this.dataSize+ " " +this.referer + " " +this.userAgent);

    }

    public String getIpAddress() {
        return ipAddress;
    }

    public LocalDateTime getQueryDate() {
        return queryDate;
    }

    public Rest getQueryMethod() {
        return queryMethod;
    }

    public int getCodeResponce() {
        return codeResponce;
    }

    public int getDataSize() {
        return dataSize;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }

private LocalDateTime convert(String s){
        String[] tmp = s.split(" ");
        String[] tmpS = tmp[0].split("/");
                String month = tmpS[1];
        String numS = "";
        switch (month){
            case ("Jan"): numS="01";break;
            case ("Feb"): numS="02";break;
            case ("Mar"): numS="03";break;
            case ("Apr"): numS="04";break;
            case ("May"): numS="05";break;
            case ("Jun"): numS="06";break;
            case ("Jul"): numS="07";break;
            case ("Aug"): numS="08";break;
            case ("Sep"): numS="09";break;
            case ("Oct"): numS="10";break;
            case ("Nov"): numS="11";break;
            case ("Dec"): numS="12";break;
        }
    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss");
      //  LocalDateTime localDateTime = LocalDateTime.parse(tmpS[0]+"/"+numS+"/"+tmpS[2],formatter);

    LocalDateTime localDateTime = LocalDateTime.of(Integer.parseInt(tmpS[2].split(":")[0]),
            Integer.parseInt(numS),
            Integer.parseInt(tmpS[0]),
            Integer.parseInt(tmpS[2].split(":")[1]),
            Integer.parseInt(tmpS[2].split(":")[2]),
           Integer.parseInt(tmpS[2].split(":")[3]));
        return localDateTime;
}

//[26/Sep/2022:10:18:47 +0300]
    //IP-адрес клиента, который сделал запрос к серверу (в примере выше — 37.231.123.209).
    //Два пропущенных свойства, на месте которых обычно стоят дефисы, но могут встречаться также и пустые строки ("").
    //Дата и время запроса в квадратных скобках.
    //Метод запроса (в примере выше — GET) и путь, по которому сделан запрос.
    //Код HTTP-ответа (в примере выше — 200).
    //Размер отданных данных в байтах (в примере выше — 61096).
    //Путь к странице, с которой перешли на текущую страницу, — referer (в примере выше — “https://nova-news.ru/search/?rss=1&lg=1”).
    //User-Agent — информация о браузере или другом клиенте, который выполнил запрос.
}
