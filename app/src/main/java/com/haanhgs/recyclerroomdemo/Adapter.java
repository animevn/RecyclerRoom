package com.haanhgs.recyclerroomdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Word> words;

    public void setWords(List<Word> words) {
        this.words = words;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvID.setText(String.format("%s", words.get(position).getID()));
        holder.tvString.setText(words.get(position).getString());
    }

    @Override
    public int getItemCount() {
        return words == null ? 0 : words.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvID;
        private final TextView tvString;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tvID);
            tvString = itemView.findViewById(R.id.tvString);
        }
    }

    public Word getWordAtPosition(int position){
        return words.get(position);
    }
}
