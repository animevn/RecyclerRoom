package com.haanhgs.recyclerroom.view;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.haanhgs.recyclerroom.R;
import com.haanhgs.recyclerroom.adapter.Adapter;
import com.haanhgs.recyclerroom.model.Word;
import com.haanhgs.recyclerroom.viewmodel.WordViewModel;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fbnMain)
    FloatingActionButton fbnMain;
    @BindView(R.id.tbrMain)
    Toolbar tbrMain;
    @BindView(R.id.rvContent)
    RecyclerView rvMain;

    private WordViewModel viewModel;
    private List<Word> wordList;

    public static final String EDIT = "edit";
    private static final int REQUEST_NEW = 1979;
    private static final int REQUEST_EDIT = 2204;
    private Adapter adapter;
    private Word chosenWord;

    private void initActionBar(){
        setSupportActionBar(tbrMain);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setTitle("Word Room Demo");
    }

    private void initViewModel(){
        adapter = new Adapter();
        viewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        viewModel.getGetAllWords().observe(this, words -> {
            adapter.setGetAllWords(words);
            wordList = words;
            adapter.notifyDataSetChanged();
        });
    }

    private void initRecyclerView(){
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.setItemAnimator(new DefaultItemAnimator());
        rvMain.setAdapter(adapter);
    }

    private void enableSwipe(){
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(adapter.getWordAtPosition(viewHolder.getAdapterPosition()));
            }
        });
        helper.attachToRecyclerView(rvMain);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initActionBar();
        initViewModel();
        initRecyclerView();
        enableSwipe();
    }

    private void newWord(){
        startActivityForResult(new Intent(this, NewActivity.class), REQUEST_NEW);
    }

    @OnClick(R.id.fbnMain)
    public void onViewClicked(){
        newWord();
    }

    public void editWord(Word word){
        chosenWord = word;
        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtra(EDIT, chosenWord.getString());
        startActivityForResult(intent, REQUEST_EDIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_NEW && resultCode == RESULT_OK && data != null){
            viewModel.insert(data.getStringExtra(NewActivity.REPLY));
        }
        if (requestCode == REQUEST_EDIT && resultCode == RESULT_OK && data != null){
            String string = data.getStringExtra(NewActivity.REPLY);
            if (string != null)
            chosenWord.setString(string);
            viewModel.update(chosenWord);
        }
    }
}
