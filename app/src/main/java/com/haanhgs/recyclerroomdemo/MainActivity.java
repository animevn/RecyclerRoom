package com.haanhgs.recyclerroomdemo;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.haanhgs.recyclerroomdemo.model.Word;
import com.haanhgs.recyclerroomdemo.viewmodel.WordViewModel;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    private static final int REQUEST = 1979;
    private WordViewModel viewModel;
    private List<Word> wordList;
    private Adapter adapter;

    private void updateActionBar(){
        Toolbar tbrMain = findViewById(R.id.tbrMain);
        setSupportActionBar(tbrMain);
    }

    private void initViewModel(){
        adapter = new Adapter();
        viewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        viewModel.getAllData().observe(this, words -> {
            adapter.setWords(words);
            MainActivity.this.wordList = words;
        });
    }

    private void initRecyclerView(){
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.setItemAnimator(new DefaultItemAnimator());
        rvMain.setAdapter(adapter);
    }

    private void enableSwipe(){
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
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
        initViewModel();
        updateActionBar();
        initRecyclerView();
        enableSwipe();
    }

    private void createNewWord(){
        startActivityForResult(new Intent(this, DetailActivity.class), REQUEST);
    }

    @OnClick(R.id.fbnMain)
    public void onViewClicked(){
        createNewWord();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST && resultCode == RESULT_OK && data != null){
            viewModel.insert(data.getStringExtra(DetailActivity.REPLY));
        }
    }
}
