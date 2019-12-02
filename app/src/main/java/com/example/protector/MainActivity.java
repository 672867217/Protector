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
import android.widget.Toast;

import com.example.protector.SQl.Gonwei;
import com.example.protector.SQl.TestData;
import com.example.protector.SQl.XiuGai;
import com.example.protector.util.Utils;
import com.example.protector.util.SerialPortUtil;

import org.litepal.crud.DataSupport;

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
    public static SerialPortUtil utils = new SerialPortUtil();
    Utils util = new Utils();
    TestData testData;
    BigDecimal b1 = new BigDecimal("0.001");
    BigDecimal b3 = new BigDecimal("0.01");
    BigDecimal b4 = new BigDecimal("0.1");
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private Timer timer;
    private XiuGai xiuGai;
    private Gonwei gonwei;

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
        final Handler handler = new Handler() {
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
        utils.openSerialPort();
        utils.onReceive(new SerialPortUtil.Receive() {
            @Override
            public void set(String str, List<String> list) {
                switch (str) {
                    case "61":
                        List<String> strings = util.getDivLines(String.format("%08d",Integer.parseInt(Integer.toBinaryString(new Utils().HexToInt(list.get(5))))),1);
                        System.out.println(strings.toString());
                        if(DataSupport.findAll(Gonwei.class).size() == 0){
                            gonwei = new Gonwei();
                        }else {
                            gonwei = DataSupport.find(Gonwei.class,1);
                        }
                        gonwei.one = is(Integer.parseInt(strings.get(7)));
                        gonwei.two = is(Integer.parseInt(strings.get(6)));
                        gonwei.three = is(Integer.parseInt(strings.get(5)));
                        gonwei.four = is(Integer.parseInt(strings.get(4)));
                        gonwei.five = is(Integer.parseInt(strings.get(3)));
                        gonwei.save();
                        break;
                    case "50":
                        switch (new Utils().HexToInt(list.get(5))){
                            case 1:
                                xiuGai = DataSupport.find(XiuGai.class,1);
                                break;
                            case 2:
                                xiuGai = DataSupport.find(XiuGai.class,2);
                                break;
                            case 3:
                                xiuGai = DataSupport.find(XiuGai.class,3);
                                break;
                            case 4:
                                xiuGai = DataSupport.find(XiuGai.class,4);
                                break;
                            case 5:
                                xiuGai = DataSupport.find(XiuGai.class,5);
                                break;
                        }
                        xiuGai.setCecheng(new Utils().HexToInt(list.get(6))+"");
                        xiuGai.setQidong(new Utils().HexToInt(list.get(7))+"");
                        xiuGai.setDuanxiang(new Utils().HexToInt(list.get(8))+"");
                        xiuGai.setM13(jisuan3(new Utils().HexToInt(list.get(9))+""));
                        xiuGai.setM30(jisuan3(new Utils().HexToInt(list.get(10))+""));
                        xiuGai.setChuanlian1(jisuan3(new Utils().HexToInt(list.get(11)+list.get(12))+""));
                        xiuGai.setChuanlian2(jisuan3(new Utils().HexToInt(list.get(13)+list.get(14))+""));
                        xiuGai.setBinglian1(jisuan3(new Utils().HexToInt(list.get(15)+list.get(16))+""));
                        xiuGai.setBinglian2(jisuan3(new Utils().HexToInt(list.get(17)+list.get(18))+""));
                        xiuGai.setDuanxiangzhiliu(jisuan2(new Utils().HexToInt(list.get(19))+""));
                        xiuGai.setJiaoliu(jisuan2(new Utils().HexToInt(list.get(20)+list.get(21))+""));
                        xiuGai.setXiangjian(new Utils().HexToInt(list.get(22)+list.get(23))+"");
                        xiuGai.setXiangduidi(new Utils().HexToInt(list.get(24)+list.get(25))+"");
                        xiuGai.setXiangduixianquan(new Utils().HexToInt(list.get(26)+list.get(27))+"");
                        xiuGai.setXianquan(new Utils().HexToInt(list.get(28)+list.get(29))+"");
                        xiuGai.save();
                        break;
                }
            }
        });
        util.hideNavKey(MainActivity.this);
        //设置页
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timer != null) {
                    timer.cancel();
                }
                startActivity(new Intent(getApplicationContext(), Setting.class));
            }
        });
        //编号查询
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timer != null) {
                    timer.cancel();
                }
                startActivity(new Intent(getApplicationContext(), NumberQuery.class));
            }
        });
        //误差分析
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (timer != null) {
                    timer.cancel();
                }
                startActivity(new Intent(getApplicationContext(), ErrorAnalysis.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer != null) {
                    timer.cancel();
                }
                View view = getLayoutInflater().inflate(R.layout.dialog_datequery, null);
                final Dialog dialog = new AlertDialog.Builder(MainActivity.this).setView(view).show();
//                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_touming);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                Button btn1 = view.findViewById(R.id.dialog_date_btn);
                Button btn2 = view.findViewById(R.id.dialog_date_btn2);
                Button btn3 = view.findViewById(R.id.dialog_date_btn3);
                Button btn4 = view.findViewById(R.id.dialog_date_btn4);
                final SharedPreferences.Editor editor = getSharedPreferences("cecheng", 0).edit();
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), DateQuery.class));
                        editor.putString("what", "1").commit();
                        dialog.dismiss();
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), DateQuery.class));
                        editor.putString("what", "2").commit();
                        dialog.dismiss();

                    }
                });
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), DateQuery.class));
                        editor.putString("what", "3").commit();

                        dialog.dismiss();

                    }
                });
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), DateQuery.class));
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
                if (timer != null) {
                    timer.cancel();
                }
                View view = LayoutInflater.from(getApplication()).inflate(R.layout.dialog_test1, null);
                final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setView(view).show();
                Button dialog1_btn1 = view.findViewById(R.id.test_dialog1_btn1);
                Button dialog1_btn2 = view.findViewById(R.id.test_dialog1_btn2);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog1_btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, Test.class));
                        dialog.dismiss();
                    }
                });
                dialog1_btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View view2 = LayoutInflater.from(getApplication()).inflate(R.layout.dialog_test2, null);
                        final AlertDialog dialog2 = new AlertDialog.Builder(MainActivity.this).setView(view2).show();
                        Button dialog2_btn1 = view2.findViewById(R.id.test_dialog2_btn1);
                        Button dialog2_btn2 = view2.findViewById(R.id.test_dialog2_btn2);
                        dialog2.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        dialog2_btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, Test.class);
                                intent.putExtra("what", 1);
                                startActivity(intent);
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
                if (timer != null) {
                    timer.cancel();
                }
                startActivity(new Intent(MainActivity.this, Stats.class));
            }
        });

        if (preferences.getBoolean("diyici", false)) {
            return;
        } else {
            for (int i = 0; i < 5; i++) {
                new Setting().add(i+1);
            }
            editor.putBoolean("diyici", true).commit();
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(1);
                }
            };
            timer.schedule(timerTask, 5000, 500);
        }
    }
    private boolean is(int num){
        if(num == 0){
            return false;
        }else
        {
            return true;
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        utils.closeSerialPort();
    }
}
