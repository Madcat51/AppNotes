package site.madcat.appnotes.domain;

import java.io.Serializable;
import java.util.List;

public interface NotesRepo extends Serializable {
    List<Note> getNotes();
    int addNote(Note note);
   boolean editNote (int id,Note note);
    boolean deleteNote(int id);
}
