package id.ac.poliban.mi.ramlan.simpleshoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import id.ac.poliban.mi.ramlan.simpleshoppinglist.databinding.ActivityItemListBinding;

public class ItemListActivity extends AppCompatActivity {
    public final static String ITEM_TAG = "id.ac.poliban.mi.ramlan.simpleshoppinglist.EXTRA.ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityItemListBinding itemListBinding = ActivityItemListBinding.inflate(getLayoutInflater());
        setContentView(itemListBinding.getRoot());

        Button[] mButton = new Button[]{
                itemListBinding.button,
                itemListBinding.button2,
                itemListBinding.button3,
                itemListBinding.button4,
                itemListBinding.button5,
                itemListBinding.button6,
        };

        for (Button button : mButton) {
            button.setOnClickListener(v -> {
                Button btn = (Button) v;

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(ITEM_TAG, btn.getText().toString());

                setResult(RESULT_OK, intent);
                finish();
            });
        }
    }
}