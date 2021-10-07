package site.madcat.appnotes.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import site.madcat.appnotes.R;
import site.madcat.appnotes.domain.Note;

public class EditNoteFragment extends Fragment {
    private EditText titleEditText;
    private EditText bodyEditText;
    private Button saveChangeButton;
public Note item;

    public EditNoteFragment(Note item) {

this.item=item;

    }

    public EditNoteFragment() {



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
        saveChangeButton.setOnClickListener(v->{
            if ( ((ListActivity) requireActivity()).newRecord == true) {

               //todo здесь добавление
            }
          //  ((ListActivity) requireActivity()).replaceFragment(new ListFragment()) ;

        });
    }


    private void initialsView() {
        titleEditText = getActivity().findViewById(R.id.title_edit_text);
        bodyEditText = getActivity().findViewById(R.id.body_edit_text);
        saveChangeButton = getActivity().findViewById(R.id.save_change_button);
    }

}