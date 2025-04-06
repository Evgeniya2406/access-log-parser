import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;

public class Statistics {
private int averageTrafficsAtHour;
private long totalTraffic;
private LocalDateTime minTime;
private LocalDateTime maxTime;

private HashSet<String> pages = new HashSet<String>();
private HashMap<OperationSystem, Integer> OSStatistics = new HashMap<OperationSystem, Integer>();


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

    public Statistics() {
        this.averageTrafficsAtHour=0;
        this.totalTraffic=0;
        this.minTime = LocalDateTime.of(2100,11,26,13,55,36,123);
        this.maxTime = LocalDateTime.of(1760,11,26,13,55,36,123);

        for (OperationSystem value : OperationSystem.values()) {
            OSStatistics.put(value,0);
        }
    }


    public HashSet<String> getPages() {
        return pages;
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

    public void addEntry(LogEntry logEntry){
        totalTraffic=totalTraffic+logEntry.dataSize;

        if (logEntry.queryDate.isAfter(maxTime)) maxTime=logEntry.queryDate;
        if (logEntry.queryDate.isBefore(minTime)) minTime=logEntry.queryDate;

        if(logEntry.codeResponce==200) pages.add(logEntry.queryPath);

        OSStatistics.put(logEntry.getUserAgent().getOperationSystem(),OSStatistics.get(logEntry.getUserAgent().getOperationSystem())+1);
    }

    public long getTrafficRate(){
        int hours=(Duration.between(minTime,maxTime).toHoursPart()+ (int) Duration.between(minTime,maxTime).toDaysPart()*24);
        long i = totalTraffic / hours;
        return i;
    }


}
