package com.haanhgs.recyclerroomdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST = 1979;
    private ViewModel viewModel;
    private Adapter adapter;
    private RecyclerView rvMain;
    private List<Word> words;

    private void updateActionBar(){
        Toolbar tbrMain = findViewById(R.id.tbrMain);
        setSupportActionBar(tbrMain);
    }

    private void updateFloatButton(){
        FloatingActionButton fbnMain = findViewById(R.id.fbnMain);
        fbnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                startActivityForResult(intent, REQUEST);
            }
        });
    }

    private void initViewModel(){
        adapter = new Adapter();
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getAllData().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                adapter.setWords(words);
                MainActivity.this.words = words;
            }
        });
    }

    private void initRecyclerView(){
        rvMain = findViewById(R.id.rvContent);
        rvMain.setLayoutManager(new GridLayoutManager(this, 1));
        rvMain.setItemAnimator(new DefaultItemAnimator());
        rvMain.setAdapter(adapter);
    }

    private void enableSwipe(){
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback
        (0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
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
        updateActionBar();
        updateFloatButton();

        initViewModel();
        initRecyclerView();
        enableSwipe();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST && resultCode == RESULT_OK && data != null){
            viewModel.insert(data.getStringExtra(NewActivity.getREPLY()));
        }

    }
}
