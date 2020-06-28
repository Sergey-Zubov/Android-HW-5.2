package com.szubov.android_hw_52;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.GregorianCalendar;

public class TaskListActivity extends AppCompatActivity {

    private Button mBtnChooseStartDate;
    private Button mBtnChooseEndDate;
    private Button mBtnTaskOk;
    private Button mBtnReturnFromTaskList;
    private CalendarView mClndrViewStartDate;
    private CalendarView mClndrViewEndDate;
    private long mStartDate;
    private String mStartDateTxt;
    private long mEndDate;
    private String mEndDateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        initViews();

    }

    private void initViews() {
        mBtnChooseStartDate = findViewById(R.id.btnChooseStartDate);
        mBtnChooseEndDate = findViewById(R.id.btnChooseEndDate);
        mClndrViewStartDate = findViewById(R.id.clndrViewStartDate);
        mClndrViewEndDate = findViewById(R.id.clndrViewEndDate);
        mBtnTaskOk = findViewById(R.id.btnTaskOk);
        mBtnReturnFromTaskList = findViewById(R.id.btnReturnFromTaskList);

        mClndrViewStartDate.setVisibility(View.GONE);
        mClndrViewEndDate.setVisibility(View.GONE);

        mBtnChooseStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClndrViewStartDate.setVisibility(View.VISIBLE);
                mClndrViewEndDate.setVisibility(View.GONE);
            }
        });

        mBtnChooseEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClndrViewEndDate.setVisibility(View.VISIBLE);
                mClndrViewStartDate.setVisibility(View.GONE);
            }
        });

        mClndrViewStartDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year,
                                            int month, int dayOfMonth) {
                mStartDateTxt = getText(R.string.btn_start_date).toString() + ": " +
                        year + "-" + month + "-" + dayOfMonth;
                mBtnChooseStartDate.setText(mStartDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(year, month, dayOfMonth);
                mStartDate = gregorianCalendar.getTimeInMillis();
                view.setVisibility(View.GONE);
            }
        });

        mClndrViewEndDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year,
                                            int month, int dayOfMonth) {
                mEndDateTxt =getText(R.string.btn_end_date).toString() + ": " +
                        year + "-" + month + "-" + dayOfMonth;
                mBtnChooseEndDate.setText(mEndDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(year,month,dayOfMonth);
                mEndDate = gregorianCalendar.getTimeInMillis();
                view.setVisibility(View.GONE);
            }
        });

        mBtnTaskOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while (true) {
                    if (mStartDateTxt == null || mEndDateTxt == null) {
                        Toast.makeText(TaskListActivity.this,
                                getText(R.string.btn_date_not_selected).toString(),
                                Toast.LENGTH_LONG).show();
                        break;
                    } else if (mStartDate > mEndDate) {
                        Toast.makeText(TaskListActivity.this,
                                getText(R.string.btn_task_ok_err).toString(),
                                Toast.LENGTH_LONG).show();
                        resetDateValues();

                        break;
                    } else {
                        Toast.makeText(TaskListActivity.this,
                                mBtnChooseStartDate.getText().toString() + "\n" +
                                mBtnChooseEndDate.getText().toString(), Toast.LENGTH_LONG).show();
                        resetDateValues();
                    }
                    break;
                }
            }
        });

        mBtnReturnFromTaskList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void resetDateValues() {
        mBtnChooseStartDate.setText(getText(R.string.btn_start_date).toString());
        mBtnChooseEndDate.setText(getText(R.string.btn_end_date).toString());
        mStartDateTxt = null;
        mEndDateTxt = null;
        mStartDate = 0;
        mEndDate = 0;
    }
}