package com.sixamigos.sjsucanvasapp.helpscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sixamigos.sjsucanvasapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HelpScreen extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_screen);
        text = (TextView)findViewById(R.id.text);
        try {
            InputStream in = getAssets().open("FAQ.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line;
            StringBuilder buff = new StringBuilder();
            while((line = reader.readLine()) != null)
                buff.append(line + "\n");
            text.setText(buff);
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
