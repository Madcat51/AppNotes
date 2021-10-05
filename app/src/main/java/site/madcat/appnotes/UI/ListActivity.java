package site.madcat.appnotes.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;

import site.madcat.appnotes.R;
import site.madcat.appnotes.domain.Note;
import site.madcat.appnotes.domain.NoteRepoImpl;
import site.madcat.appnotes.domain.NotesRepo;

public class ListActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private NotesAdapter adapter = new NotesAdapter();
    private NotesRepo repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initialsView();
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
                openEditActivity(null);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openEditActivity(@Nullable Note item) {
        Intent editIntant = new Intent(this, EditActivity.class);
        if (item != null) {
            editIntant.putExtra(Note.class.getSimpleName(), (Serializable) item);
        }
        startActivityForResult(editIntant, 1);
    }

    private void initRecyclerView() {
        repository = new NoteRepoImpl();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        fillRecyclerView();
        adapter.setData(repository.getNotes());
        adapter.setOnItemClickListener(this::onItemClick);
    }

    private void onItemClick(Note item) {
        openEditActivity(item);
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initialsView() {
        initToolbar();
        initRecyclerView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            Note note = (Note) data.getSerializableExtra(Note.class.getSimpleName());
            repository.addNote(new Note(note.getTitle(), note.getNoteBody()));
            adapter.setData(repository.getNotes());
        }
    }

    private void fillRecyclerView() {
        repository.addNote(new Note("заметка 1", "текст заметки1"));
        repository.addNote(new Note("заметка 2", "текст заметки2 лалалалала лалалалал лалалал лалалалала лалалалал лалалал лалалалала лалалалал лалалал лалалалала лалалалал лалалал лалалалала лалалалал лалалал"));
        repository.addNote(new Note("заметка 3", "текст заметки3"));
        repository.addNote(new Note("заметка 4", "текст заметки4"));
    }


}