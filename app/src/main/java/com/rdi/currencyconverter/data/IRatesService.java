package com.rdi.currencyconverter.data;

import com.rdi.currencyconverter.data.model.CurrenciesData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IRatesService {

    @GET("scripts/XML_daily.asp")
    Call<CurrenciesData> loadCurrencies();
}
