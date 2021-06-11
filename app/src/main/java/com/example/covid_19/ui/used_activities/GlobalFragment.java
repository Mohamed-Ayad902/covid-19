package com.example.covid_19.ui.used_activities;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.covid_19.R;
import com.example.covid_19.adapters.CountriesAdapter;
import com.example.covid_19.model.Corona;
import com.example.covid_19.network.CoronaAPI;
import com.example.covid_19.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;


public class GlobalFragment extends Fragment {
    private static final String TAG = "GlobalFragment";

    private CoronaAPI api;
    private List<Corona> coronaList;
    private CountriesAdapter adapter;
    private Context context;
    private RecyclerView recyclerView;
    private LottieAnimationView loading;
    private TextView totalCasesNumber, totalDeathsNumber, totalRecoveredNumber;


    @Override
    public void onAttach(@androidx.annotation.NonNull Context context) {
        super.onAttach(context);
        Retrofit client = RetrofitClient.getInstance();
        api = client.create(CoronaAPI.class);

        this.context = context;
        coronaList = new ArrayList<>();
        adapter = new CountriesAdapter(coronaList, context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_global, container, false);

        loading = view.findViewById(R.id.home_loading_animation);
        totalCasesNumber = view.findViewById(R.id.home_TV_TotalCases_Number);
        totalDeathsNumber = view.findViewById(R.id.home_TV_TotalDeath_Number);
        totalRecoveredNumber = view.findViewById(R.id.home_TV_TotalRecovered_Number);
        recyclerView = view.findViewById(R.id.home_RecyclerView);

        loading.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }

    @Override
    public void onViewCreated(@androidx.annotation.NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getCoronaStatics();
            }
        },1400);

        getWorldStatics();

    }






    private void getCoronaStatics() {

        Observable<List<Corona>> observable = api.getAllCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<List<Corona>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "Mohamed onSubscribe: ");
            }

            @Override
            public void onNext(@NonNull List<Corona> coronas) {
                Log.d(TAG, "Mohamed onNext: " + coronas.toString() + "\n");
                coronaList = coronas;
                adapter = new CountriesAdapter(coronaList, context);
                recyclerView.setAdapter(adapter);
                ViewCompat.setNestedScrollingEnabled(recyclerView, false);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                loading.setVisibility(View.GONE);
                Log.e(TAG, "Mohamed onError: " + e.getMessage());
                Log.e(TAG, "Mohamed onError: " + e.getCause());
                Toast.makeText(context, "error ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                loading.setVisibility(View.GONE);
                Log.d(TAG, "Mohamed onComplete: ");
            }
        });


    }

    private void getWorldStatics() {

        Single<Corona> single = api.getTotals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        single.subscribe(new SingleObserver<Corona>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "Mohamed onSubscribe: single ");
            }

            @Override
            public void onSuccess(@NonNull Corona corona) {
                success(corona);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "Mohamed onError single : " + e.getCause());
                Log.e(TAG, "Mohamed onError single : " + e.getMessage());
                Toast.makeText(context, "error single", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void success(Corona corona) {
        Log.d(TAG, "Mohamed onSuccess: single " + corona);

        int cases = 0;
        ValueAnimator casesAnimator = ValueAnimator.ofInt(cases, corona.getCases());
        casesAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                totalCasesNumber.setText(casesAnimator.getAnimatedValue().toString());
            }
        });
        casesAnimator.setDuration(1200);
        casesAnimator.start();

        int deaths = 0;
        ValueAnimator deathsAnimator = ValueAnimator.ofInt(deaths, corona.getDeaths());
        deathsAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                totalDeathsNumber.setText(deathsAnimator.getAnimatedValue().toString());
            }
        });
        deathsAnimator.setDuration(1200);
        deathsAnimator.start();

        int recovered = 0;
        ValueAnimator recoveredAnimator = ValueAnimator.ofInt(recovered, corona.getRecovered());
        recoveredAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                totalRecoveredNumber.setText(recoveredAnimator.getAnimatedValue().toString());
            }
        });
        recoveredAnimator.setDuration(1200);
        recoveredAnimator.start();


    }
}