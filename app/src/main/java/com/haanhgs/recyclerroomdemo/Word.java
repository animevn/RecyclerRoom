package com.haanhgs.recyclerroomdemo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "string")
    @NonNull
    private String string;

    public Word(int ID, @NonNull String string) {
        this.ID = ID;
        this.string = string;
    }

    @NonNull
    public String getString() {
        return string;
    }

    public void setString(@NonNull String string) {
        this.string = string;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
