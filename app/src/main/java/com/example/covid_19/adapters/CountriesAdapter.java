package com.example.covid_19.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.R;
import com.example.covid_19.model.Corona;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder> {

    private List<Corona> coronaList;
    private Context context;

    public CountriesAdapter(List<Corona> coronaList, Context context) {
        this.coronaList = coronaList;
        this.context = context;
    }

    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_corona_item, parent, false);
        return new CountriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesViewHolder holder, int position) {
        holder.setData(coronaList.get(position), context);
        boolean isExpanded = coronaList.get(position).isExpanded();
        holder.expandedLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return coronaList.size();
    }

    class CountriesViewHolder extends RecyclerView.ViewHolder {
        private TextView totalCases, country, todayCases, totalDeaths, todayDeaths, recovered, critical, active, casesMillion, deathsMillion, testsMillion, totalTests;
        private RelativeLayout expandedLayout;

        public CountriesViewHolder(@NonNull View itemView) {
            super(itemView);

            expandedLayout = itemView.findViewById(R.id.expandedLayout);
            country = itemView.findViewById(R.id.coronaItem_country);
            todayCases = itemView.findViewById(R.id.coronaItem_todayCases);
            todayDeaths = itemView.findViewById(R.id.coronaItem_todayDeaths);
            totalCases = itemView.findViewById(R.id.coronaItem_totalCases);
            totalDeaths = itemView.findViewById(R.id.coronaItem_totalDeath);
            recovered = itemView.findViewById(R.id.coronaItem_recovered);
            critical = itemView.findViewById(R.id.home_tv_critical);
            active = itemView.findViewById(R.id.home_tv_active);
            casesMillion = itemView.findViewById(R.id.home_tv_casesPerOneMillion);
            deathsMillion = itemView.findViewById(R.id.home_tv_deathPerMillion);
            testsMillion = itemView.findViewById(R.id.home_tv_testsPerOneMillion);
            totalTests = itemView.findViewById(R.id.home_tv_totalTests);
            ImageView imageViewDetails = itemView.findViewById(R.id.coronaItem_ImageViewDetails);

            imageViewDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Corona corona = coronaList.get(getAdapterPosition());
                    corona.setExpanded(!corona.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }

        @SuppressLint("SetTextI18n")
        private void setData(Corona corona, Context context) {

            country.setText(corona.getCountry());
            totalCases.setText(context.getResources().getString(R.string.total_cases_adapter) + corona.getCases());
            totalDeaths.setText(context.getResources().getString(R.string.total_deaths_adapter) + corona.getDeaths());
            todayCases.setText(context.getResources().getString(R.string.today_cases_adapter) + corona.getTodayCases());
            todayDeaths.setText(context.getResources().getString(R.string.today_deaths_adapter) + corona.getTodayDeaths());
            recovered.setText(context.getResources().getString(R.string.recovered_adapter) + corona.getRecovered());
            critical.setText(context.getResources().getString(R.string.critical)+corona.getCritical());
            active.setText(context.getResources().getString(R.string.active)+corona.getActive());
            casesMillion.setText(context.getResources().getString(R.string.cases_per_one_million)+corona.getCasesPerOneMillion());
            deathsMillion.setText(context.getResources().getString(R.string.deaths)+corona.getDeathsPerOneMillion());
            totalTests.setText(context.getResources().getString(R.string.total_tests)+corona.getTotalTests());
            testsMillion.setText(context.getResources().getString(R.string.tests_million)+corona.getTestsPerOneMillion());
        }
    }
}
