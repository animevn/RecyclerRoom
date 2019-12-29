package com.haanhgs.recyclerroomdemo;

import android.app.Application;
import android.os.AsyncTask;
import java.util.List;
import androidx.lifecycle.LiveData;

public class WordRepo {

    private final WordDao dao;
    private final LiveData<List<Word>> allData;

    public WordRepo(Application application){
        WordRoom wordRoom = WordRoom.init(application);
        dao = wordRoom.wordDao();
        allData = dao.getAll();
    }

    public LiveData<List<Word>> getAllData() {
        return allData;
    }

    private static class InsertAsync extends AsyncTask<Word, Void, Void>{
        private final WordDao dao;

        public InsertAsync(WordDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            dao.insert(words[0]);
            return null;
        }
    }

    public void insert(Word word){
        new InsertAsync(dao).execute(word);
    }

    public void insert(String string){
        insert(new Word(0, string));
    }

    private static class DeleteAsync extends AsyncTask<Word, Void, Void>{
        private final WordDao dao;

        public DeleteAsync(WordDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            dao.delete(words[0]);
            return null;
        }
    }

    public void delete(Word word){
        new DeleteAsync(dao).execute(word);
    }

    private static class DeleteAllAsync extends AsyncTask<Void, Void, Void>{
        private final WordDao dao;

        public DeleteAllAsync(WordDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();
            return null;
        }
    }

    public void deleteAll(){
        new DeleteAllAsync(dao).execute();
    }
}
