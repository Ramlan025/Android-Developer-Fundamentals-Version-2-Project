package id.ac.poliban.mi.ramlan.codingchallenge_033;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mHelloTextView;
    private String[] mColorArray = {"red", "pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar()!=null)getSupportActionBar().setTitle("Hello Compat_025");

        mHelloTextView = findViewById(R.id.hello_textview);


        // If savedInstanceState is not null then there is info the savedInstanceState bundle.
        // In this case it is restoring the text color
        if (savedInstanceState != null) mHelloTextView.setTextColor(savedInstanceState.getInt("color"));
    }


    //onSaveInstanceState is called due to some action (i.e phone rotated)
    //This is where we are saving the text color to be stored by storing it in the bundle outState
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        // save the current text color
        outState.putInt("color", mHelloTextView.getCurrentTextColor());
    }

    public void changeColor(View view) {
        Random random = new Random();
        String colorName = mColorArray[random.nextInt(20)];
        int colorResourceName = getResources().getIdentifier(colorName,"color",getApplicationContext()
                .getPackageName());
        //use a test of the values in the Build class to perform a different operation
        // if the app is running on a device that supports a version of Android older than API 23.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int colorRes =
                    getResources().getColor(colorResourceName, this.getTheme());
        }

        int colorRes = ContextCompat.getColor(this,colorResourceName);
        mHelloTextView.setTextColor(colorRes);
    }
}