package site.madcat.appnotes.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import site.madcat.appnotes.R;
import site.madcat.appnotes.domain.Note;
import site.madcat.appnotes.domain.NoteRepoImpl;
import site.madcat.appnotes.domain.NotesRepo;


public class ActiveNoteListFragment extends Fragment {


    public NoteListFragment noteListFragment=new NoteListFragment();
    public EditNoteFragment editNoteFragment = new EditNoteFragment();

    private static ControllerNoteFragment controllerNoteFragment;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ActiveNoteListFragment() { }

    public interface ControllerNoteFragment {
        boolean getScreenOrientation();
    }


    public static ActiveNoteListFragment newInstance(String param1, String param2) {
        ActiveNoteListFragment fragment = new ActiveNoteListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        super.onAttach(context);
        if (context instanceof ActiveNoteListFragment.ControllerNoteFragment) {
            controllerNoteFragment = (ActiveNoteListFragment.ControllerNoteFragment) context;
        } else {
            //todo
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_active_note_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadList();
    }


    public void loadList() {
        if (controllerNoteFragment.getScreenOrientation() == true) {
            getChildFragmentManager().beginTransaction().replace(R.id.active_note_fragment, noteListFragment).commit();
        } else {
            getChildFragmentManager().beginTransaction().replace(R.id.active_note_fragment, noteListFragment).replace(R.id.note_detail_fragment, editNoteFragment).commit();
        }
    }

    public  void loadNote(@Nullable Note note) {
        if (controllerNoteFragment.getScreenOrientation() == true) {
            moveToNotePortraitFragment(note);
        } else {
            moveToNoteLandFragment(note);
        }
    }

    private void moveToNotePortraitFragment(Note note) {
         getChildFragmentManager().beginTransaction().replace(R.id.container_fragment, editNoteFragment.setInputArgumentsNoteFrames(note)).addToBackStack(null).commit();
    }

    private void moveToNoteLandFragment(Note note) {
         getChildFragmentManager().beginTransaction().replace(R.id.note_detail_fragment, editNoteFragment.setInputArgumentsNoteFrames(note)).commit();
    }


    public void addNewNote(String title, String detail) {
        //NoteListFragment.addNote(new Note(title, detail));
        noteListFragment.refreshAdapter();
    }


    public void refreshAdapter() {
        noteListFragment.refreshAdapter();
    }



}