package com.haanhgs.recyclerroomdemo.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "string")
    @NonNull
    private String string;

    public Word(int id, @NonNull String string) {
        this.id = id;
        this.string = string;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getString() {
        return string;
    }

    public void setString(@NonNull String string) {
        this.string = string;
    }
}
