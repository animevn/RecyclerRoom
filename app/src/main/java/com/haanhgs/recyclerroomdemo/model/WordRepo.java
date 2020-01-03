package com.haanhgs.recyclerroomdemo.model;

import android.app.Application;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import androidx.lifecycle.LiveData;

public class WordRepo {

    private static final Executor executor = Executors.newSingleThreadExecutor();
    private final WordDao listener;
    private final LiveData<List<Word>> allData;

    public WordRepo(Application application){
        WordRoom wordRoom = WordRoom.init(application);
        listener = wordRoom.getDao();
        allData = listener.getAllWords();
    }

    public LiveData<List<Word>> getAllData() {
        return allData;
    }

    private static void insertWord(WordDao listener, Word word){
        executor.execute(() -> listener.insert(word));
    }

    public void insert(Word word){
        insertWord(listener, word);
    }

    public void insert(String string){
        insert(new Word(0, string));
    }

    private static void updateWord(WordDao listener, Word word){
        executor.execute(()-> listener.update(word));
    }

    public void update(Word word){
        updateWord(listener, word);
    }

    private static void deleteWord(WordDao listener, Word word){
        executor.execute(()-> listener.delete(word));
    }

    public void delete(Word word){
        deleteWord(listener, word);
    }

    private static void deleteAll(WordDao listener){
        executor.execute(listener::deleteAll);
    }

    public void deleteAll(){
        deleteAll(listener);
    }
}
