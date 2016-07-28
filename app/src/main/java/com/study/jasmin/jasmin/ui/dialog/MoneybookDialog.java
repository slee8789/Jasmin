package com.study.jasmin.jasmin.ui.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.study.jasmin.jasmin.R;

public class MoneybookDialog extends AppCompatActivity {
    public static final String TAG = "MoneybookDialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_moneybook);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_exit:
                Log.d(TAG, "btn_exit");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
