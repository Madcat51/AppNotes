package site.madcat.appnotes.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import site.madcat.appnotes.R;
import site.madcat.appnotes.domain.Note;
import site.madcat.appnotes.domain.NoteRepoImpl;
import site.madcat.appnotes.domain.NotesRepo;

public class ListActivity extends AppCompatActivity {
    private Toolbar toolbar;
private NotesRepo repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        repository =new NoteRepoImpl();
        initialsView();
        setSupportActionBar(toolbar);

        repository.addNote(new Note("заметка 1","текст заметки"));
        repository.addNote(new Note("заметка 2","текст заметки"));
        repository.addNote(new Note("заметка 3","текст заметки"));
        repository.addNote(new Note("заметка 4","текст заметки"));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_note: {
                openEditActivity();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openEditActivity() {
        Intent editIntant = new Intent(this, EditActivity.class);
        // editIntant.putExtra(, );
        startActivityForResult(editIntant, 1);

    }

    private void initialsView() {
        toolbar = findViewById(R.id.toolbar);
    }
}