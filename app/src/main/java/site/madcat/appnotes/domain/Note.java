package site.madcat.appnotes.domain;

import java.io.Serializable;

public class Note implements Serializable {
    private int id;
    private String title;
    private String noteDetail;

    public Note(String title, String noteDetail) {
        this.id = id;
        this.title = title;
        this.noteDetail = noteDetail;

    }


    public String getTitle() {
        return title;
    }

    public String getNoteDetail() {
        return noteDetail;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setNoteDetail(String noteDetail) {
        this.noteDetail = noteDetail;
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
