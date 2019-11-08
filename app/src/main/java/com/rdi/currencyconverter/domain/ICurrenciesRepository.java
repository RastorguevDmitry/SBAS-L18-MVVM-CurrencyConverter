package com.rdi.currencyconverter.domain;

import androidx.annotation.NonNull;

import com.rdi.currencyconverter.domain.model.Currency;

import java.io.IOException;
import java.util.List;

/**
 * Репозиторий для загрузки списка валют
 **/
public interface ICurrenciesRepository {

    /**
     * Загружает список валют
     */
    @NonNull
    List<Currency> loadCurrencies() throws IOException;
}
