package com.example.protector;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.protector.SQl.TestData;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberQuery extends AppCompatActivity implements View.OnClickListener {

    private TextView header_tv;
    private TextView header_tv2;
    private TextView header_tv3;
    private TextView header_tv4;
    private Button chaxun;
    private Button fanhui;
    private EditText chanpinbianhao;
    private Spinner chanpin_spinner;
    private TextView xinghao;
    private TextView shengchanchang;
    private GridView gridView;
    private NumberQueryItemAdapter numberQueryItemAdapter;
    private List list;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_query);
        initView();
        list = new ArrayList();
        TestData testData = new TestData();
        list.add(testData);
        list.add(testData);
        list.add(testData);
        list.add(testData);
        numberQueryItemAdapter = new NumberQueryItemAdapter(getApplicationContext(), list);
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
        chanpinbianhao = (EditText) findViewById(R.id.chanpinbianhao);
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
                list.clear();
                List<TestData> data = DataSupport.findAll(TestData.class);
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getChanpinbianma().equals(chanpinbianhao.getText().toString().trim())) {
                        if (data.get(i).getCecheng().equals("1")) {
                            list.add(data.get(i));
                        } else if (data.get(i).getCecheng().equals("2")) {
                            list.add(data.get(i));
                        } else if (data.get(i).getCecheng().equals("3")) {
                            list.add(data.get(i));
                        } else if (data.get(i).getCecheng().equals("0")) {
                            list.add(data.get(i));
                        }
                    }
                }
                numberQueryItemAdapter.notifyDataSetChanged();
                break;
            case R.id.fanhui:
                finish();
                break;
        }
    }

    public class NumberQueryItemAdapter extends BaseAdapter {

        private List<TestData> objects = new ArrayList();

        private Context context;
        private LayoutInflater layoutInflater;

        public NumberQueryItemAdapter(Context context, List list) {
            this.context = context;
            this.layoutInflater = LayoutInflater.from(context);
            this.objects = list;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public TestData getItem(int position) {
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
            initializeViews((TestData) getItem(position), (ViewHolder) convertView.getTag(), position);
            return convertView;
        }

        private void initializeViews(final TestData object, ViewHolder holder, int position) {
            //TODO implement

            switch (position) {
                case 0:
                    holder.item1Title.setText("一测");
                    break;
                case 1:
                    holder.item1Title.setText("二测");
                    break;
                case 2:
                    holder.item1Title.setText("三测");
                    break;
                case 3:
                    holder.item1Title.setText("其他");
                    break;
            }
            if(!object.getAduanxiangxiangying().equals("")){
                holder.item1Tv1.setText(object.getGongwei());
                //holder.item1Tv2.setText(simpleDateFormat.format(object.getDate())+"");
                holder.item1Tv3.setText(simpleDateFormat2.format(object.getDate())+"");
                System.out.println(object.getDate());
                holder.item1Tv4.setText("");
                holder.item1Tv5.setText(object.getQidongshijian());
                int[] arr = new int[3];
                double[] arr2 = new double[3];
                double[] arr3 = new double[3];
                final int[] arr4 = new int[9];
                arr[0] = Integer.parseInt(object.getAduanxiangxiangying());
                arr[1] = Integer.parseInt(object.getBduanxiangxiangying());
                arr[2] = Integer.parseInt(object.getCduanxiangxiangying());
                arr2[0] = Double.parseDouble(object.getAduanxiangdianya());
                arr2[1] = Double.parseDouble(object.getBduanxiangdianya());
                arr2[2] = Double.parseDouble(object.getCduanxiangdianya());
                arr3[0] = Double.parseDouble(object.getAxiangceyajiang());
                arr3[1] = Double.parseDouble(object.getBxiangceyajiang());
                arr3[2] = Double.parseDouble(object.getCxiangceyajiang());
                arr4[0] = Integer.parseInt(object.getAbxiangjianjueyuan());
                arr4[1] = Integer.parseInt(object.getAcxiangjianjueyuan());
                arr4[2] = Integer.parseInt(object.getBcxiangjianjueyuan());
                arr4[3] = Integer.parseInt(object.getAxiangduidijueyuan());
                arr4[4] = Integer.parseInt(object.getBxiangduidijueyuan());
                arr4[5] = Integer.parseInt(object.getCxiangduidijueyuan());
                arr4[6] = Integer.parseInt(object.getAxiangduixianquanjueyuan());
                arr4[7] = Integer.parseInt(object.getBxiangduixianquanjueyuan());
                arr4[8] = Integer.parseInt(object.getCxiangduixianquanjeuyuan());
                Arrays.sort(arr);
                Arrays.sort(arr2);
                Arrays.sort(arr3);
                Arrays.sort(arr4);
                holder.item1Tv6.setText(arr[arr.length-1]+"");
                holder.item1Tv7.setText(object.getM13xianshishijian());
                holder.item1Tv8.setText(object.getM30xianshishijian());
                holder.item1Tv9.setText("");
                holder.item1Tv10.setText(arr2[arr2.length-1]+"");
                holder.item1Tv11.setText(arr3[arr3.length-1]+"");
                holder.item1Tv12.setText(arr4[0]+"");
                holder.item1Tv13.setText("");
                holder.item1Tv14.setText("");
                holder.item1Title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), QueryResult.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data",object);
                        bundle.putSerializable("dianzu",arr4[0]);
                        intent.putExtra("s",bundle);
                        startActivity(intent);
                    }
                });
            }

            holder.item1Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

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
                item1Btn = (Button) view.findViewById(R.id.item1_btn);
            }
        }
    }
}
