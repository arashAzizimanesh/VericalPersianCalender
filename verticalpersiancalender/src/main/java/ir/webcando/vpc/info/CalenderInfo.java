package ir.webcando.vpc.info;

import java.util.List;

/**
 * Created by arashazizi on 2/19/18.
 */

public class CalenderInfo {

    String defDay;
    List<DateInfo> dateInfo;

    public List<DateInfo> getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(List<DateInfo> dateInfo) {
        this.dateInfo = dateInfo;
    }

    public String getDefDay() {
        return defDay;
    }

    public void setDefDay(String defDay) {
        this.defDay = defDay;
    }
}
