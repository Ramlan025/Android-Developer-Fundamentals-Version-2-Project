package id.ac.poliban.mi.ramlan.roomwordssample_101a;

import static id.ac.poliban.mi.ramlan.roomwordssample_101a.MainActivity.UPDATE_WORD;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewWordActivity extends AppCompatActivity {
    private Button mSaveButton;
    public static final String EXTRA_REPLY = "id.ac.poliban.mi.ramlan.roomwordssample_101a.REPLY";
    public static final String EXTRA_WORD_ID = "id.ac.poliban.mi.ramlan.roomwordssample_101a.REPLY_ID";
    private EditText mEditTextView;
    private Integer id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        mSaveButton = findViewById(R.id.button_save);
        mEditTextView = findViewById(R.id.edit_word);

        final Bundle extras = getIntent().getExtras();

        //Checking if the intent is not empty to not seg fault
        if(extras != null) {
            Intent intent = getIntent();
            String word = intent.getStringExtra(UPDATE_WORD);
            if (!word.isEmpty()) {
                mEditTextView.setText(word);
                mEditTextView.setSelection(word.length());
                mEditTextView.requestFocus();
            }

            id = extras.getInt(EXTRA_WORD_ID, -1);
            Log.d("ID: ", id.toString());
        }
    }

    public void saveButton(View view) {
        Intent replyIntent = new Intent();

        //User did not enter a word
        if(TextUtils.isEmpty(mEditTextView.getText())) setResult(RESULT_CANCELED, replyIntent);

        //User entered a word
        else {
            String word = mEditTextView.getText().toString();
            Log.d("Word: ", word);
            replyIntent.putExtra(EXTRA_REPLY, word);

            if(id != -1)
                replyIntent.putExtra(EXTRA_WORD_ID, id);
            setResult(RESULT_OK, replyIntent);
        }

        finish();
    }
}
