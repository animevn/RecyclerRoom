package com.haanhgs.recyclerroomdemo.model;

import android.app.Application;
import com.google.android.gms.tasks.Tasks;
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

    @SuppressWarnings("SameReturnValue")
    private static void insertWord(WordDao listener, Word word){
        Tasks.call(executor, () -> {
            listener.insert(word);
            return null;
        });
    }

    public void insert(Word word){
        insertWord(listener, word);
    }

    public void insert(String string){
        insert(new Word(0, string));
    }

    @SuppressWarnings("SameReturnValue")
    private static void updateWord(WordDao listener, Word word){
        Tasks.call(executor, () -> {
            listener.update(word);
            return null;
        });
    }

    public void update(Word word){
        updateWord(listener, word);
    }

    @SuppressWarnings("SameReturnValue")
    private static void deleteWord(WordDao listener, Word word){
        Tasks.call(executor, ()->{
            listener.delete(word);
            return null;
        });
    }

    public void delete(Word word){
        deleteWord(listener, word);
    }

    @SuppressWarnings("SameReturnValue")
    private static void deleteAll(WordDao listener){
        Tasks.call(executor, ()->{
            listener.deleteAll();
            return null;
        });
    }

    public void deleteAll(){
        deleteAll(listener);
    }
}
