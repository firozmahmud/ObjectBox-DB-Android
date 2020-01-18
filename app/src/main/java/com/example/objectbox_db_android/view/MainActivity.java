package com.example.objectbox_db_android.view;

import androidx.appcompat.widget.PopupMenu;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.objectbox_db_android.R;
import com.example.objectbox_db_android.adapter.UserAdapter;
import com.example.objectbox_db_android.databinding.ActivityMainBinding;
import com.example.objectbox_db_android.databinding.ItemUserHeadlineBinding;
import com.example.objectbox_db_android.model.Details;
import com.example.objectbox_db_android.model.User;
import com.example.objectbox_db_android.objectbox.ObjectBox;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.BoxStore;

public class MainActivity extends BaseActivity implements UserAdapter.ItemClick {

    private ActivityMainBinding binding;
    private boolean checker = true;
    private BoxStore boxStore;
    private UserAdapter userAdapter;
    private List<User> usersWhichToBeDeleted = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initComponent();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadAllData();
        binding.toolbar.homeScreen.setVisibility(View.VISIBLE);
        binding.toolbar.editScren.setVisibility(View.GONE);
    }

    private void initComponent() {

        boxStore = ObjectBox.get();
    }

    private void initListener() {

        binding.toolbar.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.toolbar.homeScreen.setVisibility(View.VISIBLE);
                binding.toolbar.editScren.setVisibility(View.GONE);

                userAdapter.clearAll();
            }
        });

        binding.toolbar.deleteOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    boxStore.boxFor(User.class).remove(usersWhichToBeDeleted);
                    toast("Deleted");
                } catch (Exception e) {
                    toast("Something wrong");
                }
            }
        });

        binding.toolbar.moreOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopUpMenu();
            }
        });


        binding.toolbar.moreInEditOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toast("More edit");
            }
        });


        binding.toolbar.searchOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("Search");
            }
        });

        binding.toolbar.editOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = usersWhichToBeDeleted.get(usersWhichToBeDeleted.size() - 1);

                Bundle bundle = new Bundle();
                bundle.putLong("id", user.id);
                bundle.putString("name", user.name);
                bundle.putString("profession", user.profession);
                bundle.putString("number", user.details.getTarget().number);
                bundle.putString("location", user.details.getTarget().location);

                startActivity(EditActivity.class, bundle);
            }
        });
    }

    private void showPopUpMenu() {

        PopupMenu popupMenu = new PopupMenu(this, binding.toolbar.moreOptions);
        popupMenu.getMenuInflater().inflate(R.menu.settings_menu, popupMenu.getMenu());


        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.menu_add:
                        startActivity(AddActivity.class);
                        return true;
                    case R.id.menu_settings:
                        return true;
                    case R.id.menu_help:
                        return true;

                }
                return false;
            }
        });

        popupMenu.show();

    }

    private void loadAllData() {

        List<User> users = boxStore.boxFor(User.class).getAll();
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));

        userAdapter = new UserAdapter(users, this);
        binding.recyclerview.setAdapter(userAdapter);

    }

    @Override
    public void itemClick(User user) {

        Bundle bundle = new Bundle();
        bundle.putString("name", user.name);
        bundle.putString("profession", user.profession);
        bundle.putString("number", user.details.getTarget().number);
        bundle.putString("location", user.details.getTarget().location);

        startActivity(DetailsActivity.class, bundle);

    }

    @Override
    public void itemLongClick(boolean isAnyItemSelected, User user) {
        if (isAnyItemSelected) {
            binding.toolbar.homeScreen.setVisibility(View.GONE);
            binding.toolbar.editScren.setVisibility(View.VISIBLE);
        } else {
            binding.toolbar.homeScreen.setVisibility(View.VISIBLE);
            binding.toolbar.editScren.setVisibility(View.GONE);
        }


        if (user.isSelected) {
            usersWhichToBeDeleted.add(user);
        } else {
            usersWhichToBeDeleted.remove(user);
        }
    }
}
