package site.madcat.appnotes.ui;

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
import site.madcat.appnotes.domain.NotesRepo;
import site.madcat.appnotes.ui.NotesAdapter;


public class ListFragment extends Fragment {
    private NotesRepo listRepo;
    private RecyclerView recyclerView;
    private NotesAdapter adapter = new NotesAdapter();
    private Controller controller;


    public ListFragment() {
    }

    public interface Controller {
        void loadNote(Note note);

        void addNewNote(String title, String detail);

        NotesRepo getRepo();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroy() {
        controller = null;
        super.onDestroy();
    }

    public void initData() {
        listRepo = controller.getRepo();
        initRecyclerView();
    }

    public void initRecyclerView() {
        recyclerView = getActivity().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setData(listRepo.getNotes());
        adapter.setOnItemClickListener(this::onItemClick);
    }

    public void refreshAdapter() {
        adapter.setData(listRepo.getNotes());
    }

    public void onItemClick(Note note) {
        controller.loadNote(note);
    }


}