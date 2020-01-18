package com.example.objectbox_db_android.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.objectbox_db_android.R;
import com.example.objectbox_db_android.databinding.ActivityEditBinding;
import com.example.objectbox_db_android.model.Details;
import com.example.objectbox_db_android.model.User;
import com.example.objectbox_db_android.objectbox.ObjectBox;

import java.util.Date;

public class EditActivity extends BaseActivity {

    private ActivityEditBinding binding;
    private String name, profession, number, location;
    private long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit);

        getExtras();
        initComponent();
        initListener();
    }

    private void getExtras() {
        Bundle bundle = getIntent().getExtras();
        id = bundle.getLong("id");
        name = bundle.getString("name");
        profession = bundle.getString("profession");
        number = bundle.getString("number");
        location = bundle.getString("location");
    }

    private void initComponent() {

        binding.toolbar.backButtonBackScreenLayout.setVisibility(View.VISIBLE);
        binding.toolbar.homeScreen.setVisibility(View.GONE);
        binding.toolbar.editScren.setVisibility(View.GONE);

        binding.toolbar.backButtonScreenTitle.setText("Edit");

        binding.nameEt.setText(name);
        binding.professionEt.setText(profession);
        binding.numberEt.setText(number);
        binding.locationEt.setText(location);
    }

    private void initListener() {

        binding.toolbar.backButtonBackScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = binding.nameEt.getText().toString().trim();
                profession = binding.professionEt.getText().toString().trim();
                number = binding.numberEt.getText().toString().trim();
                location = binding.locationEt.getText().toString().trim();

                if (name.isEmpty()) {
                    binding.nameEt.setError("Name is required");
                    binding.nameEt.requestFocus();
                    return;
                }

                if (profession.isEmpty()) {
                    binding.professionEt.setError("Profession is required");
                    binding.professionEt.requestFocus();
                    return;
                }

                if (number.isEmpty()) {
                    binding.numberEt.setError("Number is required");
                    binding.numberEt.requestFocus();
                    return;
                }

                if (location.isEmpty()) {
                    binding.locationEt.setError("Location is required");
                    binding.locationEt.requestFocus();
                    return;
                }

                updateUser(name, profession, number, location);

            }
        });
    }

    private void updateUser(String name, String profession, String number, String location) {

        User user = new User();
        Details details = new Details(number, location);
        user.id = id;
        user.name = name;
        user.profession = profession;
        user.createdAt = new Date();
        user.details.setTarget(details);

        if (ObjectBox.get().boxFor(User.class).put(user) > 0) {
            showSnackbar("Updated", true);
        } else {
            showSnackbar("Failed to update", true);
        }
    }
}
