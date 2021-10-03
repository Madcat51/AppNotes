package site.madcat.appnotes.domain;

public class Note {
    private int id;
    private String title;
    private String noteBody;

    public Note( String title, String noteBody) {

        this.title = title;
        this.noteBody = noteBody;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoteBody() {
        return noteBody;
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
