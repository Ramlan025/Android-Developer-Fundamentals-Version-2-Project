package id.ac.poliban.mi.ramlan.demosspiner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.label_spinner);
        if (spinner != null) {
                spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner.
        if (spinner != null) {
                spinner.setAdapter(adapter);
                }
        }

        public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String spinnerLabel = adapterView.getItemAtPosition(i).toString();
        displayToast(spinnerLabel);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
                case R.id.mi_breakfast:
                        displayToast(getString(R.string.mi_breakfast_messege));
                        return true;
                // ... code for other cases
                case R.id.mi_lunch:
                        displayToast(getString(R.string.mi_lunch_messege));
                        return true;
                case R.id.mi_diner:
                        displayToast(getString(R.string.mi_diner_messege));
                        return true;
                case R.id.mi_meeting:
                        displayToast(getString(R.string.mi_meeting_messege));
                        return true;
                case R.id.mi_entertiment:
                        displayToast(getString(R.string.mi_entertiment_messege));
                        return true;
                default:
                        // Do nothing
                }

        return super.onOptionsItemSelected(item);
        }
}