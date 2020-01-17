package com.example.objectbox_db_android.view;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.objectbox_db_android.R;
import com.example.objectbox_db_android.databinding.ActivityAddBinding;
import com.example.objectbox_db_android.model.Details;
import com.example.objectbox_db_android.model.User;
import com.example.objectbox_db_android.objectbox.ObjectBox;

import java.util.Date;

import io.objectbox.BoxStore;

public class AddActivity extends BaseActivity {

    private ActivityAddBinding binding;
    private BoxStore boxStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);

        initComponent();
        initListener();
    }


    private void initComponent() {

        binding.toolbar.backButtonBackScreenLayout.setVisibility(View.VISIBLE);

        boxStore = ObjectBox.get();

    }

    private void initListener() {

        binding.toolbar.backButtonBackScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = binding.nameEt.getText().toString().trim();
                String profession = binding.professionEt.getText().toString().trim();
                String number = binding.numberEt.getText().toString().trim();
                String location = binding.locationEt.getText().toString().trim();


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

                saveUser(name, profession, number, location);

            }
        });
    }

    private void saveUser(String name, String profession, String number, String location) {

        User user = new User();
        user.name = name;
        user.profession = profession;
        user.createdAt = new Date();
        user.details.setTarget(new Details(number, location));

        if (boxStore.boxFor(User.class).put(user) > 0) {
            toast("Saved");
        } else {
            toast("Failed to save");
        }

    }
}
