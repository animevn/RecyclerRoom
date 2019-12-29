package com.haanhgs.recyclerroomdemo;

import android.app.Application;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ViewModel extends AndroidViewModel {

    private final WordRepo repo;
    private final LiveData<List<Word>> allData;

    public ViewModel(@NonNull Application application) {
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



}
