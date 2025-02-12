package id.ac.poliban.mi.ramlan.hellotoast_012;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = findViewById(R.id.show_count);
        if (getSupportActionBar()!=null)getSupportActionBar().setTitle("Hello Toast_025");
    }

    public void showToast(View view) {
//        code for button toast goes here
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_LONG);
        toast.show();
    }

    public void countUp(View view) {
//        code for button count goes here
        mCount ++;
        if (mShowCount != null){
            mShowCount.setText(Integer.toString(mCount));
        }
    }
}