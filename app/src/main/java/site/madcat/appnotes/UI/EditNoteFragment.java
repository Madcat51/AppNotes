package site.madcat.appnotes.UI;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

import site.madcat.appnotes.R;
import site.madcat.appnotes.domain.Note;

public class EditNoteFragment extends Fragment {
    private EditText titleEditText;
    private EditText bodyEditText;
    private Button saveChangeButton;
    public Note item;
    private int id;
    private Controller controller;

    public EditNoteFragment() {}

    interface Controller {
        void replaceToListFragment(Note note);
        void replaceToListFragment();
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_note, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialsView();
        Bundle argument = getArguments();
        if (argument != null) {
            Note note = (Note) argument.getSerializable(Note.class.getSimpleName());
            titleEditText.setText(note.getTitle());
            bodyEditText.setText(note.getNoteBody());
            id = note.getId();
            getNoteFromList(item);
        }

        saveChangeButton.setOnClickListener(v -> {
            if (((ListActivity) requireActivity()).newRecord == true) {
                Note newNote = new Note(
                        titleEditText.getText().toString(),
                        bodyEditText.getText().toString()

                );
                controller.replaceToListFragment(newNote);

            } else {
                controller.replaceToListFragment();//
            }


        });
    }

    private void initialsView() {
        titleEditText = getActivity().findViewById(R.id.title_edit_text);
        bodyEditText = getActivity().findViewById(R.id.body_edit_text);
        saveChangeButton = getActivity().findViewById(R.id.save_change_button);
    }

    private void getNoteFromList(Note note) {
        if (note != null) {
            titleEditText.setText(note.getTitle());
            bodyEditText.setText(note.getNoteBody());
            id = note.getId();
        } else {

        }
    }

    public static EditNoteFragment setInputArgumentsNoteFrames(Note note) {
        EditNoteFragment editNoteFragment = new EditNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Note.class.getSimpleName(), note);
        editNoteFragment.setArguments(bundle);
        return editNoteFragment;
    }


}







