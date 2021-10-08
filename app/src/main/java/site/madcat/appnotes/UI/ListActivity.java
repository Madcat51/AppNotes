package site.madcat.appnotes.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import site.madcat.appnotes.R;
import site.madcat.appnotes.domain.Note;
import site.madcat.appnotes.domain.NoteRepoImpl;
import site.madcat.appnotes.domain.NotesRepo;

public class ListActivity extends AppCompatActivity implements ListFragment.Controller, EditNoteFragment.Controller {
    private Toolbar toolbar;
    public boolean newRecord = false;
    public NotesRepo repository;

    private ListFragment listFragment = new ListFragment();
    private EditNoteFragment editNoteFragment = new EditNoteFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        repository = new NoteRepoImpl();
        initToolbar();
        firstLoadFragment();
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

    public void replaceToListFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                // .replace(R.id.fragment_container, new ListFragment())
                .commit();
    }

    public void replaceToListFragment(Note item) {
        getSupportFragmentManager()
                .beginTransaction()
                // .replace(R.id.fragment_container, ListFragment.setInputArgumentsListFrames(item))
                .commit();
    }

    @Override
    public void moveToNoteFragment(Note item) {
        getSupportFragmentManager()
                .beginTransaction()
                 .replace(R.id.note_container_fragment, EditNoteFragment.setInputArgumentsNoteFrames(item))
                .hide(listFragment)
                .addToBackStack(null)
                .commit();
    }


    public void firstLoadFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.list_container_fragment, listFragment)
                .hide(editNoteFragment)
                .commit();
    }


    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void fillRecyclerView() {
        repository.addNote(new Note("заметка 1", "текст заметки1"));
        repository.addNote(new Note("заметка 2", "текст заметки2 лалалалала лалалалал лалалал лалалалала лалалалал лалалал лалалалала лалалалал лалалал лалалалала лалалалал лалалал лалалалала лалалалал лалалал"));
        repository.addNote(new Note("заметка 3", "текст заметки3"));
        repository.addNote(new Note("заметка 4", "текст заметки4"));
    }
public  NotesRepo getRepo(){
 return repository;
}

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_note: {
                newRecord = true;
                moveToNoteFragment(null);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}



