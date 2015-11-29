package com.sixamigos.sjsucanvasapp.login.helpscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sixamigos.sjsucanvasapp.R;
import com.sixamigos.sjsucanvasapp.login.canvas.LoginWebViewActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Khoa Vo on 11/20/15.
 */

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
        Intent i = new Intent(HelpScreen.this, LoginWebViewActivity.class);
        startActivity(i);
        finish();
    }
}
