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
    private EditText detailEditText;
    private Button saveChangeButton;
    private int id;
    private String oldTitle;
    private String oldDetail;
    private Controller controller;

    public EditNoteFragment() {
    }

    interface Controller {
        void loadList();

        boolean getScreenOrientation();

        void refreshAdapter();

        void editNote(int id, String title, String detail);

        void addNewNote(String title, String detail);
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
    public void onResume() {
        controller.refreshAdapter();
        super.onResume();
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
        initialsView(view);
        getParams();
        saveChangeButton.setOnClickListener(v -> {
            if (titleEditText.getText() != null | detailEditText.getText() != null) {
                if (((ListActivity) requireActivity()).newRecord == true) {
                    controller.addNewNote(titleEditText.getText().toString(), detailEditText.getText().toString());
                    ((ListActivity) requireActivity()).newRecord = false;
                    clearView();
                } else {
                    if ((oldTitle != titleEditText.getText().toString()) | (oldTitle != titleEditText.getText().toString())) {
                        controller.editNote(id, titleEditText.getText().toString(), detailEditText.getText().toString());
                    }
                }
                if (controller.getScreenOrientation() == true) {
                    controller.loadList();
                }
            }
        });
    }

    public void getParams() {
        Bundle argument = getArguments();
        if (argument != null) {
            Note note = (Note) argument.getSerializable(Note.class.getSimpleName());
            ((ListActivity) requireActivity()).newRecord = false;
            getNoteFromList(note);
        } else {
            ((ListActivity) requireActivity()).newRecord = true;
        }
    }

    private void clearView() {
        titleEditText.setText(null);
        detailEditText.setText(null);
    }

    private void initialsView(View view) {
        titleEditText = view.findViewById(R.id.title_edit_text);
        detailEditText = view.findViewById(R.id.body_edit_text);
        saveChangeButton = view.findViewById(R.id.save_change_button);
    }

    private void getNoteFromList(Note note) {
        if (note != null) {
            oldTitle = note.getTitle();
            oldDetail = note.getNoteDetail();
            titleEditText.setText(note.getTitle());
            detailEditText.setText(note.getNoteDetail());
            id = note.getId();
        }
    }

    public EditNoteFragment setInputArgumentsNoteFrames(Note note) {
        EditNoteFragment editNoteFragment = new EditNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Note.class.getSimpleName(), note);
        editNoteFragment.setArguments(bundle);
        return editNoteFragment;
    }


}







