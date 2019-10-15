package com.example.protector;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private ImageView img_shang,img_xia;
    private ListView listview1;
    private ListView listview2;

    List list1 = new ArrayList();
    List list2 = new ArrayList();
    DateQueryItemAdapter adapter1,adapter2;
    private int page,index =36, shuliang = 200;
    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_query);
        initView();


        dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        for (int i = 0; i < 36; i++) {
            Bean bean = new Bean();
            bean.number1 = "000"+(i+1);
            bean.number2 = "8000000"+(i+1);
            bean.date = dateFormat.format(new Date());
            bean.result = "合格";
            bean.name = "柳铁信息";
            if (i < 18) {
                list1.add(bean);
            }else {
                list2.add(bean);
            }
        }
        adapter1 = new DateQueryItemAdapter(this,list1);
        listview1.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();
        adapter2 = new DateQueryItemAdapter(this,list2);
        listview2.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();
        // 进入页面根据数量默认显示
        if (shuliang > 36) {
            img_shang.setBackgroundResource(R.drawable.shangjiantouhui);
            img_xia.setBackgroundResource(R.drawable.xiajiantou);
        }else {
            img_shang.setBackgroundResource(R.drawable.shangjiantouhui);
            img_xia.setBackgroundResource(R.drawable.xiajiantouhui);
        }
        //翻页点击逻辑
        page = 0;
        img_shang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              page = page-1;
            }
        });
        img_xia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = page+1;
                if (page > 0) {
                    index = index*page;
                }
                list1.clear();
                list2.clear();
                for (int i = index; i < shuliang; i++) {
                    Bean bean = new Bean();
                    bean.number1 = "000"+(i+1);
                    bean.number2 = "8000000"+(i+1);
                    bean.date = dateFormat.format(new Date());
                    bean.result = "合格";
                    bean.name = "柳铁信息";
                    if (i < index+18) {
                        list1.add(bean);
                    }else if (i>=index+18){
                        list2.add(bean);
                    }
                    adapter1.notifyDataSetChanged();
                    adapter2.notifyDataSetChanged();
                }
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
