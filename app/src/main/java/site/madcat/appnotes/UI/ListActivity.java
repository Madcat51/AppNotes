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

public class ListActivity extends AppCompatActivity implements ListFragment.Controller {
    private Toolbar toolbar;
    public boolean newRecord = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initToolbar();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new ListFragment())
                .commit();
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
                .replace(R.id.fragment_container, new ListFragment())
                .addToBackStack(null)
                .commit();
    }
    @Override
    public void replaceNoteFragment(Note item) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new EditNoteFragment(item))
                .addToBackStack(null)
                .commit();
    }


    public void replaceToNoteFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new EditNoteFragment())
                .addToBackStack(null)
                .commit();
    }



    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_note: {
                newRecord = true;
                replaceToNoteFragment();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}





/*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    *//*    if (resultCode == -1) {
            Note note = (Note) data.getSerializableExtra(Note.class.getSimpleName());
            repository.addNote(new Note(note.getTitle(), note.getNoteBody()));
            adapter.setData(repository.getNotes());
        }*//*
    }*/



  /*  private void openEditActivity(@Nullable Note item) {
        Intent editIntant = new Intent(this, EditActivity.class);
        if (item != null) {
            editIntant.putExtra(Note.class.getSimpleName(), (Serializable) item);
        }
        startActivityForResult(editIntant, 1);
    }*/