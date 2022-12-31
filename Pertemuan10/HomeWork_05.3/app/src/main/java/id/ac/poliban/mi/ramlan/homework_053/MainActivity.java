package id.ac.poliban.mi.ramlan.homework_053;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;
import java.util.Objects;

/*//
 * Answers to questions
 *
 * Q1 - Which resource qualifier is used most frequently to select for tablets?
 * A1 - Smallest screen width
 *
 * Q2 - Which folder would hold the strings.xml file for translation into French for Canada?
 * A2 - res/values-fr-rCA/
 *
 * Q3 - Which folder is for XML files that contain strings, integers, and colors?
 * A3 - res/values
 //*/


public class MainActivity extends AppCompatActivity {
    // LinkedList to hold the data
    private final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null)getSupportActionBar().setTitle("RecyclerView_025");
        int gridCount = getResources().getInteger(R.integer.grid_column_count);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            int wordListSize = mWordList.size();
            // add new word to the linked list
            mWordList.addLast("+ Word " + wordListSize);
            // notify adapter of data change
            Objects.requireNonNull(mRecyclerView.getAdapter()).notifyItemInserted(wordListSize);
            // scroll to the bottom of the list
            mRecyclerView.smoothScrollToPosition(wordListSize);
        });

        // populate the initial data
        for (int i = 0; i < 21; i++) {
            mWordList.add("Word " + i);
        }

        // set up recyclerView - find view, create and set adapter; set layout manager
        mRecyclerView = findViewById(R.id.recyclerView);
        WordListAdapter mAdapter = new WordListAdapter(this, mWordList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridCount));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            mWordList.clear();
            for (int i = 0; i < 21; i++) {
                mWordList.add("Word " + i);
            }
            Objects.requireNonNull(mRecyclerView.getAdapter()).notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
