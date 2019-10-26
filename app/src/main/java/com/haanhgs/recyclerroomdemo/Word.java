package com.haanhgs.recyclerroomdemo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "word")
    @NonNull
    private String word;

    public Word(int ID, @NonNull String word) {
        this.ID = ID;
        this.word = word;
    }

    @NonNull
    public String getWord() {
        return word;
    }

    public void setWord(@NonNull String word) {
        this.word = word;
    }
}
