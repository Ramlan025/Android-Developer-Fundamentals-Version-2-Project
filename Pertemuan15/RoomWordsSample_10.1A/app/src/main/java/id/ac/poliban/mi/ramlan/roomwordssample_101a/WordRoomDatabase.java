package id.ac.poliban.mi.ramlan.roomwordssample_101a;
/**
 *                                                                      RoomDatabase
 *
 * Room is a database layer on top of an SQLite database. Room takes care of mundane tasks that you used to handle with a database helper class such as SQLiteOpenHelper.
 **/


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//Annotating to be a RoomDatabase
//exportSchema to false since we are not keeping a history of schema versions (For migrating to databases)
@Database(entities = {Word.class}, version = 2, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase
{
    public abstract WordDao wordDao();

    //Used as a singleton so there can not be multiple instances of the database opened
    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context)
    {
        //If database instance is null then create it
        if (INSTANCE == null)
        {
            synchronized (WordRoomDatabase.class)
            {
                if (INSTANCE == null)
                {
                    //Using Room's database builder to create the RoomDatabase object with the name word_database
                    //Creating the actual database
                    //Wipes and rebuilds instead of migrating
                    //.fallbackToDestructiveMigration() - if no Migration object. Migration is not part of this practical
                    //.addCallback() adds the RoomDatabase.Callback to add a method for when the room db is built
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, "word_database").fallbackToDestructiveMigration().addCallback(sRoomDatabaseCallback).build();
                }
            }
        }

        //return if created or already exists
        return INSTANCE;
    }


    //Deleting all content and repopulating the database for when the app is started
    private static Callback sRoomDatabaseCallback =
            new Callback()
            {

                //Creating an AsyncTask to add the content to the database because it cannot be done on the UI thread
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db)
                {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>
    {
        //Need a Dao to delete and add words
        private final WordDao mDao;
        String[] words = {
                //"Dolphin", "Crocodle", "Cobra"
                };

        PopulateDbAsync(WordRoomDatabase db)
        {
            //Initializing the Dao so we can delete the words and add new ones
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params)
        {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            //mDao.deleteAll();

            //If getAnyWord() returns 0 then there are no words and we need to populate the database
            if(mDao.getAnyWord().length < 1)
            {
                for (int i = 0; i <= words.length - 1; i++)
                {
                    Word word = new Word(words[i]);
                    mDao.insert(word);
                }
            }
            return null;
        }
    }

}
