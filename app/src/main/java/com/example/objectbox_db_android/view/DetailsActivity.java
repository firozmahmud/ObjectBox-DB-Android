package com.example.objectbox_db_android.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.objectbox_db_android.R;
import com.example.objectbox_db_android.databinding.ActivityDetailsBinding;
import com.example.objectbox_db_android.model.User;

public class DetailsActivity extends BaseActivity {

    private ActivityDetailsBinding binding;
    private String name, profession, number, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        getExtras();
        initComponent();
        initListener();
    }

    private void getExtras() {
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        profession = bundle.getString("profession");
        number = bundle.getString("number");
        location = bundle.getString("location");
    }

    private void initComponent() {

        binding.toolbar.backButtonBackScreenLayout.setVisibility(View.VISIBLE);
        binding.toolbar.homeScreen.setVisibility(View.GONE);
        binding.toolbar.editScren.setVisibility(View.GONE);

        binding.toolbar.backButtonScreenTitle.setText("Details");

        binding.nameTv.setText(name);
        binding.professionTv.setText(profession);
        binding.numberTv.setText(number);
        binding.locationTv.setText(location);
    }

    private void initListener() {

        binding.toolbar.backButtonBackScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
