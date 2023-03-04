package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle arguments = getIntent().getExtras();
        String name_ = arguments.get("nameInf").toString();
        String surname_ = arguments.get("surnameInf").toString();

        TextView text1 = (TextView) findViewById(R.id.nameView);
        text1.setText(name_);
        TextView text2 = (TextView) findViewById(R.id.surnameView);
        text2.setText(surname_);
    }

    public void toMain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        TextView text1 = (TextView) findViewById(R.id.nameView);
        TextView text2 = (TextView) findViewById(R.id.surnameView);
        intent.putExtra("text1", text1.getText());
        intent.putExtra("text2", text2.getText());
        setResult(RESULT_OK, intent);
        finish();
    }
}