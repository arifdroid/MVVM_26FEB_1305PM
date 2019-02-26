package com.example.mvvm_26feb_955am;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// 1st. Object class to access data, the ENTITY
// 2nd create A DAO, data access object
// 3rd , create abstract class that extend RoomDatabase
//, this class will tie together entity and DAO.

//4th, now we create repository, repository not part of MVVM but
// it is a good architecture, since sometimes database not just come from,
//sqlite room, it can be from firebase,firestore online, that will be chached

//5th them we set the VIEW MODEL CLASSS babbayyyy

//6th then connect VIEW MODEL to MAIN ACTIVITY

public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //we cant simply create new NoteViewModel. instead, ask android system

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

    }
}
