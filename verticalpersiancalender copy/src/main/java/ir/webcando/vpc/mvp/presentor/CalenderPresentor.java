package ir.webcando.vpc.mvp.presentor;

import ir.webcando.vpc.mvp.model.CalenderModel;
import ir.webcando.vpc.mvp.view.UiCalender;

/**
 * Created by arashazizi on 2/19/18.
 */

public class CalenderPresentor {


    UiCalender calender;
    CalenderModel calenderModel;

    public CalenderPresentor(UiCalender calender) {
        this.calender = calender;
        calenderModel = new CalenderModel();
    }


    public void getCalenderData(int skipMonth) {

        calender.getCalenderData(calenderModel.getListCalender(skipMonth));

    }
}
