package com.example.covid_19.ui.used_activities;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covid_19.R;
import com.example.covid_19.adapters.QuestionAdapter;
import com.example.covid_19.adapters.TipsAdapter;
import com.example.covid_19.model.Questions;
import com.example.covid_19.model.Tips;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragment extends Fragment {

    private RecyclerView recyclerViewQuestion, recyclerViewTip;
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        recyclerViewQuestion = view.findViewById(R.id.question_recyclerView_question);
        recyclerViewQuestion.setHasFixedSize(true);
        recyclerViewQuestion.setLayoutManager(new LinearLayoutManager(context));

        recyclerViewTip = view.findViewById(R.id.question_recyclerView_tip);
        recyclerViewTip.setHasFixedSize(true);
        recyclerViewTip.setLayoutManager(new LinearLayoutManager(context));

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setQuestions();
        setTips();

    }







    private void setQuestions() {
        List<Questions> questionsList = new ArrayList<>();

        Questions question1 = new Questions();
        question1.setImage(R.drawable.no_smell);
        question1.setTitle(context.getResources().getString(R.string.loss_of_the_sense_of_smell));
        questionsList.add(question1);

        Questions question2 = new Questions();
        question2.setImage(R.drawable.loss_of_sense_of_taste);
        question2.setTitle(context.getResources().getString(R.string.loss_of_the_sense_of_taste));
        questionsList.add(question2);

        Questions question3 = new Questions();
        question3.setImage(R.drawable.no_hunger);
        question3.setTitle(context.getResources().getString(R.string.no_hunger));
        questionsList.add(question3);

        Questions question4 = new Questions();
        question4.setImage(R.drawable.red_eye);
        question4.setTitle(context.getResources().getString(R.string.red_eye));
        questionsList.add(question4);

        Questions question5 = new Questions();
        question5.setImage(R.drawable.fever);
        question5.setTitle(context.getResources().getString(R.string.fever));
        questionsList.add(question5);

        Questions question6 = new Questions();
        question6.setImage(R.drawable.chills);
        question6.setTitle(context.getResources().getString(R.string.chills));
        questionsList.add(question6);

        Questions question7 = new Questions();
        question7.setImage(R.drawable.ko7a);
        question7.setTitle(context.getResources().getString(R.string.ko7a));
        questionsList.add(question7);

        Questions question8 = new Questions();
        question8.setImage(R.drawable.difficulty_breathing);
        question8.setTitle(context.getResources().getString(R.string.difficulty_breathing));
        questionsList.add(question8);


        QuestionAdapter adapter = new QuestionAdapter(questionsList);
        adapter.notifyDataSetChanged();
        recyclerViewQuestion.setAdapter(adapter);

    }

    private void setTips() {
        List<Tips> tipsList = new ArrayList<>();

        Tips tips1 = new Tips();
        tips1.setImage(R.drawable.cleaning_spray);
        tips1.setTitle(context.getResources().getString(R.string.cleaning_spray));

        Tips tips2 = new Tips();
        tips2.setImage(R.drawable.soap);
        tips2.setTitle(context.getResources().getString(R.string.soap));

        Tips tips3 = new Tips();
        tips3.setImage(R.drawable.social_distancing);
        tips3.setTitle(context.getResources().getString(R.string.social_distancing));

        Tips tips4 = new Tips();
        tips4.setImage(R.drawable.antivirus);
        tips4.setTitle(context.getResources().getString(R.string.antivirus));

        Tips tips5 = new Tips();
        tips5.setImage(R.drawable.healthy_eating);
        tips5.setTitle(context.getResources().getString(R.string.healthy_eating));

        Tips tips6 = new Tips();
        tips6.setImage(R.drawable.rest_time);
        tips6.setTitle(context.getResources().getString(R.string.rest_time));

        Tips tips7 = new Tips();
        tips7.setImage(R.drawable.payment_method);
        tips7.setTitle(context.getResources().getString(R.string.payment_method));

        Tips tips8 = new Tips();
        tips8.setImage(R.drawable.refilling);
        tips8.setTitle(context.getResources().getString(R.string.refilling));

        Tips tips9 = new Tips();
        tips9.setImage(R.drawable.fitness);
        tips9.setTitle(context.getResources().getString(R.string.fitness));

        Tips tips10 = new Tips();
        tips10.setImage(R.drawable.community);
        tips10.setTitle(context.getResources().getString(R.string.community));

        tipsList.add(tips1);
        tipsList.add(tips2);
        tipsList.add(tips3);
        tipsList.add(tips4);
        tipsList.add(tips5);
        tipsList.add(tips6);
        tipsList.add(tips7);
        tipsList.add(tips8);
        tipsList.add(tips9);
        tipsList.add(tips10);

        TipsAdapter adapter = new TipsAdapter(tipsList);
        recyclerViewTip.setAdapter(adapter);
    }

}