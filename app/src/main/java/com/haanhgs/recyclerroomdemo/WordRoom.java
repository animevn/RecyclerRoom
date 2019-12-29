package com.haanhgs.recyclerroomdemo;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoom extends RoomDatabase {

    private static WordRoom instance;
    public abstract WordDao wordDao();

    private static class InitDatabaseAsync extends AsyncTask<Void, Void, Void>{
        private final WordDao wordDao;
        private final String[]strings = {"alpha", "beta", "gamma"};

        InitDatabaseAsync(WordRoom wordRoom) {
            wordDao = wordRoom.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (wordDao.getAny().length == 0){
                for (String string : strings) {
                    wordDao.insert(new Word(0, string));
                }
            }
            return null;
        }
    }

    private static final RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new InitDatabaseAsync(instance).execute();
        }
    };

    public static WordRoom init(final Context context){
        if (instance == null){
            synchronized (WordRoom.class){
                if (instance == null){
                    Context appContext = context.getApplicationContext();
                    instance = Room.databaseBuilder(appContext, WordRoom.class, "word_db")
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}
