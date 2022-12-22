package com.example.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText taskText;
    Button write_btn;
    TextView taskListText;
    ArrayList<String> tasks;
    HashSet<String> againTasks;
    String TASKSNAME;

    public TestFragment(ArrayList<String> s, HashSet<String> h,String txt) {
        tasks = s;
        againTasks = h;
        TASKSNAME = txt;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestFragment newInstance(String param1, String param2) {
        TestFragment fragment = new TestFragment(new ArrayList<>(), new HashSet<>(),"tasks.txt");
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_test, container, false);
        taskText = v.findViewById(R.id.editTask);
        write_btn = v.findViewById(R.id.writeTask);
        taskListText = v.findViewById(R.id.TaskList);
        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!taskText.getText().toString().equals("")) {
                    taskListText.append(taskText.getText());
                    if (tasks.contains(taskText.getText().toString())) {
                        againTasks.add(taskText.getText().toString());
                    }
                    tasks.add(taskText.getText().toString());
                    try {
                        FileOutputStream stream = getActivity().openFileOutput(TASKSNAME, Context.MODE_APPEND);
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(stream));
                        bw.write(taskText.getText().toString());
                        bw.write("\n");
                        bw.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        return v;
    }
}