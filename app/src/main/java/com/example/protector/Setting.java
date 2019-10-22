package com.example.protector;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.protector.SQl.Operator;
import com.example.protector.SQl.ProductType;
import com.example.protector.util.MyDialog;
import com.example.protector.SQl.XiuGai;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class Setting extends Activity implements View.OnClickListener {

    private TextView erptongxun;
    private TextView neibutongxun2;
    private Button fanhui;
    private Spinner spinner1;
    private TextView textView7;
    private TextView textView8;
    private Button button8;
    private Button button9;
    private Button button10;
    private Spinner spinner2;
    private TextView textView9;
    private TextView textView10;
    private Button button11;
    private Button button12;
    private Button button13;
    private TextView qidong;
    private TextView duanxiang;
    private TextView m13miao;
    private TextView m30miao;
    private TextView chuanlian;
    private TextView binglian;
    private TextView jiaoliu;
    private TextView xiangjian;
    private TextView xiangdui;
    private TextView xiangduixianquan;
    private TextView xianquanduidi;
    private EditText ed_qidongxiangying;
    private TextView textView24;
    private EditText ed_duanxiangxiangying;
    private EditText ed_13xianshi;
    private EditText ed_30xianshi;
    private EditText ed_chuanlian1;
    private EditText ed_chuanlan2;
    private EditText ed_binglian1;
    private EditText ed_binglian2;
    private EditText ed_duanxiangzhiliu;
    private EditText ed_jiaoliuxianquan;
    private EditText ed_xiangjian;
    private EditText ed_xiangduidi;
    private EditText ed_xiangdui;
    private EditText ed_xianquanduidi;
    private Button button14;
    private Button button15;
    private Button button16;
    private XiuGai xiuGai;
    private Button button17;
    private MyDialog myDialog;
    private List<ProductType> types;
    private List<Operator> operators;
    private List<String> strings;
    private List<String> strings2;
    private int index1 =1,index2 = 1;
    private ArrayAdapter arrayAdapter;
    private ArrayAdapter arrayAdapter2;
    private int a;
    private int b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        strings = new ArrayList<>();
        strings2 = new ArrayList<>();
        init();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, strings);
        arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, strings2);
        spinner1.setAdapter(arrayAdapter);
        spinner2.setAdapter(arrayAdapter2);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(types.size()!=0){
                    textView7.setText(types.get(i).getXinghao());
                    textView8.setText(types.get(i).getChangjia());
                }
                index1 = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(operators.size()!=0){
                    textView9.setText(operators.get(i).getNumber());
                    textView10.setText(operators.get(i).getSex());
                }
                index2 = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        List<XiuGai> list = DataSupport.findAll(XiuGai.class);
        if (list.size() == 0) {
            XiuGai xiuGai = new XiuGai();
            xiuGai.setQidong("150");
            xiuGai.setDuanxiang("250");
            xiuGai.setM13("1");
            xiuGai.setM30("1");
            xiuGai.setChuanlian1("20.0");
            xiuGai.setChuanlian2("27.5");
            xiuGai.setBinglian1("10.0");
            xiuGai.setBinglian2("14.0");
            xiuGai.setDuanxiangzhiliu("0.2");
            xiuGai.setJiaoliu("3.0");
            xiuGai.setXiangjian("500");
            xiuGai.setXiangduidi("500");
            xiuGai.setXiangduixianquan("500");
            xiuGai.setXianquan("500");
            xiuGai.save();
        }
        xiuGai = DataSupport.find(XiuGai.class, 1);
        ed_qidongxiangying.setText(xiuGai.getQidong());
        ed_duanxiangxiangying.setText(xiuGai.getDuanxiang());
        ed_13xianshi.setText(xiuGai.getM13());
        ed_30xianshi.setText(xiuGai.getM30());
        ed_chuanlian1.setText(xiuGai.getChuanlian1());
        ed_chuanlan2.setText(xiuGai.getChuanlian2());
        ed_binglian1.setText(xiuGai.getBinglian1());
        ed_binglian2.setText(xiuGai.getBinglian2());
        ed_duanxiangzhiliu.setText(xiuGai.getDuanxiangzhiliu());
        ed_jiaoliuxianquan.setText(xiuGai.getJiaoliu());
        ed_xiangjian.setText(xiuGai.getXiangjian());
        ed_xiangduidi.setText(xiuGai.getXiangduixianquan());
        ed_xiangdui.setText(xiuGai.getXiangduidi());
        ed_xianquanduidi.setText(xiuGai.getXianquan());
    }

    private void init(){
        types = DataSupport.findAll(ProductType.class);
        operators = DataSupport.findAll(Operator.class);
        strings.clear();
        strings2.clear();
        if(types.size() != 0){
            for (int i = 0; i < types.size(); i++) {
                strings.add(types.get(i).getName());
            }
        }
        if(operators.size() != 0){
            for (int i = 0; i < operators.size(); i++) {
                strings2.add(operators.get(i).getName());
            }
        }


    }
    private void initView() {
        erptongxun = (TextView) findViewById(R.id.erptongxun);
        neibutongxun2 = (TextView) findViewById(R.id.neibutongxun2);
        fanhui = (Button) findViewById(R.id.fanhui);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        textView7 = (TextView) findViewById(R.id.textView7);
        textView8 = (TextView) findViewById(R.id.textView8);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        textView9 = (TextView) findViewById(R.id.textView9);
        textView10 = (TextView) findViewById(R.id.textView10);
        button11 = (Button) findViewById(R.id.button11);
        button12 = (Button) findViewById(R.id.button12);
        button13 = (Button) findViewById(R.id.button13);
        qidong = (TextView) findViewById(R.id.qidong);
        duanxiang = (TextView) findViewById(R.id.duanxiang);
        m13miao = (TextView) findViewById(R.id.m13miao);
        m30miao = (TextView) findViewById(R.id.m30miao);
        chuanlian = (TextView) findViewById(R.id.chuanlian);
        binglian = (TextView) findViewById(R.id.binglian);
        jiaoliu = (TextView) findViewById(R.id.jiaoliu);
        xiangjian = (TextView) findViewById(R.id.xiangjian);
        xiangdui = (TextView) findViewById(R.id.xiangdui);
        xiangduixianquan = (TextView) findViewById(R.id.xiangduixianquan);
        xianquanduidi = (TextView) findViewById(R.id.xianquanduidi);
        ed_qidongxiangying = (EditText) findViewById(R.id.ed_qidongxiangying);
        textView24 = (TextView) findViewById(R.id.textView24);
        ed_duanxiangxiangying = (EditText) findViewById(R.id.ed_duanxiangxiangying);
        ed_13xianshi = (EditText) findViewById(R.id.ed_13xianshi);
        ed_30xianshi = (EditText) findViewById(R.id.ed_30xianshi);
        ed_chuanlian1 = (EditText) findViewById(R.id.ed_chuanlian1);
        ed_chuanlan2 = (EditText) findViewById(R.id.ed_chuanlan2);
        ed_binglian1 = (EditText) findViewById(R.id.ed_binglian1);
        ed_binglian2 = (EditText) findViewById(R.id.ed_binglian2);
        ed_duanxiangzhiliu = (EditText) findViewById(R.id.ed_duanxiangzhiliu);
        ed_jiaoliuxianquan = (EditText) findViewById(R.id.ed_jiaoliuxianquan);
        ed_xiangjian = (EditText) findViewById(R.id.ed_xiangjian);
        ed_xiangduidi = (EditText) findViewById(R.id.ed_xiangduidi);
        ed_xiangdui = (EditText) findViewById(R.id.ed_xiangdui);
        ed_xianquanduidi = (EditText) findViewById(R.id.ed_xianquanduidi);
        button14 = (Button) findViewById(R.id.button14);
        button15 = (Button) findViewById(R.id.button15);
        button16 = (Button) findViewById(R.id.button16);

        fanhui.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
        button11.setOnClickListener(this);
        button12.setOnClickListener(this);
        button13.setOnClickListener(this);
        button14.setOnClickListener(this);
        button15.setOnClickListener(this);
        button16.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.button8:
                //添加产品
                View view = LayoutInflater.from(getApplication()).inflate(R.layout.dailog_add, null);
                myDialog = new MyDialog(this, view, R.style.dialog);
                myDialog.setCancelable(false);
                myDialog.setCanceledOnTouchOutside(false);
                myDialog.show();
                TextView textView3;
                final EditText editText;
                final EditText editText2;
                final EditText editText3;
                Button button7;
                Button button17;
                textView3 = (TextView) view.findViewById(R.id.textView3);
                editText = (EditText) view.findViewById(R.id.editText);
                editText2 = (EditText) view.findViewById(R.id.editText2);
                editText3 = (EditText) view.findViewById(R.id.editText3);
                button7 = (Button) view.findViewById(R.id.button7);
                button17 = (Button) view.findViewById(R.id.button17);

                button7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(editText.getText().equals("") && editText2.getText().equals("") && editText3.getText().equals("")){
                            Toast.makeText(Setting.this, "请输入完整", Toast.LENGTH_SHORT).show();
                        }else{
                            ProductType type = new ProductType();
                            type.setName(editText.getText().toString());
                            type.setXinghao(editText2.getText().toString());
                            type.setChangjia(editText3.getText().toString());
                            type.save();
                            myDialog.dismiss();
                            init();
                            arrayAdapter.notifyDataSetChanged();
                            Toast.makeText(Setting.this, "添加成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                button17.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.dismiss();
                    }
                });
                break;
            case R.id.button9:
                //修改产品
                View view2 = LayoutInflater.from(getApplication()).inflate(R.layout.dailog_add, null);
                myDialog = new MyDialog(this,  view2, R.style.dialog);
                myDialog.setCancelable(false);
                myDialog.setCanceledOnTouchOutside(false);
                myDialog.show();
                textView3 = (TextView) view2.findViewById(R.id.textView3);
                editText = (EditText) view2.findViewById(R.id.editText);
                editText2 = (EditText) view2.findViewById(R.id.editText2);
                editText3 = (EditText) view2.findViewById(R.id.editText3);
                button7 = (Button) view2.findViewById(R.id.button7);
                button17 = (Button) view2.findViewById(R.id.button17);
                textView3.setText("修改产品类型");
                final ProductType type = DataSupport.find(ProductType.class,index1);
                editText.setText(type.getName()+"");
                editText2.setText(type.getXinghao());
                editText3.setText(type.getChangjia());
                button7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(editText.getText().equals("") && editText2.getText().equals("") && editText3.getText().equals("")){
                            Toast.makeText(Setting.this, "请输入完整", Toast.LENGTH_SHORT).show();
                        }else{
                            type.setName(editText.getText().toString());
                            type.setXinghao(editText2.getText().toString());
                            type.setChangjia(editText3.getText().toString());
                            type.save();
                            myDialog.dismiss();
                            init();
                            arrayAdapter.notifyDataSetChanged();
                            Toast.makeText(Setting.this, "修改成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                button17.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.dismiss();
                    }
                });
                break;
            case R.id.button10:
                //删除产品
                view2 = LayoutInflater.from(getApplication()).inflate(R.layout.dailog_remove, null);
                myDialog = new MyDialog(this,  view2, R.style.dialog);
                myDialog.setCancelable(false);
                myDialog.setCanceledOnTouchOutside(false);
                myDialog.show();
                TextView testDialog2Tv;
                TextView testDialog2Tv2;
                ImageView testDialog2Img;
                Button testDialog2Btn1;
                Button testDialog2Btn2;

                testDialog2Tv = (TextView) view2.findViewById(R.id.test_dialog2_tv);
                testDialog2Tv2 = (TextView) view2.findViewById(R.id.test_dialog2_tv2);
                testDialog2Img = (ImageView) view2.findViewById(R.id.test_dialog2_img);
                testDialog2Btn1 = (Button) view2.findViewById(R.id.test_dialog2_btn1);
                testDialog2Btn2 = (Button) view2.findViewById(R.id.test_dialog2_btn2);
                testDialog2Btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DataSupport.delete(ProductType.class,index1);
                        Toast.makeText(Setting.this, "删除成功", Toast.LENGTH_SHORT).show();
                        init();
                        arrayAdapter.notifyDataSetChanged();
                        myDialog.dismiss();
                    }
                });
                testDialog2Btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.dismiss();
                    }
                });
                break;
            case R.id.button11:
                //添加操作人员
                view2 = LayoutInflater.from(getApplication()).inflate(R.layout.dailog_addren, null);
                myDialog = new MyDialog(this,  view2, R.style.dialog);
                myDialog.setCancelable(false);
                myDialog.setCanceledOnTouchOutside(false);
                myDialog.show();

                Spinner spinner1;
                Spinner spinner2;

                textView3 = (TextView) view2.findViewById(R.id.textView3);
                editText = (EditText) view2.findViewById(R.id.editText);
                spinner1 = (Spinner) view2.findViewById(R.id.spinner1);
                spinner2 = (Spinner) view2.findViewById(R.id.spinner2);
                button7 = (Button) view2.findViewById(R.id.button7);
                button17 = (Button) view2.findViewById(R.id.button17);
                textView3.setText("添加操作员");
                final List list = new ArrayList();
                for (int i = 0; i < 10; i++) {
                    list.add(String.format("%03d", i + 1));
                }
                final List list1 = new ArrayList();
                list1.add("男");
                list1.add("女");
                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.spinner,list);
                ArrayAdapter arrayAdapter2 = new ArrayAdapter(getApplicationContext(),R.layout.spinner,list1);
                spinner1.setAdapter(arrayAdapter);
                spinner2.setAdapter(arrayAdapter2);
                a = 0;
                b = 0;
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        a = i;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        b = i;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                button7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(editText.getText().equals("")){
                            Toast.makeText(Setting.this, "请输入完整", Toast.LENGTH_SHORT).show();
                        }else{
                            Operator operator = new Operator();
                            operator.setName(editText.getText().toString());
                            operator.setNumber(list.get(a)+"");
                            operator.setSex(list1.get(b)+"");
                            operator.save();
                            myDialog.dismiss();
                            init();
                            Toast.makeText(Setting.this, "添加成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                button17.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.dismiss();
                    }
                });
                break;
            case R.id.button12:
                //修改操作人员
                view2 = LayoutInflater.from(getApplication()).inflate(R.layout.dailog_addren, null);
                myDialog = new MyDialog(this,  view2, R.style.dialog);
                myDialog.setCancelable(false);
                myDialog.setCanceledOnTouchOutside(false);
                myDialog.show();
                textView3 = (TextView) view2.findViewById(R.id.textView3);
                editText = (EditText) view2.findViewById(R.id.editText);
                spinner1 = (Spinner) view2.findViewById(R.id.spinner1);
                spinner2 = (Spinner) view2.findViewById(R.id.spinner2);
                textView3.setText("修改操作人员");
                button7 = (Button) view2.findViewById(R.id.button7);
                button17 = (Button) view2.findViewById(R.id.button17);
                button7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.dismiss();
                    }
                });
                button17.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.dismiss();
                    }
                });
                break;
            case R.id.button13:
                //删除操作员
                view2 = LayoutInflater.from(getApplication()).inflate(R.layout.dailog_remove, null);
                myDialog = new MyDialog(this,  view2, R.style.dialog);
                myDialog.setCancelable(false);
                myDialog.setCanceledOnTouchOutside(false);
                myDialog.show();
                testDialog2Tv = (TextView) view2.findViewById(R.id.test_dialog2_tv);
                testDialog2Tv2 = (TextView) view2.findViewById(R.id.test_dialog2_tv2);
                testDialog2Img = (ImageView) view2.findViewById(R.id.test_dialog2_img);
                testDialog2Btn1 = (Button) view2.findViewById(R.id.test_dialog2_btn1);
                testDialog2Btn2 = (Button) view2.findViewById(R.id.test_dialog2_btn2);
                testDialog2Tv.setText("修改操作人员");
                testDialog2Tv2.setText("颤三");
                testDialog2Btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.dismiss();
                    }
                });
                testDialog2Btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.dismiss();
                    }
                });
                break;
            case R.id.button14:
                //修改
                submit();

                break;
            case R.id.button15:
                //取消
                ed_qidongxiangying.setText(xiuGai.getQidong());
                ed_duanxiangxiangying.setText(xiuGai.getDuanxiang());
                ed_13xianshi.setText(xiuGai.getM13());
                ed_30xianshi.setText(xiuGai.getM30());
                ed_chuanlian1.setText(xiuGai.getChuanlian1());
                ed_chuanlan2.setText(xiuGai.getChuanlian2());
                ed_binglian1.setText(xiuGai.getBinglian1());
                ed_binglian2.setText(xiuGai.getBinglian2());
                ed_duanxiangzhiliu.setText(xiuGai.getDuanxiangzhiliu());
                ed_jiaoliuxianquan.setText(xiuGai.getJiaoliu());
                ed_xiangjian.setText(xiuGai.getXiangjian());
                ed_xiangduidi.setText(xiuGai.getXiangduixianquan());
                ed_xiangdui.setText(xiuGai.getXiangduidi());
                ed_xianquanduidi.setText(xiuGai.getXianquan());
                break;
            case R.id.button16:
                //默认值
                Moren moren = new Moren();
                ed_qidongxiangying.setText(moren.qidong);
                ed_duanxiangxiangying.setText(moren.duanxiang);
                ed_13xianshi.setText(moren.m13);
                ed_30xianshi.setText(moren.m30);
                ed_chuanlian1.setText(moren.chuanlian1);
                ed_chuanlan2.setText(moren.chuanlian2);
                ed_binglian1.setText(moren.binglian1);
                ed_binglian2.setText(moren.binglian2);
                ed_duanxiangzhiliu.setText(moren.duanxiangzhiliu);
                ed_jiaoliuxianquan.setText(moren.jiaoliu);
                ed_xiangjian.setText(moren.xiangjian);
                ed_xiangduidi.setText(moren.xiangduixianquan);
                ed_xiangdui.setText(moren.xiangduidi);
                ed_xianquanduidi.setText(moren.xianquan);
                xiuGai.setQidong("150");
                xiuGai.setDuanxiang("250");
                xiuGai.setM13("1");
                xiuGai.setM30("1");
                xiuGai.setChuanlian1("20.0");
                xiuGai.setChuanlian2("27.5");
                xiuGai.setBinglian1("10.0");
                xiuGai.setBinglian2("14.0");
                xiuGai.setDuanxiangzhiliu("0.2");
                xiuGai.setJiaoliu("3.0");
                xiuGai.setXiangjian("500");
                xiuGai.setXiangduidi("500");
                xiuGai.setXiangduixianquan("500");
                xiuGai.setXianquan("500");
                xiuGai.save();
                break;
        }
    }

    class Moren {
        String qidong = "150";
        String duanxiang = "250";
        String m13 = "1";
        String m30 = "1";
        String chuanlian1 = "20.0";
        String chuanlian2 = "27.5";
        String binglian1 = "10.0";
        String binglian2 = "14.0";
        String duanxiangzhiliu = "0.2";
        String jiaoliu = "3.0";
        String xiangjian = "500";
        String xiangduidi = "500";
        String xiangduixianquan = "500";
        String xianquan = "500";
    }

    private void submit() {
        // validate
        int a = Integer.parseInt(ed_qidongxiangying.getText().toString().trim());
        if (a > 150) {
            Toast.makeText(this, "只能输入小于150的数值！", Toast.LENGTH_SHORT).show();
            return;
        }
        String qidongxiangying = ed_qidongxiangying.getText().toString().trim();
        if (TextUtils.isEmpty(qidongxiangying)) {
            Toast.makeText(this, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }

        String duanxiangxiangying = ed_duanxiangxiangying.getText().toString().trim();
        if (TextUtils.isEmpty(duanxiangxiangying)) {
            Toast.makeText(this, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }

        String m13xianshi = ed_13xianshi.getText().toString().trim();
        if (TextUtils.isEmpty(m13xianshi)) {
            Toast.makeText(this, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }

        String m30xianshi = ed_30xianshi.getText().toString().trim();
        if (TextUtils.isEmpty(m30xianshi)) {
            Toast.makeText(this, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }

        String chuanlian1 = ed_chuanlian1.getText().toString().trim();
        if (TextUtils.isEmpty(chuanlian1)) {
            Toast.makeText(this, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }

        String chuanlan2 = ed_chuanlan2.getText().toString().trim();
        if (TextUtils.isEmpty(chuanlan2)) {
            Toast.makeText(this, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }

        String binglian1 = ed_binglian1.getText().toString().trim();
        if (TextUtils.isEmpty(binglian1)) {
            Toast.makeText(this, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }

        String binglian2 = ed_binglian2.getText().toString().trim();
        if (TextUtils.isEmpty(binglian2)) {
            Toast.makeText(this, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }

        String duanxiangzhiliu = ed_duanxiangzhiliu.getText().toString().trim();
        if (TextUtils.isEmpty(duanxiangzhiliu)) {
            Toast.makeText(this, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }

        String jiaoliuxianquan = ed_jiaoliuxianquan.getText().toString().trim();
        if (TextUtils.isEmpty(jiaoliuxianquan)) {
            Toast.makeText(this, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }

        String xiangjian = ed_xiangjian.getText().toString().trim();
        if (TextUtils.isEmpty(xiangjian)) {
            Toast.makeText(this, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }

        String xiangduidi = ed_xiangduidi.getText().toString().trim();
        if (TextUtils.isEmpty(xiangduidi)) {
            Toast.makeText(this, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }

        String xiangdui = ed_xiangdui.getText().toString().trim();
        if (TextUtils.isEmpty(xiangdui)) {
            Toast.makeText(this, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }

        String xianquanduidi = ed_xianquanduidi.getText().toString().trim();
        if (TextUtils.isEmpty(xianquanduidi)) {
            Toast.makeText(this, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        xiuGai.setQidong(ed_qidongxiangying.getText().toString());
        xiuGai.setDuanxiang(ed_duanxiangxiangying.getText().toString());
        xiuGai.setM13(ed_13xianshi.getText().toString());
        xiuGai.setM30(ed_30xianshi.getText().toString());
        xiuGai.setChuanlian1(ed_chuanlian1.getText().toString());
        xiuGai.setChuanlian2(ed_chuanlan2.getText().toString());
        xiuGai.setBinglian1(ed_binglian1.getText().toString());
        xiuGai.setBinglian2(ed_binglian2.getText().toString());
        xiuGai.setDuanxiangzhiliu(ed_duanxiangzhiliu.getText().toString());
        xiuGai.setJiaoliu(ed_jiaoliuxianquan.getText().toString());
        xiuGai.setXiangjian(ed_xiangjian.getText().toString());
        xiuGai.setXiangduidi(ed_xiangduidi.getText().toString());
        xiuGai.setXiangduixianquan(ed_xiangdui.getText().toString());
        xiuGai.setXianquan(ed_xianquanduidi.getText().toString());
        xiuGai.save();
    }
}
