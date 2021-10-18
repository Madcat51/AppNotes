package site.madcat.appnotes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import site.madcat.appnotes.R;

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