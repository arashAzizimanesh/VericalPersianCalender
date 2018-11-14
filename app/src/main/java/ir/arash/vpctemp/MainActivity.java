package ir.arash.vpctemp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ir.webcando.vpc.customview.CalenderView;

public class MainActivity extends AppCompatActivity {
    CalenderView calenderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        calenderView = findViewById(R.id.calenderView);
        Typeface font = Typeface.createFromAsset(getAssets(), "rraffic.ttf");
        calenderView.setFont(font);
        calenderView.setBackgrandSelector(getResources().getColor(R.color.colorPrimary));
        calenderView.setDaysColor(getResources().getColor(R.color.black));
        calenderView.setSelectedType(CalenderView.SELECT_ITEM_SINGLE);
        calenderView.setSkipMonth(6);
        calenderView.initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
