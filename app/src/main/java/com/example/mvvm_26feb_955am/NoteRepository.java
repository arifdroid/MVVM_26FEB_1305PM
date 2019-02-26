package com.example.mvvm_26feb_955am;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;

    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {

        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);

        this.noteDao = noteDatabase.noteDao();
        this.allNotes = noteDao.getAllNotes();
    }

    //database operation SCRUD called here, will be run by room library
    //the thing is
    //database operation need to run on background thread to avoid performance issue
    //but room will not run it for us, this we have to define it ourself with aysnctask
    //( except for live data background operation )
    // DATABASE OPERATION DETAILED HERE.>>

    //this is from dao interface,, THIS IS THE API BASICALLY.

    public void insert(Note note){

        //this we call constructor below, at asynctask level
        new InsertNoteAysnctask(noteDao).execute(note);
    }

    public void update(Note note){
        new UpdateNoteAsyctask(noteDao).execute(note); //execute in the background function, hence the argument we pass
    }

    public void delete(Note note){
        new DeleteNoteAsynctask(noteDao).execute(note);
    }

    public void deleteAllNote(){
        new DeleteAllAsynctask(noteDao).execute();
    }


    //THIS IS LIVE DATA, where background task is automatically operated in room library


    public LiveData<List<Note>> getAllNotes() { //pre defined by room library
        return allNotes;
    }


    //// HERE WE DEFINE OUR ASYNCTASK

    //inner private static class

    private static class InsertNoteAysnctask extends AsyncTask<Note,Void,Void>{

        //field of static class
        private NoteDao noteDao;


        private InsertNoteAysnctask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.insert(notes[0]);

            return null;

        }
    }

    // this is asynctask for update

    private static class UpdateNoteAsyctask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        private UpdateNoteAsyctask(NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.update(notes[0]);
            return null;
        }
    }

    //aysnctask for delete

    private static class DeleteNoteAsynctask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        private DeleteNoteAsynctask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.delete(notes[0]);
            return null;
        }
    }

    //// asynctask for deleteaaaaaaalllll

    private static class DeleteAllAsynctask extends AsyncTask<Void,Void,Void>{

        private NoteDao noteDao;

        private DeleteAllAsynctask(NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {

           noteDao.deleteAllNote();
            return null;
        }
    }

}
