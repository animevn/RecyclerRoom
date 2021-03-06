package com.haanhgs.recyclerroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.haanhgs.recyclerroom.R;
import com.haanhgs.recyclerroom.model.Word;
import com.haanhgs.recyclerroom.view.MainActivity;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Word> getAllWords;

    public void setGetAllWords(List<Word> getAllWords) {
        this.getAllWords = getAllWords;
    }

    public Word getWordAtPosition(int position){
        return getAllWords.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getAllWords.get(position));
    }

    @Override
    public int getItemCount() {
        return getAllWords == null ? 0 : getAllWords.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvID)
        TextView tvID;
        @BindView(R.id.tvString)
        TextView tvString;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view->{
                Context context = itemView.getContext();
                ((MainActivity)context).editWord(getAllWords.get(getAdapterPosition()));
            });
        }

        private void bind(Word word){
            tvID.setText(String.format("%s", word.getId()));
            tvString.setText(String.format("%s", word.getString()));
        }
    }
}
