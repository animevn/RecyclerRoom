package com.haanhgs.recyclerroom.model;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Update
    void update(Word word);

    @Delete
    void delete(Word word);

    @Query("delete from word_table")
    void deleteAll();

    @Query("select * from word_table limit 1")
    Word[] getAnyWord();

    @Query("select * from word_table order by string asc")
    LiveData<List<Word>> getAllWords();

}
