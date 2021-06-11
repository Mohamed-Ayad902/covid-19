package com.example.covid_19.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.R;
import com.example.covid_19.model.Tips;

import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipsViewHolder> {

    private List<Tips> tipsList;

    public TipsAdapter(List<Tips> tipsList) {
        this.tipsList = tipsList;
    }

    @NonNull
    @Override
    public TipsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_tips_item, parent, false);
        return new TipsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipsViewHolder holder, int position) {
        holder.setData(tipsList.get(position));
    }

    @Override
    public int getItemCount() {
        return tipsList.size();
    }

    static class TipsViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public TipsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.question_ImageView);
            textView = itemView.findViewById(R.id.question_TextView);
        }

        private void setData(Tips tips) {
            imageView.setImageResource(tips.getImage());
            textView.setText(tips.getTitle());
        }
    }
}
