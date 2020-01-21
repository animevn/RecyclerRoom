package com.haanhgs.recyclerroomdemo.view;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.haanhgs.recyclerroomdemo.R;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

    private void initActionBar(){
        setSupportActionBar(tbrMain);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setTitle("Word Room Demo");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initActionBar();
    }

    @OnClick(R.id.fbnMain)
    public void onViewClicked(){
    }

}
