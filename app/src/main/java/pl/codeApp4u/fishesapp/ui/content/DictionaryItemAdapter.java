package pl.codeApp4u.fishesapp.ui.content;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pl.codeApp4u.fishesapp.R;
import pl.codeApp4u.fishesapp.domain.DictionaryItem;

public class DictionaryItemAdapter extends RecyclerView.Adapter<DictionaryItemAdapter.ViewHolder> {

    private List<DictionaryItem> notes = new ArrayList<>();

    void updateItems(List<DictionaryItem> notes) {
        this.notes.clear();
        this.notes.addAll(notes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dictionary, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DictionaryItem note = notes.get(position);
        holder.titleTxt.setText(note.getName());
        Picasso.get().load(new File(note.getFile())).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTxt;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.item_title_txt);
            imageView = itemView.findViewById(R.id.item_image_view);
        }
    }
}
