package com.haanhgs.recyclerroomdemo.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int ID;

    @NonNull
    @ColumnInfo(name = "string")
    private String string;


    public Word(int ID, @NonNull String string) {
        this.ID = ID;
        this.string = string;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @NonNull
    public String getString() {
        return string;
    }

    public void setString(@NonNull String string) {
        this.string = string;
    }
}
