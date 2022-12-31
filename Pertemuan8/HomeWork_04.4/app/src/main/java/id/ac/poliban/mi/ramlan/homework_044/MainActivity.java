package id.ac.poliban.mi.ramlan.homework_044;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/*
 *Answers to questions
 *
 * Q1 - Which template provides an Activity with an options menu and the v7 appcompat support library Toolbar as the app bar?
 * A1 - Basic Activity template
 *
 * Q2 - Which dependency do you need in order to use a TabLayout?
 * A2 - com.android.support:design
 *
 * Q3 - Where do you define each child Activity and parent Activity to provide Up navigation?
 * A3 - To provide the Up button for a child screen Activity, declare the parent Activity in the child Activity section of the AndroidManifest.xml file.
 */
public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE =
            "id.ac.poliban.mi.ramlan.homework_044.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null)getSupportActionBar().setTitle("Droid Cafe_025");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, OrderActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClickImage(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_donut:
                intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.imageView_froyo:
                intent = new Intent(this, ThirdActivity.class);
                startActivity(intent);
                break;
            case R.id.imageView_ics:
                intent = new Intent(this, FourthActivity.class);
                startActivity(intent);
                break;
        }
    }

}