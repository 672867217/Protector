package com.example.protector;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QueryResult extends AppCompatActivity implements View.OnClickListener {

    private TextView test_item2_tv;
    private TextView textView13;
    private TextView result_tv8;
    private TextView result_tv9;
    private TextView result_tv10;
    private TextView result_tv11;
    private TextView textView11;
    private TextView result_tv12;
    private TextView result_tv13;
    private TextView result_tv14;
    private TextView result_tv15;
    private TextView result_tv16;
    private TextView result_tv17;
    private TextView result_tv18;
    private TextView result_tv19;
    private TextView result_tv20;
    private TextView result_tv21;
    private TextView result_tv22;
    private TextView result_tv23;
    private TextView test_item2_tv6;
    private TextView header_tv;
    private TextView header_tv2;
    private TextView header_tv3;
    private TextView header_tv4;
    private Button btn_dayin;
    private Button btn_cancel;
    private Spinner stats_spinner;
    private TextView stats_tv1;
    private TextView textView5;
    private TextView textView6;
    private TextView result_tv1;
    private TextView result_tv2;
    private TextView result_tv3;
    private TextView result_tv4;
    private TextView result_tv5;
    private TextView result_tv6;
    private TextView result_tv7;
    private ImageView image1;
    private ImageView image2;
    private TextView result2_tv7;
    private TextView result2_tv8;
    private TextView result2_tv9;
    private TextView result2_tv10;
    private TextView result2_tv11;
    private TextView result2_tv12;
    private TextView result2_tv13;
    private TextView result2_tv14;
    private TextView result2_tv15;
    private TextView result2_tv1;
    private TextView result2_tv2;
    private TextView result2_tv3;
    private TextView result2_tv4;
    private TextView result2_tv5;
    private TextView result2_tv6;
    private TextView result2_tv16;
    private TextView result2_tv17;
    private TextView result2_tv18;
    private TextView result2_tv19;
    private TextView result2_tv20;
    private TextView result2_tv21;
    private TextView result2_tv22;
    private TextView result2_tv23;
    private TextView result2_tv24;
    private TextView result2_tv25;
    private TextView result_tv26;
    private TextView result_tv27;
    private TextView result_tv28;
    private TextView result_tv29;
    private TextView result_tv30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_result);
        initView();

    }

    private void initView() {
        test_item2_tv = (TextView) findViewById(R.id.test_item2_tv);
        textView13 = (TextView) findViewById(R.id.textView13);
        result_tv8 = (TextView) findViewById(R.id.result_tv8);
        result_tv9 = (TextView) findViewById(R.id.result_tv9);
        result_tv10 = (TextView) findViewById(R.id.result_tv10);
        result_tv11 = (TextView) findViewById(R.id.result_tv11);
        textView11 = (TextView) findViewById(R.id.textView11);
        result_tv12 = (TextView) findViewById(R.id.result_tv12);
        result_tv13 = (TextView) findViewById(R.id.result_tv13);
        result_tv14 = (TextView) findViewById(R.id.result_tv14);
        result_tv15 = (TextView) findViewById(R.id.result_tv15);
        result_tv16 = (TextView) findViewById(R.id.result_tv16);
        result_tv17 = (TextView) findViewById(R.id.result_tv17);
        result_tv18 = (TextView) findViewById(R.id.result_tv18);
        result_tv19 = (TextView) findViewById(R.id.result_tv19);
        result_tv20 = (TextView) findViewById(R.id.result_tv20);
        result_tv21 = (TextView) findViewById(R.id.result_tv21);
        result_tv22 = (TextView) findViewById(R.id.result_tv22);
        result_tv23 = (TextView) findViewById(R.id.result_tv23);
        test_item2_tv6 = (TextView) findViewById(R.id.test_item2_tv6);
        header_tv = (TextView) findViewById(R.id.header_tv);
        header_tv2 = (TextView) findViewById(R.id.header_tv2);
        header_tv3 = (TextView) findViewById(R.id.header_tv3);
        header_tv4 = (TextView) findViewById(R.id.header_tv4);
        btn_dayin = (Button) findViewById(R.id.btn_dayin);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        stats_spinner = (Spinner) findViewById(R.id.stats_spinner);
        stats_tv1 = (TextView) findViewById(R.id.stats_tv1);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        result_tv1 = (TextView) findViewById(R.id.result_tv1);
        result_tv2 = (TextView) findViewById(R.id.result_tv2);
        result_tv3 = (TextView) findViewById(R.id.result_tv3);
        result_tv4 = (TextView) findViewById(R.id.result_tv4);
        result_tv5 = (TextView) findViewById(R.id.result_tv5);
        result_tv6 = (TextView) findViewById(R.id.result_tv6);
        result_tv7 = (TextView) findViewById(R.id.result_tv7);
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        result2_tv7 = (TextView) findViewById(R.id.result2_tv7);
        result2_tv8 = (TextView) findViewById(R.id.result2_tv8);
        result2_tv9 = (TextView) findViewById(R.id.result2_tv9);
        result2_tv10 = (TextView) findViewById(R.id.result2_tv10);
        result2_tv11 = (TextView) findViewById(R.id.result2_tv11);
        result2_tv12 = (TextView) findViewById(R.id.result2_tv12);
        result2_tv13 = (TextView) findViewById(R.id.result2_tv13);
        result2_tv14 = (TextView) findViewById(R.id.result2_tv14);
        result2_tv15 = (TextView) findViewById(R.id.result2_tv15);
        result2_tv1 = (TextView) findViewById(R.id.result2_tv1);
        result2_tv2 = (TextView) findViewById(R.id.result2_tv2);
        result2_tv3 = (TextView) findViewById(R.id.result2_tv3);
        result2_tv4 = (TextView) findViewById(R.id.result2_tv4);
        result2_tv5 = (TextView) findViewById(R.id.result2_tv5);
        result2_tv6 = (TextView) findViewById(R.id.result2_tv6);
        result2_tv16 = (TextView) findViewById(R.id.result2_tv16);
        result2_tv17 = (TextView) findViewById(R.id.result2_tv17);
        result2_tv18 = (TextView) findViewById(R.id.result2_tv18);
        result2_tv19 = (TextView) findViewById(R.id.result2_tv19);
        result2_tv20 = (TextView) findViewById(R.id.result2_tv20);
        result2_tv21 = (TextView) findViewById(R.id.result2_tv21);
        result2_tv22 = (TextView) findViewById(R.id.result2_tv22);
        result2_tv23 = (TextView) findViewById(R.id.result2_tv23);
        result2_tv24 = (TextView) findViewById(R.id.result2_tv24);
        result2_tv25 = (TextView) findViewById(R.id.result2_tv25);
        result_tv26 = (TextView) findViewById(R.id.result_tv26);
        result_tv27 = (TextView) findViewById(R.id.result_tv27);
        result_tv28 = (TextView) findViewById(R.id.result_tv28);
        result_tv29 = (TextView) findViewById(R.id.result_tv29);
        result_tv30 = (TextView) findViewById(R.id.result_tv30);

        btn_dayin.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dayin:

                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }
}
