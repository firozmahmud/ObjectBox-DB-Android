package com.example.objectbox_db_android.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class BaseActivity extends AppCompatActivity {


    protected void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void toast(String message, boolean isLongToast) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    protected void startActivity(Class toActivity) {

        startActivity(new Intent(this, toActivity));
    }

    protected void startActivity(Class toActivity, Bundle data) {

        startActivity(new Intent(this, toActivity).putExtras(data));
    }

    protected void startActivity(Class toActivity, boolean isFinished) {
        startActivity(new Intent(this, toActivity));
        this.finish();
    }

    protected void startActivity(Class toActivity, Bundle data, boolean isFinished) {
        startActivity(new Intent(this, toActivity).putExtras(data));
        this.finish();
    }

    protected void showSnackbar(String message, boolean isLong) {


        Snackbar snackbar;

        if (isLong) {
            snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

        } else {
            snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        }

        snackbar.show();

        View view = snackbar.getView();
        TextView txtv = view.findViewById(com.google.android.material.R.id.snackbar_text);
        txtv.setGravity(Gravity.CENTER_HORIZONTAL);
    }

}
