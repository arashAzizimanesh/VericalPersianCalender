package ir.webcando.vpc.click;

import java.util.List;

/**
 * Created by arashazizi on 3/6/18.
 */

public interface OnSelectedDate {

    void onSelectedSingleDate(String date, String errorMessage);
    void onSelectedMultiDate(List<String> dates, String errorMessage);
}
