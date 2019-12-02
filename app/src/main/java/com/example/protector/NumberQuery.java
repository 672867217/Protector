package com.example.protector;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.protector.SQl.TestData;
import com.example.protector.SQl.XiuGai;
import com.example.protector.util.Utils;

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
        new Utils().hideNavKey(NumberQuery.this);

        list = new ArrayList();
        for (int i = 0; i < 4; i++) {
            TestData testData = new TestData();
            list.add(testData);
        }

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
                List<TestData> data = DataSupport.findAll(TestData.class);
                int a = 0;
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getChanpinbianma().equals(chanpinbianhao.getText().toString().trim())) {
                        if (data.get(i).getCecheng().equals("1")) {
                            list.set(0,data.get(i));
                            a++;
                        } else if (data.get(i).getCecheng().equals("2")) {
                            list.set(1,data.get(i));
                            a++;
                        } else if (data.get(i).getCecheng().equals("3")) {
                            list.set(2,data.get(i));
                            a++;
                        } else if (data.get(i).getCecheng().equals("0")) {
                            list.set(3,data.get(i));
                            a++;
                        }
                        ArrayAdapter arrayAdapter = new ArrayAdapter(NumberQuery.this,R.layout.spinner, new String[]{data.get(i).getChanpinname()});
                        chanpin_spinner.setAdapter(arrayAdapter);
                        xinghao.setText(data.get(i).getXinghao());
                        shengchanchang.setText(data.get(i).getShengchanchang());
                    }
                }
                if(a>0){
                    numberQueryItemAdapter.notifyDataSetChanged();
                }else
                {
                    Toast.makeText(this, "没有查到"+chanpinbianhao.getText().toString()+"的数据", Toast.LENGTH_SHORT).show();
                }
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
            if (!object.getAduanxiangxiangying().equals("0")) {
                holder.item1Tv1.setText(object.getGongwei());
                holder.item1Tv2.setText(simpleDateFormat.format(object.getDate())+"");
                holder.item1Tv3.setText(simpleDateFormat2.format(object.getDate2()) + "");
                System.out.println(object.getDate());
                holder.item1Tv4.setText(object.getName());

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
                holder.item1Tv6.setText(arr[arr.length - 1] + "");
                holder.item1Tv7.setText(object.getM13xianshishijian());
                holder.item1Tv8.setText(object.getM30xianshishijian());
                holder.item1Tv9.setText(object.getXianquanchuanlian5());
                holder.item1Tv10.setText(arr2[arr2.length - 1] + "");
                holder.item1Tv11.setText(arr3[arr3.length - 1] + "");
                holder.item1Tv12.setText(arr4[0] + "");
                holder.item1Title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), QueryResult.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", object);
                        bundle.putInt("dianzu", arr4[0]);
                        bundle.putInt("flag", 1);
                        intent.putExtra("s", bundle);
                        startActivity(intent);
                    }
                });
                boolean is = true;
                XiuGai xiuGai = DataSupport.find(XiuGai.class, Integer.parseInt(object.getGongwei()));
                if (Double.parseDouble(object.getQidongshijian()) * 100
                        > Double.parseDouble(xiuGai.getQidong()) * 100) {
                    holder.item1Tv5.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                }
                if (arr[2] * 100
                        > Double.parseDouble(xiuGai.getDuanxiang()) * 100) {
                    holder.item1Tv6.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                }
                if (Double.parseDouble(object.getM13xianshishijian()) * 100
                        < (13-Double.parseDouble(xiuGai.getM13())) * 100
                        || Double.parseDouble(object.getM13xianshishijian()) * 100
                        > (13+Double.parseDouble(xiuGai.getM13())) * 100) {
                    holder.item1Tv7.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                }
                if (Double.parseDouble(object.getM30xianshishijian()) * 100
                        < (30-Double.parseDouble(xiuGai.getM30())) * 100
                        || Double.parseDouble(object.getM30xianshishijian()) * 100
                        > (30+Double.parseDouble(xiuGai.getM30())) * 100) {
                    holder.item1Tv8.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                }
                if (Double.parseDouble(object.getXianquanchuanlian5()) * 100
                        > Double.parseDouble(xiuGai.getChuanlian2()) * 100
                        || Double.parseDouble(object.getXianquanchuanlian5()) * 100
                        < Double.parseDouble(xiuGai.getChuanlian1()) * 100) {
                    holder.item1Tv9.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                }
                if (arr2[2] * 100
                        > Double.parseDouble(xiuGai.getDuanxiangzhiliu()) * 100) {
                    holder.item1Tv10.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                }
                if (arr3[2] * 100
                        > Double.parseDouble(xiuGai.getJiaoliu()) * 100) {
                    holder.item1Tv11.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                }
                if (arr4[0] * 100
                       < Double.parseDouble(xiuGai.getXianquan()) * 100) {
                    holder.item1Tv12.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                }
                holder.item1Tv13.setText(guzhang(object.getAjiguzhang(), object.getBjiguzhang()));
                if(guzhang(object.getAjiguzhang(), object.getBjiguzhang()).equals("不合格"))
                {
                    holder.item1Tv13.setBackgroundResource(R.drawable.dialog_test2_3);
                }
                if (is && holder.item1Tv13.getText().toString().equals("合格")) {
                    holder.item1Tv14.setText("合格");
                } else {
                    holder.item1Tv14.setText("不合格");
                    holder.item1Tv14.setBackgroundResource(R.drawable.dialog_test2_3);
                }
            }

            holder.item1Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(NumberQuery.this, "测试中。。。", Toast.LENGTH_SHORT).show();
                }
            });
        }
        private String guzhang(String s1, String s2) {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == '1' || s2.charAt(i) == '1') {
                    return "不合格";
                }
            }
            return "合格";
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
