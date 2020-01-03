package com.haanhgs.recyclerroomdemo.viewmodel;

import android.app.Application;
import com.haanhgs.recyclerroomdemo.model.Word;
import com.haanhgs.recyclerroomdemo.model.WordRepo;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class WordViewModel extends AndroidViewModel {

    private final WordRepo repo;
    private final LiveData<List<Word>> allData;

    public WordViewModel(@NonNull Application application) {
        super(application);
        repo = new WordRepo(application);
        allData = repo.getAllData();
    }

    public LiveData<List<Word>> getAllData() {
        return allData;
    }

    public void delete(Word word){
        repo.delete(word);
    }

    public void deleteAll(){
        repo.deleteAll();
    }

    public void insert(Word word){
        repo.insert(word);
    }

    public void insert(String string){
        repo.insert(string);
    }

    public void update(Word word){
        repo.update(word);
    }


}
