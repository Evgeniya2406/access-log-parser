import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;


public class Statistics {
private int averageVisitsAtHour;
private long totalTraffic;
private LocalDateTime minTime;
private LocalDateTime maxTime;
private int totalVisits;
@Getter
private int userVisits;
@Getter
private int errorRequests;
private HashSet<String> pages = new HashSet<String>();
private HashSet<String> noPages = new HashSet<String>();
private HashMap<OperationSystem, Integer> OSStatistics = new HashMap<OperationSystem, Integer>();
private HashMap<Browser, Integer> browserStatistics = new HashMap<Browser, Integer>();
@Getter
private HashSet<String> unicUsers = new HashSet<String>();
private HashMap<Long, Integer> visitsAtSecond = new HashMap<>();
private HashSet<String> domens = new HashSet<>();
private HashMap<String, Integer> unicUserVisits = new HashMap<>();



    public HashMap<String, Double> getOSStatistics() {
        HashMap<String, Double> sttc = new HashMap<String, Double>();
        Double sum=0.0;
        for (Integer value : OSStatistics.values()) {
            sum=sum+ value;
        }
        for (OperationSystem value : OperationSystem.values()) {
            sttc.put(value.toString(),OSStatistics.get(value)/sum);
        }
        return sttc;
    }

    public HashMap<String, Double> getBrowserStatistics() {
        HashMap<String, Double> sttc = new HashMap<String, Double>();
        Double sum=0.0;
        for (Integer value : browserStatistics.values()) {
            sum=sum+ value;
        }
        for (Browser value : Browser.values()) {
            sttc.put(value.toString(),browserStatistics.get(value)/sum);
        }
        return sttc;
    }

    public int getVisitsAtSecond(Long countSecond) {
       // ZoneOffset zone=ZoneOffset.of("Z");
        return this.visitsAtSecond.get(countSecond);
    }

    public Statistics() {
        this.averageVisitsAtHour=0;
        this.totalTraffic=0;
        this.totalVisits =0;
        this.userVisits=0;
        this.errorRequests=0;
        this.minTime = LocalDateTime.of(2100,11,26,13,55,36,123);
        this.maxTime = LocalDateTime.of(1760,11,26,13,55,36,123);

        for (OperationSystem value : OperationSystem.values()) {
            OSStatistics.put(value,0);
        }
        for (Browser value : Browser.values()) {
           browserStatistics.put(value,0);
        }
    }


    public HashSet<String> getPages() {
        return pages;
    }

    public HashSet<String> getNoPages() {
        return noPages;
    }

    public long getTotalTraffic() {
        return totalTraffic;
    }

    public LocalDateTime getMinTime() {
        return minTime;
    }

    public LocalDateTime getMaxTime() {
        return maxTime;
    }

    public int getTotalVisits() {
        return totalVisits;
    }

    public void addEntry(LogEntry logEntry){
        totalVisits++;
        //LocalDateTime localDateTime =  LocalDateTime.of(2022,9,25,6,25,10);
       // if (logEntry.queryDate.compareTo(localDateTime)==0) { // visits=visits+1; visits+=1;
        //  boolean b= true;
       // }
        if (logEntry.getReferer().contains("/")) {
            domens.add(logEntry.getReferer());
        }
        if (!logEntry.getUserAgent().getBot()) {
            userVisits=userVisits+1;
            unicUsers.add(logEntry.ipAddress);

            // Подсчет количества посещений за одну каждую секунду
            Long std = getSecondDuration(logEntry.queryDate);
            if (!visitsAtSecond.containsKey(std)){
                visitsAtSecond.put(std,1);
            } else {
                visitsAtSecond.put(std, visitsAtSecond.get(std) + 1);
            }
            // Подсчет посещений каждым пользователем
            if (!unicUserVisits.containsKey(logEntry.ipAddress)){
                unicUserVisits.put(logEntry.ipAddress, 1);
            } else {
                unicUserVisits.put(logEntry.ipAddress, unicUserVisits.get(logEntry.ipAddress) + 1);
            }
        }

        if (logEntry.codeResponce%100==4 || logEntry.codeResponce%100==5) errorRequests+=1;

        totalTraffic=totalTraffic+logEntry.dataSize;

        if (logEntry.queryDate.isAfter(maxTime)) maxTime=logEntry.queryDate;
        if (logEntry.queryDate.isBefore(minTime)) minTime=logEntry.queryDate;

        if(logEntry.codeResponce==200) pages.add(logEntry.queryPath);
        if(logEntry.codeResponce==404) noPages.add(logEntry.queryPath);


        OSStatistics.put(logEntry.getUserAgent().getOperationSystem(),OSStatistics.get(logEntry.getUserAgent().getOperationSystem())+1);

        browserStatistics.put(logEntry.getUserAgent().getBrowser(),browserStatistics.get(logEntry.getUserAgent().getBrowser())+1);
    }


    public long getSecondDuration(LocalDateTime t) {
        long d = t.getDayOfYear();
        long h = t.getHour();
        long m = t.getMinute();
        long s = t.getSecond();
        return (d * 86400)+ (h * 3600) + (m * 60) + s;
    }


    public long getTrafficRate(){
        int hours=(Duration.between(minTime,maxTime).toHoursPart()+ (int) Duration.between(minTime,maxTime).toDaysPart()*24);
        long i = totalTraffic / hours;
        return i;
    }

    public int getAverageVisitsAtHour() {
        int hours=(Duration.between(minTime,maxTime).toHoursPart()+ (int) Duration.between(minTime,maxTime).toDaysPart()*24);
        int i = userVisits / hours;
        return i;
    }
    public int getErrorRequestAtHour() {
        int hours=(Duration.between(minTime,maxTime).toHoursPart()+ (int) Duration.between(minTime,maxTime).toDaysPart()*24);
        int i = errorRequests / hours;
        return i;
    }
    public int getAverageUserTraffic() {
        int i = userVisits / unicUsers.size();
        return i;
    }


    public  HashSet<String> getDomens(String str) {
        HashSet<String> siteList = new HashSet<>();
       domens.forEach(value -> {
           if (value.contains(str)) {
               siteList.add(value);
           }
       });
        return siteList;
    }

    public String getUnicUserMaxVisits() {
        int max =  unicUserVisits.values().stream().max(Integer::compare).get();
       return String.valueOf(unicUserVisits.entrySet().stream().filter(it -> it.getValue().equals(max)).findFirst());
    }
}
