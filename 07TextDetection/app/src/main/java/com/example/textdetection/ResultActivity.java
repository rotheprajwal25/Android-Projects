package com.example.textdetection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private Button back;
    private TextView resultTextView;
    private String resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        back = findViewById(R.id.back_button);
        resultTextView = findViewById(R.id.result_textview);
        resultText = getIntent().getStringExtra(TextDetection.RESULT_TEXT);
        resultTextView.setText(resultText);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
