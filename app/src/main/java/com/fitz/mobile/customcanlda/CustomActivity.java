package com.fitz.mobile.customcanlda;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CustomActivity extends BaseActivity implements
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener {

    private TextView mTitle;





    private CalendarView mCalendarView;


    private RecyclerView mRecyclerView;

    private CalendarLayout mCalendarLayout;
    private final static String TAG= CustomActivity.class.getSimpleName();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom;
    }

    @Override
    protected Toolbar getToolBar() {
        return findViewById(R.id.toolBar);
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {

        mCalendarLayout = (CalendarLayout) findViewById(R.id.calendarLayout);
        mCalendarView = findViewById(R.id.calendarView);
        mCalendarView.setOnCalendarSelectListener(this);
        mCalendarView.setOnYearChangeListener(this);
        mTitle = findViewById(R.id.toolbar_title);


        Log.e(TAG,  mCalendarView.getCurYear()+mCalendarView.getCurMonth()+mCalendarView.getCurDay()+"");
        mTitle.setText(mCalendarView.getCurMonth()+"月"+ mCalendarView.getCurDay()+"日");

        findViewById(R.id.toggleView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCalendarLayout.isExpand())mCalendarLayout.shrink();
                else mCalendarLayout.expand();

            }
        });

        mRecyclerView = findViewById(R.id.recy_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyAdapter(this,getDatas()));
    }


    public List<RecyModel> getDatas() {

        List<RecyModel> modelList = new ArrayList<>();
        for (int i =0;i<20;i++){

            RecyModel model = new RecyModel("this is title "+i,"this is time "+i,"this is loaction1","this is location2");
            model.isNow = i%2==0;
            modelList.add(model);
        }
        return modelList;
    }


    public void prevMonth(View view){

        mCalendarView.scrollToPre(true);
    }

    public void nextMonth(View view){

        mCalendarView.scrollToNext(true);
    }

    //  对日期事件进行标注，如果不需要直接将text赋值为 "no_need",代码中会对该字段进行判断
    @Override
    protected void initData() {
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();
        Map<String, Calendar> map = new HashMap<>();

        map.put(getSchemeCalendar(year, month, 3).toString(),
                getSchemeCalendar(year, month, 3));

        map.put(getSchemeCalendar(year, month, 6).toString(),
                getSchemeCalendar(year, month, 6));

        map.put(getSchemeCalendar(year, month, 9).toString(),
                getSchemeCalendar(year, month, 9));

        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        mCalendarView.setSchemeDate(map);

    }

    private Calendar getSchemeCalendar(int year, int month, int day) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(0xFF13acf0);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme("no_need");
        return calendar;
    }



    public void testClick(View view){

        Toast.makeText(this,"添加事件",Toast.LENGTH_LONG).show();
    }
    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        mTitle.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");

    }

    @Override
    public void onYearChange(int year) {

    }


}
