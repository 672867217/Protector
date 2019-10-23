package com.example.protector;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.protector.SQl.TestData;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.text.ParseException;
import com.example.protector.SQl.ProductType;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateQuery extends AppCompatActivity implements View.OnClickListener {

    private TextView footer_tv1;
    private TextView footer_tv2;
    private EditText footer_tv3;
    private AbsoluteLayout layout_footer;
    private TextView header_tv;
    private TextView header_tv2;
    private TextView header_tv3;
    private TextView header_tv4;
    private Button btn_chaxuan;
    private Button btn_cancel;
    private Spinner stats_spinner;
    private TextView stats_tv1;
    private TextView stats_tv2;
    private TextView stats_tv3;
    private TextView tv_cecheng;
    private ListView listview1;
    private ListView listview2;
    private ImageView img_shang;
    private ImageView img_xia;
    List<Bean> list1 = new ArrayList();
    List<Bean> list2 = new ArrayList();
    DateQueryItemAdapter adapter1, adapter2;
    private int index = 0, num36 = 36, shuliang = 0, page;
    private SimpleDateFormat dateFormat;
    private Calendar calendar;
    private List<TestData> dataList2;
    private SimpleDateFormat dateFormat2;
    private int cecheng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_query);
        initView();

        SharedPreferences preferences = getSharedPreferences("cecheng",0);
        cecheng = Integer.parseInt(preferences.getString("what","1"));
        switch (cecheng) {
            case 0:
                tv_cecheng.setText("其他");
                break;
            case 1:
                tv_cecheng.setText("一测");
                break;
            case 2:
                tv_cecheng.setText("二测");
                break;
            case 3:
                tv_cecheng.setText("三测");
                break;
        }

        dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        //spinner
        final List list = new ArrayList();

        final List<ProductType> types = DataSupport.findAll(ProductType.class);
        for (int i = 0; i < types.size(); i++) {
            list.add(types.get(i).getName());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list);
        stats_spinner.setAdapter(arrayAdapter);
        stats_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(types.size()!=0){
                    stats_tv1.setText(types.get(i).getXinghao());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //表格适配器部分
        adapter1 = new DateQueryItemAdapter(this, list1);
        listview1.setAdapter(adapter1);
        adapter2 = new DateQueryItemAdapter(this, list2);
        listview2.setAdapter(adapter2);

        if (shuliang == 0) {
            listview1.setEnabled(false);
            listview2.setEnabled(false);
            init();
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
                if (!editable.toString().equals("")) {
                    if (page >= Integer.parseInt(editable.toString()) && Integer.parseInt(editable.toString()) > 0) {
                        index = Integer.parseInt(editable.toString()) - 1;
                        list1.clear();
                        list2.clear();
                        int a = 0;
                        if (num36 * index + num36 >shuliang ) {
                            a = shuliang;
                        } else {
                            a = num36 * index + num36;
                        }
                        for (int i = num36 * index; i < a; i++) {
                            Bean bean = new Bean();
                            bean.number1 = String.valueOf((i + 1));
                            bean.number2 = dataList2.get(i).getChanpinbianma();
                            bean.date = dateFormat2.format(dataList2.get(i).getDate());
                            bean.result = "合格";
                            bean.name = "人员1";
                            bean.id = dataList2.get(i).getId();
                            if (i < num36 * index + 18) {
                                list1.add(bean);
                            } else if (i >= num36 * index + 18 && i < num36 * index + num36) {
                                list2.add(bean);
                            }
                            if (i == a - 1 && a % num36 > 0) {
                                for (int j = 0; j < num36 - shuliang % num36; j++) {
                                    bean = new Bean();
                                    bean.number1 = "";
                                    bean.number2 = "";
                                    bean.date = "";
                                    bean.result = "";
                                    bean.name = "";
                                    bean.id = -1;
                                    if (num36 - shuliang % num36 - j > 18) {
                                        list1.add(bean);
                                    } else {
                                        list2.add(bean);
                                    }
                                }
                            }
                        }
                        adapter1.notifyDataSetChanged();
                        adapter2.notifyDataSetChanged();
                        if (index + 1 == page && index > 0) {
                            img_xia.setImageResource(R.drawable.xiajiantouhui);
                            img_xia.setEnabled(false);
                            img_shang.setImageResource(R.drawable.shangjiantou);
                            img_shang.setEnabled(true);
                        } else if (index + 1 == page && index == 0) {
                            img_xia.setImageResource(R.drawable.xiajiantouhui);
                            img_xia.setEnabled(false);
                            img_shang.setImageResource(R.drawable.shangjiantouhui);
                            img_shang.setEnabled(false);
                        } else if (index == 0) {
                            img_shang.setImageResource(R.drawable.shangjiantouhui);
                            img_shang.setEnabled(false);
                            img_xia.setImageResource(R.drawable.xiajiantou);
                            img_xia.setEnabled(true);
                        } else {
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
                    if (index == 1) {
                        img_shang.setImageResource(R.drawable.shangjiantouhui);
                        img_shang.setEnabled(false);
                    }
                    img_xia.setEnabled(true);
                    img_xia.setImageResource(R.drawable.xiajiantou);
                    index = index - 1;
                    footer_tv3.setText(index + 1 + "");
                }
            }
        });
        img_xia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page > index + 1) {
                    index = index + 1;
                    if (index + 1 == page) {
                        img_xia.setImageResource(R.drawable.xiajiantouhui);
                        img_xia.setEnabled(false);
                    }
                    img_shang.setImageResource(R.drawable.shangjiantou);
                    img_shang.setEnabled(true);
                    footer_tv3.setText(index + 1 + "");
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
                        stats_tv2.setText(year + "-" + mymonth + "-" + myday);
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
                        stats_tv3.setText(year + "-" + mymonth + "-" + myday);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.DAY_OF_WEEK), calendar.get(Calendar.DAY_OF_WEEK)).show();
            }
        });


        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list1.get(position).id != -1) {
                    Intent intent = new Intent(getApplicationContext(), QueryResult.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("data", list1.get(position).id);
                    bundle.putInt("flag",2);
                    intent.putExtra("s",bundle);
                    startActivity(intent);
                }
            }
        });
        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list2.get(position).id != -1) {
                    Intent intent = new Intent(getApplicationContext(), QueryResult.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("data", list2.get(position).id);
                    bundle.putInt("flag",2);
                    intent.putExtra("s",bundle);
                    startActivity(intent);
                }
            }
        });


    }

    private void init() {
        img_shang.setImageResource(R.drawable.shangjiantouhui);
        img_shang.setEnabled(false);
        img_xia.setImageResource(R.drawable.xiajiantouhui);
        img_xia.setEnabled(false);
        for (int j = 0; j < 36; j++) {
            Bean bean = new Bean();
            bean.number1 = "";
            bean.number2 = "";
            bean.date = "";
            bean.result = "";
            bean.name = "";
            if (j < 18) {
                list1.add(bean);
            } else {
                list2.add(bean);
            }
        }
    }

    private  void init2(){
        if (shuliang % 36 == 0) {
            page = shuliang / 36;
        } else {
            page = shuliang / 36 + 1;
        }
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
            case R.id.btn_chaxuan:
                if (stats_tv2.getText().toString().isEmpty()) {
                    Toast.makeText(this, "请选择开始日期", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (stats_tv3.getText().toString().equals("")) {
                    Toast.makeText(this, "请选择结束日期", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<TestData> dataList = DataSupport.findAll(TestData.class);
                dataList2 = new ArrayList<>();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                for (int j = 0; j < dataList.size(); j++) {
                    try {
                        Long ceshiDate = dateFormat.parse(dateFormat.format(dataList.get(j).getDate())).getTime();
                        Long startDate = dateFormat.parse(stats_tv2.getText().toString()).getTime();
                        Long endDate = dateFormat.parse(stats_tv3.getText().toString()).getTime();
                        if (ceshiDate >= startDate && ceshiDate <= endDate && Integer.parseInt(dataList.get(j).getCecheng())==(cecheng)) {
                            dataList2.add(dataList.get(j));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (dataList2.isEmpty()) {
                    Toast.makeText(this, "没有符合条件的数据", Toast.LENGTH_SHORT).show();
                    return;
                }
                shuliang = dataList2.size();
                init2();
                dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                listview1.setEnabled(true);
                listview2.setEnabled(true);
                footer_tv3.setText("1");
                layout_footer.setVisibility(View.VISIBLE);
                footer_tv1.setText("统计："+stats_tv2.getText().toString()+" 至 " +stats_tv3.getText().toString()+"   "
                        +tv_cecheng.getText().toString()+"测试数量："+dataList2.size()+"台，通过"+dataList2.size()+"台，未通过0台");
                footer_tv2.setText("共 "+page+" 页  第");
                break;

        }
    }

    private void initView() {
        footer_tv1 = (TextView) findViewById(R.id.footer_tv1);
        footer_tv2 = (TextView) findViewById(R.id.footer_tv2);
        footer_tv3 = (EditText) findViewById(R.id.footer_tv3);
        layout_footer = (AbsoluteLayout) findViewById(R.id.layout_footer);
        header_tv = (TextView) findViewById(R.id.header_tv);
        header_tv2 = (TextView) findViewById(R.id.header_tv2);
        header_tv3 = (TextView) findViewById(R.id.header_tv3);
        header_tv4 = (TextView) findViewById(R.id.header_tv4);
        btn_chaxuan = (Button) findViewById(R.id.btn_chaxuan);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        stats_spinner = (Spinner) findViewById(R.id.stats_spinner);
        stats_tv1 = (TextView) findViewById(R.id.stats_tv1);
        stats_tv2 = (TextView) findViewById(R.id.stats_tv2);
        stats_tv3 = (TextView) findViewById(R.id.stats_tv3);
        tv_cecheng = (TextView) findViewById(R.id.tv_cecheng);
        listview1 = (ListView) findViewById(R.id.listview1);
        listview2 = (ListView) findViewById(R.id.listview2);
        img_shang = (ImageView) findViewById(R.id.img_shang);
        img_xia = (ImageView) findViewById(R.id.img_xia);

        btn_chaxuan.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }

    class Bean {
        private String number1;
        private String number2;
        private String date;
        private  String result;
        private String name;
        private int id;
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
            if (bean.number1.equals("")) {
                holder.itemTv1.setText("");
            } else {
                holder.itemTv1.setText(String.format("%04d",Integer.parseInt(bean.number1)));
            }
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
