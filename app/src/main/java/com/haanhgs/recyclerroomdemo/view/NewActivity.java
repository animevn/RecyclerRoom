package com.haanhgs.recyclerroomdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import com.haanhgs.recyclerroomdemo.R;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewActivity extends AppCompatActivity {

    @BindView(R.id.tbrNew)
    Toolbar tbrNew;
    @BindView(R.id.etNew)
    EditText etNew;
    @BindView(R.id.bnNew)
    Button bnNew;

    public static final String REPLY = "reply";

    private void initToolBar() {
        setSupportActionBar(tbrNew);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("New Word");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent.getStringExtra(MainActivity.EDIT) != null) {
            etNew.setText(intent.getStringExtra(MainActivity.EDIT));
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        ButterKnife.bind(this);
        initToolBar();
        handleIntent();
    }

    private void handleButtonClicked(){
        Intent intent = new Intent();
        if (!TextUtils.isEmpty(etNew.getText())){
            intent.putExtra(REPLY, etNew.getText().toString());
            setResult(RESULT_OK, intent);
        }else {
            setResult(RESULT_CANCELED, intent);
        }
        finish();
    }

    @OnClick(R.id.bnNew)
    public void onViewClicked() {
        handleButtonClicked();
    }
}
