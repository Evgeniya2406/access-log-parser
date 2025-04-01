import java.time.Duration;
import java.time.LocalDateTime;

public class Statistics {
private int averageTrafficsAtHour;
private long totalTraffic;
private LocalDateTime minTime;
private LocalDateTime maxTime;


    public Statistics() {
        this.averageTrafficsAtHour=0;
        this.totalTraffic=0;
        this.minTime = LocalDateTime.of(2100,11,26,13,55,36,123);
        this.maxTime = LocalDateTime.of(1760,11,26,13,55,36,123);
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
    }

    public long getTrafficRate(){
        int hours=(Duration.between(minTime,maxTime).toHoursPart()+ (int) Duration.between(minTime,maxTime).toDaysPart()*24);
        long i = totalTraffic / hours;
        return i;
    }
}
