package com.haanhgs.recyclerroomdemo.model;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoom extends RoomDatabase {

    private static final String[]strings = {"alpha", "beta", "gamma"};
    private static final Executor executor = Executors.newSingleThreadExecutor();
    private static WordRoom instance;
    public abstract WordDao getListener();

    private static void initWordList(WordRoom wordRoom){
        executor.execute(() -> {
            WordDao listener = wordRoom.getListener();
            if (listener.getAnyWord().length == 0){
                for (String string:strings) listener.insert(new Word(0, string));
            }
        });
    }

    private static final RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            initWordList(instance);
        }
    };

    public static WordRoom init(Context context){
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
