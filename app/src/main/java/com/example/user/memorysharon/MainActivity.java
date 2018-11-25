package com.example.user.memorysharon;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText et;
    TextView tv;
    Button saveButton, readButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);
        saveButton = findViewById(R.id.saveButton);
        tv = findViewById(R.id.tv);
        readButton = findViewById(R.id.readButton);
    }

    public void saveData(View view) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("Data", Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);
        try {
            bw.write(et.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData(View view) throws IOException {
        InputStream is = null;
        try {
            is = openFileInput("Data");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String data;
        StringBuffer buffer = new StringBuffer();
        while ((data = br.readLine()) != null) {
            buffer.append(data + "\n");
            tv.setText(data);
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}