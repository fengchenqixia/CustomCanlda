package com.fitz.mobile.customcanlda;

import android.annotation.SuppressLint;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;


public class CustomActivity extends BaseActivity implements
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener {

    private TextView mTitle;

    private TextView mCurrentDay;



    private CalendarView mCalendarView;



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
        mCurrentDay = findViewById(R.id.current_month);

        Log.e(TAG,  mCalendarView.getCurYear()+mCalendarView.getCurMonth()+mCalendarView.getCurDay()+"");
        mTitle.setText(mCalendarView.getCurMonth()+"月"+ mCalendarView.getCurDay()+"日");
        mCurrentDay.setText(mCalendarView.getCurYear()+"年"+mCalendarView.getCurMonth()+"月");

    }


    public void prevMonth(View view){

        mCalendarView.scrollToPre(true);
    }

    public void nextMonth(View view){

        mCalendarView.scrollToNext(true);
    }
    @Override
    protected void initData() {


    }


    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        mTitle.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mCurrentDay.setText(calendar.getYear()+"年"+calendar.getMonth()+"月");
    }

    @Override
    public void onYearChange(int year) {

    }


}
