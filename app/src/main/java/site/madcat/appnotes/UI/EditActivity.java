package site.madcat.appnotes.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

import site.madcat.appnotes.R;
import site.madcat.appnotes.domain.Note;
import site.madcat.appnotes.domain.NoteRepoImpl;
import site.madcat.appnotes.domain.NotesRepo;

public class EditActivity extends AppCompatActivity {

    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Bundle arguments = getIntent().getExtras();
       /* if (arguments != null) {
            Note note;
            note = (Note) arguments.getSerializable(Note.class.getSimpleName());
            titleEditText.setText(note.getTitle());
            bodyEditText.setText(note.getNoteBody());
            id = note.getId();
        } else {
            newRecord = true;
        }

        saveChangeButton.setOnClickListener(view -> {
            Intent intent = new Intent();
            if (newRecord == true) {
                Note note = new Note(
                        titleEditText.getText().toString(),
                        bodyEditText.getText().toString()
                );
                intent.putExtra(Note.class.getSimpleName(), (Serializable) note);
                setResult(RESULT_OK, intent);
            }
            finish();
        });*/
    }



}