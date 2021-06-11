package com.example.covid_19.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.R;
import com.example.covid_19.model.Questions;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private List<Questions> questionsList;

    public QuestionAdapter(List<Questions> questionsList) {
        this.questionsList = questionsList;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_tips_item, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        holder.setData(questionsList.get(position));
    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }

    static class QuestionViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.question_ImageView);
            textView = itemView.findViewById(R.id.question_TextView);
        }

        private void setData(Questions questions) {
            imageView.setImageResource(questions.getImage());
            textView.setText(questions.getTitle());
        }
    }
}
