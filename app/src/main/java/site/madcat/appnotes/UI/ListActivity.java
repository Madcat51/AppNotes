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

public class ListActivity extends AppCompatActivity implements ListFragment.Controller, EditNoteFragment.Controller {
    private Toolbar toolbar;
    public boolean newRecord = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initToolbar();
        fillFragment();
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
                .commit();
    }

    public void replaceToListFragment(Note item) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, ListFragment.setInputArgumentsListFrames(item))
                .commit();
    }

    @Override
    public void replaceNoteFragment(Note item) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, EditNoteFragment.setInputArgumentsNoteFrames(item))
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

    public void fillFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new ListFragment())
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
}



