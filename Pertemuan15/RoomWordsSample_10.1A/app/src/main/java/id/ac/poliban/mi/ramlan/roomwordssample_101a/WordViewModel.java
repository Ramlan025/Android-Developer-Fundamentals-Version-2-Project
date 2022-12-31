package id.ac.poliban.mi.ramlan.roomwordssample_101a;
/**
 *                                                  ViewModel
 *
 * A ViewModel holds your app's UI data in a way that survives configuration changes. Separating your app's UI data from your Activity and Fragment
 * classes lets you better follow the single responsibility principle: Your activities and fragments are responsible for drawing data to the screen,
 * while your ViewModel is responsible for holding and processing all the data needed for the UI.
 *
 * The WordViewModel hides everything about the backend from the user interface. It provides methods for accessing the UI data, and it returns LiveData
 * so that MainActivity can set up the observer relationship. Views, activities, and fragments only interact with the data through the ViewModel.
 * As such, it doesn't matter where the data comes from.
 *
 * **/

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel
{
    //Reference to the repo
    private WordRepository mRepository;

    //Used to cache the list of words
    private LiveData<List<Word>> mAllWords;

    public WordViewModel(Application application)
    {
        super(application);

        //Getting a list of all words from the WordRepo
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    //Getter method that gets all the words and hides the implementation from the UI
    LiveData<List<Word>> getAllWords() { return mAllWords; }

    //Inserting a word into the repo which inserts it into the db
    //Called from the MainActivity in onActivityResult and used the Repository insert to
    // insert into the DB Room
    public void insert(Word word) { mRepository.insert(word); }

    //Calling the deleteAll in the WordRepo to Asynchronously call deleteAll for the Dao
    public void deleteAll() { mRepository.deleteAll(); }

    //Calling deleteWord in the WordRepo to Asynchronously call deleteWord for the Dao
    public void deleteWord(Word word) { mRepository.deleteWord(word); }

    //Calling updateWord in the WordRepo to Asynchronously call updateWord for the Dao
    public void updateWord(Word word) {mRepository.updateWord(word); }
}
