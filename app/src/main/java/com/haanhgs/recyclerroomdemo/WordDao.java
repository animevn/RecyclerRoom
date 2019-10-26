package com.haanhgs.recyclerroomdemo;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Delete
    void delete(Word word);

    @Query("delete from word_table")
    void deleteAll();

    @Query("select * from word_table limit 1")
    Word[] getAny();

    @Query("select * from word_table order by string asc")
    LiveData<List<Word>> getAll();

}
