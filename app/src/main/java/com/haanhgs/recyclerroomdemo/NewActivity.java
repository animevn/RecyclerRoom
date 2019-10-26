package com.haanhgs.recyclerroomdemo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NewActivity extends AppCompatActivity {

    private static final String REPLY = "new";

    public static String getREPLY() {
        return REPLY;
    }

    private void updateActionBar(){
        Toolbar tbrNew = findViewById(R.id.tbrNew);
        setSupportActionBar(tbrNew);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        updateActionBar();

        final EditText etNew = findViewById(R.id.etNew);
        Button bnNew = findViewById(R.id.bnNew);
        bnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (TextUtils.isEmpty(etNew.getText())){
                    setResult(RESULT_CANCELED, intent);
                }else {
                    setResult(RESULT_OK, intent);
                    intent.putExtra(REPLY, etNew.getText().toString());
                }
                finish();
            }
        });

    }
}
