package com.example.covid_19.network;

import com.example.covid_19.model.Corona;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CoronaAPI {

    @GET("countries/")
    Observable<List<Corona>> getAllCountries();

    @GET("all")
    Single<Corona>getTotals();

    @GET("countries/{countryName}")
    Single<Corona>getCountry(@Path("countryName")String countryName);

}
