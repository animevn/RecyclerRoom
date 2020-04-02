package com.haanhgs.recyclerroom.model;

import android.app.Application;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import androidx.lifecycle.LiveData;

public class WordRepo {

    private final WordDao listener;
    private final LiveData<List<Word>> getAllWords;
    private static final Executor executor = Executors.newSingleThreadExecutor();

    public WordRepo(Application application){
        WordRoom wordRoom = WordRoom.init(application);
        listener = wordRoom.getListener();
        getAllWords = listener.getAllWords();
    }

    public LiveData<List<Word>> getGetAllWords() {
        return getAllWords;
    }

    public void insert(Word word){
        executor.execute(() -> listener.insert(word));
    }

    public void insert(String string){
        executor.execute(()->listener.insert(new Word(0, string)));
    }

    public void update(Word word){
        executor.execute(()->listener.update(word));
    }

    public void delete(Word word){
        executor.execute(()->listener.delete(word));
    }

    public void deleteAll(){
        executor.execute(listener::deleteAll);
    }
}
