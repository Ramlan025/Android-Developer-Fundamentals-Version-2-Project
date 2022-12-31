package id.ac.poliban.mi.ramlan.roomwordssample_101a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/* Building the adapter that bridges the gap between the data in the word list and the RecyclerView that is going to display it */
/* Used to put the data into the adapter */
public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    // LayoutInflator is used to inflate the XML. Reads a layout XML description and converts it into the corresponding View items.
    private final LayoutInflater mInflater;

    //click listener for each view holder
    private static ClickListener clickListener;

    // Cached copy of words
    private List<Word> mWords;

    //Constructor for id.ac.poliban.mi.ramlan.roomwordssample_101a.WordListAdapter
    WordListAdapter(Context context) { mInflater = LayoutInflater.from(context); }


    //Inflating the item layout and returns a ViewHolder with the layout and the adapter (data)
    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    //Get word at position
    public Word getWordAtPosition(int position) { return mWords.get(position);}

    //Connects the data to the view holder
    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mWords != null) {
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        }
        else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
        }
    }

    void setWords(List<Word> words)
    {
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    //Class for a single list item in the recycler view
    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            wordItemView = itemView.findViewById(R.id.textView);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        WordListAdapter.clickListener = clickListener;}

    public interface ClickListener { void onItemClick(int position, View v);}
}
