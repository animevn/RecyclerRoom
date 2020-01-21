package com.haanhgs.recyclerroomdemo.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import com.haanhgs.recyclerroomdemo.R;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewActivity extends AppCompatActivity {


    @BindView(R.id.tbrNew)
    Toolbar tbrNew;
    @BindView(R.id.etNew)
    EditText etNew;
    @BindView(R.id.bnNew)
    Button bnNew;

    private void initToolBar(){
        setSupportActionBar(tbrNew);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("New Word");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        ButterKnife.bind(this);
        initToolBar();
    }
}
