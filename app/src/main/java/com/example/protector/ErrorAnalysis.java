package com.example.protector;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
    private GridView gridView;
    private ListView listView;
    private ErroritemAdapter adapter;
    private ErrorlistitemAdapter errorlistitemAdapter;
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
        List list2 = new ArrayList();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list2.add("");
        list2.add("");
        list2.add("");
        list2.add("");
        list2.add("");
        list2.add("");
        list2.add("");
        list2.add("");
        list2.add("");
        list2.add("");
        list2.add("");
        adapter = new ErroritemAdapter(getApplicationContext(),list);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        errorlistitemAdapter = new ErrorlistitemAdapter(getApplicationContext(),list2);
        listView.setAdapter(errorlistitemAdapter);
        errorlistitemAdapter.notifyDataSetChanged();
    }

    private void initView() {
        headerTv = (TextView) findViewById(R.id.header_tv);
        headerTv2 = (TextView) findViewById(R.id.header_tv2);
        headerTv3 = (TextView) findViewById(R.id.header_tv3);
        headerTv4 = (TextView) findViewById(R.id.header_tv4);
        btnChaxun = (Button) findViewById(R.id.btn_chaxun);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        statsSpinner = (Spinner) findViewById(R.id.stats_spinner);
        statsTv1 = (TextView) findViewById(R.id.stats_tv1);
        statsTv2 = (TextView) findViewById(R.id.stats_tv2);
        statsTv3 = (TextView) findViewById(R.id.stats_tv3);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        gridView = (GridView) findViewById(R.id.gridView);
        listView = (ListView) findViewById(R.id.listView);
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

        private List<String> objects = new ArrayList();

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
                convertView = layoutInflater.inflate(R.layout.errorlistitem, null);
                convertView.setTag(new ViewHolder(convertView));
            }
            initializeViews((String)getItem(position), (ViewHolder) convertView.getTag());
            return convertView;
        }

        private void initializeViews(String object, ViewHolder holder) {
            //TODO implement
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
