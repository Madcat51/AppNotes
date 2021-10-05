package site.madcat.appnotes.domain;

import java.io.Serializable;

public class Note implements Serializable {
    private int id;
    private String title;
    private String noteBody;

    public Note(String title, String noteBody) {
        this.id = id;
        this.title = title;
        this.noteBody = noteBody;

    }


    public String getTitle() {
        return title;
    }

    public String getNoteBody() {
        return noteBody;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }


    public void setID(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


}
