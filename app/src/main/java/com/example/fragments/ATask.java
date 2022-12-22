package com.example.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ATask#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ATask extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    HashSet<String> againTasks;
    TextView list;

    public ATask(HashSet<String> e) {
        againTasks = e;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ATask.
     */
    // TODO: Rename and change types and number of parameters
    public static ATask newInstance(String param1, String param2) {
        ATask fragment = new ATask(new HashSet<>());
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
        View v = inflater.inflate(R.layout.fragment_a_task, container, false);
        list = v.findViewById(R.id.again_list_task);
        Iterator<String> iterator = againTasks.iterator();
        while(iterator.hasNext()) {
            String tmp = iterator.next() + "\n";
            list.append(tmp);
        }
        return v;
    }
}