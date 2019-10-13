package com.example.protector;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.protector.SQl.XiuGai;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        XiuGai moren = new XiuGai();
//        ed_qidongxiangying.setText(moren.);
//        ed_duanxiangxiangying.setText(moren.duanxiang);
//        ed_13xianshi.setText(moren.m13);
//        ed_30xianshi.setText(moren.m30);
//        ed_chuanlian1.setText(moren.chuanlian1);
//        ed_chuanlan2.setText(moren.chuanlian2);
//        ed_binglian1.setText(moren.binglian1);
//        ed_binglian2.setText(moren.binglian2);
//        ed_duanxiangzhiliu.setText(moren.duanxiangzhiliu);
//        ed_jiaoliuxianquan.setText(moren.jiaoliu);
//        ed_xiangjian.setText(moren.xiangjian);
//        ed_xiangduidi.setText(moren.xiangduixianquan);
//        ed_xiangdui.setText(moren.xiangduidi);
//        ed_xianquanduidi.setText(moren.xianquan);
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

                break;
            case R.id.button9:

                break;
            case R.id.button10:

                break;
            case R.id.button11:

                break;
            case R.id.button12:

                break;
            case R.id.button13:

                break;
            case R.id.button14:
                //修改
                XiuGai xiuGai = new XiuGai();
                submit();

                break;
            case R.id.button15:
                //取消
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
                break;
        }
    }

    class Moren{
        String qidong ="150";
        String duanxiang ="250";
        String m13 ="1";
        String m30 ="1";
        String chuanlian1 ="20.0";
        String chuanlian2 ="27.5";
        String binglian1 ="10.0";
        String binglian2 ="14.0";
        String duanxiangzhiliu ="0.2";
        String jiaoliu ="3.0";
        String xiangjian ="500";
        String xiangduidi ="500";
        String xiangduixianquan ="500";
        String xianquan ="500";
    }

    private void submit() {
        // validate
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


    }
}
