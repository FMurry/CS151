package com.sixamigos.sjsucanvasapp.helpscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sixamigos.sjsucanvasapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HelpScreen extends AppCompatActivity {

    @Bind(R.id.text) TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_screen);
        ButterKnife.bind(this);

        try {
            InputStream in = getAssets().open("FAQ.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line;
            StringBuilder buff = new StringBuilder();
            while((line = reader.readLine()) != null)
                buff.append(line + "\n");
            mTextView.setText(buff);
            in.close();
            reader.close();

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
