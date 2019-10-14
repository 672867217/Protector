package com.example.protector;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Button;

public class Test extends AppCompatActivity implements View.OnClickListener {

    private TextView header_tv;
    private TextView header_tv2;
    private TextView header_tv3;
    private TextView header_tv4;
    private Button test_btn;
    private GridView test_gv1;
    private GridView test_gv2;
    TestGv1ItemAdapter testGv1ItemAdapter;
    TestGv2ItemAdapter testGv2ItemAdapter;
    List list1 = new ArrayList();
    List list2 = new ArrayList();

    String[] gv1_name = {"一测统计","二测统计","三测统计","其他统计"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();

        for (int i = 0; i < 5; i++) {
            if (i < 4) {
                Bean1 bean1 = new Bean1();
                bean1.name = gv1_name[i];
                list1.add(bean1);
            }

            Bean1 bean2 = new Bean1();
            bean2.name = "工位"+(i+1);
            list2.add(bean2);
        }
        testGv1ItemAdapter = new TestGv1ItemAdapter(this,list1);
        test_gv1.setAdapter(testGv1ItemAdapter);
        testGv1ItemAdapter.notifyDataSetChanged();

        testGv2ItemAdapter = new TestGv2ItemAdapter(this,list2);
        test_gv2.setAdapter(testGv2ItemAdapter);
        testGv2ItemAdapter.notifyDataSetChanged();


    }

    private void initView() {
        header_tv = (TextView) findViewById(R.id.header_tv);
        header_tv2 = (TextView) findViewById(R.id.header_tv2);
        header_tv3 = (TextView) findViewById(R.id.header_tv3);
        header_tv4 = (TextView) findViewById(R.id.header_tv4);
        test_btn = (Button) findViewById(R.id.test_btn);
        test_gv1 = (GridView) findViewById(R.id.test_gv1);
        test_gv2 = (GridView) findViewById(R.id.test_gv2);

        test_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_btn:
                finish();
                break;
        }
    }

    class Bean1{
        String name;
        int value1,value2,value3,value4;
    }

    public class TestGv1ItemAdapter extends BaseAdapter {

        private List<Bean1> objects = new ArrayList<Bean1>();

        private Context context;
        private LayoutInflater layoutInflater;

        public TestGv1ItemAdapter(Context context,List<Bean1> objects) {
            this.context = context;
            this.objects = objects;
            this.layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return objects.size();
        }

        @Override
        public Bean1 getItem(int position) {
            return objects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.test_gv1_item, null);
                convertView.setTag(new ViewHolder(convertView));
            }
            initializeViews(getItem(position), (ViewHolder) convertView.getTag());
            return convertView;
        }

        private void initializeViews(Bean1 bean1, ViewHolder holder) {
            //TODO implement
            holder.testItem1Tv.setText(bean1.name);
            holder.testItem1Tv1.setText(bean1.value1+"");
            holder.testItem1Tv2.setText(bean1.value2+"");
            holder.testItem1Tv3.setText(bean1.value3+"");
            holder.testItem1Tv4.setText(bean1.value4+"");
        }

        protected class ViewHolder {
            private TextView testItem1Tv;
            private TextView testItem1Tv1;
            private TextView testItem1Tv2;
            private TextView testItem1Tv3;
            private TextView testItem1Tv4;

            public ViewHolder(View view) {
                testItem1Tv = (TextView) view.findViewById(R.id.test_item1_tv);
                testItem1Tv1 = (TextView) view.findViewById(R.id.test_item1_tv1);
                testItem1Tv2 = (TextView) view.findViewById(R.id.test_item1_tv2);
                testItem1Tv3 = (TextView) view.findViewById(R.id.test_item1_tv3);
                testItem1Tv4 = (TextView) view.findViewById(R.id.test_item1_tv4);
            }
        }
    }


//   class Bean2{}

    public class TestGv2ItemAdapter extends BaseAdapter {

        private List<Bean1> objects = new ArrayList<Bean1>();

        private Context context;
        private LayoutInflater layoutInflater;

        public TestGv2ItemAdapter(Context context,List<Bean1> objects) {
            this.context = context;
            this.objects = objects;
            this.layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return objects.size();
        }

        @Override
        public Bean1 getItem(int position) {
            return objects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.test_gv2_item, null);
                convertView.setTag(new ViewHolder(convertView));
            }
            initializeViews(getItem(position), (ViewHolder) convertView.getTag());
            return convertView;
        }

        private void initializeViews(Bean1 bean1, ViewHolder holder) {
            //TODO implement
            holder.testItem2Tv.setText(bean1.name);
            holder.testItem2Tv1.setText(bean1.value1+"");
            holder.testItem2Tv2.setText(bean1.value2+"");
            holder.testItem2Tv3.setText(bean1.value3+"");
            holder.testItem2Tv4.setText(bean1.value4+"");

            holder.testBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View view = LayoutInflater.from(Test.this).inflate(R.layout.dialog_test4,null);
                    final Dialog dialog = new AlertDialog.Builder(Test.this).setView(view).show();
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//                    final Timer timer = new Timer();
//                    TimerTask timerTask = new TimerTask() {
//                        @Override
//                        public void run() {
//                            dialog.dismiss();
//                            System.out.println("提示关闭了~~~~~~");
//                            timer.cancel();
//                        }
//                    };
//                    timer.schedule(timerTask,0,1000);
                }
            });
        }

        protected class ViewHolder {
            private TextView testItem2Tv;
            private TextView testItem2Tv1;
            private TextView testItem2Tv2;
            private TextView testItem2Tv3;
            private TextView testItem2Tv4;
            private TextView testItem2Tv5;
            private TextView testItem2Tv6;
            private TextView testItem2Tv7;
            private TextView testItem2Tv8;
            private TextView testItem2Tv9;
            private TextView testItem2Tv10;
            private TextView testItem2Tv11;
            private TextView testItem2Tv12;
            private TextView testItem2Tv13;
            private TextView testItem2Tv14;
            private TextView testItem2Tv15;
            private TextView testItem2Tv16;
            private TextView testItem2Tv17;
            private Button testBtn4;
            private Button testBtn3;
            private Button testBtn2;

            public ViewHolder(View view) {
                testItem2Tv = (TextView) view.findViewById(R.id.test_item2_tv);
                testItem2Tv1 = (TextView) view.findViewById(R.id.test_item2_tv1);
                testItem2Tv2 = (TextView) view.findViewById(R.id.test_item2_tv2);
                testItem2Tv3 = (TextView) view.findViewById(R.id.test_item2_tv3);
                testItem2Tv4 = (TextView) view.findViewById(R.id.test_item2_tv4);
                testItem2Tv5 = (TextView) view.findViewById(R.id.test_item2_tv5);
//                testItem2Tv6 = (TextView) view.findViewById(R.id.test_item2_tv6);
                testItem2Tv7 = (TextView) view.findViewById(R.id.test_item2_tv7);
                testItem2Tv8 = (TextView) view.findViewById(R.id.test_item2_tv8);
                testItem2Tv9 = (TextView) view.findViewById(R.id.test_item2_tv9);
                testItem2Tv10 = (TextView) view.findViewById(R.id.test_item2_tv10);
                testItem2Tv11 = (TextView) view.findViewById(R.id.test_item2_tv11);
                testItem2Tv12 = (TextView) view.findViewById(R.id.test_item2_tv12);
                testItem2Tv13 = (TextView) view.findViewById(R.id.test_item2_tv13);
                testItem2Tv14 = (TextView) view.findViewById(R.id.test_item2_tv14);
                testItem2Tv15 = (TextView) view.findViewById(R.id.test_item2_tv15);
                testItem2Tv16 = (TextView) view.findViewById(R.id.test_item2_tv16);
                testItem2Tv17 = (TextView) view.findViewById(R.id.test_item2_tv17);
                testBtn4 = (Button) view.findViewById(R.id.test_btn4);
                testBtn3 = (Button) view.findViewById(R.id.test_btn3);
                testBtn2 = (Button) view.findViewById(R.id.test_btn2);
            }
        }
    }

}
