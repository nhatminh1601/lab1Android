package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultfromActivity extends AppCompatActivity {
    TextView user, pass, birthdate, gender, hobbies;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultfrom);
        Bundle bundle = getIntent().getExtras();
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        birthdate = findViewById(R.id.birthDate);
        gender = findViewById(R.id.gender);
        hobbies = findViewById(R.id.hobbies);

        user.setText(bundle.getString("user"));
        pass.setText("***********");
        birthdate.setText(bundle.getString("birthDate"));
        gender.setText(bundle.getString("gender"));
        hobbies.setText(bundle.getString("hobbies"));
        btnExit=findViewById(R.id.btnExit);
        handleExit();
    }

    private void handleExit() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}