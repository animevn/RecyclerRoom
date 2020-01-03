package com.haanhgs.recyclerroomdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.haanhgs.recyclerroomdemo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {

    public static final String REPLY = "new";
    @BindView(R.id.etNew)
    EditText etWord;
    @BindView(R.id.bnNew)
    Button bnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent.getStringExtra("edit") != null){
            etWord.setText(intent.getStringExtra("edit"));
        }
    }

    private void handleButtonSave(){
        Intent intent = new Intent();
        if (TextUtils.isEmpty(etWord.getText())){
            setResult(RESULT_CANCELED, intent);
        }else {
            setResult(RESULT_OK, intent);
            intent.putExtra(REPLY, etWord.getText().toString());
        }
        finish();
    }

    @OnClick(R.id.bnNew)
    public void onViewClicked() {
        handleButtonSave();
    }
}
