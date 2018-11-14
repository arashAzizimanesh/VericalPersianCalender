package ir.webcando.vpc.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import ir.webcando.vpc.R;
import ir.webcando.vpc.click.OnSelectedDate;
import ir.webcando.vpc.info.CalenderInfo;
import ir.webcando.vpc.info.DateInfo;
import ir.webcando.vpc.info.DayInfo;
import ir.webcando.vpc.mvp.presentor.CalenderPresentor;
import ir.webcando.vpc.mvp.view.UiCalender;


public class CalenderView extends NestedScrollView implements UiCalender {

    public static final int SELECT_ITEM_SINGLE = 0;
    public static final int SELECT_ITEM_Multi = 1;
    private int selectedItem = SELECT_ITEM_SINGLE;
    public static int SKIP_MONTH = 6;


    private CalenderPresentor calenderPresentor;

    private List<CustomTextView> selectedTextView;
    private DisplayMetrics displayMetrics;

    private List<String> selectedDates;

    private int backgrandSelector;
    private int titleColor;
    private int daysColor;
    private LinearLayout linearLayout;

    private Typeface typeface;


    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public void setDaysColor(int daysColor) {
        this.daysColor = daysColor;
    }

    public void setBackgrandSelector(int backgrandSelector) {
        this.backgrandSelector = backgrandSelector;
    }

    public void getOnSelectedDate(OnSelectedDate onSelectedDate) {

        if (selectedDates.size() > 0) {

            if (selectedItem == 0) {

                onSelectedDate.onSelectedSingleDate(selectedDates.get(0), "");


            } else if (selectedItem == 1) {

                onSelectedDate.onSelectedMultiDate(selectedDates, "");

            }
        } else {

            onSelectedDate.onSelectedMultiDate(null, "هیچ تاریخی انتخاب نشده");
            onSelectedDate.onSelectedSingleDate(null, "هیچ تاریخی انتخاب نشده");

        }
    }

    public CalenderView(Context context) {
        super(context);

    }

    public CalenderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);

    }

    public void setSkipMonth(int skipMonth) {

        SKIP_MONTH = skipMonth;

    }

    public void setSelectedType(int selectedType) {

        selectedItem = selectedType;
    }

    private void getAttrs(AttributeSet attributeSet) {

        final TypedArray a = getContext().obtainStyledAttributes(attributeSet, R.styleable.CalenderView);

        selectedItem = a.getInteger(R.styleable.CalenderView_selectItem, SELECT_ITEM_SINGLE);
        SKIP_MONTH = a.getInteger(R.styleable.CalenderView_skipMonth, 6);
        backgrandSelector = a.getColor(R.styleable.CalenderView_selectorColor, getResources().getColor(R.color.colorAccent));
        titleColor = a.getColor(R.styleable.CalenderView_titleColor, getResources().getColor(R.color.colorPrimary));
        daysColor = a.getColor(R.styleable.CalenderView_daysColor, getResources().getColor(R.color.black));


    }

    public CalenderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(attrs);

    }

    public void initView() {
        displayMetrics = getContext().getResources().getDisplayMetrics();
        selectedTextView = new ArrayList<>();
        selectedDates = new ArrayList<>();
//        setOrientation(VERTICAL);
        linearLayout = new LinearLayout(getContext());

        FrameLayout.LayoutParams layoutParamss = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParamss);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        addView(linearLayout);


        calenderPresentor = new CalenderPresentor(this);
        calenderPresentor.getCalenderData(SKIP_MONTH);


    }


    @Override
    public void getCalenderData(CalenderInfo calenderInfo) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        List<DateInfo> dateInfo = calenderInfo.getDateInfo();
        String defDay = calenderInfo.getDefDay();


        for (int i = 0; i < dateInfo.size(); i++) {

            DateInfo info = dateInfo.get(i);


            List<LinearLayout> layWeek = new ArrayList<>();
            View rootView = inflater.inflate(R.layout.month_item, null);
            CustomTextView txtMonth = rootView.findViewById(R.id.txt_month);
            LinearLayout layJome = rootView.findViewById(R.id.lay_jome);
            LinearLayout layPanjShanbe = rootView.findViewById(R.id.lay_panj_shanbe);
            LinearLayout layChaharShanbe = rootView.findViewById(R.id.lay_chahar_shanbe);
            LinearLayout laySeShanbe = rootView.findViewById(R.id.lay_se_shanbe);
            LinearLayout layDOShanbe = rootView.findViewById(R.id.lay_do_shanbe);
            LinearLayout layYekShanbe = rootView.findViewById(R.id.lay_yek_shanbe);
            LinearLayout layShanbe = rootView.findViewById(R.id.lay_shanbe);

            layWeek.add(layShanbe);
            layWeek.add(layYekShanbe);
            layWeek.add(layDOShanbe);
            layWeek.add(laySeShanbe);
            layWeek.add(layChaharShanbe);
            layWeek.add(layPanjShanbe);
            layWeek.add(layJome);

            txtMonth.setText(info.getMonthName() + " " + info.getYear());

            txtMonth.setTextColor(titleColor);

            if (typeface != null) {

                txtMonth.setTypeface(typeface);
            }

            String persianDate = info.getYear() + "/" + (info.getMonthNumber() < 10 ? "0" + info.getMonthNumber() : info.getMonthNumber()) + "/";

            Log.d("persianDate", "getCalenderData: " + persianDate);


            List<DayInfo> dayInfos = info.getDayInfos();

            for (int j = 0; j < dayInfos.size(); j++) {

                DayInfo dayInfo = dayInfos.get(j);


                int numerTAke = 0;
                for (int k = 0; k < layWeek.size(); k++) {


                    LinearLayout layout = layWeek.get(k);
                    String tag = (String) layout.getTag();

                    if (dayInfo.getDayName().equals(tag)) {
                        View dayView = inflater.inflate(R.layout.day_item, null);
                        final RelativeLayout layoutTxt = dayView.findViewById(R.id.main_txt_lay);
                        final CustomTextView txtDay = dayView.findViewById(R.id.txt_day);
                        txtDay.setText(dayInfo.getDayNumber());
                        layout.addView(dayView);
                        if (typeface != null) {

                            txtDay.setTypeface(typeface);
                        }
                        int h = displayMetrics.widthPixels / 8;
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(h, h);
                        layoutTxt.setLayoutParams(layoutParams);


                        if (i == 0) {

                            if (dayInfo.getDayNumber().equals(defDay) || Integer.parseInt(dayInfo.getDayNumber()) > Integer.parseInt(defDay)) {

//                                txtDay.setBackgroundResource(R.drawable.circle_border);
//                                selectedTextView.add(txtDay);

                                txtDay.setPersianDate(persianDate + (Integer.parseInt(dayInfo.getDayNumber()) < 10 ? "0" + info.getMonthNumber() : dayInfo.getDayNumber()));
                                Log.d("perdate", "getCalenderData: " + persianDate + (Integer.parseInt(dayInfo.getDayNumber()) < 10 ? "0" + info.getMonthNumber() : dayInfo.getDayNumber()));

                                // todo

                                txtDay.setTextColor(daysColor);
                                addOrRemoveDate(txtDay);
                            } else {

                                txtDay.setTextColor(getContext().getResources().getColor(R.color.grayText));

                            }

                        } else {

//                            txtDay.setBackgroundResource(R.drawable.circle_border);
//                            selectedTextView.add(txtDay);
                            // TODO: 3/6/18
                            txtDay.setPersianDate(persianDate + (Integer.parseInt(dayInfo.getDayNumber()) < 10 ? "0" + dayInfo.getDayNumber() : dayInfo.getDayNumber()));

                            Log.d("perdate", "getCalenderData: " + persianDate + (Integer.parseInt(dayInfo.getDayNumber()) < 10 ? "0" + dayInfo.getDayNumber() : dayInfo.getDayNumber()));
                            txtDay.setTextColor(daysColor);
                            addOrRemoveDate(txtDay);
                        }


                        if (j == 0) {

                            numerTAke = k;

                            Log.d("sdsdsds", "getCalenderData: " + numerTAke);

                        }

                        break;
                    }


                }

                if (numerTAke > 0) {

                    for (int e = 0; e < numerTAke; e++) {
                        Log.d("sdsdsdsds", "getCalenderData: " + e);
                        LinearLayout layout = layWeek.get(e);
                        View dayView = inflater.inflate(R.layout.day_item, null);
                        CustomTextView txtDay = dayView.findViewById(R.id.txt_day);
                        RelativeLayout relativeLayout = dayView.findViewById(R.id.main_txt_lay);
                        if (typeface != null) {

                            txtDay.setTypeface(typeface);
                        }
                        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (displayMetrics.widthPixels * 0.12));
                        relativeLayout.setLayoutParams(layoutParams);

                        layout.addView(dayView);


                        txtDay.setVisibility(INVISIBLE);


                    }

                }
//
            }

            linearLayout.addView(rootView);


        }


    }

    public void addOrRemoveDate(final CustomTextView txtDay) {
//        Drawable backCircle = getResources().getDrawable(R.drawable.circle_border);
        if (backgrandSelector > 0) {


        }
        txtDay.setOnClickListener(new OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                txtDay.setBackgroundColor(getContext().getResources().getColor(R.color.colorGray));

                if (!selectedTextView.remove(txtDay)) {
                    if (selectedItem == 0) {

                        if (selectedTextView.size() > 0) {

                            selectedTextView.get(0).setBackgroundColor(Color.TRANSPARENT);
                            selectedTextView.remove(0);
                            selectedDates.remove(0);

                        }
                    }


                    selectedTextView.add(txtDay);
                    txtDay.setBackgroundResource(R.drawable.circle_border);


                    GradientDrawable drawable = (GradientDrawable) txtDay.getBackground();
                    drawable.setColor(backgrandSelector);

                    selectedDates.add(txtDay.getPersianDate());


                } else {

                    txtDay.setBackgroundColor(Color.TRANSPARENT);
                    selectedDates.remove(txtDay.getPersianDate());

                }
            }
        });
    }


    public void setFont(Typeface typeface) {

        this.typeface = typeface;

    }
}
