package com.example.protector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.example.protector.SQl.XiuGai;
import com.example.protector.util.MyDialog;
import com.example.protector.util.SerialPortUtil;
import com.example.protector.util.Utils;

import org.litepal.crud.DataSupport;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import androidx.annotation.NonNull;

public class Setting extends MyDialog implements View.OnClickListener {

    private TextView erptongxun;
    private TextView neibutongxun2;
    private Button fanhui;
    private Button fanhui2;
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
    private int index1 = 0, index2 = 0;
    private ArrayAdapter arrayAdapter;
    private ArrayAdapter arrayAdapter2;
    private ArrayAdapter arrayAdapter3;
    private int a;
    private int b;
    private List list;
    private List list1;
    private List list2;
    private List<Chanpin> chanpins;
    private List<Caozuoyuan> caozuoyuans;
    private Spinner spinner;
    private int gonwei = 1;
    private View view = null;
    BigDecimal b1 = new BigDecimal("0.001");
    BigDecimal b3 = new BigDecimal("0.01");
    BigDecimal b4 = new BigDecimal("0.1");
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
    SerialPortUtil util = MainActivity.utils;
    private Handler handler;
    TextView testDialog2Tv;
    ImageView testDialog2Img;
    Button testDialog2Btn1;
    private int a1;
    private MyDialog myDialog1;
    Context mycontext;
    public Setting(final Context context, View layout, int style) {
        super(context, layout, style);
        initView();
        mycontext = context;
        list = new ArrayList();
        list2 = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(String.format("%03d", i + 1));
        }



        list1 = new ArrayList();
        list1.add("男");
        list1.add("女");
        for (int i = 0; i < 5; i++) {
            list2.add(i + 1 + "");
        }

        chanpins = new ArrayList<>();
        caozuoyuans = new ArrayList<>();
        strings = new ArrayList<>();
        strings2 = new ArrayList<>();
        init();
        arrayAdapter = new ArrayAdapter(context, R.layout.spinner, strings);
        arrayAdapter2 = new ArrayAdapter(context,  R.layout.spinner, strings2);
        arrayAdapter3 = new ArrayAdapter(context,  R.layout.spinner, list2);
        spinner1.setAdapter(arrayAdapter);
        spinner2.setAdapter(arrayAdapter2);
        spinner.setAdapter(arrayAdapter3);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gonwei = i + 1;
                List<XiuGai> xiuGais = DataSupport.findAll(XiuGai.class);
                for (int j = 0; j < xiuGais.size(); j++) {
                    if (xiuGais.get(j).getGonwei() == gonwei) {
                        set(j+1);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (types.size() != 0) {
                    textView7.setText(types.get(i).getXinghao());
                    textView8.setText(types.get(i).getChangjia());
                }
                index1 = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (operators.size() != 0) {
                    textView9.setText(operators.get(i).getNumber());
                    textView10.setText(operators.get(i).getSex());
                }
                index2 = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        util.onReceive(new SerialPortUtil.Receive() {
            @Override
            public void set(String str, List<String> list) {
                if (str.equals("60")) {
                    handler.sendEmptyMessage(1);
                }
                if (str.equals("50")) {
                    a1 = 0;
                    switch (new Utils().HexToInt(list.get(5))) {
                        case 1:
                            xiuGai = DataSupport.find(XiuGai.class, 1);
                            a1 = 1;
                            break;
                        case 2:
                            xiuGai = DataSupport.find(XiuGai.class, 2);
                            a1 = 2;
                            break;
                        case 3:
                            xiuGai = DataSupport.find(XiuGai.class, 3);
                            a1 = 3;
                            break;
                        case 4:
                            xiuGai = DataSupport.find(XiuGai.class, 4);
                            a1 = 4;
                            break;
                        case 5:
                            xiuGai = DataSupport.find(XiuGai.class, 5);
                            a1 = 5;
                            break;
                    }
                    xiuGai.setCecheng(new Utils().HexToInt(list.get(6)) + "");
                    xiuGai.setQidong(new Utils().HexToInt(list.get(7)) + "");
                    xiuGai.setDuanxiang(new Utils().HexToInt(list.get(8)) + "");
                    xiuGai.setM13(jisuan3(new Utils().HexToInt(list.get(9)) + ""));
                    xiuGai.setM30(jisuan3(new Utils().HexToInt(list.get(10)) + ""));
                    xiuGai.setChuanlian1(jisuan3(new Utils().HexToInt(list.get(11) + list.get(12)) + ""));
                    xiuGai.setChuanlian2(jisuan3(new Utils().HexToInt(list.get(13) + list.get(14)) + ""));
                    xiuGai.setBinglian1(jisuan3(new Utils().HexToInt(list.get(15) + list.get(16)) + ""));
                    xiuGai.setBinglian2(jisuan3(new Utils().HexToInt(list.get(17) + list.get(18)) + ""));
                    xiuGai.setDuanxiangzhiliu(jisuan2(new Utils().HexToInt(list.get(19)) + ""));
                    xiuGai.setJiaoliu(jisuan2(new Utils().HexToInt(list.get(20) + list.get(21)) + ""));
                    xiuGai.setXiangjian(new Utils().HexToInt(list.get(22) + list.get(23)) + "");
                    xiuGai.setXiangduidi(new Utils().HexToInt(list.get(24) + list.get(25)) + "");
                    xiuGai.setXiangduixianquan(new Utils().HexToInt(list.get(26) + list.get(27)) + "");
                    xiuGai.setXianquan(new Utils().HexToInt(list.get(28) + list.get(29)) + "");
                    xiuGai.save();
                    handler.sendEmptyMessage(2);
                }
            }
        });
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        view = LayoutInflater.from(context).inflate(R.layout.dialog_canshu, null);
                        myDialog1 = new MyDialog(context, view, R.style.dialog);
                        testDialog2Tv = (TextView) view.findViewById(R.id.test_dialog2_tv);
                        testDialog2Img = (ImageView) view.findViewById(R.id.test_dialog2_img);
                        testDialog2Btn1 = (Button) view.findViewById(R.id.test_dialog2_btn1);
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
                        myDialog1.show();
                        testDialog2Btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view1) {
                                myDialog1.dismiss();
                                view = null;
                            }
                        });
                        break;
                    case 2:
                        if(spinner.getSelectedItem().equals(a1)){
                            set(a1);
                        }
                        break;
                    case 3:
                        view = LayoutInflater.from(context).inflate(R.layout.dialog_canshu, null);
                        myDialog1 = new MyDialog(context, view, R.style.dialog);
                        TextView testDialog2Tv;
                        ImageView testDialog2Img;
                        Button testDialog2Btn1;
                        testDialog2Tv = (TextView) view.findViewById(R.id.test_dialog2_tv);
                        testDialog2Img = (ImageView) view.findViewById(R.id.test_dialog2_img);
                        myDialog1.show();
                        testDialog2Tv.setText("修改参数失败");
                        testDialog2Img.setImageResource(R.drawable.baozhi2);
                        testDialog2Btn1 = (Button) view.findViewById(R.id.test_dialog2_btn1);
                        testDialog2Btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view1) {
                                myDialog1.dismiss();
                                view = null;
                                set(Integer.parseInt( spinner.getSelectedItem()+""));
                            }
                        });
                        break;
                }
            }
        };
    }
    public void add(int gonwei) {
        XiuGai xiuGai = new XiuGai();
        xiuGai.setCecheng("1");
        xiuGai.setQidong("150");
        xiuGai.setGonwei(gonwei);
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

    private void set(int j) {
        xiuGai = DataSupport.find(XiuGai.class, j);
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

    private void init() {
        types = DataSupport.findAll(ProductType.class);
        operators = DataSupport.findAll(Operator.class);
        strings.clear();
        strings2.clear();
        chanpins.clear();
        caozuoyuans.clear();
        if (types.size() != 0) {
            for (int i = 0; i < types.size(); i++) {
                strings.add(types.get(i).getName());
                Chanpin chanpin = new Chanpin();
                chanpin.id = types.get(i).getId();
                chanpin.name = types.get(i).getName();
                chanpin.xinghao = types.get(i).getXinghao();
                chanpin.changjia = types.get(i).getChangjia();
                chanpins.add(chanpin);
            }
            textView7.setText(types.get(0).getXinghao());
            textView8.setText(types.get(0).getChangjia());
        } else {
            textView7.setText("");
            textView8.setText("");
        }
        if (operators.size() != 0) {
            for (int i = 0; i < operators.size(); i++) {
                strings2.add(operators.get(i).getName());
                Caozuoyuan caozuoyuan = new Caozuoyuan();
                caozuoyuan.id = operators.get(i).getId();
                caozuoyuan.name = operators.get(i).getName();
                caozuoyuan.number = operators.get(i).getNumber();
                caozuoyuan.sex = operators.get(i).getSex();
                caozuoyuans.add(caozuoyuan);
            }
            textView9.setText(operators.get(0).getNumber());
            textView10.setText(operators.get(0).getSex());
        } else {
            textView9.setText("");
            textView10.setText("");
        }

    }

    private void initView() {
        erptongxun = (TextView) findViewById(R.id.erptongxun);
        neibutongxun2 = (TextView) findViewById(R.id.neibutongxun2);
        fanhui = (Button) findViewById(R.id.fanhui);
        fanhui2 = (Button) findViewById(R.id.fanhui2);
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

        fanhui2.setOnClickListener(this);
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
        spinner = (Spinner) findViewById(R.id.spinner);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui:
                dismiss();
                break;
            case R.id.fanhui2:
                util.sendSerialPort("AA00FF500"+spinner.getSelectedItem());
                break;
            case R.id.button8:
                //添加产品
                View view = LayoutInflater.from(mycontext).inflate(R.layout.dailog_add, null);
                myDialog = new MyDialog(mycontext, view, R.style.dialog);
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
                        if (editText.getText().equals("") && editText2.getText().equals("") && editText3.getText().equals("")) {
                            Toast.makeText(mycontext, "请输入完整", Toast.LENGTH_SHORT).show();
                        } else {
                            ProductType type = new ProductType();
                            type.setName(editText.getText().toString());
                            type.setXinghao(editText2.getText().toString());
                            type.setChangjia(editText3.getText().toString());
                            type.save();
                            myDialog.dismiss();
                            init();
                            arrayAdapter.notifyDataSetChanged();
                            Toast.makeText(mycontext, "添加成功", Toast.LENGTH_SHORT).show();
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
                View view2 = LayoutInflater.from(mycontext).inflate(R.layout.dailog_add, null);
                myDialog = new MyDialog(mycontext, view2, R.style.dialog);
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
                final ProductType type = DataSupport.find(ProductType.class, index1 + 1);
                editText.setText(type.getName() + "");
                editText2.setText(type.getXinghao());
                editText3.setText(type.getChangjia());
                button7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (editText.getText().equals("") && editText2.getText().equals("") && editText3.getText().equals("")) {
                            Toast.makeText(mycontext, "请输入完整", Toast.LENGTH_SHORT).show();
                        } else {
                            type.setName(editText.getText().toString());
                            type.setXinghao(editText2.getText().toString());
                            type.setChangjia(editText3.getText().toString());
                            type.save();
                            myDialog.dismiss();
                            init();
                            arrayAdapter.notifyDataSetChanged();
                            Toast.makeText(mycontext, "修改成功", Toast.LENGTH_SHORT).show();
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
                view2 = LayoutInflater.from(mycontext).inflate(R.layout.dailog_remove, null);
                myDialog = new MyDialog(mycontext, view2, R.style.dialog);
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
                        DataSupport.delete(ProductType.class, chanpins.get(index1).id);
                        Toast.makeText(mycontext, "删除成功", Toast.LENGTH_SHORT).show();
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
                view2 = LayoutInflater.from(mycontext).inflate(R.layout.dailog_addren, null);
                myDialog = new MyDialog(mycontext, view2, R.style.dialog);
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
                editText.setText("");
                ArrayAdapter adapter = new ArrayAdapter(mycontext,  R.layout.spinner, list);
                ArrayAdapter adapter2 = new ArrayAdapter(mycontext,  R.layout.spinner, list1);
                spinner1.setAdapter(adapter);
                spinner2.setAdapter(adapter2);
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
                        if (editText.getText().equals("")) {
                            Toast.makeText(mycontext, "请输入完整", Toast.LENGTH_SHORT).show();
                        } else {
                            Operator operator = new Operator();
                            operator.setName(editText.getText().toString());
                            operator.setNumber(list.get(a) + "");
                            operator.setSex(list1.get(b) + "");
                            operator.save();
                            myDialog.dismiss();
                            init();
                            arrayAdapter2.notifyDataSetChanged();
                            Toast.makeText(mycontext, "添加成功", Toast.LENGTH_SHORT).show();
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
                view2 = LayoutInflater.from(mycontext).inflate(R.layout.dailog_addren, null);
                myDialog = new MyDialog(mycontext, view2, R.style.dialog);
                myDialog.setCancelable(false);
                myDialog.setCanceledOnTouchOutside(false);
                myDialog.show();
                textView3 = (TextView) view2.findViewById(R.id.textView3);
                editText = (EditText) view2.findViewById(R.id.editText);
                spinner1 = (Spinner) view2.findViewById(R.id.spinner1);
                spinner2 = (Spinner) view2.findViewById(R.id.spinner2);
                textView3.setText("修改操作人员");
                editText.setText(strings2.get(index2));
                adapter = new ArrayAdapter(mycontext, android.R.layout.simple_spinner_item, list);
                adapter2 = new ArrayAdapter(mycontext, android.R.layout.simple_spinner_item, list1);
                spinner1.setAdapter(adapter);
                spinner2.setAdapter(adapter2);
                spinner1.setSelection(Integer.parseInt(textView9.getText().toString()) - 1, true);
                if (textView10.getText().equals("男")) {
                    spinner2.setSelection(0, true);
                } else {
                    spinner2.setSelection(1, true);
                }

                button7 = (Button) view2.findViewById(R.id.button7);
                button17 = (Button) view2.findViewById(R.id.button17);
                button7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Operator operator = DataSupport.find(Operator.class, index2 + 1);
                        operator.setName(editText.getText().toString());
                        operator.setNumber(list.get(a) + "");
                        operator.setSex(list1.get(b) + "");
                        operator.save();
                        Toast.makeText(mycontext, "修改成功", Toast.LENGTH_SHORT).show();
                        init();
                        arrayAdapter2.notifyDataSetChanged();
                        myDialog.dismiss();
                    }
                });
                button17.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.dismiss();
                    }
                });
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
                break;
            case R.id.button13:
                //删除操作员
                view2 = LayoutInflater.from(mycontext).inflate(R.layout.dailog_remove, null);
                myDialog = new MyDialog(mycontext, view2, R.style.dialog);
                myDialog.setCancelable(false);
                myDialog.setCanceledOnTouchOutside(false);
                myDialog.show();
                testDialog2Tv = (TextView) view2.findViewById(R.id.test_dialog2_tv);
                testDialog2Tv2 = (TextView) view2.findViewById(R.id.test_dialog2_tv2);
                testDialog2Img = (ImageView) view2.findViewById(R.id.test_dialog2_img);
                testDialog2Btn1 = (Button) view2.findViewById(R.id.test_dialog2_btn1);
                testDialog2Btn2 = (Button) view2.findViewById(R.id.test_dialog2_btn2);
                testDialog2Tv.setText("删除操作人员");
                testDialog2Tv2.setText(strings2.get(index2));
                testDialog2Btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DataSupport.delete(Operator.class, caozuoyuans.get(index2).id);
                        Toast.makeText(mycontext, "删除成功", Toast.LENGTH_SHORT).show();
                        init();
                        arrayAdapter2.notifyDataSetChanged();
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
                final AlertDialog alertDialog = new AlertDialog.Builder(mycontext).create();
                alertDialog.setTitle("提示");
                alertDialog.setMessage("是否恢复默认值");
                alertDialog.setButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
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
                        util.sendSerialPort(cmd());
                    }
                });
                alertDialog.setButton2("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                break;
        }
    }

    private String cmd() {
        String s = "AA00FF51190101";
        s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getQidong())));
        s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getDuanxiang())));
        s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getM13()) * 10));
        s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getM30()) * 10));
        s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getChuanlian1()) * 10));
        s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getChuanlian2()) * 10));
        s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getBinglian1()) * 10));
        s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getBinglian2()) * 10));
        s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getDuanxiangzhiliu()) * 100));
        s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getJiaoliu()) * 100));
        s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getXiangjian())));
        s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getXiangduidi())));

        s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getXiangduixianquan())));
        s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getXianquan())));
        s += "19";
        return s;
    }

    class Chanpin {
        private int id;
        private String name;
        private String xinghao;
        private String changjia;
    }

    class Caozuoyuan {
        private int id;
        private String name;
        private String number;
        private String sex;
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
        float a = Float.parseFloat(ed_qidongxiangying.getText().toString().trim());
        if (a > 150) {
            Toast.makeText(mycontext, "启动响应时间只能输入小于150的数值！", Toast.LENGTH_SHORT).show();
            return;
        }
        String qidongxiangying = ed_qidongxiangying.getText().toString().trim();
        if (TextUtils.isEmpty(qidongxiangying)) {
            Toast.makeText(mycontext, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }
        float b = Float.parseFloat(ed_duanxiangxiangying.getText().toString().trim());
        if (b > 250) {
            Toast.makeText(mycontext, "断相响应时间只能输入小于250的数值！", Toast.LENGTH_SHORT).show();
            return;
        }
        String duanxiangxiangying = ed_duanxiangxiangying.getText().toString().trim();
        if (TextUtils.isEmpty(duanxiangxiangying)) {
            Toast.makeText(mycontext, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }
        float c = Float.parseFloat(ed_13xianshi.getText().toString().trim());
        if (c > 1) {
            Toast.makeText(mycontext, "13秒限时时间只能输入小于1的数值！", Toast.LENGTH_SHORT).show();
            return;
        }
        String m13xianshi = ed_13xianshi.getText().toString().trim();
        if (TextUtils.isEmpty(m13xianshi)) {
            Toast.makeText(mycontext, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }
        float d = Float.parseFloat(ed_30xianshi.getText().toString().trim());
        if (d > 1) {
            Toast.makeText(mycontext, "30秒限时时间只能输入小于1的数值！", Toast.LENGTH_SHORT).show();
            return;
        }
        String m30xianshi = ed_30xianshi.getText().toString().trim();
        if (TextUtils.isEmpty(m30xianshi)) {
            Toast.makeText(mycontext, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }
        float e = Float.parseFloat(ed_chuanlian1.getText().toString().trim());
        if (e < 20) {
            Toast.makeText(mycontext, "只能输入大于20的数值！", Toast.LENGTH_SHORT).show();
            return;
        }
        String chuanlian1 = ed_chuanlian1.getText().toString().trim();
        if (TextUtils.isEmpty(chuanlian1)) {
            Toast.makeText(mycontext, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }
        double f = Double.parseDouble(ed_chuanlan2.getText().toString().trim());
        if (f > 27.5) {
            Toast.makeText(mycontext, "只能输入小于27.5的数值！", Toast.LENGTH_SHORT).show();
            return;
        }
        String chuanlan2 = ed_chuanlan2.getText().toString().trim();
        if (TextUtils.isEmpty(chuanlan2)) {
            Toast.makeText(mycontext, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }
        float g = Float.parseFloat(ed_binglian1.getText().toString().trim());
        if (g < 10) {
            Toast.makeText(mycontext, "只能输入大于10的数值！", Toast.LENGTH_SHORT).show();
            return;
        }
        String binglian1 = ed_binglian1.getText().toString().trim();
        if (TextUtils.isEmpty(binglian1)) {
            Toast.makeText(mycontext, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }
        float h = Float.parseFloat(ed_binglian2.getText().toString().trim());
        if (h > 14) {
            Toast.makeText(mycontext, "只能输入小于14的数值！", Toast.LENGTH_SHORT).show();
            return;
        }
        String binglian2 = ed_binglian2.getText().toString().trim();
        if (TextUtils.isEmpty(binglian2)) {
            Toast.makeText(mycontext, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }
        double i = Double.parseDouble(ed_duanxiangzhiliu.getText().toString().trim());
        if (i > 0.2) {
            Toast.makeText(mycontext, "断相直流输出电压只能输入小于0.2的数值！", Toast.LENGTH_SHORT).show();
            return;
        }
        String duanxiangzhiliu = ed_duanxiangzhiliu.getText().toString().trim();
        if (TextUtils.isEmpty(duanxiangzhiliu)) {
            Toast.makeText(mycontext, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }
        float j = Float.parseFloat(ed_jiaoliuxianquan.getText().toString().trim());
        if (j > 3) {
            Toast.makeText(mycontext, "交流线圈压降只能输入小于3的数值！", Toast.LENGTH_SHORT).show();
            return;
        }
        String jiaoliuxianquan = ed_jiaoliuxianquan.getText().toString().trim();
        if (TextUtils.isEmpty(jiaoliuxianquan)) {
            Toast.makeText(mycontext, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }
        float k = Float.parseFloat(ed_xiangjian.getText().toString().trim());
        if (k < 500) {
            Toast.makeText(mycontext, "相间绝缘电阻只能输入大于500的数值！", Toast.LENGTH_SHORT).show();
            return;
        }
        String xiangjian = ed_xiangjian.getText().toString().trim();
        if (TextUtils.isEmpty(xiangjian)) {
            Toast.makeText(mycontext, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }
        float l = Float.parseFloat(ed_xiangduidi.getText().toString().trim());
        if (l < 500) {
            Toast.makeText(mycontext, "相对地绝缘电阻只能输入大于500的数值！", Toast.LENGTH_SHORT).show();
            return;
        }
        String xiangduidi = ed_xiangduidi.getText().toString().trim();
        if (TextUtils.isEmpty(xiangduidi)) {
            Toast.makeText(mycontext, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }
        float m = Float.parseFloat(ed_xiangdui.getText().toString().trim());
        if (m < 500) {
            Toast.makeText(mycontext, "相对线圈绝缘电阻只能输入大于500的数值！", Toast.LENGTH_SHORT).show();
            return;
        }
        String xiangdui = ed_xiangdui.getText().toString().trim();
        if (TextUtils.isEmpty(xiangdui)) {
            Toast.makeText(mycontext, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }
        float n = Float.parseFloat(ed_xianquanduidi.getText().toString().trim());
        if (n < 500) {
            Toast.makeText(mycontext, "线圈对地绝缘电阻只能输入大于500的数值！", Toast.LENGTH_SHORT).show();
            return;
        }
        String xianquanduidi = ed_xianquanduidi.getText().toString().trim();
        if (TextUtils.isEmpty(xianquanduidi)) {
            Toast.makeText(mycontext, "不能留空！", Toast.LENGTH_SHORT).show();
            return;
        }
        util.sendSerialPort(cmd());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (view == null) {
                    handler.sendEmptyMessage(3);
                }
            }
        }, 1000);
    }

    private String jisuan(String s) {
        BigDecimal b2 = new BigDecimal(s);
        return decimalFormat.format(b2.multiply(b1));
    }

    private String jisuan2(String s) {
        BigDecimal b2 = new BigDecimal(s);
        return decimalFormat.format(b2.multiply(b3));
    }

    private String jisuan3(String s) {
        BigDecimal b2 = new BigDecimal(s);
        return decimalFormat.format(b2.multiply(b4));
    }
}