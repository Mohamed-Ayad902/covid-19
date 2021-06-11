package com.example.covid_19.ui.used_activities;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19.R;
import com.example.covid_19.model.Corona;
import com.example.covid_19.network.CoronaAPI;
import com.example.covid_19.network.RetrofitClient;
import com.scrounger.countrycurrencypicker.library.Buttons.CountryCurrencyButton;
import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.Currency;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;


public class LocalFragment extends Fragment {
    private static final String TAG = "LocalFragment";

    private Context context;
    private CountryCurrencyButton currencyButton;
    private TextView totCases, todCases, milCases, totDeaths, todDeaths, milDeaths, critical, active, totTests, milTests, totRecovered;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local, container, false);

        currencyButton = view.findViewById(R.id.local_btn_select);
        currencyButton.setCountry(Country.getCountry("AF", context));

        totCases = view.findViewById(R.id.local_tv_TotalCasesNumber);
        totRecovered = view.findViewById(R.id.local_tv_RecoveredNumber);
        todCases = view.findViewById(R.id.local_tv_TodayCasesNumber);
        milCases = view.findViewById(R.id.local_tv_casesPerOneMillionNumber);
        milTests = view.findViewById(R.id.local_tv_testsPerOneMillionNumber);
        milDeaths = view.findViewById(R.id.local_tv_MillionDeathNumber);
        totDeaths = view.findViewById(R.id.local_tv_TotalDeathNumber);
        todDeaths = view.findViewById(R.id.local_tv_TodayDeathsNumber);
        critical = view.findViewById(R.id.local_tv_criticalNumber);
        active = view.findViewById(R.id.local_tv_activeNumber);
        totTests = view.findViewById(R.id.local_tv_totalTestsNumber);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn();
        getCountryStatics(currencyButton.getCountry().getName());
    }


    private void btn() {

        currencyButton.setOnClickListener(new CountryCurrencyPickerListener() {
            @Override
            public void onSelectCountry(Country country) {

                if (country.getCurrency() == null)
                    getCountryStatics(country.getName());
                else
                    Toast.makeText(context, String.format("name: %s\ncurrencySymbol: %s", country.getName(), country.getCurrency().getSymbol()), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSelectCurrency(Currency currency) {

            }
        });

    }

    private void getCountryStatics(String countryName) {
        Retrofit retrofit = RetrofitClient.getInstance();
        CoronaAPI api = retrofit.create(CoronaAPI.class);

        Single<Corona> single = api.getCountry(countryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        single.subscribe(new SingleObserver<Corona>() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                Log.d(TAG, "Mohamed onSubscribe: local ");
            }

            @Override
            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Corona corona) {
                Done(corona);
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                error(e);
            }
        });

    }

    private void Done(Corona corona) {

        Log.d(TAG, "Mohamed local Success: " + corona);
        final int duration = 2000;


        int cases = 0;
        ValueAnimator casesAnimator = ValueAnimator.ofInt(cases, corona.getCases());
        casesAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                totCases.setText(casesAnimator.getAnimatedValue().toString());
            }
        });
        casesAnimator.setDuration(duration);
        casesAnimator.start();


        int deaths = 0;
        ValueAnimator deathsAnimator = ValueAnimator.ofInt(deaths, corona.getDeaths());
        deathsAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                totDeaths.setText(deathsAnimator.getAnimatedValue().toString());
            }
        });
        deathsAnimator.setDuration(duration);
        deathsAnimator.start();


        int recovered = 0;
        ValueAnimator recoveredAnimator = ValueAnimator.ofInt(recovered, corona.getRecovered());
        recoveredAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                totRecovered.setText(recoveredAnimator.getAnimatedValue().toString());
            }
        });
        recoveredAnimator.setDuration(duration);
        recoveredAnimator.start();


        int todayDeaths = 0;
        ValueAnimator todDeathsAnimator = ValueAnimator.ofInt(todayDeaths, corona.getTodayDeaths());
        casesAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                todDeaths.setText(todDeathsAnimator.getAnimatedValue().toString());
            }
        });
        todDeathsAnimator.setDuration(duration);
        todDeathsAnimator.start();


        int todayCases = 0;
        ValueAnimator todCasesAnimator = ValueAnimator.ofInt(todayCases, corona.getTodayCases());
        deathsAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                todCases.setText(todCasesAnimator.getAnimatedValue().toString());
            }
        });
        todCasesAnimator.setDuration(duration);
        todCasesAnimator.start();


        int criticalNumber = 0;
        ValueAnimator criticalAnimator = ValueAnimator.ofInt(criticalNumber, corona.getCritical());
        casesAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                critical.setText(criticalAnimator.getAnimatedValue().toString());
            }
        });
        criticalAnimator.setDuration(duration);
        criticalAnimator.start();


        int activeNumber = 0;
        ValueAnimator activeAnimator = ValueAnimator.ofInt(activeNumber, corona.getActive());
        deathsAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                active.setText(activeAnimator.getAnimatedValue().toString());
            }
        });
        activeAnimator.setDuration(duration);
        activeAnimator.start();


        int totalTests = 0;
        ValueAnimator totalTestsAnimator = ValueAnimator.ofInt(totalTests, corona.getTotalTests());
        casesAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                totTests.setText(totalTestsAnimator.getAnimatedValue().toString());
            }
        });
        totalTestsAnimator.setDuration(duration);
        totalTestsAnimator.start();


        int testsPerMillion = 0;
        ValueAnimator testsPerMillionAnimator = ValueAnimator.ofInt(testsPerMillion, corona.getTestsPerOneMillion());
        deathsAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                milTests.setText(testsPerMillionAnimator.getAnimatedValue().toString());
            }
        });
        testsPerMillionAnimator.setDuration(duration);
        testsPerMillionAnimator.start();


        int millionCases = 0;
        ValueAnimator millionCasesAnimator = ValueAnimator.ofInt(millionCases, corona.getCasesPerOneMillion());
        casesAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                milCases.setText(millionCasesAnimator.getAnimatedValue().toString());
            }
        });
        millionCasesAnimator.setDuration(duration);
        millionCasesAnimator.start();


        int millionDeaths = 0;
        ValueAnimator millionDeathsAnimator = ValueAnimator.ofInt(millionDeaths, corona.getDeathsPerOneMillion());
        deathsAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                milDeaths.setText(millionDeathsAnimator.getAnimatedValue().toString());
            }
        });
        millionDeathsAnimator.setDuration(duration);
        millionDeathsAnimator.start();


    }

    private void error(Throwable e) {
        Log.e(TAG, "Mohamed onError: local " + e.getMessage());
        Log.e(TAG, "Mohamed onError: local " + e.getCause());
        Toast.makeText(context, "error occurred", Toast.LENGTH_SHORT).show();

        totCases.setText("");
        totRecovered.setText("");
        todCases.setText("");
        milCases.setText("");
        milTests.setText("");
        milDeaths.setText("");
        totDeaths.setText("");
        todDeaths.setText("");
        critical.setText("");
        active.setText("");
        totTests.setText("");

    }
}