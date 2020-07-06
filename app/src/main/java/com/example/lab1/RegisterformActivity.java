package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterformActivity extends AppCompatActivity {
    EditText user, pass, retype, birthdate;
    RadioButton male, female;
    CheckBox tennis, football, others;
    Button reset, sign, select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = findViewById(R.id.edUsername);
        pass = findViewById(R.id.edPass);
        retype = findViewById(R.id.edRetype);
        birthdate = findViewById(R.id.edDate);
        male = findViewById(R.id.btMale);
        female = findViewById(R.id.btnFemale);
        tennis = findViewById(R.id.cbTenis);
        football = findViewById(R.id.cbfoolball);
        others = findViewById(R.id.cbOthers);
        reset = findViewById(R.id.btnRest);
        sign = findViewById(R.id.sign);
        select = findViewById(R.id.btnSelect);

        handleReset();
        handleSelect();
        handleSign();
    }

    private void handleSelect() {
        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDate();
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDate();
            }
        });
    }

    private void handleSign() {
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getText().toString().isEmpty()) {
                    user.requestFocus();
                    Toast.makeText(getApplicationContext(), "Kiểm tra lại username không được rỗng.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass.getText().toString().isEmpty() || !pass.getText().toString().equals(retype.getText().toString())) {
                    pass.requestFocus();
                    Toast.makeText(getApplicationContext(), "Kiểm tra lại Password và Retype.", Toast.LENGTH_SHORT).show();
                    return;
                }
                String gender = "";
                if (male.isChecked()) {
                    gender = "Male";
                } else gender = "Female";
                String hobbies = "";
                if (tennis.isChecked()) {
                    hobbies = " Tennis,";
                }
                if (football.isChecked()) {
                    hobbies += " Tennis,";
                }
                if (others.isChecked()) {
                    hobbies += " Other";
                }
                if (!birthdate.getText().toString().equals("")) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    simpleDateFormat.setLenient(false);
                    try {
                        Date javaDate = simpleDateFormat.parse(birthdate.getText().toString());
                    } catch (ParseException e) {
                        Toast.makeText(getApplicationContext(), "Kiểm tra lại BirthDate.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                Intent intent = new Intent(getApplicationContext(), ResultfromActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("user", user.getText().toString());
                bundle.putString("pass", pass.getText().toString());
                bundle.putString("birthDate", birthdate.getText().toString());
                bundle.putString("gender", gender);
                bundle.putString("hobbies", hobbies);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });


    }

    private void handleReset() {
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setText("");
                pass.setText("");
                retype.setText("");
                birthdate.setText("");
                male.setChecked(true);
                female.setChecked(false);
                tennis.setChecked(true);
                football.setChecked(false);
                others.setChecked(false);

            }
        });

    }

    private void SetDate() {
        final Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                birthdate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, date);
        datePickerDialog.show();
    }
}