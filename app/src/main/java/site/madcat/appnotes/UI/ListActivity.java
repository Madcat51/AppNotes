package site.madcat.appnotes.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import java.io.Serializable;

import site.madcat.appnotes.R;
import site.madcat.appnotes.domain.Note;
import site.madcat.appnotes.domain.NoteRepoImpl;
import site.madcat.appnotes.domain.NotesRepo;

public class ListActivity extends AppCompatActivity implements ListFragment.Controller, EditNoteFragment.Controller {
    private Toolbar toolbar;
    public boolean newRecord = false;
    public NotesRepo repository;
    public ListFragment listFragment;
    public EditNoteFragment editNoteFragment = new EditNoteFragment();
    private static final String REPOKEY = "REPO_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        if (listFragment == null) {
            listFragment = new ListFragment();
        }


        repository = new NoteRepoImpl();
        initToolbar();
        loadList();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(REPOKEY, (Serializable) repository);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        repository = (NoteRepoImpl) savedInstanceState.getSerializable(REPOKEY);
    }

    public void loadList() {
        if (getScreenOrientation() == true) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, listFragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, listFragment).replace(R.id.note_detail_fragment, editNoteFragment).commit();
        }
    }

    public boolean getScreenOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return true;
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            return false;
        else
            return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void editNote(int id, String title, String detail) {
        repository.deleteNote(id);
        addNewNote(title, detail);
    }

    public void addNewNote(String title, String detail) {
        repository.addNote(new Note(title, detail));
        listFragment.refreshAdapter();
    }


    public void loadNote(Note note) {
        if (getScreenOrientation() == true) {
            moveToNotePortraitFragment(note);
        } else {
            moveToNoteLandFragment(note);
        }
    }

    private void moveToNotePortraitFragment(Note note) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, editNoteFragment.setInputArgumentsNoteFrames(note)).addToBackStack(null).commit();
    }

    private void moveToNoteLandFragment(Note note) {
        getSupportFragmentManager().beginTransaction().replace(R.id.note_detail_fragment, editNoteFragment.setInputArgumentsNoteFrames(note)).commit();
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public NotesRepo getRepo() {
        return repository;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_note: {
                newRecord = true;
                loadNote(null);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}



