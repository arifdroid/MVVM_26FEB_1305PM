package com.example.mvvm_26feb_955am;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {  //android view model, provide us the context

    private NoteRepository noteRepository;

    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }

    //operation connect repository with view model

    public void insert(Note note){

            noteRepository.insert(note);
    }

    public void update(Note note){

            noteRepository.update(note);
    }

    public void delete(Note note){
        noteRepository.delete(note);
    }

    public void deleteAllNotes(){
        noteRepository.deleteAllNote();
    }

    public LiveData<List<Note>> getAllNotes(){
            return allNotes;
    }

}
