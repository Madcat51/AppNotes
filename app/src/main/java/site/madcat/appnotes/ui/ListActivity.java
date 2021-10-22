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
import site.madcat.appnotes.ui.pages.arhives.ArhiveNoteListFragment;
import site.madcat.appnotes.ui.pages.settings.SettingFragment;

public class ListActivity extends AppCompatActivity implements NoteListFragment.Controller, EditNoteFragment.Controller,ActiveNoteListFragment.ControllerNoteFragment {
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    public NotesRepo activeNotesRepository;
    public boolean newRecord = false;

    public ArhiveNoteListFragment arhiveFragment=new ArhiveNoteListFragment();
    public ActiveNoteListFragment mainListFragment=new ActiveNoteListFragment();
    public SettingFragment settingFragment=new SettingFragment();


    private static final String REPOKEY = "REPO_KEY";


    /*   @Nullable
   @Override
   public Object onRetainCustomNonConfigurationInstance() {
       return listFragment;
   }
*/
    @Nullable
    @Override
    public Object getLastCustomNonConfigurationInstance() {
        return super.getLastCustomNonConfigurationInstance();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        //   outState.putSerializable(REPOKEY, (Serializable)activeNotesRepository);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        //   activeNotesRepository= (NoteRepoImpl) savedInstanceState.getSerializable(REPOKEY);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        activeNotesRepository = new NoteRepoImpl();
        initViews();
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


    @Override
    public void loadList() {

    }


    public boolean getScreenOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return true;
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            return false;
        else
            return true;
    }



    public void editNote(int id, String title, String detail) {
        activeNotesRepository.deleteNote(id);
        addNewNote(title, detail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_note: {
                newRecord = true;
             ActiveNoteListFragment.loadNote(null);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }




    public NotesRepo getRepo() {
        return activeNotesRepository;
    }

    @Override
    public void loadNote(Note note) {

    }

    @Override
    public void refreshAdapter() {

    }


}




