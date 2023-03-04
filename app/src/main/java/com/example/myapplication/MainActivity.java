package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button) findViewById(R.id.buttonAuth);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Клавиша нажата (программный способ)");
                toSecond(view);
            }
        });

    }

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onActivityResult(ActivityResult result) {
                    //Log.d(TAG, "onActivityResult");
                    if (result.getResultCode() == RESULT_OK){
                        Intent intent = result.getData();
                        TextView text = findViewById(R.id.textView);
                        text.setText("Последняя авторизация:" + intent.getStringExtra("text1") + " " + intent.getStringExtra("text2"));

                        text = findViewById(R.id.editTextName);
                        text.setText(intent.getStringExtra(""));
                        text = findViewById(R.id.editTextSurname);
                        text.setText(intent.getStringExtra(""));
                    }
                }
            }
    );

    public void toSecond(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        Log.i(TAG, "Клавиша нажата (декларативный способ)");

        EditText nameText = findViewById(R.id.editTextName);
        String nameInf = nameText.getText().toString();
        intent.putExtra("nameInf", nameInf);

        EditText surnameText = findViewById(R.id.editTextSurname);
        String surnameInf = surnameText.getText().toString();
        intent.putExtra("surnameInf", surnameInf);

        mStartForResult.launch(intent);
    }
}