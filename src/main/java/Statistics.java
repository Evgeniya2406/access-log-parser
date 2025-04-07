import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;


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
        totalVisits++; // visits=visits+1; visits+=1;
        if (logEntry.getUserAgent().getBot()) {
            userVisits=userVisits+1;
            unicUsers.add(logEntry.ipAddress);
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

}
