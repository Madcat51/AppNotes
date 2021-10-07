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
    private NotesRepo repository;
    private RecyclerView recyclerView;
    private NotesAdapter adapter = new NotesAdapter();
    private Context activitycontext;
    public ListFragment() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

 /*   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activitycontext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();


    }

    private void initRecyclerView() {
        repository = new NoteRepoImpl();
        recyclerView = getActivity().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activitycontext));
        recyclerView.setAdapter(adapter);
        fillRecyclerView();
        adapter.setData(repository.getNotes());
       // adapter.setOnItemClickListener(this::onItemClick);
    }

    private void fillRecyclerView() {
        repository.addNote(new Note("заметка 1", "текст заметки1"));
        repository.addNote(new Note("заметка 2", "текст заметки2 лалалалала лалалалал лалалал лалалалала лалалалал лалалал лалалалала лалалалал лалалал лалалалала лалалалал лалалал лалалалала лалалалал лалалал"));
        repository.addNote(new Note("заметка 3", "текст заметки3"));
        repository.addNote(new Note("заметка 4", "текст заметки4"));
    }


}