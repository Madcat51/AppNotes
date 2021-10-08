package site.madcat.appnotes.UI;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import site.madcat.appnotes.R;
import site.madcat.appnotes.domain.Note;
import site.madcat.appnotes.domain.NoteRepoImpl;
import site.madcat.appnotes.domain.NotesRepo;


public class ListFragment extends Fragment {
    private NotesRepo listRepo;
    private RecyclerView recyclerView;
    private NotesAdapter adapter = new NotesAdapter();
    private Controller controller;
    private Bundle noteForSave;

    public ListFragment() {
    }

    interface Controller {
        void moveToNoteFragment(Note item);
        void fillRecyclerView();
         NotesRepo getRepo();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Controller) {
            controller = (Controller) context;
        } else {
            //todo
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listRepo= controller.getRepo();
        initRecyclerView();
        noteForSave = getArguments();
        if (noteForSave != null) {
        /*    Note note = (Note) noteForSave.getSerializable(Note.class.getSimpleName());
            repository.addNote(new Note(note.getTitle(), note.getNoteBody()));
            adapter.setData(repository.getNotes());
            repository.addNote(new Note(note.getTitle(), note.getNoteBody()));*/

        }

    }

    @Override
    public void onDestroy() {
        controller = null;
        super.onDestroy();
    }

    private void initRecyclerView() {
        recyclerView = getActivity().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        controller.fillRecyclerView();
        adapter.setData(listRepo.getNotes());
        adapter.setOnItemClickListener(this::onItemClick);
    }

    private void onItemClick(Note item) {
        controller.moveToNoteFragment(item);
    }


    public static ListFragment setInputArgumentsListFrames(Note note) {
        ListFragment listFragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Note.class.getSimpleName(), note);
        listFragment.setArguments(bundle);
        return listFragment;
    }


}