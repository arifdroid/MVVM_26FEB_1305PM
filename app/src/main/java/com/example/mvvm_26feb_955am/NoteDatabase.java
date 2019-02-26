package com.example.mvvm_26feb_955am;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

//3rd, this is abstract class that use to tie, entity and dao


//i think this is singleton

@Database(entities = {Note.class}, version = 1)

public abstract class NoteDatabase extends RoomDatabase {

    //i think this is perform by room library
    public abstract NoteDao noteDao(); //this abstract method/function of noteDao(), return noteDao

    private static NoteDatabase instance;   //this is field with

    //this method only work in one thread at a time to avoid conflict
    //this method, work as singleton to create an instance.
    //this method call itself

    public static synchronized NoteDatabase getInstance(Context context){

            if(instance==null){ ///if  not instance, create one
                instance = Room.databaseBuilder(context.getApplicationContext(),

                        NoteDatabase.class, "note_database")
                        .fallbackToDestructiveMigration()
                        .build();
    }

    return instance;
    }


}
