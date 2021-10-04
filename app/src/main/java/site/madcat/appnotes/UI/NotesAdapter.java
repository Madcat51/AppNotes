package site.madcat.appnotes.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import site.madcat.appnotes.R;
import site.madcat.appnotes.UI.NoteVH;
import site.madcat.appnotes.domain.Note;

public class NotesAdapter extends RecyclerView.Adapter<NoteVH> {
    private List<Note> data = new ArrayList<>();
    private onItemClickListener clickListener = null;

    public void setData(List<Note> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public NoteVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteVH holder, int position) {
        Note note = getItem(position);
        holder.itemView.setOnClickListener(v->clickListener.onItemClick(note) );
        holder.titleTextView.setText(note.getTitle());
        holder.detailTextView.setText(note.getNoteBody());
    }

    private Note getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();

    }

    public void setOnItemClickListener(onItemClickListener listener) {
        clickListener = listener;
    }

    interface onItemClickListener {
        void onItemClick(Note item);
    }


}

