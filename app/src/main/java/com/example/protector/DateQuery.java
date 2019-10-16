package com.example.protector;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateQuery extends AppCompatActivity implements View.OnClickListener {

    private TextView header_tv;
    private TextView header_tv2;
    private TextView header_tv3;
    private TextView header_tv4;
    private Button btn_dayin;
    private Button btn_cancel;
    private Spinner stats_spinner;
    private TextView stats_tv1;
    private TextView stats_tv2;
    private TextView stats_tv3;
    private TextView textView5;
    private TextView textView6;
    private TextView footer_tv3;
    private TextView footer_tv1;
    private TextView footer_tv2;
    private ImageView img_shang, img_xia;
    private ListView listview1;
    private ListView listview2;

    List list1 = new ArrayList();
    List list2 = new ArrayList();
    DateQueryItemAdapter adapter1, adapter2;
    private int index=0, num36 = 36, shuliang = 0,page;
    private SimpleDateFormat dateFormat;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_query);
        initView();
        dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        //spinner
        List list = new ArrayList();

        for (int i = 0; i < 5; i++) {
            list.add(" 智能冗余型断相保护器"+(i+1));
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,list);
        stats_spinner.setAdapter(arrayAdapter);

        //表格适配器部分
        adapter1 = new DateQueryItemAdapter(this, list1);
        listview1.setAdapter(adapter1);
        adapter2 = new DateQueryItemAdapter(this, list2);
        listview2.setAdapter(adapter2);
        //翻页逻辑
        if(shuliang%36==0){
            page = shuliang/36;
        }else
        {
            page = shuliang/36+1;
        }
        footer_tv3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().equals("")){
                    if(page>=Integer.parseInt(editable.toString()) && Integer.parseInt(editable.toString())>0){
                        index = Integer.parseInt(editable.toString())-1;
                        list1.clear();
                        list2.clear();
                        int a = 0;
                        if(num36 * index + num36>shuliang){
                            a = shuliang;
                        }else{
                            a = num36 * index + num36;
                        }
                        for (int i = num36 * index; i < a; i++) {
                            Bean bean = new Bean();
                            bean.number1 = "000" + (i + 1);
                            bean.number2 = "8000000" + (i + 1);
                            bean.date = dateFormat.format(new Date());
                            bean.result = "合格";
                            bean.name = "柳铁信息";
                            if (i < num36 * index + 18) {
                                list1.add(bean);
                            } else if (i >= num36 * index + 18 && i < num36 * index + num36) {
                                list2.add(bean);
                            }
                            if (i == a-1 && a%num36 >0) {
                                for (int j = 0; j < num36 - shuliang % num36; j++) {
                                    bean = new Bean();
                                    bean.number1 = "";
                                    bean.number2 = "";
                                    bean.date = "";
                                    bean.result = "";
                                    bean.name = "";
                                    if (num36 - shuliang % num36 -j> 18) {
                                        list1.add(bean);
                                    } else {
                                        list2.add(bean);
                                    }
                                }
                            }
                        }
                        adapter1.notifyDataSetChanged();
                        adapter2.notifyDataSetChanged();
                        if(index+1 == page){
                            img_xia.setImageResource(R.drawable.xiajiantouhui);
                            img_xia.setEnabled(false);
                            img_shang.setImageResource(R.drawable.shangjiantou);
                            img_shang.setEnabled(true);
                        }else if(index == 0){
                            img_shang.setImageResource(R.drawable.shangjiantouhui);
                            img_shang.setEnabled(false);
                            img_xia.setImageResource(R.drawable.xiajiantou);
                            img_xia.setEnabled(true);
                        }else
                        {
                            img_shang.setImageResource(R.drawable.shangjiantou);
                            img_shang.setEnabled(true);
                            img_xia.setImageResource(R.drawable.xiajiantou);
                            img_xia.setEnabled(true);
                        }
                    }
                }
            }
        });
        footer_tv3.setText("1");
        img_shang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    if(index == 1){
                        img_shang.setImageResource(R.drawable.shangjiantouhui);
                        img_shang.setEnabled(false);
                    }
                    img_xia.setEnabled(true);
                    img_xia.setImageResource(R.drawable.xiajiantou);
                    index = index - 1;
                    footer_tv3.setText(index+1+"");
                }
            }
        });
        img_xia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page > index+1) {
                    index = index + 1;
                    if(index+1 == page){
                        img_xia.setImageResource(R.drawable.xiajiantouhui);
                        img_xia.setEnabled(false);
                    }
                    img_shang.setImageResource(R.drawable.shangjiantou);
                    img_shang.setEnabled(true);
                    footer_tv3.setText(index+1+"");
                }
            }
        });
        //选择测试日期段
        calendar = Calendar.getInstance();
        stats_tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DateQuery.this, new DatePickerDialog.OnDateSetListener() {
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
                new DatePickerDialog(DateQuery.this, new DatePickerDialog.OnDateSetListener() {
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
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.DAY_OF_WEEK), calendar.get(Calendar.DAY_OF_WEEK)).show();
            }
        });

    }

    private void initView() {
        header_tv = (TextView) findViewById(R.id.header_tv);
        header_tv2 = (TextView) findViewById(R.id.header_tv2);
        header_tv3 = (TextView) findViewById(R.id.header_tv3);
        header_tv4 = (TextView) findViewById(R.id.header_tv4);
        btn_dayin = (Button) findViewById(R.id.btn_dayin);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        stats_spinner = (Spinner) findViewById(R.id.stats_spinner);
        stats_tv1 = (TextView) findViewById(R.id.stats_tv1);
        stats_tv2 = (TextView) findViewById(R.id.stats_tv2);
        stats_tv3 = (TextView) findViewById(R.id.stats_tv3);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        img_shang = (ImageView) findViewById(R.id.img_shang);
        img_xia = (ImageView) findViewById(R.id.img_xia);

        btn_dayin.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        footer_tv3 = (TextView) findViewById(R.id.footer_tv3);
        footer_tv1 = (TextView) findViewById(R.id.footer_tv1);
        footer_tv2 = (TextView) findViewById(R.id.footer_tv2);
        listview1 = (ListView) findViewById(R.id.listview1);
        listview2 = (ListView) findViewById(R.id.listview2);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dayin:
                startActivity(new Intent(DateQuery.this, QueryResult.class));
                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }

    class Bean {
        String number1, number2, date, result, name;
    }

    public class DateQueryItemAdapter extends BaseAdapter {

        private List<Bean> objects = new ArrayList<Bean>();

        private Context context;
        private LayoutInflater layoutInflater;

        public DateQueryItemAdapter(Context context, List<Bean> objects) {
            this.context = context;
            this.objects = objects;
            this.layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return objects.size();
        }

        @Override
        public Bean getItem(int position) {
            return objects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.date_query_item, null);
                convertView.setTag(new ViewHolder(convertView));
            }
            initializeViews((Bean) getItem(position), (ViewHolder) convertView.getTag());
            return convertView;
        }

        private void initializeViews(Bean bean, ViewHolder holder) {
            //TODO implement
            holder.itemTv1.setText(bean.number1);
            holder.itemTv2.setText(bean.number2);
            holder.itemTv3.setText(bean.date);
            holder.itemTv4.setText(bean.result);
            holder.itemTv5.setText(bean.name);
        }

        protected class ViewHolder {
            private TextView itemTv1;
            private TextView itemTv2;
            private TextView itemTv3;
            private TextView itemTv4;
            private TextView itemTv5;

            public ViewHolder(View view) {
                itemTv1 = (TextView) view.findViewById(R.id.item_tv1);
                itemTv2 = (TextView) view.findViewById(R.id.item_tv2);
                itemTv3 = (TextView) view.findViewById(R.id.item_tv3);
                itemTv4 = (TextView) view.findViewById(R.id.item_tv4);
                itemTv5 = (TextView) view.findViewById(R.id.item_tv5);
            }
        }
    }

}
