package com.haanhgs.recyclerroomdemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class NewActivity extends AppCompatActivity {

    public static final String REPLY = "new";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

    }
}
