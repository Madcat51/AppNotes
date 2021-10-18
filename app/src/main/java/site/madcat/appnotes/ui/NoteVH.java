package site.madcat.appnotes.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import site.madcat.appnotes.R;

public class NoteVH extends RecyclerView.ViewHolder{
    public TextView titleTextView= itemView.findViewById(R.id.title_text_view);
    public TextView detailTextView= itemView.findViewById(R.id.detail_text_view);

    public NoteVH(@NonNull View itemView) {
        super(itemView);

    }


}
