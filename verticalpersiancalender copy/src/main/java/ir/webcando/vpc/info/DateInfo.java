package ir.webcando.vpc.info;

import java.util.List;

/**
 * Created by arashazizi on 2/19/18.
 */

public class DateInfo {

    String monthName;
    int monthNumber;
    String year;
    List<DayInfo> dayInfos;


    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<DayInfo> getDayInfos() {
        return dayInfos;
    }

    public void setDayInfos(List<DayInfo> dayInfos) {
        this.dayInfos = dayInfos;
    }
}
