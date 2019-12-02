package com.example.protector;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.protector.SQl.Operator;
import com.example.protector.SQl.ProductType;
import com.example.protector.SQl.TestData;
import com.example.protector.SQl.XiuGai;
import com.example.protector.util.Utils;

import org.litepal.crud.DataSupport;

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
    private Button up, down;
    private int page, index = 8, shuliang = 0;
    private ErroritemAdapter adapter;
    private ErrorlistitemAdapter errorlistitemAdapter;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
    private List<Particular> list2;
    private Calendar calendar;
    private List<Particular2> list;
    private List<TestData> data;
    private List<TestData> gongweiList1;
    private List<TestData> gongweiList2;
    private List<TestData> gongweiList3;
    private List<TestData> gongweiList4;
    private List<TestData> gongweiList5;
    private List sp1 = new ArrayList();
    private List sp2 = new ArrayList();
    BigDecimal b3 = new BigDecimal("0.01");
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private int posi;

    private String jisuan2(String s) {
        BigDecimal b2 = new BigDecimal(s);
        return decimalFormat.format(b2.multiply(b3));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_analysis);
        initView();

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        //下方控制栏
        new Utils().hideNavKey(ErrorAnalysis.this);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        list = new ArrayList();
        list2 = new ArrayList();
        list.add(new Particular2());
        list.add(new Particular2());
        list.add(new Particular2());
        list.add(new Particular2());
        list.add(new Particular2());
        init();
        adapter = new ErroritemAdapter(getApplicationContext(), list);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        List<ProductType> types = DataSupport.findAll(ProductType.class);
        for (int i = 0; i < types.size(); i++) {
            sp1.add(types.get(i).getName());
            sp2.add(types.get(i).getXinghao());
        }
        ArrayAdapter saveAdapter = new ArrayAdapter(ErrorAnalysis.this, R.layout.spinner, sp1);
        statsSpinner.setAdapter(saveAdapter);
        statsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                statsTv1.setText(sp2.get(i)+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        errorlistitemAdapter = new ErrorlistitemAdapter(getApplicationContext(), list2);
        listView.setAdapter(errorlistitemAdapter);
        listView.setEnabled(false);
        btn();
        page = 0;
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page > 0) {
                    if (page == 1) {
                        up.setBackgroundResource(R.drawable.shangjiantouhui);
                        up.setEnabled(false);
                    }
                    down.setEnabled(true);
                    down.setBackgroundResource(R.drawable.xiajiantou);
                    page = page - 1;
                    textView11.setText(page + 1 + "");
                    list2.clear();
                    int a = 0;
                    if (8 * page + 8 > shuliang) {
                        a = shuliang;
                    } else {
                        a = 8 * page + 8;
                    }
                    for (int i = index * page; i < a; i++) {
                        list2.add(get(i));
                        errorlistitemAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shuliang / 8 > page) {
                    if (page + 1 == shuliang / 8) {
                        down.setBackgroundResource(R.drawable.xiajiantouhui);
                        down.setEnabled(false);
                    }
                    up.setBackgroundResource(R.drawable.shangjiantou);
                    up.setEnabled(true);
                    page = page + 1;
                    textView11.setText(page + 1 + "");
                    list2.clear();
                    int a = 0;
                    if (8 * page + 8 > shuliang) {
                        a = shuliang;
                    } else {
                        a = 8 * page + 8;
                    }
                    for (int i = 8 * page; i < a; i++) {
                        list2.add(get(i));
                        if (i == a - 1 && a % 8 > 0) {
                            for (int j = 0; j < 8 - shuliang % 8; j++) {
                                list2.add(new Particular());
                            }
                        }
                        errorlistitemAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        //选择测试日期段
        calendar = Calendar.getInstance();
        statsTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ErrorAnalysis.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String mymonth, myday;
                        if (month + 1 < 10) {
                            mymonth = "0" + (month + 1);
                        } else {
                            mymonth = String.valueOf(month + 1);
                        }
                        if (dayOfMonth  < 10) {
                            myday = "0" + dayOfMonth ;
                        } else {
                            myday = String.valueOf(dayOfMonth);
                        }
                        statsTv2.setText(year + "-" + mymonth + "-" + myday);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        statsTv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ErrorAnalysis.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String mymonth, myday;
                        if (month + 1 < 10) {
                            mymonth = "0" + (month + 1);
                        } else {
                            mymonth = String.valueOf(month + 1);
                        }
                        if (dayOfMonth  < 10) {
                            myday = "0" + dayOfMonth ;
                        } else {
                            myday = String.valueOf(dayOfMonth);
                        }
                        statsTv3.setText(year + "-" + mymonth + "-" + myday);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btnChaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                List<TestData> testDataList = DataSupport.findAll(TestData.class);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                //符合日期条件的所有数据list
                List<TestData> dataList = new ArrayList<>();
                for (int i = 0; i < testDataList.size(); i++) {
                    try {
                        Long startDate = dateFormat.parse(statsTv2.getText().toString()).getTime();
                        Long endDate = dateFormat.parse(statsTv3.getText().toString()).getTime();
                        Long ceshiDate = dateFormat.parse(dateFormat.format(testDataList.get(i).getDate())).getTime();
                        if (ceshiDate >= startDate && ceshiDate <= endDate
                                && testDataList.get(i).getChanpinname().equals(statsSpinner.getSelectedItem())
                                && testDataList.get(i).getXinghao().equals(statsTv1.getText().toString())) {
                            dataList.add(testDataList.get(i));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (dataList.isEmpty()) {
                    Toast.makeText(ErrorAnalysis.this, "没有符合条件的数据", Toast.LENGTH_SHORT).show();
                    return;
                }
                //每个工位的所有数据list
                gongweiList1 = new ArrayList<>();
                gongweiList2 = new ArrayList<>();
                gongweiList3 = new ArrayList<>();
                gongweiList4 = new ArrayList<>();
                gongweiList5 = new ArrayList<>();
                for (int j = 0; j < dataList.size(); j++) {
                    switch (dataList.get(j).getGongwei()) {
                        case "1":
                            gongweiList1.add(dataList.get(j));
                            break;
                        case "2":
                            gongweiList2.add(dataList.get(j));
                            break;
                        case "3":
                            gongweiList3.add(dataList.get(j));
                            break;
                        case "4":
                            gongweiList4.add(dataList.get(j));
                            break;
                        case "5":
                            gongweiList5.add(dataList.get(j));
                            break;
                    }
                }
                for (int i = 0; i < 5; i++) {
                    String[] data = new String[14];
                    switch (i) {
                        case 0:
                            if (!gongweiList1.isEmpty()) {
                                data = Wucha(gongweiList1,1);
                            }
                            break;
                        case 1:
                            if (!gongweiList2.isEmpty()) {
                                data = Wucha(gongweiList2,2);
                            }
                            break;
                        case 2:
                            if (!gongweiList3.isEmpty()) {
                                data = Wucha(gongweiList3,3);
                            }
                            break;
                        case 3:
                            if (!gongweiList4.isEmpty()) {
                                data = Wucha(gongweiList4,4);
                            }
                            break;
                        case 4:
                            if (!gongweiList5.isEmpty()) {
                                data = Wucha(gongweiList5,5);
                            }
                            break;
                    }
                    Particular2 particular2 = new Particular2();
                    particular2.id = "工位"+(i+1);
                    particular2.a = data[0];
                    particular2.b = data[1];
                    particular2.c = data[2];
                    particular2.chuanlian = data[3];
                    particular2.binglian = data[4];
                    particular2.qidong = data[5];
                    particular2.duanxiang = data[6];
                    particular2.m13 = data[7];
                    particular2.m30 = data[8];
                    particular2.ceshi1 = data[9];
                    particular2.ceshi2 = data[10];
                    particular2.ceshi3 = data[11];
                    particular2.qita = data[12];
                    particular2.heji = data[13];
                    list.add(particular2);
                }
                adapter.notifyDataSetChanged();


            }
        });

    }
    private void btn(){
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
    }
    private void init(){

        for (int j = 0; j < 8; j++) {
            Particular particular = new Particular();
            list2.add(particular);
        }
    }

    class Particular2 {
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
        String ceshi1,ceshi2,ceshi3,qita,heji;
    }

    class Particular {
        String id = "";
        String number= "";
        String time= "";
        String a= "";
        String b= "";
        String c= "";
        String chuanlian= "";
        String binglian= "";
        String qidong= "";
        String duanxiang= "";
        String m13= "";
        String m30= "";
        String ceshi= "",man= "";
    }

    private String[] Wucha(List<TestData> testDataList,int gongwei){
        List<Double> list1 = new ArrayList<>();
        List<Double> list2 = new ArrayList<>();
        List<Double> list3 = new ArrayList<>();
        List<Double> list4 = new ArrayList<>();
        List<Double> list5 = new ArrayList<>();
        List<Integer> list6 = new ArrayList<>();
        List<Integer> list7 = new ArrayList<>();
        List<Integer> list8 = new ArrayList<>();
        List<Integer> list9 = new ArrayList<>();
        XiuGai xiuGai = DataSupport.find(XiuGai.class,gongwei);
        int a = 0,b = 0,c = 0,d = 0;
        for (int i = 0; i < testDataList.size(); i++) {
            list1.add(Double.valueOf(testDataList.get(i).getAxiangawucha()));
            list1.add(Double.valueOf(testDataList.get(i).getBxiangawucha()));
            list2.add(Double.valueOf(testDataList.get(i).getAxiangbwucha()));
            list2.add(Double.valueOf(testDataList.get(i).getBxiangbwucha()));
            list3.add(Double.valueOf(testDataList.get(i).getAxiangcwucha()));
            list3.add(Double.valueOf(testDataList.get(i).getBxiangcwucha()));
            if(Integer.parseInt(testDataList.get(i).getQidongshijian())-Integer.parseInt(xiuGai.getQidong())<0){
                list6.add(Integer.parseInt(testDataList.get(i).getQidongshijian())-Integer.parseInt(xiuGai.getQidong()));
            }
            if(Integer.parseInt(testDataList.get(i).getAduanxiangxiangying())-Integer.parseInt(xiuGai.getDuanxiang())<0){
                list7.add(Integer.parseInt(testDataList.get(i).getAduanxiangxiangying())-Integer.parseInt(xiuGai.getDuanxiang()));
            }
            if(Integer.parseInt(testDataList.get(i).getBduanxiangxiangying())-Integer.parseInt(xiuGai.getDuanxiang())<0){
                list7.add(Integer.parseInt(testDataList.get(i).getBduanxiangxiangying())-Integer.parseInt(xiuGai.getDuanxiang()));
            }
            if(Integer.parseInt(testDataList.get(i).getCduanxiangxiangying())-Integer.parseInt(xiuGai.getDuanxiang())<0){
                list7.add(Integer.parseInt(testDataList.get(i).getCduanxiangxiangying())-Integer.parseInt(xiuGai.getDuanxiang()));
            }
            if(Double.parseDouble(testDataList.get(i).getM13xianshishijian())*100-(13+Double.parseDouble(xiuGai.getM13()))*100<0){
                list8.add(Integer.parseInt(String.valueOf(Double.parseDouble(testDataList.get(i).getM13xianshishijian())*100-(13+Double.parseDouble(xiuGai.getM13()))*100).replace(".0","")));
            }
            if(Double.parseDouble(testDataList.get(i).getM30xianshishijian())*100-(30+Double.parseDouble(xiuGai.getM30()))*100<0){
                list9.add(Integer.parseInt(String.valueOf(Double.parseDouble(testDataList.get(i).getM30xianshishijian())*100-(30+Double.parseDouble(xiuGai.getM13()))*100).replace(".0","")));
            }
            switch (testDataList.get(i).getCecheng()) {
                case "0":
                    d++;
                    break;
                case "1":
                    a++;
                    break;
                case "2":
                    b++;
                    break;
                case "3":
                    c++;
                    break;
            }
        }
        //升序排序 排序后计算最大和最小 误差值
        Collections.sort(list1);
        Collections.sort(list2);
        Collections.sort(list3);
        Collections.sort(list6);
        Collections.sort(list7);
        Collections.sort(list8);
        Collections.sort(list9);
        String[] data = new String[14];
        data[0] = list1.get(list1.size()-1)+"";
        data[1] = list2.get(list2.size()-1)+"";
        data[2] = list3.get(list3.size()-1)+"";
        System.out.println(list1.toString());
        System.out.println(list2.toString());
        System.out.println(list3.toString());
        data[3] = "";
        data[4] = "";
        data[5] = Math.abs(list6.get(0))+"";
        data[6] = Math.abs(list7.get(0))+"";
        data[7] = Math.abs(list8.get(0))+"";
        data[8] = Math.abs(list9.get(0))+"";
        data[9] = a+"";
        data[10] = b+"";
        data[11] = c+"";
        data[12] = d+"";
        data[13] = testDataList.size()+"";
        return data;
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
        down = (Button) findViewById(R.id.down);
    }

    public class ErroritemAdapter extends BaseAdapter {

        private List<Particular2> objects = new ArrayList<Particular2>();

        private Context context;
        private LayoutInflater layoutInflater;

        public ErroritemAdapter(Context context, List list) {
            this.context = context;
            this.layoutInflater = LayoutInflater.from(context);
            this.objects = list;
        }

        @Override
        public int getCount() {
            return objects.size();
        }

        @Override
        public Particular2 getItem(int position) {
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
            initializeViews((Particular2) getItem(position), (ViewHolder) convertView.getTag(),position);
            return convertView;
        }

        private void initializeViews(Particular2 particular2, ViewHolder holder, final int position) {
            //TODO implement
            holder.item1Title.setText("工位"+(position+1));

            if(particular2.m13 == null){
                holder.itemBtn.setBackgroundResource(R.drawable.queding);
                holder.itemBtn.setEnabled(false);
                holder.itemBtn.setTextColor(Color.parseColor("#000000"));
            }else
            {
                holder.itemTv1.setText(particular2.a.replace("-",""));
                holder.itemTv2.setText(particular2.b.replace("-",""));
                holder.itemTv3.setText(particular2.c.replace("-",""));
                holder.itemTv4.setText(particular2.chuanlian);
                holder.itemTv5.setText(particular2.binglian);
                holder.itemTv6.setText(particular2.qidong);
                holder.itemTv7.setText(particular2.duanxiang);
                holder.itemTv8.setText(particular2.m13);
                holder.itemTv9.setText(particular2.m30);
                holder.itemTv10.setText(particular2.ceshi1);
                holder.itemTv11.setText(particular2.ceshi2);
                holder.itemTv12.setText(particular2.ceshi3);
                holder.itemTv13.setText(particular2.qita);
                holder.itemTv14.setText(particular2.heji);
                holder.itemBtn.setBackgroundResource(R.drawable.xiangxixinxi);
                holder.itemBtn.setEnabled(true);
                holder.itemBtn.setTextColor(Color.parseColor("#000000"));
                if(Double.parseDouble(particular2.a)>0.1){
                    holder.itemTv1.setTextColor(Color.parseColor("#ffffff"));
                    holder.itemTv1.setBackgroundResource(R.drawable.dialog_test2_3);
                }

                if(Double.parseDouble(particular2.b)>0.1){
                    holder.itemTv2.setTextColor(Color.parseColor("#ffffff"));
                    holder.itemTv2.setBackgroundResource(R.drawable.dialog_test2_3);
                }

                if(Double.parseDouble(particular2.c)>0.1){
                    holder.itemTv3.setTextColor(Color.parseColor("#ffffff"));
                    holder.itemTv3.setBackgroundResource(R.drawable.dialog_test2_3);
                }

//                if(Double.parseDouble(particular2.chuanlian)>0.5){
//                    holder.itemTv4.setTextColor(Color.parseColor("#ffffff"));
//                    holder.itemTv4.setBackgroundResource(R.drawable.dialog_test2_3);
//                }
//
//                if(Double.parseDouble(particular2.binglian)>0.5){
//                    holder.itemTv5.setTextColor(Color.parseColor("#ffffff"));
//                    holder.itemTv5.setBackgroundResource(R.drawable.dialog_test2_3);
//                }
            }
            holder.itemBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    posi = position;
                    switch (position)
                    {
                        case 0:
                            data = gongweiList1;
                            break;
                        case 1:
                            data = gongweiList2;
                            break;
                        case 2:
                            data = gongweiList3;
                            break;
                        case 3:
                            data = gongweiList4;
                            break;
                        case 4:
                            data = gongweiList5;
                            break;
                    }
                    shuliang = data.size();
                    list2.clear();
                    int a = 0;
                    if (8 * page + 8 > shuliang) {
                        a = shuliang;
                    } else {
                        a = 8 * page + 8;
                    }
                    for (int i = 8 * page; i < a; i++) {

                        list2.add(get(i));
                        if (i == a - 1 && a % 8 > 0) {
                            for (int j = 0; j < 8 - shuliang % 8; j++) {
                                list2.add(new Particular());
                            }
                        }
                        errorlistitemAdapter = new ErrorlistitemAdapter(ErrorAnalysis.this,list2);
                        listView.setAdapter(errorlistitemAdapter);
                    }
                    if(data.size()>8){
                        down.setBackgroundResource(R.drawable.xiajiantou);
                        down.setEnabled(true);
                    }
                }
            });
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

    private Particular get(int i){
        Particular particular = new Particular();
        particular.id = String.format("%04d", i + 1);
        particular.number = data.get(i).getChanpinbianma();
        particular.time = dateFormat.format(data.get(i).getDate());
        particular.a = data.get(i).getAxiangawucha();
        particular.b = data.get(i).getAxiangbwucha();
        particular.c = data.get(i).getAxiangcwucha();
        particular.chuanlian = data.get(i).getXianquanchuanlian5();
        particular.binglian = data.get(i).getXianquanbinglian();
        particular.qidong = data.get(i).getQidongshijian();
        double[] arr = new double[3];
        arr[0] = Double.parseDouble(data.get(i).getAduanxiangxiangying());
        arr[1] = Double.parseDouble(data.get(i).getBduanxiangxiangying());
        arr[2] = Double.parseDouble(data.get(i).getCduanxiangxiangying());
        Arrays.sort(arr);
        particular.duanxiang = arr[2]+"";
        particular.m13 = data.get(i).getM13xianshishijian();
        particular.m30 = data.get(i).getM30xianshishijian();
        particular.ceshi = data.get(i).getTongguo();
        particular.man = data.get(i).getName();
        return particular;
    }
    public class ErrorlistitemAdapter extends BaseAdapter {

        private List<Particular> objects = new ArrayList();

        private Context context;
        private LayoutInflater layoutInflater;

        public ErrorlistitemAdapter(Context context, List list) {
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
                convertView = layoutInflater.inflate(R.layout.errorlistitem, parent, false);
                convertView.setTag(new ViewHolder(convertView));
            }
            initializeViews((Particular) getItem(position), (ViewHolder) convertView.getTag());
            return convertView;
        }

        private void initializeViews(Particular object, ViewHolder holder) {
            //TODO implement
            XiuGai xiuGai =DataSupport.find(XiuGai.class,posi);
            if(!object.a.equals("")){
                holder.textView12.setText(object.id);
                holder.textView13.setText(object.number);
                holder.textView14.setText(object.time);
                holder.textView15.setText(object.a);
                holder.textView16.setText(object.b);
                holder.textView17.setText(object.c);
                holder.textView18.setText(object.chuanlian);
                holder.textView19.setText(object.binglian);
                holder.textView20.setText(Integer.parseInt(object.qidong)-Integer.parseInt(xiuGai.getQidong())+"");
                holder.textView21.setText(Integer.parseInt(object.duanxiang.replace(".0",""))-Integer.parseInt(xiuGai.getDuanxiang().replace(".0",""))+"");
                holder.textView22.setText(Integer.parseInt(String.valueOf(Double.parseDouble(object.m13)*100-(13+Double.parseDouble(xiuGai.getM13()))*100).replace(".0",""))+"");
                holder.textView23.setText(Integer.parseInt(String.valueOf(Double.parseDouble(object.m30)*100-(30+Double.parseDouble(xiuGai.getM30()))*100).replace(".0",""))+"");
                holder.textView25.setText(object.ceshi);
                holder.textView26.setText(object.man);
                if(Double.parseDouble(object.a)>0.1){
                    holder.textView15.setBackgroundResource(R.drawable.dialog_test2_3);
                    holder.textView15.setTextColor(Color.parseColor("#ffffff"));
                }

                if(Double.parseDouble(object.b)>0.1){
                    holder.textView16.setBackgroundResource(R.drawable.dialog_test2_3);
                    holder.textView16.setTextColor(Color.parseColor("#ffffff"));
                }

                if(Double.parseDouble(object.c)>0.1){
                    holder.textView17.setBackgroundResource(R.drawable.dialog_test2_3);
                    holder.textView17.setTextColor(Color.parseColor("#ffffff"));
                }
            }
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
