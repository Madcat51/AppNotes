package site.madcat.appnotes.domain;

import java.util.ArrayList;
import java.util.List;

public class NoteRepoImpl implements NotesRepo {
    public ArrayList<Note> cash = new ArrayList<>();
    private int counter = 0;


    @Override
    public List<Note> getNotes() {
        return new ArrayList<>(cash);

    }

    @Override
    public int addNote(Note note) {
        int newId=++counter;
        note.setID(newId);
        cash.add(note);
        return newId;
    }

    @Override
    public boolean editNote(int id,Note note) {
        deleteNote(id);
        note.setId(id);
        cash.add(note);
        return true;

    }

    @Override
    public boolean deleteNote(int id) {
        for (int i=0;i<cash.size();i++){

            if (cash.get(i).getId()==id){
                cash.remove(i);
                return true;
            }
        }
        return false;
    }






}
