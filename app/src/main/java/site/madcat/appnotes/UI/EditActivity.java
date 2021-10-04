package site.madcat.appnotes.UI;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import site.madcat.appnotes.R;
import site.madcat.appnotes.domain.Note;

public class EditActivity extends AppCompatActivity {
private EditText titleEditText;
    private EditText bodyEditText;
    private Button saveChangeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initialsView();
        saveChangeButton.setOnClickListener(v->{
            Note note=new Note(
                    titleEditText.getText().toString(),
                    bodyEditText.getText().toString()
            );

        });
    }


    private void initialsView(){
        titleEditText=findViewById(R.id.title_edit_text);
        bodyEditText=findViewById(R.id.body_edit_text);
        saveChangeButton=findViewById(R.id.save_change_button);
    }

}