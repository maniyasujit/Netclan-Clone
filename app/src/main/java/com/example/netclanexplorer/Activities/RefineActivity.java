package com.example.netclanexplorer.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.netclanexplorer.R;
import com.google.android.material.slider.Slider;

public class RefineActivity extends AppCompatActivity {

    private ImageView ivBack;
    private EditText etStatus;
    private TextView tvStatusLength, tvSaveAndExplore;
    private Slider sliderDistance;
    private Spinner spAvailability;
    private String[] availabilities = {"Available | Hey Let Us Connect", "Away | Stay Discrete and watch",
            "Busy | Do not Disturb | Will catch Up Later", "SOS | Emergency! Need Assistance | Help"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refine);

        sliderDistance = findViewById(R.id.slider_distance);
        spAvailability = findViewById(R.id.sp_availability);
        tvStatusLength = findViewById(R.id.tv_status_length);
        etStatus = findViewById(R.id.et_status);
        ivBack = findViewById(R.id.iv_back);
        tvSaveAndExplore = findViewById(R.id.tv_save_and_explore);

        ivBack.setOnClickListener(v -> onBackPressed());

        tvSaveAndExplore.setOnClickListener(v -> {
            startActivity(new Intent(RefineActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
        });

        tvStatusLength.setText(etStatus.length() + "/250");

        etStatus.setSelectAllOnFocus(true);
        etStatus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                tvStatusLength.setText(etStatus.length() + "/250");
            }
        });

        spAvailability.setSelection(0);
        spAvailability.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(RefineActivity.this, availabilities[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter spAvailabilityAdapter = new ArrayAdapter(this, R.layout.spinner_item, availabilities);
        spAvailability.setAdapter(spAvailabilityAdapter);
    }
}