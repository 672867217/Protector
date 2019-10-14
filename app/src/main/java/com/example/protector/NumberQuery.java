package com.example.protector;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class NumberQuery extends AppCompatActivity implements View.OnClickListener {

    private TextView header_tv;
    private TextView header_tv2;
    private TextView header_tv3;
    private TextView header_tv4;
    private Button chaxun;
    private Button fanhui;
    private TextView chanpinbianhao;
    private Spinner chanpin_spinner;
    private TextView xinghao;
    private TextView shengchanchang;
    private GridView gridView;
    private NumberQueryItemAdapter numberQueryItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_query);
        initView();
        List list = new ArrayList();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        numberQueryItemAdapter = new NumberQueryItemAdapter(getApplicationContext(),list);
        gridView.setAdapter(numberQueryItemAdapter);
        numberQueryItemAdapter.notifyDataSetChanged();
    }


    private void initView() {
        header_tv = (TextView) findViewById(R.id.header_tv);
        header_tv2 = (TextView) findViewById(R.id.header_tv2);
        header_tv3 = (TextView) findViewById(R.id.header_tv3);
        header_tv4 = (TextView) findViewById(R.id.header_tv4);
        chaxun = (Button) findViewById(R.id.chaxun);
        fanhui = (Button) findViewById(R.id.fanhui);
        chanpinbianhao = (TextView) findViewById(R.id.chanpinbianhao);
        chanpin_spinner = (Spinner) findViewById(R.id.chanpin_spinner);
        xinghao = (TextView) findViewById(R.id.xinghao);
        shengchanchang = (TextView) findViewById(R.id.shengchanchang);
        gridView = (GridView) findViewById(R.id.gridView);

        chaxun.setOnClickListener(this);
        fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chaxun:

                break;
            case R.id.fanhui:
                finish();
                break;
        }
    }
    public class NumberQueryItemAdapter extends BaseAdapter {

        private List<String> objects = new ArrayList();

        private Context context;
        private LayoutInflater layoutInflater;

        public NumberQueryItemAdapter(Context context,List list) {
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
                convertView = layoutInflater.inflate(R.layout.number_query_item, null);
                convertView.setTag(new ViewHolder(convertView));
            }
            initializeViews((String)getItem(position), (ViewHolder) convertView.getTag());
            return convertView;
        }

        private void initializeViews(String object, ViewHolder holder) {
            //TODO implement
            holder.item1Title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),NumberQueryParticular.class));
                }
            });
        }

        protected class ViewHolder {
            private TextView item1Title;
            private TextView item1Tv1;
            private TextView item1Tv2;
            private TextView item1Tv3;
            private TextView item1Tv4;
            private TextView item1Tv5;
            private TextView item1Tv6;
            private TextView item1Tv7;
            private TextView item1Tv8;
            private TextView item1Tv9;
            private TextView item1Tv10;
            private TextView item1Tv11;
            private TextView item1Tv12;
            private TextView item1Tv13;
            private TextView item1Tv14;
            private TextView item1Tv15;
            private TextView item1Tv16;
            private Button item1Btn;

            public ViewHolder(View view) {
                item1Title = (TextView) view.findViewById(R.id.item1_title);
                item1Tv1 = (TextView) view.findViewById(R.id.item1_tv1);
                item1Tv2 = (TextView) view.findViewById(R.id.item1_tv2);
                item1Tv3 = (TextView) view.findViewById(R.id.item1_tv3);
                item1Tv4 = (TextView) view.findViewById(R.id.item1_tv4);
                item1Tv5 = (TextView) view.findViewById(R.id.item1_tv5);
                item1Tv6 = (TextView) view.findViewById(R.id.item1_tv6);
                item1Tv7 = (TextView) view.findViewById(R.id.item1_tv7);
                item1Tv8 = (TextView) view.findViewById(R.id.item1_tv8);
                item1Tv9 = (TextView) view.findViewById(R.id.item1_tv9);
                item1Tv10 = (TextView) view.findViewById(R.id.item1_tv10);
                item1Tv11 = (TextView) view.findViewById(R.id.item1_tv11);
                item1Tv12 = (TextView) view.findViewById(R.id.item1_tv12);
                item1Tv13 = (TextView) view.findViewById(R.id.item1_tv13);
                item1Tv14 = (TextView) view.findViewById(R.id.item1_tv14);
                item1Tv15 = (TextView) view.findViewById(R.id.item1_tv15);
                item1Tv16 = (TextView) view.findViewById(R.id.item1_tv16);
                item1Btn = (Button) view.findViewById(R.id.item1_btn);
            }
        }
    }
}
