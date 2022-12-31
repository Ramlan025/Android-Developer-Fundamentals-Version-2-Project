package id.ac.poliban.mi.ramlan.roomwordssample_101a; /**
 *                                              Dao (Data Access Object)
 *
 * The data access object, or Dao, is an annotated class where you specify SQL queries and associate them with method calls.
 * The compiler checks the SQL for errors, then generates queries from the annotations. For common queries, the libraries
 * provide convenience annotations such as @Insert.
 * **/


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao //Identifies the class as a DAO
public interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll(); //deletes all words

    //List of words wrapped in a LiveData class to help our app respond to data changes
    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords(); //gets all words

    //Used to get one word from the db to see if it is empty or not
    //Does not needed to be LiveData because we are calling it explicitly
    @Query("SELECT * from word_table LIMIT 1")
    Word[] getAnyWord();

    //Delete deletes a single row in db
    @Delete
    void deleteWord(Word word);

    @Update
    void update(Word... word);
}

