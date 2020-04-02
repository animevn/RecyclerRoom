package com.haanhgs.recyclerroom.viewmodel;

import android.app.Application;
import com.haanhgs.recyclerroom.model.Word;
import com.haanhgs.recyclerroom.model.WordRepo;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class WordViewModel extends AndroidViewModel {

    private final WordRepo repo;
    private final LiveData<List<Word>> getAllWords;

    public WordViewModel(@NonNull Application application) {
        super(application);
        repo = new WordRepo(application);
        getAllWords = repo.getGetAllWords();
    }

    public LiveData<List<Word>> getGetAllWords() {
        return getAllWords;
    }

    public void insert(Word word){
        repo.insert(word);
    }

    public void insert(String string){
        repo.insert(string);
    }

    public void update(Word word){
        repo.update(word );
    }

    public void delete(Word word){
        repo.delete(word);
    }

    public void deleteAll(){
        repo.deleteAll();
    }


}
