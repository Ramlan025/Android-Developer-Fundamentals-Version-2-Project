package id.ac.poliban.mi.ramlan.simpleshoppinglist;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import id.ac.poliban.mi.ramlan.simpleshoppinglist.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private int mTextViewCount = 0;
    private ActivityMainBinding mainBinding;
    private TextView[] mTextView;
    private final ActivityResultLauncher<Intent> mAddShoppingList =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent intent = result.getData();

                            String item = intent.getStringExtra(ItemListActivity.ITEM_TAG);
                            mTextView[mTextViewCount].setText(item);
                            mTextViewCount++;

                            if (mTextViewCount == 9)
                                mainBinding.addItem.setEnabled(false);
                        }
                    });

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        String[] textView = new String[mTextViewCount + 1];
        for (int i = 0; i <= mTextViewCount; i++) {
            String text = mTextView[i].getText().toString();
            textView[i] = text;
        }

        outState.putStringArray("item_list", textView);
        outState.putBoolean("addItem_disabled", mainBinding.addItem.isEnabled());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mTextView = new TextView[]{
                mainBinding.textView,
                mainBinding.textView2,
                mainBinding.textView3,
                mainBinding.textView4,
                mainBinding.textView5,
                mainBinding.textView6,
                mainBinding.textView7,
                mainBinding.textView8,
                mainBinding.textView9,
                mainBinding.textView10,
        };

        mainBinding.addItem.setOnClickListener(v -> {
            Intent intent = new Intent(this, ItemListActivity.class);
            mAddShoppingList.launch(intent);
        });

        mainBinding.btnSearchLoc.setOnClickListener(v -> {
            String loc = mainBinding.editTextSearchLocation.getText().toString();

            Uri geoLoc = Uri.parse("geo:0,0?q=" + loc);
            Intent intent = new Intent(Intent.ACTION_VIEW, geoLoc);

            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);
            else
                Log.d(LOG_TAG, "Can't handle open a location");
        });

        if (savedInstanceState != null) {
            String[] tv = savedInstanceState.getStringArray("item_list");
            mTextViewCount = tv.length - 1;

            for (int i = 0; i <= mTextViewCount; i++)
                mTextView[i].setText(tv[i]);
            mainBinding.addItem.setEnabled(savedInstanceState.getBoolean("addItem_disabled"));
        }
    }
}