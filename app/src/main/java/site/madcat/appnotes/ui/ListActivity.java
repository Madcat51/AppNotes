package site.madcat.appnotes.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;

import site.madcat.appnotes.R;
import site.madcat.appnotes.domain.Note;
import site.madcat.appnotes.domain.NoteRepoImpl;
import site.madcat.appnotes.domain.NotesRepo;
import site.madcat.appnotes.ui.pages.arhives.ArhiveFragment;
import site.madcat.appnotes.ui.pages.settings.SettingFragment;

public class ListActivity extends AppCompatActivity implements  ListFragment.Controller, EditNoteFragment.Controller {
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    public boolean newRecord = false;
    public NotesRepo repository;
    public ArhiveFragment arhiveFragment=new ArhiveFragment();
    public MainListFragment mainListFragment=new MainListFragment();
    public SettingFragment settingFragment=new SettingFragment();
    public ListFragment listFragment;
    public EditNoteFragment editNoteFragment = new EditNoteFragment();
    private static final String REPOKEY = "REPO_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        repository = new NoteRepoImpl();
        initViews();


       /* listFragment = (ListFragment) getLastCustomNonConfigurationInstance();
        if (listFragment == null) {
            listFragment = new ListFragment();
        }*/
        //loadList();
    }

    private void initViews() {
        initToolbar();
        initBottomMenu();
    }

    private void initBottomMenu() {
        bottomNavigationView = findViewById(R.id.navigation_menu_view);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.note_navigation: {
                    fragment = mainListFragment;
                    break;
                }
                case R.id.arhive_navigation: {
                    fragment = arhiveFragment;
                    break;
                }
                case R.id.setting_navigation: {
                    fragment = settingFragment;
                    break;
                }
                default:
                    fragment = mainListFragment;
                    break;
            }
            fragmentManager.beginTransaction().replace(R.id.container_fragment,fragment).commit();
            return false;
        });
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Nullable
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return listFragment;
    }

    @Nullable
    @Override
    public Object getLastCustomNonConfigurationInstance() {
        return super.getLastCustomNonConfigurationInstance();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(REPOKEY, (Serializable) repository);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
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

    public void refreshAdapter() {
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



