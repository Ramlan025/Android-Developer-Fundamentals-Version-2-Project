package id.ac.poliban.mi.ramlan.roomwordssample_101a;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 *                                          Repository Class
 *
 * A Repository is a class that abstracts access to multiple data sources. The Repository is not part of the Architecture Components
 * libraries, but is a suggested best practice for code separation and architecture. A Repository class handles data operations. It
 * provides a clean API to the rest of the app for app data.
 *
 * A Repository manages query threads and allows you to use multiple backends. In the most common example, the Repository implements
 * the logic for deciding whether to fetch data from a network or use results cached in the local database.
 *
 * **/

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;


    //Constructor that gets a handle to the database and initializes the member variables
    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    //Wrapper to return the words as LiveData which basically notifies the MainActivity (Observer) when the words have been changed and the UI is updated
    LiveData<List<Word>> getAllWords()
    {
        return mAllWords;
    }

    //Wrapper for the insert method called from the WordViewModel which uses the WordDao
    public void insert(Word word) {/*Have to use an AsnycTask to call insert so it will be on a non-UI thread or the app will crash*/new insertAsyncTask(mWordDao).execute(word);}

    //Invokes the AsyncTask deleteAll below which will be called from the ViewModel
    public void deleteAll()
    {
        new deleteAllWordsAsyncTask(mWordDao).execute();
    }

    public void deleteWord(Word word) { new deleteWordAsyncTask(mWordDao).execute(word);}

    public void updateWord(Word word) { new updateWordAsyncTask(mWordDao).execute(word);}

    //AsyncTask<Parameter Type, Type to publish progress, Result type>
    private static class insertAsyncTask extends AsyncTask<Word, Void, Void>
    {
        private WordDao mAsyncTaskDao;

        //Constructor for the AsyncTask. Passed in a id.ac.poliban.mi.ramlan.roomwordssample_101a.
        // WordDao to call the insert method
        insertAsyncTask(WordDao dao) { mAsyncTaskDao = dao;}

        //Calling insert on another thread
        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    //AsyncTask to call the Dao's deleteAll() method
    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private WordDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(WordDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    //AsyncTask to call the Dao's @Delete query
    private static class deleteWordAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        deleteWordAsyncTask(WordDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params)
        {
            mAsyncTaskDao.deleteWord(params[0]);
            return null;
        }
    }

    //AsyncTask to call the Dao's @Update query
    private static class updateWordAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        updateWordAsyncTask(WordDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
}
