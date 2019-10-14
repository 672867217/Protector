package com.example.protector;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Stats extends AppCompatActivity implements View.OnClickListener {
    StatsGvItemAdapter adapter;
    private TextView header_tv;
    private TextView header_tv2;
    private TextView header_tv3;
    private TextView header_tv4;
    private Button stats_btn1;
    private Button stats_btn2;
    private TextView stats_tv1;
    private TextView stats_tv2;
    private TextView stats_tv3;
    private GridView stats_gridview;
    private Spinner stats_spinner;
    private Calendar calendar;
    List<Bean> beanList = new ArrayList<>();
    Boolean isNull = true;
    String[] strings = {"一测统计","二测统计","三测统计","其他测统计","汇总测统计",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        initView();

        List list = new ArrayList();

        for (int i = 0; i < strings.length; i++) {
            list.add(" 智能亢余型断相保护器"+(i+1));
            Bean bean = new Bean();
            bean.name = strings[i];
            beanList.add(bean);
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,list);
        stats_spinner.setAdapter(arrayAdapter);

        adapter = new StatsGvItemAdapter(beanList);
        stats_gridview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        stats_tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                new DatePickerDialog(Stats.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String mymonth,myday;
                        if (month+1 < 10) {
                            mymonth = "0"+(month+1);
                        } else {
                            mymonth = String.valueOf(month+1);
                        }
                        if (dayOfMonth+1 < 10) {
                            myday = "0"+(dayOfMonth+1);
                        } else {
                            myday = String.valueOf(dayOfMonth+1);
                        }
                        stats_tv2.setText(year+"-"+mymonth+"-"+myday);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.DAY_OF_WEEK), calendar.get(Calendar.DAY_OF_WEEK)).show();
            }
        });
        stats_tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(Stats.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String mymonth,myday;
                        if (month+1 < 10) {
                            mymonth = "0"+(month+1);
                        } else {
                            mymonth = String.valueOf(month+1);
                        }
                        if (dayOfMonth+1 < 10) {
                            myday = "0"+(dayOfMonth+1);
                        } else {
                            myday = String.valueOf(dayOfMonth);
                        }
                        stats_tv3.setText(year+"-"+mymonth+"-"+myday);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.DAY_OF_WEEK), calendar.get(Calendar.DAY_OF_WEEK));
                datePickerDialog.show();
            }
        });





    }

    private void initView() {
        header_tv = (TextView) findViewById(R.id.header_tv);
        header_tv2 = (TextView) findViewById(R.id.header_tv2);
        header_tv3 = (TextView) findViewById(R.id.header_tv3);
        header_tv4 = (TextView) findViewById(R.id.header_tv4);
        stats_btn1 = (Button) findViewById(R.id.stats_btn1);
        stats_btn2 = (Button) findViewById(R.id.stats_btn2);
        stats_tv1 = (TextView) findViewById(R.id.stats_tv1);
        stats_tv2 = (TextView) findViewById(R.id.xinghao);
        stats_tv3 = (TextView) findViewById(R.id.shengchanchang);
        stats_gridview = (GridView) findViewById(R.id.stats_gridview);
        stats_spinner = (Spinner) findViewById(R.id.chanpin_spinner);

        stats_btn1.setOnClickListener(this);
        stats_btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //统计
            case R.id.stats_btn1:
                String date1 = stats_tv2.getText().toString();
                String date2 = stats_tv3.getText().toString();

                break;
            //返回
            case R.id.stats_btn2:
                finish();
                break;
        }
    }

    class Bean {
        String name;
        int value1, value2, value3, value4, value5, value6, value7;
    }

    public class StatsGvItemAdapter extends BaseAdapter {

        private List<Bean> list = new ArrayList<>();

        public StatsGvItemAdapter(List list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Bean getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.stats_gv_item, null);
                convertView.setTag(new ViewHolder(convertView));
            }
            initializeViews(getItem(position), (ViewHolder) convertView.getTag());
            return convertView;
        }

        private void initializeViews(Bean bean, ViewHolder holder) {
            //TODO implement
            holder.statsItemTv.setText(bean.name);
            if (!isNull) {

                holder.statsItemTv1.setText(bean.value1+"");
                holder.statsItemTv2.setText(bean.value2+"");
                holder.statsItemTv3.setText(bean.value3+"");
                holder.statsItemTv4.setText(bean.value4+"");
                holder.statsItemTv5.setText(bean.value5+"");
                holder.statsItemTv6.setText(bean.value6+"");
                holder.statsItemTv7.setText(bean.value7+"");
            }

        }

        protected class ViewHolder {
            private TextView statsItemTv;
            private TextView statsItemTv1;
            private TextView statsItemTv2;
            private TextView statsItemTv3;
            private TextView statsItemTv4;
            private TextView statsItemTv5;
            private TextView statsItemTv6;
            private TextView statsItemTv7;

            public ViewHolder(View view) {
                statsItemTv = (TextView) view.findViewById(R.id.stats_item_tv);
                statsItemTv1 = (TextView) view.findViewById(R.id.stats_item_tv1);
                statsItemTv2 = (TextView) view.findViewById(R.id.stats_item_tv2);
                statsItemTv3 = (TextView) view.findViewById(R.id.stats_item_tv3);
                statsItemTv4 = (TextView) view.findViewById(R.id.stats_item_tv4);
                statsItemTv5 = (TextView) view.findViewById(R.id.stats_item_tv5);
                statsItemTv6 = (TextView) view.findViewById(R.id.stats_item_tv6);
                statsItemTv7 = (TextView) view.findViewById(R.id.stats_item_tv7);
            }
        }
    }


}
