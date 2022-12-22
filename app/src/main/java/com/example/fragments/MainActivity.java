package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    Button switch_btn, end_btn,again_btn;
    FragmentManager fm;
    FragmentTransaction ft;
    TestFragment ctf;
    FinalTask comtf;
    ATask againtf;

    ArrayList<String> tasks = new ArrayList<>();
    HashSet<String> againTasks = new HashSet<>();

    final String TASKSNAME = "tasks.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switch_btn = findViewById(R.id.switch_btn);
        end_btn = findViewById(R.id.end_btn);
        again_btn = findViewById(R.id.again_btn);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(TASKSNAME)));
            String s = br.readLine();
            while (s!=null){
                tasks.add(s);
                againTasks.add(s);
                s=br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ctf = new TestFragment(tasks,againTasks, TASKSNAME);
        comtf = new FinalTask(tasks);
        againtf = new ATask(againTasks);
        ft.add(R.id.container_task,ctf);
        ft.commit();
        switch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft = fm.beginTransaction();
                ft.replace(R.id.container_task,ctf);
                ft.commit();
            }
        });
        end_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft = fm.beginTransaction();
                ft.replace(R.id.container_task,comtf);
                ft.commit();
            }
        });
        again_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft = fm.beginTransaction();
                ft.replace(R.id.container_task,againtf);
                ft.commit();
            }
        });
    }
}