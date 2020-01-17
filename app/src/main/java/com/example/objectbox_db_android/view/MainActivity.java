package com.example.objectbox_db_android.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.objectbox_db_android.R;
import com.example.objectbox_db_android.adapter.UserAdapter;
import com.example.objectbox_db_android.databinding.ActivityMainBinding;
import com.example.objectbox_db_android.model.User;
import com.example.objectbox_db_android.objectbox.ObjectBox;

import java.util.List;

import io.objectbox.BoxStore;

public class MainActivity extends BaseActivity implements UserAdapter.ItemClick {

    private ActivityMainBinding binding;
    private boolean checker = true;
    private BoxStore boxStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        initComponent();
        initListener();
    }

    private void initComponent() {

        boxStore = ObjectBox.get();

        loadAllData();

    }

    private void initListener() {

        binding.toolbar.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.toolbar.homeScreen.setVisibility(View.VISIBLE);
                binding.toolbar.editScren.setVisibility(View.GONE);
            }
        });

        binding.toolbar.deleteOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toast("Deleet");
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
                toast("Edit");
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

        binding.recyclerview.setAdapter(new UserAdapter(users, this));

    }

    @Override
    public void itemClick(User user, int position) {

        toast(user.details.getTarget().number);

    }

    @Override
    public void itemLongClick(User user, int position) {

        binding.toolbar.homeScreen.setVisibility(View.GONE);
        binding.toolbar.editScren.setVisibility(View.VISIBLE);
    }
}
