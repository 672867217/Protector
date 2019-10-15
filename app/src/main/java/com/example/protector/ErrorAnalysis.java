package com.example.protector;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ErrorAnalysis extends AppCompatActivity {

    private TextView headerTv;
    private TextView headerTv2;
    private TextView headerTv3;
    private TextView headerTv4;
    private Button btnChaxun;
    private Button btnCancel;
    private Spinner statsSpinner;
    private TextView statsTv1;
    private TextView statsTv2;
    private TextView statsTv3;
    private TextView textView5;
    private TextView textView6;
    private TextView textView11;
    private GridView gridView;
    private ListView listView;
    private Button up,down;
    private int page, index = 8, shuliang = 100;
    private ErroritemAdapter adapter;
    private ErrorlistitemAdapter errorlistitemAdapter;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_analysis);
        initView();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        List list = new ArrayList();
        final List list2 = new ArrayList();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        for (int i = 0; i < shuliang; i++) {
            if(i==8){
                break;
            }
            Particular particular = new Particular();
            particular.id = String.format("%04d",i+1);
            particular.number = String.format("%04d",i+1);
            particular.time = dateFormat.format(new Date());
            particular.a = "0.08";
            particular.b = "0.16";
            particular.c = "0.68";
            particular.chuanlian = "1.08";
            particular.binglian = "0.28";
            particular.qidong = "0";
            particular.duanxiang = "0";
            particular.m13 = "21";
            particular.m30 = "33";
            particular.ceshi = "合格";
            particular.man = "赵四";
            list2.add(particular);
        }
        adapter = new ErroritemAdapter(getApplicationContext(),list);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        errorlistitemAdapter = new ErrorlistitemAdapter(getApplicationContext(),list2);
        listView.setAdapter(errorlistitemAdapter);
        listView.setEnabled(false);
        errorlistitemAdapter.notifyDataSetChanged();
        if (shuliang > 8) {
            up.setBackgroundResource(R.drawable.shangjiantouhui);
            down.setBackgroundResource(R.drawable.xiajiantou);
            up.setEnabled(false);
        } else {
            up.setBackgroundResource(R.drawable.shangjiantouhui);
            down.setBackgroundResource(R.drawable.xiajiantouhui);
            up.setEnabled(false);
            down.setEnabled(false);
        }
        page = 0;
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page > 0) {
                    if(page == 1){
                        up.setBackgroundResource(R.drawable.shangjiantouhui);
                        up.setEnabled(false);
                    }
                    down.setEnabled(true);
                    down.setBackgroundResource(R.drawable.xiajiantou);
                    page = page - 1;
                    textView11.setText(page+1+"");
                    list2.clear();
                    for (int i = index * page; i < index * page + 8; i++) {
                        Particular particular = new Particular();
                        particular.id = String.format("%04d",i+1);
                        particular.number = String.format("%04d",i+1);
                        particular.time = dateFormat.format(new Date());
                        particular.a = "0.08";
                        particular.b = "0.16";
                        particular.c = "0.68";
                        particular.chuanlian = "1.08";
                        particular.binglian = "0.28";
                        particular.qidong = "0";
                        particular.duanxiang = "0";
                        particular.m13 = "21";
                        particular.m30 = "33";
                        particular.ceshi = "合格";
                        particular.man = "赵四";
                        list2.add(particular);
                        errorlistitemAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shuliang / 8 > page) {
                    if(page+1 == shuliang/8){
                        down.setBackgroundResource(R.drawable.xiajiantouhui);
                        down.setEnabled(false);
                    }
                    up.setBackgroundResource(R.drawable.shangjiantou);
                    up.setEnabled(true);
                    page = page + 1;
                    textView11.setText(page+1+"");
                    list2.clear();
                    for (int i = index * page; i < index * page + 8; i++) {
                        if (i == shuliang) {
                            for (int j = 0; j < 8 - shuliang % 8; j++) {
                                Particular particular = new Particular();
                                particular.id = String.format("%04d",i+1);
                                particular.number = String.format("%04d",i+1);
                                particular.time = dateFormat.format(new Date());
                                particular.a = "0.08";
                                particular.b = "0.16";
                                particular.c = "0.68";
                                particular.chuanlian = "1.08";
                                particular.binglian = "0.28";
                                particular.qidong = "0";
                                particular.duanxiang = "0";
                                particular.m13 = "21";
                                particular.m30 = "33";
                                particular.ceshi = "合格";
                                particular.man = "赵四";
                                list2.add(particular);
                            }
                            return;
                        } else {
                            Particular particular = new Particular();
                            particular.id = String.format("%04d",i+1);
                            particular.number = String.format("%04d",i+1);
                            particular.time = dateFormat.format(new Date());
                            particular.a = "0.08";
                            particular.b = "0.16";
                            particular.c = "0.68";
                            particular.chuanlian = "1.08";
                            particular.binglian = "0.28";
                            particular.qidong = "0";
                            particular.duanxiang = "0";
                            particular.m13 = "21";
                            particular.m30 = "33";
                            particular.ceshi = "合格";
                            particular.man = "赵四";
                            list2.add(particular);
                        }
                        errorlistitemAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    class Particular{
        String id;
        String number;
        String time;
        String a;
        String b;
        String c;
        String chuanlian;
        String binglian;
        String qidong;
        String duanxiang;
        String m13;
        String m30;
        String ceshi;
        String man;
    }
    private void initView() {
        headerTv = (TextView) findViewById(R.id.header_tv);
        headerTv2 = (TextView) findViewById(R.id.header_tv2);
        headerTv3 = (TextView) findViewById(R.id.header_tv3);
        headerTv4 = (TextView) findViewById(R.id.header_tv4);
        btnChaxun = (Button) findViewById(R.id.btn_dayin);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        statsSpinner = (Spinner) findViewById(R.id.stats_spinner);
        statsTv1 = (TextView) findViewById(R.id.stats_tv1);
        statsTv2 = (TextView) findViewById(R.id.stats_tv2);
        statsTv3 = (TextView) findViewById(R.id.stats_tv3);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView11 = (TextView) findViewById(R.id.textView11);
        gridView = (GridView) findViewById(R.id.gridView);
        listView = (ListView) findViewById(R.id.listView);
        up = (Button) findViewById(R.id.up);
        down= (Button) findViewById(R.id.down);
    }

    public class ErroritemAdapter extends BaseAdapter {

        private List<String> objects = new ArrayList<String>();

        private Context context;
        private LayoutInflater layoutInflater;

        public ErroritemAdapter(Context context,List list) {
            this.context = context;
            this.layoutInflater = LayoutInflater.from(context);
            this.objects = list;
        }

        @Override
        public int getCount() {
            return objects.size();
        }

        @Override
        public String getItem(int position) {
            return objects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.erroritem, null);
                convertView.setTag(new ViewHolder(convertView));
            }
            initializeViews((String)getItem(position), (ViewHolder) convertView.getTag());
            return convertView;
        }

        private void initializeViews(String object, ViewHolder holder) {
            //TODO implement
        }

        protected class ViewHolder {
            private TextView item1Title;
            private TextView itemTv1;
            private TextView itemTv2;
            private TextView itemTv3;
            private TextView itemTv4;
            private TextView itemTv5;
            private TextView itemTv6;
            private TextView itemTv7;
            private TextView itemTv8;
            private TextView itemTv9;
            private TextView itemTv10;
            private TextView itemTv11;
            private TextView itemTv12;
            private TextView itemTv13;
            private TextView itemTv14;
            private Button itemBtn;

            public ViewHolder(View view) {
                item1Title = (TextView) view.findViewById(R.id.item1_title);
                itemTv1 = (TextView) view.findViewById(R.id.item_tv1);
                itemTv2 = (TextView) view.findViewById(R.id.item_tv2);
                itemTv3 = (TextView) view.findViewById(R.id.item_tv3);
                itemTv4 = (TextView) view.findViewById(R.id.item_tv4);
                itemTv5 = (TextView) view.findViewById(R.id.item_tv5);
                itemTv6 = (TextView) view.findViewById(R.id.item_tv6);
                itemTv7 = (TextView) view.findViewById(R.id.item_tv7);
                itemTv8 = (TextView) view.findViewById(R.id.item_tv8);
                itemTv9 = (TextView) view.findViewById(R.id.item_tv9);
                itemTv10 = (TextView) view.findViewById(R.id.item_tv10);
                itemTv11 = (TextView) view.findViewById(R.id.item_tv11);
                itemTv12 = (TextView) view.findViewById(R.id.item_tv12);
                itemTv13 = (TextView) view.findViewById(R.id.item_tv13);
                itemTv14 = (TextView) view.findViewById(R.id.item_tv14);
                itemBtn = (Button) view.findViewById(R.id.item_btn);
            }
        }
    }

    public class ErrorlistitemAdapter extends BaseAdapter {

        private List<Particular> objects = new ArrayList();

        private Context context;
        private LayoutInflater layoutInflater;

        public ErrorlistitemAdapter(Context context,List list) {
            this.context = context;
            this.layoutInflater = LayoutInflater.from(context);
            this.objects = list;
        }

        @Override
        public int getCount() {
            return objects.size();
        }

        @Override
        public Particular getItem(int position) {
            return objects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.errorlistitem, parent,false);
                convertView.setTag(new ViewHolder(convertView));
            }
            initializeViews((Particular)getItem(position), (ViewHolder) convertView.getTag());
            return convertView;
        }

        private void initializeViews(Particular object, ViewHolder holder) {
            //TODO implement
            holder.textView12.setText(object.id);
            holder.textView13.setText(object.number);
            holder.textView14.setText(object.time);
            holder.textView15.setText(object.a);
            holder.textView16.setText(object.b);
            holder.textView17.setText(object.c);
            holder.textView18.setText(object.chuanlian);
            holder.textView19.setText(object.binglian);
            holder.textView20.setText(object.qidong);
            holder.textView21.setText(object.duanxiang);
            holder.textView22.setText(object.m13);
            holder.textView23.setText(object.m30);
            holder.textView25.setText(object.ceshi);
            holder.textView26.setText(object.man);
        }

        protected class ViewHolder {
            private TextView textView12;
            private TextView textView13;
            private TextView textView14;
            private TextView textView15;
            private TextView textView16;
            private TextView textView17;
            private TextView textView18;
            private TextView textView19;
            private TextView textView20;
            private TextView textView21;
            private TextView textView22;
            private TextView textView23;
            private TextView textView25;
            private TextView textView26;

            public ViewHolder(View view) {
                textView12 = (TextView) view.findViewById(R.id.textView12);
                textView13 = (TextView) view.findViewById(R.id.textView13);
                textView14 = (TextView) view.findViewById(R.id.textView14);
                textView15 = (TextView) view.findViewById(R.id.textView15);
                textView16 = (TextView) view.findViewById(R.id.textView16);
                textView17 = (TextView) view.findViewById(R.id.textView17);
                textView18 = (TextView) view.findViewById(R.id.textView18);
                textView19 = (TextView) view.findViewById(R.id.textView19);
                textView20 = (TextView) view.findViewById(R.id.textView20);
                textView21 = (TextView) view.findViewById(R.id.textView21);
                textView22 = (TextView) view.findViewById(R.id.textView22);
                textView23 = (TextView) view.findViewById(R.id.textView23);
                textView25 = (TextView) view.findViewById(R.id.textView25);
                textView26 = (TextView) view.findViewById(R.id.textView26);
            }
        }
    }

}
