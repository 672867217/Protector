package com.example.protector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button2;
    private Button button;
    private Button button4;
    private Button button3;
    private Button button6;
    private Button button5;
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

    }
}
