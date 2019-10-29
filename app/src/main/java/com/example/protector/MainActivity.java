package com.example.protector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

import com.example.protector.SQl.TestData;
import com.example.protector.util.Utils;
import com.example.protector.util.SerialPortUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button2;
    private Button button;
    private Button button4;
    private Button button3;
    private Button button6;
    private Button button5;
    private SharedPreferences.Editor editor;
    private SerialPortUtil utils = new SerialPortUtil();
    Utils util = new Utils();
    TestData testData ;
    BigDecimal b1 = new BigDecimal("0.001");
    BigDecimal b3 = new BigDecimal("0.01");
    BigDecimal b4 = new BigDecimal("0.1");
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        button2 = (Button) findViewById(R.id.button2);
        button = (Button) findViewById(R.id.button);
        button4 = (Button) findViewById(R.id.button4);
        button3 = (Button) findViewById(R.id.button3);
        button6 = (Button) findViewById(R.id.button6);
        button5 = (Button) findViewById(R.id.button5);
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    timer.cancel();
                    button2.performClick();
                }
            }
        };
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

//        utils.openSerialPort();

        util.hideNavKey(MainActivity.this);
        for (int i = 0; i < 5; i++) {
            List<String> strings = util.getDivLines("AA00FF1049020100C804C4B8D20404008000E600F200F400FC00F1008E0CE90FF3F7080CF5371724094C0A3C0B18008800EC00F500E605190BD1040105E904DF03A203B103C3047E04E104220334",2);
            if(strings.get(3).equals("10")){
                testData = new TestData();
                testData.setDate(new Date());
                testData.setGongwei(new Utils().HexToInt(strings.get(5))+"");
                testData.setCecheng(new Utils().HexToInt(strings.get(6))+"");
                testData.setCeshishichang(new Utils().HexToInt(strings.get(7)+strings.get(8))+"");
                testData.setChanpinbianma(new Utils().HexToInt(strings.get(9)+strings.get(10)+strings.get(11)+strings.get(12))+"");
                            /*testData.setAjiguzhang(new Utils().HexToInt(strings.get(5))+"");
                            testData.setBjiguzhang(new Utils().HexToInt(strings.get(5))+"");
                            testData.setBaojin(new Utils().HexToInt(strings.get(5))+"");*/
                testData.setXianquanchuanlian1(jisuan3(new Utils().HexToInt(strings.get(17)+strings.get(18))+""));
                testData.setXianquanchuanlian2(jisuan3(new Utils().HexToInt(strings.get(19)+strings.get(20))+""));
                testData.setXianquanchuanlian3(jisuan3(new Utils().HexToInt(strings.get(21)+strings.get(22))+""));
                testData.setXianquanchuanlian4(jisuan3(new Utils().HexToInt(strings.get(23)+strings.get(24))+""));
                testData.setXianquanchuanlian5(jisuan3(new Utils().HexToInt(strings.get(25)+strings.get(26))+""));
                testData.setXianquanbinglian(jisuan3(new Utils().HexToInt(strings.get(27)+strings.get(28))+""));
                testData.setAjiwucha(jisuan3(new Utils().HexToInt(strings.get(29))+""));
                testData.setBjiwucha(jisuan3(new Utils().HexToInt(strings.get(30))+""));
                testData.setAxiangawucha(jisuan2(new Utils().HexToInt(strings.get(31))+""));
                testData.setAxiangbwucha(jisuan2(new Utils().HexToInt(strings.get(32))+""));
                testData.setAxiangcwucha(jisuan2(new Utils().HexToInt(strings.get(33))+""));
                testData.setBxiangawucha(jisuan2(new Utils().HexToInt(strings.get(34))+""));
                testData.setBxiangbwucha(jisuan2(new Utils().HexToInt(strings.get(35))+""));
                testData.setBxiangcwucha(jisuan2(new Utils().HexToInt(strings.get(36))+""));
                testData.setAduanxiangdianya(jisuan2(new Utils().HexToInt(strings.get(37))+""));
                testData.setBduanxiangdianya(jisuan2(new Utils().HexToInt(strings.get(38))+""));
                testData.setCduanxiangdianya(jisuan2(new Utils().HexToInt(strings.get(39))+""));
                testData.setAxiangceyajiang(jisuan(new Utils().HexToInt(strings.get(40)+strings.get(41))+""));
                testData.setBxiangceyajiang(jisuan(new Utils().HexToInt(strings.get(42)+strings.get(43))+""));
                testData.setCxiangceyajiang(jisuan(new Utils().HexToInt(strings.get(44)+strings.get(45))+""));
                testData.setQidongshijian(new Utils().HexToInt(strings.get(46)+strings.get(47))+"");
                testData.setAduanxiangxiangying(new Utils().HexToInt(strings.get(48)+strings.get(49))+"");
                testData.setBduanxiangxiangying(new Utils().HexToInt(strings.get(50)+strings.get(51))+"");
                testData.setCduanxiangxiangying(new Utils().HexToInt(strings.get(52)+strings.get(53))+"");
                testData.setM13xianshishijian(jisuan2(new Utils().HexToInt(strings.get(54)+strings.get(55))+""));
                testData.setM30xianshishijian(jisuan2(new Utils().HexToInt(strings.get(56)+strings.get(57))+""));
                testData.setAbxiangjianjueyuan(new Utils().HexToInt(strings.get(58)+strings.get(59))+"");
                testData.setAcxiangjianjueyuan(new Utils().HexToInt(strings.get(60)+strings.get(61))+"");
                testData.setBcxiangjianjueyuan(new Utils().HexToInt(strings.get(62)+strings.get(63))+"");
                testData.setAxiangduidijueyuan(new Utils().HexToInt(strings.get(64)+strings.get(65))+"");
                testData.setBxiangduidijueyuan(new Utils().HexToInt(strings.get(66)+strings.get(67))+"");
                testData.setCxiangduidijueyuan(new Utils().HexToInt(strings.get(68)+strings.get(69))+"");
                testData.setAxiangduixianquanjueyuan(new Utils().HexToInt(strings.get(70)+strings.get(71))+"");
                testData.setBxiangduixianquanjueyuan(new Utils().HexToInt(strings.get(72)+strings.get(73))+"");
                testData.setCxiangduixianquanjeuyuan(new Utils().HexToInt(strings.get(74)+strings.get(75))+"");
                testData.setXianquanduidijueyuan(new Utils().HexToInt(strings.get(76)+strings.get(77))+"");
                testData.save();
            }
        }
        //串口数据监听事件
//        utils.setOnDataReceiveListener(new SerialPortUtils.OnDataReceiveListener() {
//            @Override
//            public void onDataReceive(byte[] buffer, int size) {
//                //
//                //在线程中直接操作UI会报异常：ViewRootImpl$CalledFromWrongThreadException
//                //解决方法：handler
//                //
//                mBuffer = buffer;
//                Toast.makeText(MainActivity.this, "size："+ String.valueOf(mBuffer.length)+"数据监听："+ new String(mBuffer), Toast.LENGTH_SHORT).show();
//                System.out.println( new String(mBuffer));
//            }
//        });
        //设置页
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Setting.class));
            }
        });
        //编号查询
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),NumberQuery.class));
            }
        });
        //误差分析
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),ErrorAnalysis.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.dialog_datequery, null);
                final Dialog dialog = new AlertDialog.Builder(MainActivity.this).setView(view).show();
//                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_touming);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                Button btn1 = view.findViewById(R.id.dialog_date_btn);
                Button btn2 = view.findViewById(R.id.dialog_date_btn2);
                Button btn3 = view.findViewById(R.id.dialog_date_btn3);
                Button btn4 = view.findViewById(R.id.dialog_date_btn4);
                final SharedPreferences.Editor editor = getSharedPreferences("cecheng",0).edit();
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),DateQuery.class));
                        editor.putString("what", "1").commit();
                        dialog.dismiss();
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),DateQuery.class));
                        editor.putString("what", "2").commit();

                        dialog.dismiss();

                    }
                });
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),DateQuery.class));
                        editor.putString("what", "3").commit();

                        dialog.dismiss();

                    }
                });
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),DateQuery.class));
                        editor.putString("what", "0").commit();

                        dialog.dismiss();

                    }
                });

            }
        });

        //测试页
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
                View view = LayoutInflater.from(getApplication()).inflate(R.layout.dialog_test1,null);
                final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setView(view).show();
                Button dialog1_btn1 = view.findViewById(R.id.test_dialog1_btn1);
                Button dialog1_btn2 = view.findViewById(R.id.test_dialog1_btn2);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog1_btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this,Test.class));
                        dialog.dismiss();
                    }
                });
                dialog1_btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View view2 = LayoutInflater.from(getApplication()).inflate(R.layout.dialog_test2,null);
                        final AlertDialog dialog2 = new AlertDialog.Builder(MainActivity.this).setView(view2).show();
                        Button dialog2_btn1 = view2.findViewById(R.id.test_dialog2_btn1);
                        Button dialog2_btn2 = view2.findViewById(R.id.test_dialog2_btn2);
                        dialog2.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        dialog2_btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(MainActivity.this,Test.class));
                                dialog.dismiss();
                                dialog2.dismiss();
                            }
                        });
                        dialog2_btn2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog2.dismiss();
                            }
                        });
                    }
                });
            }
        });
        //统计页
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Stats.class));
            }
        });

        if (preferences.getBoolean("diyici", false)) {
            return;
        }else {
            editor.putBoolean("diyici", true).commit();
            timer = new Timer();
            TimerTask timerTask =new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(1);
                }
            };
            timer.schedule(timerTask,5000,500);
        }
    }

    private String jisuan(String s){
        BigDecimal b2 = new BigDecimal(s);
        return decimalFormat.format(b2.multiply(b1));
    }
    private String jisuan2(String s){
        BigDecimal b2 = new BigDecimal(s);
        return decimalFormat.format(b2.multiply(b3));
    }
    private String jisuan3(String s){
        BigDecimal b2 = new BigDecimal(s);
        return decimalFormat.format(b2.multiply(b4));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        utils.closeSerialPort();
    }
}
