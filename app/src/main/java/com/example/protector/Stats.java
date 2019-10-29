package com.example.protector;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.protector.SQl.Operator;
import com.example.protector.SQl.ProductType;
import com.example.protector.SQl.TestData;
import com.example.protector.util.Utils;

import org.litepal.crud.DataSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Stats extends AppCompatActivity implements View.OnClickListener {
    StatsGvItemAdapter adapter;
    private Calendar calendar;
    List<Bean> beanList = new ArrayList<>();
    String[] strings = {"一测统计", "二测统计", "三测统计", "其他测统计", "汇总测统计",};
    String date1, date2;
    private TextView header_tv;
    private TextView header_tv2;
    private TextView header_tv3;
    private TextView header_tv4;
    private Button stats_btn1;
    private Button stats_btn2;
    private Spinner chanpin_spinner;
    private TextView xinghao;
    private TextView stats_tv1;
    private TextView stats_tv2;
    private GridView stats_gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        initView();
        new Utils().hideNavKey(Stats.this);

        List list = new ArrayList();

        for (int i = 0; i < strings.length; i++) {
            Bean bean = new Bean();
            bean.name = strings[i];
            beanList.add(bean);
        }
        final List<ProductType> types = DataSupport.findAll(ProductType.class);
        for (int i = 0; i < types.size(); i++) {
            list.add(types.get(i).getName());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list);
        chanpin_spinner.setAdapter(arrayAdapter);
        chanpin_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(types.size()!=0){
                    xinghao.setText(types.get(i).getXinghao());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        adapter = new StatsGvItemAdapter(beanList);
        stats_gridview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        stats_tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                new DatePickerDialog(Stats.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String mymonth, myday;
                        if (month + 1 < 10) {
                            mymonth = "0" + (month + 1);
                        } else {
                            mymonth = String.valueOf(month + 1);
                        }
                        if (dayOfMonth + 1 < 10) {
                            myday = "0" + (dayOfMonth + 1);
                        } else {
                            myday = String.valueOf(dayOfMonth + 1);
                        }
                        stats_tv1.setText(year + "-" + mymonth + "-" + myday);
                        date1 = stats_tv1.getText().toString();
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.DAY_OF_WEEK), calendar.get(Calendar.DAY_OF_WEEK)).show();
            }
        });
        stats_tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                new DatePickerDialog(Stats.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String mymonth, myday;
                        if (month + 1 < 10) {
                            mymonth = "0" + (month + 1);
                        } else {
                            mymonth = String.valueOf(month + 1);
                        }
                        if (dayOfMonth + 1 < 10) {
                            myday = "0" + (dayOfMonth + 1);
                        } else {
                            myday = String.valueOf(dayOfMonth);
                        }
                        stats_tv2.setText(year + "-" + mymonth + "-" + myday);
                        date2 = stats_tv2.getText().toString();
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.DAY_OF_WEEK), calendar.get(Calendar.DAY_OF_WEEK)).show();
            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //统计
            case R.id.stats_btn1:
                if (stats_tv1.getText().toString().isEmpty()) {
                    Toast.makeText(this, "请选择开始日期", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (stats_tv2.getText().toString().equals("")) {
                    Toast.makeText(this, "请选择结束日期", Toast.LENGTH_SHORT).show();
                    return;
                }
                beanList.clear();
                List<TestData> dataList = DataSupport.findAll(TestData.class);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                List<TestData> dataList2 = new ArrayList<>();
                for (int j = 0; j < dataList.size(); j++) {
                    try {
                        Long ceshiDate = dateFormat.parse(dateFormat.format(dataList.get(j).getDate())).getTime();
                        Long startDate = dateFormat.parse(date1).getTime();
                        Long endDate = dateFormat.parse(date2).getTime();
                        if (ceshiDate >= startDate && ceshiDate <= endDate) {
                            dataList2.add(dataList.get(j));
                        } else {
                            Toast.makeText(this, "没有符合条件的统计结果！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                int[] arr = {1,2,3,0};
                for (int i = 0; i < 5; i++) {
                    Bean bean = new Bean();
                    bean.name = strings[i];
                    int shuliang = 0, shichang = 0;
                    List<Integer> shichangList = new ArrayList<>();
                    for (int j = 0; j < dataList2.size(); j++) {
                        if (i == 4) {
                            shuliang = dataList2.size();
                            shichang += Integer.parseInt(dataList2.get(j).getCeshishichang());
                            shichangList.add(Integer.valueOf(dataList2.get(j).getCeshishichang()));
                        }else if (Integer.parseInt(dataList2.get(j).getCecheng())==arr[i]){
                            shuliang++;
                            shichang += Integer.parseInt(dataList2.get(j).getCeshishichang());
                            shichangList.add(Integer.valueOf(dataList2.get(j).getCeshishichang()));
                        }
                    }
                    if (shuliang != 0) {
                        Collections.sort(shichangList);
                        bean.value1 = String.valueOf(shuliang);
                        bean.value2 = String.valueOf(shuliang);
                        bean.value3 = String.valueOf(0);
                        bean.value4 = String.valueOf(100);
                        bean.value5 = (shichang / shuliang) % 60 + "'" + (shichang / shuliang) / 60;
                        bean.value6 = shichangList.get(0) % 60 + "'" + shichangList.get(0) / 60;
                        bean.value7 = shichangList.get(shichangList.size() - 1) % 60 + "'"
                                + shichangList.get(shichangList.size() - 1) / 60;
                    }
                    beanList.add(bean);
                }
                adapter.notifyDataSetChanged();

                break;
            //返回
            case R.id.stats_btn2:
                finish();
                break;

        }
    }

    private void initView() {
        header_tv = (TextView) findViewById(R.id.header_tv);
        header_tv2 = (TextView) findViewById(R.id.header_tv2);
        header_tv3 = (TextView) findViewById(R.id.header_tv3);
        header_tv4 = (TextView) findViewById(R.id.header_tv4);
        stats_btn1 = (Button) findViewById(R.id.stats_btn1);
        stats_btn2 = (Button) findViewById(R.id.stats_btn2);
        chanpin_spinner = (Spinner) findViewById(R.id.chanpin_spinner);
        xinghao = (TextView) findViewById(R.id.xinghao);
        stats_tv1 = (TextView) findViewById(R.id.stats_tv1);
        stats_tv2 = (TextView) findViewById(R.id.stats_tv2);
        stats_gridview = (GridView) findViewById(R.id.stats_gridview);

        stats_btn1.setOnClickListener(this);
        stats_btn2.setOnClickListener(this);
    }

    class Bean {
        String name="", value1="", value2="",value3="",value4="",value5="",value6="",value7="";
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

                holder.statsItemTv1.setText(bean.value1 + "");
                holder.statsItemTv2.setText(bean.value2 + "");
                holder.statsItemTv3.setText(bean.value3 + "");
                holder.statsItemTv4.setText(bean.value4 + "");
                holder.statsItemTv5.setText(bean.value5 + "");
                holder.statsItemTv6.setText(bean.value6 + "");
                holder.statsItemTv7.setText(bean.value7 + "");


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
