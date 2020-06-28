package com.szubov.android_hw_52;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = findViewById(R.id.toolbarMainActivity);
        setSupportActionBar(mToolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case (R.id.action_open_notes): {
                Toast.makeText(MainActivity.this, getText(R.string.menu_toast_note),
                        Toast.LENGTH_LONG).show();
                Intent intentNotes = new Intent(MainActivity.this,
                        NotesActivity.class);
                startActivity(intentNotes);
                break;
            }
            case (R.id.action_open_task_list): {
                Toast.makeText(MainActivity.this, getText(R.string.menu_toast_task_list),
                        Toast.LENGTH_LONG).show();
                Intent intentNotes = new Intent(MainActivity.this,
                        TaskListActivity.class);
                startActivity(intentNotes);
                break;
            }
            case (R.id.action_open_delivery_form): {
                Toast.makeText(MainActivity.this, getText(R.string.menu_toast_delivery_form),
                        Toast.LENGTH_LONG).show();
                Intent intentNotes = new Intent(MainActivity.this,
                        DeliveryFormActivity.class);
                startActivity(intentNotes);
                break;
            }
            case (R.id.action_open_payment_form): {
                Toast.makeText(MainActivity.this, getText(R.string.menu_toast_payment_form),
                        Toast.LENGTH_LONG).show();
                Intent intentNotes = new Intent(MainActivity.this,
                        PaymentFormActivity.class);
                startActivity(intentNotes);
                break;
            }
            case (R.id.action_settings): {
                Toast.makeText(MainActivity.this, getText(R.string.menu_toast_settings),
                        Toast.LENGTH_LONG).show();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}