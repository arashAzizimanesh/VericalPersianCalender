package ir.webcando.vpc.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by ashkan on 12/11/2016.
 */
public class CustomTextView extends android.support.v7.widget.AppCompatTextView {
    String persianDate;

    public String getPersianDate() {
        return persianDate;
    }

    public void setPersianDate(String persianDate) {
        this.persianDate = persianDate;
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context);
    }

    public CustomTextView(Context context) {
        super(context);
        setFont(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }

    public void setFont(Context context) {
//        Typeface font = Typeface.createFromAsset(context.getAssets(), "font.ttf");
//        setTypeface(font);
    }
}
